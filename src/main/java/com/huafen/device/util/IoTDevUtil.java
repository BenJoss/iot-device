package com.huafen.device.util;

import java.util.UUID;

public class IoTDevUtil {
	
	/**
	 *  设备开关指令配置
	 */
	public final static String  DEVICE_CONFIG="deviceConfig";
	/**
	 *  灯开关指令配置
	 */
	public final static String  LIGHT_CONFIG="lightConfig";
	/**
	 *  LED开关指令配置
	 */
	public final static String  LED_CONFIG="LedConfig";
	/**
	 *  设备开关
	 */
	
	public final static String  DEV_TOPIC="AD-M3";
	
	public final static String  DEV_ICE="DEVICE";
	
	public final static String  TOPIC="Topic";
	
	public final static String  DEV_ICE_OPEN="001";
	
	/**
	 *  设备开关
	 */

	public final static String  LIGHT_ONE_TOUCH_STATUS="OFF";
	
	public final static String  LIGHT_TOUCH_STATUS="OFF";
	
	public final static String  LIGHT_ONE_ON_TOUCH_STATUS="ON";
	
	public final static String  LIGHT_TOUCH_ON_STATUS="ON";
	
	
	/**
	 * 1：待借用、2：借用中、3：完结、4：异常、5：取消
	 */
	public final static String  TO_BE_BORROWED="1";
	
	public final static String  IN_BORROWED="2";
	
	public final static String  FINISH_BORROWED="3";
	
	public final static String  EXCEPTION_BORROWED="4";
	
	public final static String  CANCEL_BORROWED="5";
	/**
	 * 平板借用状态：1：使用中、2：空闲、3: 已归还
	 */
	public final static String  USEING_STATUS="1";
	/**
	 * 平板借用状态：1：使用中、2：空闲、3: 已归还
	 */
	public final static String  IDLE_STATE="2";
	
	/**
	 * 平板借用状态：1：使用中、2：空闲、3: 已归还
	 */
	public final static String  RETURN_STATE="3";
	
	/**
	 * 平板状态：1：启用、2：禁用
	 */
	public final static String  ENABLE_STATE="1";
	
	public final static String  DISABLE_STATE="2";
	
	public final static String  ALL_DAY="全天";
	

	public final static String  MORN_DAY="上午";
	
	
	public final static String  AFTER_DAY="下午";
	
	public final static String  DATE_UINX=" ";
	
	public final static String  ZERO_UINX="00:00:00";
	
	public final static String  MORN_UINX="08:00:00";
	
	public final static String  AFTER_UINX="14:00:00";
	
	public final static Integer TRY_NUM = 3;
	
	// public final static Integer TABLET_SUM = 30;
	
	public final static String  TABLET_SUM = "TABLET_SUM";
	
	public final static String[] arr = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	public final static String  RETURN_TOKEN="RETURN_TOKEN";
	
	public final static String  BORRO_TOKEN="BORRO_TOKEN";
	
	public final static String  IOTDEV_RESULT="data";
	
	public final static String  RETURN_STATE_NUM="returnNum";
	
	public final static String  T_IOT_DEVICE_BORROW="t_iot_device_borrow";
	
	public final static String  PARTI_TION="partition";
	
   /** 
    * @Description: 创建Token，怕麻烦的可以直接生成UUID作为toekn的key和value 
    * @Date: 2020/1/8 0008 
    */ 
    public static String getToken(){
        String token_value = UUID.randomUUID().toString().replace("-","");
        try {
            return token_value;
        }catch (Exception e){
            return null;
        }
    }

}
