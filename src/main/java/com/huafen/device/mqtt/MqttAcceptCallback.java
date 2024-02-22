package com.huafen.device.mqtt;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huafen.device.model.config.MqttProperties;
import com.huafen.device.service.IotDeviceRoomService;

import lombok.extern.slf4j.Slf4j;  

@Component
@Slf4j
public class MqttAcceptCallback implements MqttCallbackExtended{
	
	
    @Autowired
    private MqttAcceptClient mqttAcceptClient;
    
    @Autowired
    private MqttProperties mqttProperties;
   
	@Autowired
	@Qualifier("iotDeviceRoomService")
	private IotDeviceRoomService iotDeviceRoomService;
	
	@Override
	public void connectionLost(Throwable cause) {
		
        synchronized(this) {
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
		
        
	}
    /**
     * 客户端收到消息触发
     * @param topic       主题
     * @param mqttMessage 消息
     */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		  String msg = new String(message.getPayload());
		  switch (topic) {
				case "room_png_topic":
					if (msg.equals("upate")) {
						//刷新会议图片缓存
					}
					log.info("刷新会议图片缓存");
					iotDeviceRoomService.loadIotRoomImgInfo();
					break;
		
				default:
					break;
		  }
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
