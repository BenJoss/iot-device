package com.huafen.device.model.topic;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("灯光")
public class LightTopic implements Serializable{

	private static final long serialVersionUID = 7292845905721577470L;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
	@NotBlank(message ="主题不能为空值")
	@ApiModelProperty(value="主题", example = "AD-M3/LIGHT-01-205-Topic")
	private String topic;
	
	@ApiModelProperty(value="前排灯",example = "ON",required  = false)
	private String frontLight;
	
	@ApiModelProperty(value="后排灯",example = "ON",required  = false)
	private String rearLight;
	
	@ApiModelProperty(value="氛围灯",example = "ON",required  = false)
	private String ambLight;
	
	@ApiModelProperty(value="筒灯",example = "ON",required  = false)
	private String downLight;
	
	@ApiModelProperty(value="灯光一键开关",example = "ON",required  = false)
	private String lightOneTouch;
}
