package com.huafen.device.util;

import java.util.concurrent.ConcurrentHashMap;

public class CallCacheUtil {

	static final int DEFAULT_CAPACITY = 16;

	private static ConcurrentHashMap<String, Object> callCacheMap = new ConcurrentHashMap<String, Object>(DEFAULT_CAPACITY);
	
	
	/**
     * 私有的默认构造子，保证外界无法直接实例化
    */
   private CallCacheUtil(){};

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
	 */
	private static class SingletonHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static CallCacheUtil cacheUtil = new CallCacheUtil();
	}


	public static CallCacheUtil getInstance() {
		return SingletonHolder.cacheUtil;
	}

	public  void  put(String key,Object object)  {
		callCacheMap.put(key,object);
    }
	
	public  Object  get(String key)  {
	   return	callCacheMap.get(key);
    }
}
