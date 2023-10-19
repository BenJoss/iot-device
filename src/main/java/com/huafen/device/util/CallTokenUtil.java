package com.huafen.device.util;

public class CallTokenUtil {

	
	private final static String ORG_ACCESSS_TOKEN="ORG_ACCESSS_TOKEN";
	
	public final static String ACCESS_TOKEN="access_token";
	
	public static String getOrgAccesssToken() {
	    return	(String)CallCacheUtil.getInstance().get(ORG_ACCESSS_TOKEN) == null?"":(String)CallCacheUtil.getInstance().get(ORG_ACCESSS_TOKEN);
	}
	
	public static void setOrgAccesssToken(String accessToken) {
		CallCacheUtil.getInstance().put(ORG_ACCESSS_TOKEN, accessToken);
	}
}
