package com.huafen.device.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huafen.device.model.config.RedisProperties;

@Configuration
public class RedissionConfig {

    /**
     * redisson协议前缀
     */
    private static final String SCHEMA_PREFIX = "redis://";
    
    
    @Bean
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer().
                setAddress(SCHEMA_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort());
        singleServerConfig.setTimeout(redisProperties.getTimeout());
        singleServerConfig.setPingConnectionInterval(30000);
        singleServerConfig.setConnectionPoolSize(redisProperties.getConnectionPoolSize());
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        if (!"".equals(redisProperties.getPassword())) {
        	singleServerConfig.setPassword(redisProperties.getPassword());
		}
        if (!"".equals(redisProperties.getUsername())) {
            singleServerConfig.setUsername(redisProperties.getUsername());	
        }
        return Redisson.create(config);
    }
}
