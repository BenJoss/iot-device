package com.huafen.device.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
 	
	@Bean
	public RestTemplate restTemplate( ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		// 支持中文编码
		restTemplate.getMessageConverters().add(new HttpMessageConverter());
		return restTemplate;
	}
 
	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SSL factory = new SSL(); //这里使用刚刚配置的SSL
		// SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);//单位为ms
		factory.setConnectTimeout(5000);//单位为ms
		return factory;
	}

}
