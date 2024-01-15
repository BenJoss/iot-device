package com.huafen.device.config;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.huafen.device.mapper.IotDeviceRoomMapper;
import com.huafen.device.model.param.IotConfig;
import com.huafen.device.service.IotDeviceRoomTaskService;
import com.huafen.device.util.CallRMUtil;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class ScheduledConfig implements SchedulingConfigurer {

	@Autowired
	@Qualifier("iotDeviceRoomTaskService")
	private IotDeviceRoomTaskService iotDeviceRoomTaskService;
	
	@Autowired
	private IotDeviceRoomMapper iotDeviceRoomMapper;
	
	private volatile ScheduledTaskRegistrar registrar;

	private final int POOL_SIZE = 10;

	private static ReentrantLock publishLock = new ReentrantLock();
	
	private static ReentrantLock loadLock = new ReentrantLock();
	
	 @Bean
	 public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
	        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	        scheduler.setPoolSize(POOL_SIZE);
	        scheduler.setThreadNamePrefix("scheduler-");
	        scheduler.setRemoveOnCancelPolicy(true);
	        return scheduler;
	    }

	 private void initSceneNameTask(ScheduledTaskRegistrar taskRegistrar) {
		 IotConfig iotConfig = new IotConfig();
    	 iotConfig.setKey(CallRMUtil.AUTO_SCENE_NAME_TASK);
    	 Map<String, String>  configMap = iotDeviceRoomMapper.queryIotConfig(iotConfig);
    	 if (configMap != null) {
        		String autoSceneNameCronTask =  configMap.get(CallRMUtil.CON_VALUE);
        		CronTask cronTask = new CronTask(() -> {
        			try {
        				publishLock.tryLock(30,TimeUnit.SECONDS);
        				iotDeviceRoomTaskService.autoSceneNameTaskService();
        			}catch (Exception e) {
        				
        			} finally {
        				publishLock.unlock();
        			}    			
        			
        		}, autoSceneNameCronTask);
        		
        		taskRegistrar.scheduleCronTask(cronTask);
    		}
	 }
	 
	
	 private void initSceneNameLoadTask(ScheduledTaskRegistrar taskRegistrar) {
		    IotConfig iotConfig = new IotConfig();
		    iotConfig.setKey(CallRMUtil.AUTO_SCENE_NAME_LOAD_TASK);
	    	 Map<String, String>  configMap = iotDeviceRoomMapper.queryIotConfig(iotConfig);
	    	 if (configMap != null) {
	    		   String autoSceneNameLoadTask =  configMap.get(CallRMUtil.CON_VALUE);
	    		   CronTask cronTask = new CronTask(() -> {
	        			try {
	        				loadLock.tryLock(30,TimeUnit.SECONDS);
	        				iotDeviceRoomTaskService.sceneNameLoadConfigTaskService();
	        			}catch (Exception e) {
	        				
	        			} finally {
	        				loadLock.unlock();
	        			}    			
	        			
	        		}, autoSceneNameLoadTask);
	        		
	        		taskRegistrar.scheduleCronTask(cronTask);
	    	 }
	 }
	 
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {		
		try {
			  ThreadPoolTaskScheduler taskScheduler = threadPoolTaskScheduler();
		      taskRegistrar.setScheduler(taskScheduler);
			  this.initSceneNameTask(taskRegistrar);
			  this.initSceneNameLoadTask(taskRegistrar);
	          this.registrar = taskRegistrar;
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}

	}
	
	@PreDestroy
	public void destroy() {
	     this.registrar.destroy();
	}
}
