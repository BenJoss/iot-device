package com.huafen.device.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.device.model.page.IotPageBean;
import com.huafen.device.model.param.MultiRoomParam;
import com.huafen.device.model.req.ReposeDTO;
import com.huafen.device.model.room.IotRoom;
import com.huafen.device.model.room.ManuRoom;
import com.huafen.device.model.room.MultiRoom;
import com.huafen.device.service.IotDeviceRoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"设备控制相关接口"})
@RestController
@RequestMapping("/IotRoomEditCtrl")
public class IotRoomEditCtrl {

	@Autowired
	@Qualifier("iotDeviceRoomService")
	private IotDeviceRoomService iotDeviceRoomService;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotPageBean.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "分页查询会议室设备状态", notes = "分页查询会议室设备状态")
    @PostMapping("/queryIotRoomPageList")
    @ResponseBody
    public IotPageBean<IotRoom> queryIotRoomPageList(@RequestBody IotPageBean<IotRoom> iotPageBean) {
		return iotDeviceRoomService.queryIotDeviceRoomPageList(iotPageBean);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "查询智能会议场景", notes = "查询智能会议场景")
    @PostMapping("/queryMultiRoomInfo")
    @ResponseBody
    public ReposeDTO<List<MultiRoom>>  queryMultiRoomInfo(@RequestBody MultiRoomParam multiRoomParam) {
		return iotDeviceRoomService.queryMultiRoomInfoService(multiRoomParam);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "查询手动会议场景", notes = "查询手动会议场景")
    @PostMapping("/queryManuRoomInfo")
    @ResponseBody
    public ReposeDTO<ManuRoom>  queryManuRoomInfo(@RequestBody MultiRoomParam multiRoomParam) {
		return iotDeviceRoomService.queryManuRoomInfoService(multiRoomParam);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "保存智能会议场景", notes = "保存智能会议场景")
    @PostMapping("/saveMultiRoomInfo")
    @ResponseBody
    public ReposeDTO<MultiRoom> saveMultiRoomInfo(@Validated  @RequestBody MultiRoom multiRoom) {
		return iotDeviceRoomService.saveMultiRoomInfoService(multiRoom);
	}
}
