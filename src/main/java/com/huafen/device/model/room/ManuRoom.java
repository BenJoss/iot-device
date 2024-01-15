package com.huafen.device.model.room;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("手动会议场景")
public class ManuRoom implements Serializable{

	private static final long serialVersionUID = -831554151403626760L;
	
	@ApiModelProperty(value="会议室id",example = "7781766872039424")
	private String roomID;
	
	@ApiModelProperty(value="会议室名称",example = "A2-228")
	private String roomName;
	
	@ApiModelProperty(value="会议场景", example = "现场会议场景")
	private String onSiteMeet;
	
	@ApiModelProperty(value="现场会议状态", example = "ON")
	private String onSiteStatus;
	
	@ApiModelProperty(value="灯光", example = "roomLight")
	private RoomLight roomLight;
	
	@ApiModelProperty(value="LED开关", example = "ledRoom")
	private LedRoom ledRoom;
	
	@ApiModelProperty(value="设备开关", example = "deviceRoom")
	private DeviceRoom deviceRoom;
	
}
