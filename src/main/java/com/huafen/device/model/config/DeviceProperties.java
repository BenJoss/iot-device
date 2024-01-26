package com.huafen.device.model.config;

import java.io.Serializable;

import lombok.Data;

@Data
public class DeviceProperties implements Serializable{
	
	private static final long serialVersionUID = -2517261169684916474L;

	 /**
     * 前置时间量
     */
	private long editPreposTime;
	/**
	 * 前缀
	 */
	private String prefix;
	/**
	 * 打开指令
	 */
	private String open;
	
	/**
	 * 关闭指令
	 */
	private String close;
	/**
	 * 执行次数
	 */
	private int count;
}
