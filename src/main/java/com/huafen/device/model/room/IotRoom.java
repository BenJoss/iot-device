package com.huafen.device.model.room;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会议室设备状态")
public class IotRoom implements Serializable{

	private static final long serialVersionUID = 5697974347803076949L;

	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@ApiModelProperty(value="会议室名称",example = "A2-228")
	private String roomName;
	
	@ApiModelProperty(value="会议状态")
	private String meetStatus;
	
	@ApiModelProperty(value="温度")
	private String tempe;
	
	@ApiModelProperty(value="湿度")
	private String humid;
	
	@ApiModelProperty(value="灯光")
	private String lightStatus;
	
	@ApiModelProperty(value="设备")
	private String deviceStatus;
	
	@ApiModelProperty(value="会议室图片")
	private String roomImg;
	
	private Long floorId;
	
	@ApiModelProperty(value="是否为会议预约会议室:true 是、false 否",example = "true")
	private Boolean isMeetAppoint;
}
