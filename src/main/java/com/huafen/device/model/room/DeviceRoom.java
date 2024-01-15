package com.huafen.device.model.room;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备开关")
public class DeviceRoom implements Serializable{

	private static final long serialVersionUID = 7574317230207822234L;

	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@ApiModelProperty(value="会议室名称", example = "A2-228")
	private String roomName;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
	@NotBlank(message ="设备开关不能为空值")
	@ApiModelProperty(value="设备开关",example = "ON")
	private String deviceSwitch;
	
}
