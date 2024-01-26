package com.huafen.device.model.config;

import java.io.Serializable;

import lombok.Data;

@Data
public class LightProperties implements Serializable{
	
	private static final long serialVersionUID = -6667393383501821017L;
	
	/**
	 * 前缀
	 */
	private String prefix;
//	/**
//	 * 前缀
//	 */
//	private String Prefix;
//	/**
//	 * 前缀
//	 */
//	private String Prefix;
//	/**
//	 * 前缀
//	 */
//	private String Prefix;
	/**
	 * 灯光一键打开指令
	 */
	private String oneTouchOpen;
	/**
	 * 灯光一键关闭指令
	 */
	private String oneTouchClose;
	/**
	 * 前排灯打开指令
	 */
	private String frontOpen;
	
	/**
	 * 前排灯关闭指令
	 */
	private String frontClose;
	
	/**
	 * 后排灯打开指令
	 */
	private String rearOpen;
	
	/**
	 * 后排灯关闭指令
	 */
	private String rearClose;
	
	/**
	 * 氛围灯打开指令
	 */
	private String ambOpen;
	
	/**
	 * 氛围灯关闭指令
	 */
	private String ambClose;
	
	/**
	 * 筒灯打开指令
	 */
	private String downOpen;
	
	/**
	 * 筒灯关闭指令
	 */
	private String downClose;

	/**
	 * 执行次数
	 */
	private int count;
}
