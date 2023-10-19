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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huafen.device.model.config.MqttProviderPros;

@Configuration
public class MqttProviderConfig {

    @Autowired
    private MqttProviderPros mqttProperties;
    
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MqttProviderConfig.class);
	/**
     * 客户端对象
     */
    private static MqttClient client;
    
    
    public static MqttClient getMqttClient() {
        return client;
    }
    
    @Bean
    public MqttClient connect(){
        try {
            //创建MQTT客户端对象
            client = new MqttClient(mqttProperties.getHostUrl(),mqttProperties.getClientId(),new MemoryPersistence());
            //连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            //是否清空session，设置为false表示服务器会保留客户端的连接记录（订阅主题，qos），客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
            //设置为true表示每次连接到服务端都是以新的身份
            options.setCleanSession(true);
            //设置连接用户名
            options.setUserName(mqttProperties.getUsername());
            //设置连接密码
            options.setPassword(mqttProperties.getPassword().toCharArray());
            //设置超时时间，单位为秒
            options.setConnectionTimeout(mqttProperties.getTimeout());
            //设置心跳时间 单位为秒，表示服务器每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
            options.setKeepAliveInterval(mqttProperties.getKeepAlive());
            options.setAutomaticReconnect(mqttProperties.getReconnect());
            options.setCleanSession(mqttProperties.getCleanSession());
            //设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
            options.setWill("willTopic",(mqttProperties.getClientId() + "与服务器断开连接").getBytes(),0,false);
            //设置回调
            client.setCallback(new MqttProviderCallBack());
            client.connect(options);
        } catch (MqttException e) {
        	log.error(e.getLocalizedMessage());
        }
        return client;
    }
    
    /**
     * 重新连接
     */
    public void reconnection() {
        try {
        	client.connect();
        } catch (MqttException e) {
        	log.error("重新连接失败:"+e.getMessage());
        }
    }
    
    public void publish(int qos,boolean retained,String topic,String message){
    	 if (null != client && client.isConnected()) {
    		    MqttMessage mqttMessage = new MqttMessage();
    	        mqttMessage.setQos(qos);
    	        mqttMessage.setRetained(retained);
    	        mqttMessage.setPayload(message.getBytes());
    	        //主题目的地，用于发布/订阅消息
    	        MqttTopic mqttTopic = client.getTopic(topic);
    	        //提供一种机制来跟踪消息的传递进度。
    	        //用于在以非阻塞方式（在后台运行）执行发布时跟踪消息的传递进度
    	        MqttDeliveryToken token;
    	        try {
    	            //将指定消息发布到主题，但不等待消息传递完成。返回的token可用于跟踪消息的传递状态。
    	            //一旦此方法干净地返回，消息就已被客户端接受发布。当连接可用时，将在后台完成消息传递。
    	            token = mqttTopic.publish(mqttMessage);
    	            token.waitForCompletion();
    	            log.info("mqtt 推送主题  "+mqttTopic+" token:"+token.getMessageId()+" 成功");
    	        } catch (MqttException e) {
    	        	log.error("mqtt推送主题"+mqttTopic+"异常："+e.getLocalizedMessage());
    	        	throw new RuntimeException(e);
    	        }
    	 }else {
    		 reconnection();
		}
        
    }
}
