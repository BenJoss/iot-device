package com.huafen.device.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.device.model.page.IotPageBean;
import com.huafen.device.model.param.IotConfig;
import com.huafen.device.model.param.MultiRoomParam;
import com.huafen.device.model.param.MultiScenParam;
import com.huafen.device.model.room.DeviceRoom;
import com.huafen.device.model.room.IotRoom;
import com.huafen.device.model.room.LedRoom;
import com.huafen.device.model.room.ManuRoom;
import com.huafen.device.model.room.MeetInfo;
import com.huafen.device.model.room.MultiRoom;
import com.huafen.device.model.room.RoomLight;

@Mapper
public interface IotDeviceRoomMapper {

	Map<String,String>	queryIotConfig(IotConfig iotConfig);
	
	int countIotRoomPage(IotPageBean<IotRoom> iotPageBean);
	
	List<MeetInfo> queryMeetInfoList(MultiRoomParam multiRoomParam);
	
	List<IotRoom> queryIotRoomPageList(IotPageBean<IotRoom> iotPageBean);
	
	List<MultiRoom> queryIotMultiRoomTaskList(MultiScenParam  multiRoomParam);
	
	List<MultiRoom> queryIotMultiRoomList(MultiRoomParam multiRoomParam);
	
	RoomLight queryIotRoomLightList(MultiRoomParam multiRoomParam);
	
	LedRoom queryIotLedRoomList(MultiRoomParam multiRoomParam);
	
	DeviceRoom queryIotDeviceRoomList(MultiRoomParam multiRoomParam);
	
	ManuRoom queryIotManuRoomInfo(MultiRoomParam multiRoomParam);
	
	ManuRoom queryFirstIotManuRoomInfo(MultiRoomParam multiRoomParam);
	
	int insertIotMultiRoom(MultiRoom multiRoom);
	
	int insertIotRoomLight(RoomLight roomLight);
	
	int insertIotLedRoom(LedRoom ledRoom);
	
	int insertIotDeviceRoom(DeviceRoom deviceRoom);
	
	int countIotMultiRoom(MultiScenParam multiScenParam);
	
	int countIotRoomLight(MultiScenParam multiScenParam);
	
	int countIotLedRoom(MultiScenParam multiScenParam);
	
	int countIotDeviceRoom(MultiScenParam multiScenParam);
	
	int updateIotMultiRoom(MultiRoom multiRoom);
	
	int updateIotRoomLight(RoomLight roomLight);
	
	int updateIotLedRoom(LedRoom ledRoom);
	
	int updateIotDeviceRoom(DeviceRoom deviceRoom);
	
	int updateIotMultiRoomOff(MultiScenParam multiScenParam);
}
