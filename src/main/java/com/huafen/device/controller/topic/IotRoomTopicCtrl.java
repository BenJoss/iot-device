package com.huafen.device.controller.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.device.model.req.ReposeDTO;
import com.huafen.device.model.room.LedRoom;
import com.huafen.device.model.room.MatrixSignal;
import com.huafen.device.model.topic.DeviceRoomTopic;
import com.huafen.device.model.topic.LightTopic;
import com.huafen.device.mqtt.MqttProviderConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"设备主题发送相关接口"})
@RestController
@RequestMapping("/IotRoomTopicCtrl")
public class IotRoomTopicCtrl {

	@Autowired
    private MqttProviderConfig providerClient;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "发送打开关闭会议灯光指令", notes = "发送打开关闭会议灯光指令")
    @PostMapping("/sendLightRoomMsg")
    @ResponseBody
    public ReposeDTO<String> sendLightRoomMsg(@RequestBody LightTopic lightTopic) {
		providerClient.publish(1, false, "ESP-8288/LIGHT-FRONT-205-Topic", "");			
		return new ReposeDTO<String>();
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "发送打开关闭LED指令", notes = "发送打开关闭LED指令")
    @PostMapping("/sendLedRoomMsg")
    @ResponseBody
    public ReposeDTO<String> sendLedRoomMsg(@RequestBody LedRoom ledRoom) {
		return new ReposeDTO<String>();
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "发送打开关闭会议设备指令", notes = "发送打开关闭会议设备指令")
    @PostMapping("/sendDeviceRoomMsg")
    @ResponseBody
    public ReposeDTO<String> sendDeviceRoomMsg(@RequestBody DeviceRoomTopic deviceRoomTopic) {
		  String topic = deviceRoomTopic.getTopic();
		  String deviceValue = deviceRoomTopic.getDeviceValue();
		  providerClient.publishHex(1, false, topic,deviceValue);	
		return new ReposeDTO<String>();
	}
	
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "发送打开关闭矩阵设备指令", notes = "发送打开关闭矩阵设备指令")
    @PostMapping("/sendMatrixMsg")
    @ResponseBody
    public ReposeDTO<String> sendMatrixMsg(@RequestBody MatrixSignal matrixSignal) {
		return new ReposeDTO<String>();
	}
	
	
	
}
