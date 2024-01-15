package com.huafen.device.model.room;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智能会议场景")
public class MultiRoom implements Serializable{

	private static final long serialVersionUID = -4176815914693541025L;
	
	@NotBlank(message ="会议室id不能为空值")
	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	
	@ApiModelProperty(value="会议室名称", example = "A2-228")
	private String roomName;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
	@ApiModelProperty(value="开启场景", example = "ON")
	private String switchSatus;
	
	@ApiModelProperty(value="定时时间", example = "60")
	private Integer timing;
	
	@ApiModelProperty(value="显示顺序", example = "1")
	private Integer showOrder;
	
	@NotNull(message ="灯光不能为空值")
	@ApiModelProperty(value="灯光", example = "roomLight")
	private RoomLight roomLight;
	
	@NotNull(message ="LED开关不能为空值")
	@ApiModelProperty(value="LED开关", example = "ledRoom")
	private LedRoom ledRoom;
	
	@NotNull(message ="设备开关不能为空值")
	@ApiModelProperty(value="设备开关", example = "deviceRoom")
	private DeviceRoom deviceRoom;

}
