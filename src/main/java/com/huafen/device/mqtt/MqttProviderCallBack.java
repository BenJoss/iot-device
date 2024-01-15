package com.huafen.device.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.huafen.device.model.config.MqttProviderPros;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class MqttProviderCallBack implements MqttCallback {
	
	
    @Autowired
    private MqttProviderPros mqttProperties;
    
    @Autowired
    private MqttProviderConfig providerClient;
    
    /**
     * 与服务器断开连接的回调
     * @author xct
     * @param throwable
     * @return void
     * @date 2021/7/30 16:19
     */
    @Override
    public void connectionLost(Throwable throwable) {
    	 System.out.println(mqttProperties.getClientId() + " 与服务器断开连接");
    	 synchronized(this) {
    		 int reConnectNum = 0;
             while (reConnectNum <= 3) {
             	if (MqttProviderConfig.getMqttClient() == null || !MqttProviderConfig.getMqttClient().isConnected()) {
                     log.info("【MQTT-生产端】emqx重新连接....................................................");
                     providerClient.reconnection();
                 }else {
                 	return;
                 }
             	reConnectNum++;
             }
 		}
        
    }

    /**
     * 消息到达的回调
     * @author xct
     * @param s
     * @param mqttMessage
     * @return void
     * @date 2021/7/30 16:19
     */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

    }

    /**
     * 消息发布成功的回调
     * @author xct
     * @param iMqttDeliveryToken
     * @return void
     * @date 2021/7/30 16:20
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        IMqttAsyncClient client = iMqttDeliveryToken.getClient();
        System.out.println(client.getClientId() + " 发布消息成功！");
    }

}
