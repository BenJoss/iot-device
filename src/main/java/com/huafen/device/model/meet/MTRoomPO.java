package com.huafen.device.model.meet;

import java.io.Serializable;

public class MTRoomPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4356617769476393798L;


	private Long id;
	
	private String roomName;
	
	private String addr;
	
    private int cap;
	
	private int maxCap;
	
	private String majorPicture;
	
	private String status;
	
	private String services;
	
	private String floor;

	private int existMeeting;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public int getMaxCap() {
		return maxCap;
	}

	public void setMaxCap(int maxCap) {
		this.maxCap = maxCap;
	}

	public String getMajorPicture() {
		return majorPicture;
	}

	public void setMajorPicture(String majorPicture) {
		this.majorPicture = majorPicture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getExistMeeting() {
		return existMeeting;
	}

	public void setExistMeeting(int existMeeting) {
		this.existMeeting = existMeeting;
	}
	
	
	
}
