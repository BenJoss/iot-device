package com.huafen.device.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huafen.device.mapper.IotDeviceRoomMapper;
import com.huafen.device.model.config.DeviceProperties;
import com.huafen.device.model.config.LedProperties;
import com.huafen.device.model.config.LightProperties;
import com.huafen.device.model.param.IotConfig;
import com.huafen.device.model.param.MultiRoomParam;
import com.huafen.device.model.param.MultiScenParam;
import com.huafen.device.model.room.DeviceRoom;
import com.huafen.device.model.room.LedRoom;
import com.huafen.device.model.room.MeetInfo;
import com.huafen.device.model.room.MultiRoom;
import com.huafen.device.model.room.RoomLight;
import com.huafen.device.mqtt.MqttProviderConfig;
import com.huafen.device.service.IotDeviceRoomTaskService;
import com.huafen.device.util.CallRMUtil;
import com.huafen.device.util.DateUtil;
import com.huafen.device.util.IoTDevUtil;

import lombok.extern.slf4j.Slf4j;

@Service("iotDeviceRoomTaskService")
@Slf4j
public class IotDeviceRoomTaskServiceImpl implements IotDeviceRoomTaskService{

	@Autowired
	private IotDeviceRoomMapper iotDeviceRoomMapper;
	
	@Autowired
    private MqttProviderConfig providerClient;
	
	@Resource
    private RedissonClient redissonClient;
	
	private void sendRoomLightTopic(RoomLight  roomLight) {
		  try {
			  String cacheKey = IoTDevUtil.LIGHT_CONFIG;
 			  RBucket<LightProperties> bucket = redissonClient.getBucket(cacheKey);  
 			  LightProperties lightProperties = bucket.get();
 			  if (lightProperties == null) {
 				 log.error("设备控制缓存未加载");
	 		     return;
 			  }
 			 //
 			 String sceneName = roomLight.getSceneName();
 			 String roomName  = roomLight.getRoomName();
 			 String frontLight =  roomLight.getFrontLight();
 			 if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(frontLight)) {
 				  String topic = lightProperties.getFrontPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
 				  String open = lightProperties.getOpen();
			      providerClient.publish(1, false, topic, open);
			      log.info(roomName+" "+sceneName + "：前排灯指令发送完毕"+" 值: "+open);
 			 }
 			 //
 			String rearLight = roomLight.getRearLight();
 			if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(rearLight)) {
 				  String topic = lightProperties.getRearPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
				  String open = lightProperties.getOpen();
			      providerClient.publish(1, false, topic, open);
			      log.info(roomName +" "+sceneName + "：后排灯指令发送完毕"+" 值: "+open);
 			}
 			//
 			String downLight  = roomLight.getDownLight();
 			if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(downLight)) {
				  String topic = lightProperties.getDownPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
				  String open = lightProperties.getOpen();
			      providerClient.publish(1, false, topic, open);
			      log.info(roomName +" "+sceneName + "：筒灯指令发送完毕"+" 值: "+open);
			}
 		   //
 			String ambLight = roomLight.getAmbLight();
 			if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(ambLight)) {
				  String topic = lightProperties.getAmbPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
				  String open = lightProperties.getOpen();
			      providerClient.publish(1, false, topic, open);
			      log.info(roomName +" "+sceneName + "：氛围灯指令发送完毕"+" 值: "+open);
			}
 		  //
 			String lightOneTouch = roomLight.getLightOneTouch();
 			if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(lightOneTouch)) {
				  String topic = lightProperties.getOneTouchPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
				  String open = lightProperties.getOpen();
			      providerClient.publish(1, false, topic, open);
			      log.info(roomName +" "+sceneName + "：灯光一键开关指令发送完毕"+" 值: "+open);
			}
		} catch (Exception exception) {
			log.error("灯开关指令发送异常: "+exception.getMessage());
		}
	}
	
	private void sendLedRoomTopic(LedRoom  ledRoom) {
		  try {
			  String cacheKey = IoTDevUtil.LED_CONFIG;
 			  RBucket<LedProperties> bucket = redissonClient.getBucket(cacheKey);  
 			  LedProperties ledProperties = bucket.get();
 			  if (ledProperties == null) {
 				 log.error("设备控制缓存未加载");
	 		     return;
 			  }
			  String roomName  = ledRoom.getRoomName();
			  String sceneName = ledRoom.getSceneName();
			  String topic = ledProperties.getPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
			  if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(ledRoom.getLedSwitch())) {
				     String open = ledProperties.getOpen();
				     providerClient.publish(1, false, topic, open);
				     log.info(roomName +" "+sceneName + "：Led开关指令发送完毕"+" 值: "+open);
			  }
		} catch (Exception exception) {
			log.error("Led开关指令发送异常: "+exception.getMessage());
		}
	}
	
	private void sendDeviceRoomTopic(DeviceRoom  deviceRoom) {
		  try {
			  String cacheKey = IoTDevUtil.DEVICE_CONFIG;
 			  RBucket<DeviceProperties> bucket = redissonClient.getBucket(cacheKey);  
 			  DeviceProperties deviceProperties = bucket.get();
 			  if (deviceProperties == null) {
 				 log.error("设备控制缓存未加载");
	 		     return;
 			  }
			  String roomName  = deviceRoom.getRoomName();
			  String  sceneName = deviceRoom.getSceneName();
			  String topic = deviceProperties.getPrefix()+roomName+"-"+IoTDevUtil.TOPIC;
			  if (IoTDevUtil.LIGHT_TOUCH_ON_STATUS.equals(deviceRoom.getDeviceSwitch())) {
				  String open = deviceProperties.getOpen();
				  providerClient.publishHex(1, false, topic,open);	
				  log.info(roomName +" "+sceneName + "：设备开关指令发送完毕"+" 值: "+open);
			} 
		} catch (Exception exception) {
			log.error("设备开关指令发送异常: "+exception.getMessage());
		}
	}
	
	
	private void sendSceneNameMsg(MeetInfo meetInfo,MultiRoom multiRoom) {
		try {
			Date startTime  = meetInfo.getStartTime();
			if (startTime != null) {
				String cacheKey = IoTDevUtil.DEVICE_CONFIG;
	 			RBucket<DeviceProperties> bucket = redissonClient.getBucket(cacheKey);  
	 			DeviceProperties deviceProperties = bucket.get();
	 		    if (deviceProperties == null) {
	 		    	log.error("设备控制缓存未加载");
	 		    	return;
	 			}
				LocalDateTime localStartTime = LocalDateTime.ofInstant(startTime.toInstant(), java.time.ZoneId.systemDefault()); 
				Integer timing  = multiRoom.getTiming();
				LocalDateTime startTimeMinutes =  localStartTime.minusMinutes(Long.parseLong(String.valueOf(timing)));
				LocalDateTime currentTimeObj =  LocalDateTime.now();
				currentTimeObj = currentTimeObj.truncatedTo(ChronoUnit.MINUTES);
				startTimeMinutes = startTimeMinutes.truncatedTo(ChronoUnit.MINUTES);
				long editPreposTime = deviceProperties.getEditPreposTime();
				LocalDateTime endDateTimeMinutes =  startTimeMinutes.plusMinutes(editPreposTime);
				endDateTimeMinutes = endDateTimeMinutes.truncatedTo(ChronoUnit.MINUTES);
				DateTimeFormatter formatter = DateUtil.getDateTimeFormatter();  
				String currentTime = currentTimeObj.format(formatter);
				String startTimeM = startTimeMinutes.format(formatter);
				String endTime = endDateTimeMinutes.format(formatter);
 				if (DateUtil.isTimeInRange(currentTime, startTimeM, endTime)) {
						String sceneName = multiRoom.getSceneName();
						MultiRoomParam multiRoomParam = new MultiRoomParam();
						multiRoomParam.setRoomID(multiRoom.getRoomID());
						multiRoomParam.setSceneName(sceneName);
						// 灯
						RoomLight  roomLight = iotDeviceRoomMapper.queryIotRoomLightList(multiRoomParam);
						this.sendRoomLightTopic(roomLight);
						// led
						LedRoom  ledRoom = iotDeviceRoomMapper.queryIotLedRoomList(multiRoomParam);
						this.sendLedRoomTopic(ledRoom);
						// 设备
						DeviceRoom  deviceRoom = iotDeviceRoomMapper.queryIotDeviceRoomList(multiRoomParam);
						this.sendDeviceRoomTopic(deviceRoom);
				}
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}
		
	}
	
	@Override
	public void autoSceneNameTaskService() {
		 try {
			   MultiScenParam  multiScenParam = new MultiScenParam();
			   multiScenParam.setSwitchSatus(IoTDevUtil.LIGHT_TOUCH_ON_STATUS);
			   List<MultiRoom> multiRoomList  =  iotDeviceRoomMapper.queryIotMultiRoomTaskList(multiScenParam);
			   if (!multiRoomList.isEmpty()) {
				   for(MultiRoom multiRoom :  multiRoomList) {
					   String roomID  = multiRoom.getRoomID();
					   MultiRoomParam multiRoomParam = new MultiRoomParam();
					   multiRoomParam.setRoomID(roomID);
					   List<MeetInfo> meetInfoList  = iotDeviceRoomMapper.queryMeetInfoList(multiRoomParam);
					   if (!meetInfoList.isEmpty()) {
						   for(MeetInfo meetInfo :meetInfoList ) {
							   this.sendSceneNameMsg(meetInfo, multiRoom);
						   }
					   }
				 }
				   
			}
		} catch (Exception exception) {
		     log.error(exception.getMessage());
		}
		
	}

	@Override
	public void sceneNameLoadConfigTaskService() {
		try {
			// 加载设备配置
        	IotConfig iotConfig = new IotConfig();
        	iotConfig.setKey(IoTDevUtil.DEVICE_CONFIG);
        	Map<String, String>  configMap = iotDeviceRoomMapper.queryIotConfig(iotConfig);
        	if (configMap != null) {
        		 String deviceConfig =  configMap.get(CallRMUtil.CON_VALUE);
        		 DeviceProperties deviceProperties
        		 = JSON.parseObject(deviceConfig,DeviceProperties.class);
        		 if (deviceProperties != null) {
        			 String cacheKey = IoTDevUtil.DEVICE_CONFIG;
        			 RBucket<DeviceProperties> bucket = redissonClient.getBucket(cacheKey);  
        			 if (bucket.get() == null) {
        				 bucket.set(deviceProperties);  
    					 bucket.expire(1, TimeUnit.DAYS);
					}else {
						 long  liveTime =  bucket.remainTimeToLive();
						 bucket.set(deviceProperties);  
    					 bucket.expire(liveTime, TimeUnit.MILLISECONDS);
					}
				
			}
         }
		} catch (Exception exception) {
			 log.error(exception.getMessage());
		}
		
		// 加载灯配置
        try {
        	IotConfig iotConfig = new IotConfig();
        	iotConfig.setKey(IoTDevUtil.LIGHT_CONFIG);
        	Map<String, String>  configMap = iotDeviceRoomMapper.queryIotConfig(iotConfig);
        	if (configMap != null) {
        		 String lightConfig =  configMap.get(CallRMUtil.CON_VALUE);
        		 LightProperties lightProperties
        		 = JSON.parseObject(lightConfig,LightProperties.class);
        		 if (lightProperties != null) {
        			 String cacheKey = IoTDevUtil.LIGHT_CONFIG;
        			 RBucket<LightProperties> bucket = redissonClient.getBucket(cacheKey);  
        			 if (bucket.get() == null) {
        				 bucket.set(lightProperties);  
    					 bucket.expire(1, TimeUnit.DAYS);
					}else {
						 long  liveTime =  bucket.remainTimeToLive();
						 bucket.set(lightProperties);  
    					 bucket.expire(liveTime, TimeUnit.MILLISECONDS);
					}
        		 }
        	}
		} catch (Exception exception) {
			 log.error(exception.getMessage());
		}
        
		// 加载LED配置
        try {
        	IotConfig iotConfig = new IotConfig();
        	iotConfig.setKey(IoTDevUtil.LED_CONFIG);
        	Map<String, String>  configMap = iotDeviceRoomMapper.queryIotConfig(iotConfig);
        	if (configMap != null) {
        		 String ledConfig =  configMap.get(CallRMUtil.CON_VALUE);
        		 LedProperties ledProperties
	       		 = JSON.parseObject(ledConfig,LedProperties.class);
        		 if (ledProperties != null) {
        			 String cacheKey = IoTDevUtil.LED_CONFIG;
        			 RBucket<LedProperties> bucket = redissonClient.getBucket(cacheKey);  
        			 if (bucket.get() == null) {
        				 bucket.set(ledProperties);  
    					 bucket.expire(1, TimeUnit.DAYS);
					}else {
						 long  liveTime =  bucket.remainTimeToLive();
						 bucket.set(ledProperties);  
    					 bucket.expire(liveTime, TimeUnit.MILLISECONDS);
					}
        		 }
        	}
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}
        
	}
	
	
	
	
}
