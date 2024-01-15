package com.huafen.device.model.topic;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备开关")
public class DeviceRoomTopic implements Serializable{
	
	private static final long serialVersionUID = 8663295665643503728L;
	
	@NotBlank(message ="主题不能为空值")
	@ApiModelProperty(value="主题", example = "AD-M3/DEVICE-01-205-Topic")
	private String topic;
	
	@ApiModelProperty(value="设备开关值",example = "\\x55\\x01\\xA4\\x00\\x00\\xA5")
	private String deviceValue;

}
