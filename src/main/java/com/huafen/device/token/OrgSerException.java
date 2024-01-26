package com.huafen.device.token;

public class OrgSerException extends RuntimeException {

	private Object msg;
	
	
	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6874680713687970751L;

	public OrgSerException() {
		super();
	}

	public OrgSerException(String message) {
		super(message);
	}

	public OrgSerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public OrgSerException(Throwable throwable) {
		super(throwable);
	}

}
