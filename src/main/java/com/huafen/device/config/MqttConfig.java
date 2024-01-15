package com.huafen.device.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.huafen.device.model.config.MqttProperties;
import com.huafen.device.mqtt.MqttAcceptClient;
import com.huafen.device.mqtt.MqttCondition;



@Configuration
@Order(1000)
@RefreshScope
public class MqttConfig {
	@Autowired
    private MqttAcceptClient mqttAcceptClient;
	@Autowired
	private MqttProperties mqttProperties;
    @Conditional(MqttCondition.class)
    @Bean
    public MqttAcceptClient getMqttAcceptClient(){
        mqttAcceptClient.setClientId(mqttProperties.getClientId());
        mqttAcceptClient.connect();
        return mqttAcceptClient;
    }
}
