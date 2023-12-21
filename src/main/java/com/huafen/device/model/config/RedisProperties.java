package com.huafen.device.model.config;

//@Component
//@ConfigurationProperties(prefix = "redis")
//TODO
@Deprecated
public class RedisProperties {

	private String host;
	
	
	private String port;
	
    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 数据库
     */
    private int  database;
    
    /**
     * 锁超时间
     */
    private long lockWatchdogTimeout;
    
    /**
     * 获取锁的最大尝试时间
     */
    private long tryTime;
    
    /**
     * 加锁的时间
     */
    private long lockTime;
    
    private int  connectionPoolSize;
    
    private String password;
    
    private String username;
    
    /**
     * 
     * 
     */
    
    private String prefix;

    private String lockName;
    
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public long getLockWatchdogTimeout() {
		return lockWatchdogTimeout;
	}

	public void setLockWatchdogTimeout(long lockWatchdogTimeout) {
		this.lockWatchdogTimeout = lockWatchdogTimeout;
	}

	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public long getTryTime() {
		return tryTime;
	}

	public void setTryTime(long tryTime) {
		this.tryTime = tryTime;
	}

	public long getLockTime() {
		return lockTime;
	}

	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
    
    
	
}
