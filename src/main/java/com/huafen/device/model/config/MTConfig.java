package com.huafen.device.model.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "mtcofig")
@RefreshScope
@Data
public class MTConfig {

    private String roomIdTimeUrl;
    
    private String floorUrl;
    
    private String roomUrl;

    private String mtinfoUrl;
    
    private String openApiUrl;
    
    private String udsUploadPath;
    
    private String udsDownloadPath;
}
