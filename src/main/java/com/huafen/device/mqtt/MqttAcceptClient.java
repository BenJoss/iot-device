package com.huafen.device.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huafen.device.model.config.MqttProperties;

@Component
public class MqttAcceptClient {
    @Autowired
    private MqttAcceptCallback mqttAcceptCallback;
    @Autowired
    private MqttProperties mqttProperties;
    
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MqttAcceptClient.class);
    
    private static MqttClient mqttClient;
    
    public static MqttClient getMqttClient() {
        return mqttClient;
    }
    
    public static void setMqttClient(MqttClient mqttClient) {
        MqttAcceptClient.mqttClient = mqttClient;
    }
    
    /**
     * 客户端连接
     */
    public void connect() {
        MqttClient client;
        try {
            client = new MqttClient(mqttProperties.getHostUrl(), mqttProperties.getClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(mqttProperties.getUsername());
            options.setPassword(mqttProperties.getPassword().toCharArray());
            options.setConnectionTimeout(mqttProperties.getTimeout());
            options.setKeepAliveInterval(mqttProperties.getKeepAlive());
            options.setAutomaticReconnect(mqttProperties.getReconnect());
            options.setCleanSession(mqttProperties.getCleanSession());
            MqttAcceptClient.setMqttClient(client);
            try {
                // 设置回调
                client.setCallback(mqttAcceptCallback);
                client.connect(options);
            } catch (Exception e) {
            	log.error("客户端连接异常:"+e.getMessage());
            }
        } catch (Exception e) {
        	 log.error("客户端连接异常:"+e.getMessage());
        }
    }
    /**
     * 重新连接
     */
    public void reconnection() {
        try {
            mqttClient.connect();
        } catch (MqttException e) {
        	log.error("重新连接失败:"+e.getMessage());
        }
    }
    
    /**
     * 订阅某个主题
     *
     * @param topic 主题
     * @param qos   连接方式
     */
    public void subscribe(String topic, int qos) {
        log.info("==============开始订阅主题==============" + topic);
        try {
            mqttClient.subscribe(topic, qos);
        } catch (MqttException e) {
        	log.error("订阅某个主题失败:"+e.getMessage());
        }
    }
    /**
     * 订阅多个主题
     *
     * @param topic 主题
     * @param qos   连接方式
     */
    public void subscribe(String[] topic, int[] qos) {
        log.info("==============开始订阅主题==============" + topic);
        try {
            mqttClient.subscribe(topic,qos);
        } catch (MqttException e) {
        	log.error("订阅多个主题失败:"+e.getMessage());
        }
    }
    /**
     * 取消订阅某个主题
     *
     * @param topic
     */
    public void unsubscribe(String topic) {
        log.info("==============开始取消订阅主题==============" + topic);
        try {
            mqttClient.unsubscribe(topic);
        } catch (MqttException e) {
        	log.error("取消订阅某个主题失败:"+e.getMessage());
        }
    }
    
    
    
    public void publishMessage(String pubTopic, String message, int qos,Boolean retained) {
        if (null != mqttClient && mqttClient.isConnected()) {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message.getBytes());
            mqttMessage.setRetained(retained);

            MqttTopic topic = mqttClient.getTopic(pubTopic);

            if (null != topic) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if (!publish.isComplete()) {
                        System.out.println("消息发布成功");
                    }
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } else {
        	reconnection();
        }

    }

}
