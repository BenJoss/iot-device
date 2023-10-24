package com.huafen.device.model.param;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备信息维护参数")
public class IotDeviceParam implements Serializable{

	private static final long serialVersionUID = -8271458327666335945L;
	@ApiModelProperty(value="设备ID",example = "E280689400004020F535A17F",required = true)
	private String deviceID;
	@ApiModelProperty(value="设备ID集合",example = "E280689400004020F535A17F,E280689400004020F535A17E",required = false)
	private List<String> deviceIDList;
	@ApiModelProperty(value="设备IP",example = "172.28.5.141",required  = true )
	private String deviceIP;
	@ApiModelProperty(value="设备端口",example = "17000",required = true)
	private String devicePort;
	
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	public List<String> getDeviceIDList() {
		return deviceIDList;
	}
	public void setDeviceIDList(List<String> deviceIDList) {
		this.deviceIDList = deviceIDList;
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
	
	
}
