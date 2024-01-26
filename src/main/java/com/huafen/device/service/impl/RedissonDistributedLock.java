package com.huafen.device.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huafen.device.model.config.RedisProperties;
import com.huafen.device.service.IDistributedLock;



@Component
public class RedissonDistributedLock implements IDistributedLock{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedissonDistributedLock.class);
	
	@Resource
    private RedissonClient redissonClient;
    /**
     * 统一前缀
     */
    @Autowired
    private RedisProperties redisProperties;
 
    @Override
    public RLock lock(String key) {
        return this.lock(key, 0L, TimeUnit.SECONDS, false);
    }
 
    @Override
    public RLock lock(String key, long lockTime, TimeUnit unit, boolean fair) {
        RLock lock = getLock(key, fair);
        // 获取锁,失败一直等待,直到获取锁,不支持自动续期
        if (lockTime > 0L) {
            lock.lock(lockTime, unit);
        } else {
            // 具有Watch Dog 自动延期机制 默认续30s 每隔30/3=10 秒续到30s
            lock.lock();
        }
         return lock;
    }
 
    @Override
    public RLock tryLock(String key, long tryTime) throws Exception {
        return this.tryLock(key, tryTime, 0L, TimeUnit.SECONDS, false);
    }
 
    @Override
    public RLock tryLock(String key, long tryTime, long lockTime, TimeUnit unit, boolean fair)
            throws Exception {
        RLock lock = getLock(key, fair);
        boolean lockAcquired;
        // 尝试获取锁，获取不到超时异常,不支持自动续期
        if (lockTime > 0L) {
            lockAcquired = lock.tryLock(tryTime, lockTime, unit);
        } else {
            // 具有Watch Dog 自动延期机制 默认续30s 每隔30/3=10 秒续到30s
            lockAcquired = lock.tryLock(tryTime, unit);
        }
        if (lockAcquired) {
            return lock;
        }
        return null;
    }
 
    /**
     * 获取锁
     *
     * @param key
     * @param fair
     * @return
     */
    private RLock getLock(String key, boolean fair) {
        RLock lock;
        String lockKey = redisProperties.getPrefix() + ":" + key;
        if (fair) {
            // 获取公平锁
            lock = redissonClient.getFairLock(lockKey);
        } else {
            // 获取普通锁
            lock = redissonClient.getLock(lockKey);
        }
        return lock;
    }
 
    @Override
    public void unLock(Object lock) {
        if (!(lock instanceof RLock)) {
            throw new IllegalArgumentException("Invalid lock object");
        }
        RLock rLock = (RLock) lock;
        if (rLock.isLocked()) {
            try {
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                log.error("释放分布式锁异常", e);
            }
        }
    }

}
