package com.huafen.device.util;

public enum DeviceEunm {
	
	 ROOM_IMG_CACHE{
	        public String obtainSource() {
	            return "roomImgCache";
	        }
	    },
	 
	 ROOM_PNG_TOPIC{
	        public String obtainSource() {
	            return "room_png_topic";
	        }
	    }
	 ;
	
	 public abstract String obtainSource();

}
