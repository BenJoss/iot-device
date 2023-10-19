package com.huafen.device.model.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("响应体")
public class ReposeDTO<T> implements Serializable{

	private static final long serialVersionUID = 8738672867129435137L;

	@ApiModelProperty(value="响应码")
	private int repCode;
	
	@ApiModelProperty(value="响应消息")
	private String repMsg;
	
	@ApiModelProperty(value="响应返回体")
	private T result;

	public int getRepCode() {
		return repCode;
	}

	public void setRepCode(int repCode) {
		this.repCode = repCode;
	}

	public String getRepMsg() {
		return repMsg;
	}

	public void setRepMsg(String repMsg) {
		this.repMsg = repMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	
}
