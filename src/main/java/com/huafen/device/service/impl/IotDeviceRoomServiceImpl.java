package com.huafen.device.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.huafen.device.config.RepCode;
import com.huafen.device.mapper.IotDeviceRoomMapper;
import com.huafen.device.model.config.MTConfig;
import com.huafen.device.model.meet.MTRoomPO;
import com.huafen.device.model.page.IotPageBean;
import com.huafen.device.model.param.MultiRoomParam;
import com.huafen.device.model.param.MultiScenParam;
import com.huafen.device.model.req.RepMTRoom;
import com.huafen.device.model.req.ReposeDTO;
import com.huafen.device.model.room.DeviceRoom;
import com.huafen.device.model.room.IotRoom;
import com.huafen.device.model.room.LedRoom;
import com.huafen.device.model.room.ManuRoom;
import com.huafen.device.model.room.MultiRoom;
import com.huafen.device.model.room.RoomLight;
import com.huafen.device.service.IotDeviceRoomService;
import com.huafen.device.util.CallRMUtil;
import com.huafen.device.util.DeviceEunm;
import com.huafen.device.util.IoTDevUtil;
import lombok.extern.slf4j.Slf4j;

@Service("iotDeviceRoomService")
@Slf4j
public class IotDeviceRoomServiceImpl implements IotDeviceRoomService{

	@Autowired
	private IotDeviceRoomMapper iotDeviceRoomMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Resource
	private CacheManager cacheManager;
	
	@Autowired
	private MTConfig mtConfig;
	
	
	@Override
	public void loadIotRoomImgInfo() {
	    try {
			    Cache cache = cacheManager.getCache(DeviceEunm.ROOM_IMG_CACHE.obtainSource());
			    IotPageBean<IotRoom> iotPageBean = new IotPageBean<IotRoom>();
				List<IotRoom> callRoomList = iotDeviceRoomMapper.queryCallRoomList(iotPageBean);
				for(IotRoom item :callRoomList) {
				    cache.put(item.getRoomID(), item);
				}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	@Override
	public IotPageBean<IotRoom> queryIotDeviceRoomPageList(IotPageBean<IotRoom> iotPageBean) {
		 try {
			    if(CallRMUtil.IOT_ROOM_ALL.equals(iotPageBean.getAddress())) {
			    	iotPageBean.setAddress(null);
				}
				if(CallRMUtil.IOT_ROOM_ALL.equals(iotPageBean.getFloor())) {
					iotPageBean.setFloor(null);
				}	
				int totalRecord = 0;
				if (iotPageBean.getTotalRecord() == 0) {
					iotPageBean.setSource(CallRMUtil.IOT_ROOM_SOURCE);
					totalRecord = iotDeviceRoomMapper.countIotRoomPage(iotPageBean); 
				}else {
					totalRecord = iotPageBean.getTotalRecord();
				}
			    IotPageBean<IotRoom> pageParam = new IotPageBean<IotRoom>
				(iotPageBean.getPageNum(),iotPageBean.getPageSize(),totalRecord);
				pageParam.setFloor(iotPageBean.getFloor());
				pageParam.setAddress(iotPageBean.getAddress());
				pageParam.setRoomName(iotPageBean.getRoomName());
				pageParam.setSource(CallRMUtil.IOT_ROOM_SOURCE);
				
				List<IotRoom> iotRoomList = iotDeviceRoomMapper.queryIotRoomPageList(pageParam);
				if (!iotRoomList.isEmpty()) {
					String url =  mtConfig.getRoomUrl();
					IotRoom iotRoom = iotRoomList.get(0);
					Long floorId = iotRoom.getFloorId();
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("pageIndex", 1);
					param.put("pageSize", 30);
					param.put("floorId", floorId);
					HttpHeaders httpHeaders = new HttpHeaders();
					HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
					ResponseEntity<RepMTRoom> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
							RepMTRoom.class, param);
					// 获取响应对象里的 body 对象
					RepMTRoom body = responseEntity.getBody();
					Integer code = body.getCode();
					if (RepCode.MEET_SUCCESS_CODE == code) {
						List<MTRoomPO> mRoomList = body.getResult();
						if (!mRoomList.isEmpty()) {
							  //
							  for(IotRoom iotRoomItem : iotRoomList) {
								  List<MTRoomPO> filterList = mRoomList.stream()
						                    .filter(item -> iotRoomItem.getRoomName().equals(item.getRoomName()))
						                    .collect(Collectors.toList());
								 if (!filterList.isEmpty()) {
										 MTRoomPO mtRoomPO = filterList.get(0);
										 if (mtRoomPO.getExistMeeting() == 1) {
											 iotRoomItem.setMeetStatus(CallRMUtil.HAVE);
										 }else  if (mtRoomPO.getExistMeeting() == 0) {
											 iotRoomItem.setMeetStatus(CallRMUtil.ON_HAVE);
										 }	 
										 iotRoomItem.setIsMeetAppoint(true);
								 }else {
									 iotRoomItem.setIsMeetAppoint(false);
									 iotRoomItem.setMeetStatus(CallRMUtil.ON_HAVE);
								}
								 try {
										Cache cache = cacheManager.getCache(DeviceEunm.ROOM_IMG_CACHE.obtainSource());
										IotRoom  iotRoomCache = cache.get(iotRoomItem.getRoomID(),IotRoom.class);
										if (null !=  iotRoomCache && null != iotRoomCache.getRoomImg() ) {
											iotRoomItem.setRoomImg(iotRoomCache.getRoomImg());
										}else {
											  log.info("加载会议室图片缓存:"+iotRoomItem.getRoomID());
											  this.loadIotRoomImgInfo();
											  iotRoomCache = cache.get(iotRoomItem.getRoomID(),IotRoom.class);
											  iotRoomItem.setRoomImg(iotRoomCache.getRoomImg());
										}
								} catch (Exception e) {
									
								}
							}
						}
					}
				}
				pageParam.setTotalRecord(totalRecord);
				pageParam.setData(iotRoomList);
				return pageParam;
		}catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return iotPageBean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public ReposeDTO<MultiRoom> saveMultiRoomInfoService(MultiRoom multiRoom) {
		ReposeDTO<MultiRoom> reposeDTO = new ReposeDTO<MultiRoom>();
		try {
			String roomID = multiRoom.getRoomID();
			String sceneName = multiRoom.getSceneName();
			MultiScenParam multiScenParam = new MultiScenParam();
			multiScenParam.setRoomID(roomID);
			multiScenParam.setSceneName(sceneName);
			String switchSatus = multiRoom.getSwitchSatus();
			if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(switchSatus)) {
				iotDeviceRoomMapper.updateIotMultiRoomOff(multiScenParam);
			}
			int countMulti =  iotDeviceRoomMapper.countIotMultiRoom(multiScenParam);
			if (countMulti == 0) {
				iotDeviceRoomMapper.insertIotMultiRoom(multiRoom);
			}else {
				iotDeviceRoomMapper.updateIotMultiRoom(multiRoom);
			}
			RoomLight roomLight = multiRoom.getRoomLight();
			roomLight.setRoomID(roomID);
			roomLight.setSceneName(sceneName);
			int countRoomLight =  iotDeviceRoomMapper.countIotRoomLight(multiScenParam);
			if (countRoomLight == 0) {
				iotDeviceRoomMapper.insertIotRoomLight(roomLight);
			}else {
				iotDeviceRoomMapper.updateIotRoomLight(roomLight);
			}
			LedRoom ledRoom =multiRoom.getLedRoom();
			ledRoom.setRoomID(roomID);
			ledRoom.setSceneName(sceneName);
			int countIotLedRoom =  iotDeviceRoomMapper.countIotLedRoom(multiScenParam);
			if (countIotLedRoom == 0) {
				iotDeviceRoomMapper.insertIotLedRoom(ledRoom);
			}else {
				iotDeviceRoomMapper.updateIotLedRoom(ledRoom);
			}
			DeviceRoom deviceRoom = multiRoom.getDeviceRoom();
			deviceRoom.setRoomID(roomID);
			deviceRoom.setSceneName(sceneName);
			int countIotDeviceRoom =  iotDeviceRoomMapper.countIotDeviceRoom(multiScenParam);
			if (countIotDeviceRoom == 0) {
				iotDeviceRoomMapper.insertIotDeviceRoom(deviceRoom);
			}else {
				iotDeviceRoomMapper.updateIotDeviceRoom(deviceRoom);
			}
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception ex) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			reposeDTO.setRepMsg(ex.getMessage());
			log.error(ex.getMessage());
		}
		return reposeDTO;
	}

	@Override
	public ReposeDTO<List<MultiRoom>> queryMultiRoomInfoService(MultiRoomParam multiRoomParam) {
		ReposeDTO<List<MultiRoom>>  reposeDTO = new ReposeDTO<List<MultiRoom>> ();
		try {
			
			List<MultiRoom>  multiRoomList = iotDeviceRoomMapper.queryIotMultiRoomList(multiRoomParam);
			
			if (!multiRoomList.isEmpty()) {
				
				for(MultiRoom multiRoom :  multiRoomList) {
					
					String sceneName = multiRoom.getSceneName();
					multiRoomParam.setSceneName(sceneName);
					// 灯
					RoomLight  roomLight = iotDeviceRoomMapper.queryIotRoomLightList(multiRoomParam);
					multiRoom.setRoomLight(roomLight);
					// led
					LedRoom  ledRoom = iotDeviceRoomMapper.queryIotLedRoomList(multiRoomParam);
					multiRoom.setLedRoom(ledRoom);
					// 设备
					DeviceRoom  deviceRoom = iotDeviceRoomMapper.queryIotDeviceRoomList(multiRoomParam);
					multiRoom.setDeviceRoom(deviceRoom);
				}
				
			}
			reposeDTO.setResult(multiRoomList);
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception ex) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			reposeDTO.setRepMsg(ex.getMessage());
			log.error(ex.getMessage());
		}
		return reposeDTO;
	}

	@Override
	public ReposeDTO<ManuRoom> queryManuRoomInfoService(MultiRoomParam multiRoomParam) {
		ReposeDTO<ManuRoom> reposeDTO = new ReposeDTO<ManuRoom>();
		try {
			String roomID = multiRoomParam.getRoomID();
			multiRoomParam.setSwitchSatus(IoTDevUtil.LIGHT_TOUCH_ON_STATUS);
			ManuRoom  manuRoom = new ManuRoom();
				// 灯
				RoomLight  roomLight = new RoomLight();
				roomLight.setRoomID(roomID);
				roomLight.setAmbLight(IoTDevUtil.LIGHT_TOUCH_STATUS);
				roomLight.setDownLight(IoTDevUtil.LIGHT_TOUCH_STATUS);
				roomLight.setFrontLight(IoTDevUtil.LIGHT_TOUCH_STATUS);
				roomLight.setLightOneTouch(IoTDevUtil.LIGHT_TOUCH_STATUS);
				roomLight.setRearLight(IoTDevUtil.LIGHT_TOUCH_STATUS);
				// led
				LedRoom  ledRoom = new LedRoom();
				ledRoom.setLedSwitch(IoTDevUtil.LIGHT_TOUCH_STATUS);
				ledRoom.setRoomID(roomID);
				// 设备
				DeviceRoom  deviceRoom = new DeviceRoom();
				deviceRoom.setDeviceSwitch(IoTDevUtil.LIGHT_TOUCH_STATUS);
				deviceRoom.setRoomID(roomID);
				//
				manuRoom.setRoomLight(roomLight);
				manuRoom.setLedRoom(ledRoom);
				manuRoom.setDeviceRoom(deviceRoom);
			
			reposeDTO.setResult(manuRoom);
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception ex) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			reposeDTO.setRepMsg(ex.getMessage());
			log.error(ex.getMessage());
		}
		return reposeDTO;
	}

	
	
	
}
