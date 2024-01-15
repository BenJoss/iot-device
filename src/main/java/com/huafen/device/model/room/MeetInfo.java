package com.huafen.device.model.room;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智能会议信息")
public class MeetInfo implements Serializable{

	private static final long serialVersionUID = -2350014383547723240L;
    
	@ApiModelProperty(value="会议名称",example = "人资部会议")
	private String meetName;
	@ApiModelProperty(value="会议开始时间")
	private Date startTime;
	@ApiModelProperty(value="会议结束时间")
	private Date endTime;
	
}
