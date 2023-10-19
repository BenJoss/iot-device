package com.huafen.device.mqtt;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huafen.device.model.config.MqttProperties;


@Component
public class MqttAcceptCallback implements MqttCallbackExtended{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(MqttAcceptCallback.class);
	
    @Autowired
    private MqttAcceptClient mqttAcceptClient;
    
    @Autowired
    private MqttProperties mqttProperties;
    
	
	@Override
	public void connectionLost(Throwable cause) {
		log.info("【MQTT-消费端】连接断开：" + cause.getMessage());
        int reConnectNum = 0;
        while (reConnectNum <= 3) {
        	if (MqttAcceptClient.getMqttClient() == null || !MqttAcceptClient.getMqttClient().isConnected()) {
                log.info("【MQTT-消费端】emqx重新连接....................................................");
                mqttAcceptClient.reconnection();
            }else {
            	return;
            }
        	reConnectNum++;
		}
	}
    /**
     * 客户端收到消息触发
     * @param topic       主题
     * @param mqttMessage 消息
     */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		String[] topics = token.getTopics();
        for (String topic : topics) {
            log.info("【MQTT-消费端】向主题：" + topic + "发送消息成功！");
        }
        try {
            MqttMessage message = token.getMessage();
            byte[] payload = message.getPayload();
            String s = new String(payload, "UTF-8");
            log.info("【MQTT-消费端】消息的内容是：" + s);
        } catch (MqttException e) {
        	log.error("【MQTT-消费端】失败:"+e.getMessage());
        } catch (UnsupportedEncodingException e) {
        	log.error("【MQTT-消费端】失败:"+e.getMessage());
        }
		
	}
    /**
     * 连接emq服务器后触发
     * @param b
     * @param s
     */
	@Override
	public void connectComplete(boolean reconnect, String serverURI) {
		log.info("--------------------【MQTT-消费端】连接成功！--------------------");
		String[] topic =  mqttProperties.getDefaultTopic().split(":");
		String[] qosArr = mqttProperties.getQos();
		int[] qos = new int[qosArr.length];
		for (int i = 0; i < qosArr.length; i++) {
			  qos[i] = Integer.parseInt(qosArr[i]);
		}
        mqttAcceptClient.subscribe(topic, qos);
		
	}

}
