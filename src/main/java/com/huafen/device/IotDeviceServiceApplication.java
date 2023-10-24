package com.huafen.device;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class IotDeviceServiceApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(IotDeviceServiceApplication.class, args);
		String host;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
	        System.out.println("[----------------------------------------------------------]");
	        System.out.println("物联网设备信息维护服务启动 :" + host );
	        System.out.println("[----------------------------------------------------------]");     
		} catch (UnknownHostException e) {
			
		}
	}
}
