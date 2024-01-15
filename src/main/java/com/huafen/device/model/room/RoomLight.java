package com.huafen.device.model.room;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("灯光")
public class RoomLight implements Serializable{

	private static final long serialVersionUID = -8996365295331950839L;
	
	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@ApiModelProperty(value="会议室名称", example = "A2-228")
	private String roomName;
	
	@NotBlank(message ="场景名称不能为空值")
	@ApiModelProperty(value="场景名称", example = "现场会议场景")
	private String sceneName;
	
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
