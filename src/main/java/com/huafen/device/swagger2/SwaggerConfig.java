package com.huafen.device.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

	 @Bean
	    public Docket operationDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .groupName("设备信息维护相关接口")
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.huafen.device.controller.edit"))//扫描的包路径
	                .build();
	    }
	 
	 @Bean
	    public Docket iotRoomDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .groupName("设备控制相关接口")
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.huafen.device.controller.room"))//扫描的包路径
	                .build();
	    }
	 
	 @Bean
	    public Docket iotRoomTopicDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .groupName("设备主题发送相关接口")
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.huafen.device.controller.topic"))//扫描的包路径
	                .build();
	    }
	 
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("物联网设备信息维护接口文档")
	                .version("1.0.0")
	                .build();
	    }
}
