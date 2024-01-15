package com.huafen.device.model.room;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("矩阵输出区")
public class MatrixOut implements Serializable{

	private static final long serialVersionUID = -6661582447613386823L;

	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
	@ApiModelProperty(value="LED开关",example = "ON")
	private String ledSwitch;
	
	@ApiModelProperty(value="腾讯会议",example = "腾讯会议")
	private String tencentMeet;
	
	@ApiModelProperty(value="电视左",example = "电视左")
	private String tvLeft;
	
	@ApiModelProperty(value="电视右",example = "电视右")
	private String tvRight;
	
	@ApiModelProperty(value="编码器",example = "编码器")
	private String enCoder;
	
	@ApiModelProperty(value="电视左",example = "国网终端1")
	private String terminal1;
	
	@ApiModelProperty(value="电视左",example = "国网终端2")
	private String terminal2;
}
