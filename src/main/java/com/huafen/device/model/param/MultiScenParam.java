package com.huafen.device.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("智能会议场景查询参数")
@Data
public class MultiScenParam implements Serializable{

	private static final long serialVersionUID = 1348359908343579406L;

	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@ApiModelProperty(value="开启场景", example = "ON")
	private String switchSatus;
	
	@ApiModelProperty(value="场景名称",example = "现场、视频")
	private String sceneName;
}
