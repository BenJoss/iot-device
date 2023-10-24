package com.huafen.device.model.page;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PageBean<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4120134091666547284L;
	
	@ApiModelProperty(value="设备名称",example = "设备01",required = false)
	private String deviceName;
	@ApiModelProperty(value="设备IP",example = "172.28.5.141",required  = false )
	private String deviceIP;
	@ApiModelProperty(value="设备所在会议室",example = "A2-206",required = false)
	private String deviceRoom;
	@ApiModelProperty(value="当前页数",example = "1")
    private int pageNum; //当前页数
	@ApiModelProperty(value="每页显示数",example = "10")
    private int pageSize; //每页显示数
	@ApiModelProperty(value="总页数",example = "0")
    private int totalPage; //总页数
	@ApiModelProperty(value="总的记录数",example = "0")
    private int totalRecord; //总的记录数
	// @ApiModelProperty(value="当前页面的数据集合",example = "List")
    private List<T> data; //当前页面的数据集合
    private int start;
    private int end;

    public PageBean() {
    	
    }

    public PageBean(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        //计算总页数
        this.totalPage=totalRecord%pageSize==0?(totalRecord/pageSize):(totalRecord/pageSize+1);
        //计算每页的起始下标
        this.start=(pageNum-1)*pageSize;
        this.end=this.start+pageSize;
    }

    
    

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceIP() {
		return deviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}
	
	public String getDeviceRoom() {
		return deviceRoom;
	}

	public void setDeviceRoom(String deviceRoom) {
		this.deviceRoom = deviceRoom;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	
    
    

}
