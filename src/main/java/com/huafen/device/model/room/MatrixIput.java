package com.huafen.device.model.room;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("矩阵输入区")
public class MatrixIput implements Serializable{
	private static final long serialVersionUID = 3161909301866130367L;

	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
	@ApiModelProperty(value="无线投屏",example = "无线投屏")
	private String wireScreen;
	
	@ApiModelProperty(value="腾讯会议",example = "腾讯会议")
	private String tencentMeet;
	
	@ApiModelProperty(value="DVD",example = "DVD")
	private String DVD;
	
}
