package com.huafen.device.model.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mtcofig")
public class MTConfig {
   
    private String floorUrl;
    
    private String roomUrl;

    private String mtinfoUrl;
    
    private String orgUserAd;
    

	public String getFloorUrl() {
		return floorUrl;
	}

	public void setFloorUrl(String floorUrl) {
		this.floorUrl =  floorUrl;
	}

	public String getRoomUrl() {
		return roomUrl;
	}

	public String getOrgUserAd() {
		return orgUserAd;
	}

	public void setOrgUserAd(String orgUserAd) {
		this.orgUserAd = orgUserAd;
	}

	public void setRoomUrl(String roomUrl) {
		this.roomUrl = roomUrl;
	}

	public String getMtinfoUrl() {
		return mtinfoUrl;
	}

	public void setMtinfoUrl(String mtinfoUrl) {
		this.mtinfoUrl =  mtinfoUrl;
	}

	
    
    
}
