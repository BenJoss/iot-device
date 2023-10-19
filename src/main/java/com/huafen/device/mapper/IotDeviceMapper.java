package com.huafen.device.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.device.model.device.IotDeviceDTO;
import com.huafen.device.model.page.PageBean;
import com.huafen.device.model.param.IotDeviceParam;

@Mapper
public interface IotDeviceMapper {

	int countIotDeviceDTO(IotDeviceParam iotDeviceParam);
	
	int countIotDeviceByPage(PageBean<IotDeviceDTO> pageBean);
	
	int insertIotDeviceDTO(IotDeviceDTO iotDeviceDTO);
	
	int deleteIotDeviceInfo(IotDeviceParam iotDeviceParam);
	
	int updateIotDeviceInfo(IotDeviceDTO iotDeviceDTO);
	
	List<IotDeviceDTO> queryIotDevicePageList(PageBean<IotDeviceDTO> pageBean);
}
