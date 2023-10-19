package com.huafen.device.service;

import com.huafen.device.model.device.IotDeviceDTO;
import com.huafen.device.model.page.PageBean;
import com.huafen.device.model.param.IotDeviceParam;
import com.huafen.device.model.req.ReposeDTO;

public interface IotDeviceEditService {

	public ReposeDTO<Integer>  increaseDeviceInfo(IotDeviceDTO iotDeviceDTO);
	
	public ReposeDTO<Integer> deleteIotDeviceInfoService(IotDeviceParam iotDeviceParam);
	
	public ReposeDTO<Integer> updateIotDeviceInfoService(IotDeviceDTO iotDeviceDTO);
	
	public PageBean<IotDeviceDTO> queryIotDevicePageListService(PageBean<IotDeviceDTO> pageBean);
}
