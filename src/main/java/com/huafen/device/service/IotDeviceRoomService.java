package com.huafen.device.service;

import java.util.List;

import com.huafen.device.model.page.IotPageBean;
import com.huafen.device.model.param.MultiRoomParam;
import com.huafen.device.model.req.ReposeDTO;
import com.huafen.device.model.room.IotRoom;
import com.huafen.device.model.room.ManuRoom;
import com.huafen.device.model.room.MultiRoom;

public interface IotDeviceRoomService {

	public IotPageBean<IotRoom> queryIotDeviceRoomPageList(IotPageBean<IotRoom> iotPageBean);
	
	public ReposeDTO<MultiRoom> saveMultiRoomInfoService(MultiRoom multiRoom);
	
	public ReposeDTO<List<MultiRoom>> queryMultiRoomInfoService(MultiRoomParam multiRoomParam);
	
	public ReposeDTO<ManuRoom>  queryManuRoomInfoService(MultiRoomParam multiRoomParam);
}
