package com.huafen.device.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.huafen.device.mqtt.MqttAcceptClient;
import com.huafen.device.mqtt.MqttCondition;
@Configuration
public class MqttConfig {
	@Autowired
    private MqttAcceptClient mqttAcceptClient;
	
    @Conditional(MqttCondition.class)
    @Bean
    public MqttAcceptClient getMqttAcceptClient(){
        mqttAcceptClient.connect();
        return mqttAcceptClient;
    }
}
