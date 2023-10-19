package com.huafen.device.model.device;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备信息维护")
public class IotDeviceDTO implements Serializable{

	private static final long serialVersionUID = 5055505027945303480L;
	@ApiModelProperty(value="设备ID",example = "E280689400004020F535A17F",required = true)
	private String deviceID;
	@ApiModelProperty(value="设备名称",example = "设备01",required = true)
	private String deviceName;
	@ApiModelProperty(value="设备型号",example = "A2764",required = true)
	private String deviceModel;
	@ApiModelProperty(value="设备品牌",example = "联想",required = true)
	private String deviceBrand;
	@ApiModelProperty(value="设备IP",example = "172.28.5.141",required  = true )
	private String deviceIP;
	@ApiModelProperty(value="设备端口",example = "17000",required = true)
	private String devicePort;
	@ApiModelProperty(value="设备状态",example = "设备状态：1：在线、2：下线")
	private String deviceState;
	@ApiModelProperty(value="设备所在会议室",example = "A2-206",required = true)
	private String deviceRoom;
	@ApiModelProperty(value="设备区域",example = "会议中心",required = true)
	private String deviceArea;
	@ApiModelProperty(value="添加时间",example = "2023-10-17 08:00:00")
	private String increaseTime;
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceBrand() {
		return deviceBrand;
	}
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	public String getDeviceIP() {
		return deviceIP;
	}
	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}
	public String getDevicePort() {
		return devicePort;
	}
	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}
	public String getDeviceState() {
		return deviceState;
	}
	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}
	public String getDeviceRoom() {
		return deviceRoom;
	}
	public void setDeviceRoom(String deviceRoom) {
		this.deviceRoom = deviceRoom;
	}
	public String getDeviceArea() {
		return deviceArea;
	}
	public void setDeviceArea(String deviceArea) {
		this.deviceArea = deviceArea;
	}
	public String getIncreaseTime() {
		return increaseTime;
	}
	public void setIncreaseTime(String increaseTime) {
		this.increaseTime = increaseTime;
	}

	
}
