package com.huafen.device.model.config;

import java.io.Serializable;

import lombok.Data;

@Data
public class LightProperties implements Serializable{
	
	private static final long serialVersionUID = -6667393383501821017L;
	
	/**
	 * 前排灯前缀
	 */
	private String frontPrefix;
	/**
	 * 后排灯前缀
	 */
	private String rearPrefix;
	/**
	 * 氛围灯前缀
	 */
	private String ambPrefix;
	/**
	 * 筒灯前缀
	 */
	private String downPrefix;
	/**
	 * 灯光一键前缀
	 */
	private String oneTouchPrefix;
	/**
	 * 打开指令
	 */
	private String open;
	
	/**
	 * 关闭指令
	 */
	private String close;

}
