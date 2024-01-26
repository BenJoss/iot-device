package com.huafen.device.model.req;

import java.io.Serializable;
import java.util.ArrayList;

import com.huafen.device.model.meet.MTRoomPO;


public class RepMTRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4663966872097445738L;
	
	private Integer code;
	
	private String msg;
	
	private ArrayList<MTRoomPO>  result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<MTRoomPO> getResult() {
		return result;
	}

	public void setResult(ArrayList<MTRoomPO> result) {
		this.result = result;
	}
	
	

}
