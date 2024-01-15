package com.huafen.device.model.room;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("LED开关")
public class LedRoom implements Serializable{

	private static final long serialVersionUID = -8565567297561759478L;

	
	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@ApiModelProperty(value="会议室名称", example = "A2-228")
	private String roomName;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
	@NotBlank(message ="LED开关不能为空值")
	@ApiModelProperty(value="LED开关",example = "ON")
	private String LedSwitch;
}
