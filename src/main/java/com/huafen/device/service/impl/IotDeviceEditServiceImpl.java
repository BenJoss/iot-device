package com.huafen.device.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.device.mapper.IotDeviceMapper;
import com.huafen.device.model.device.IotDeviceDTO;
import com.huafen.device.model.page.PageBean;
import com.huafen.device.model.param.IotDeviceParam;
import com.huafen.device.model.req.ReposeDTO;
import com.huafen.device.service.IotDeviceEditService;
import com.huafen.device.config.RepCode;

@Service("iotDeviceEditService")
public class IotDeviceEditServiceImpl implements IotDeviceEditService{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IotDeviceEditServiceImpl.class);
	@Autowired
	private IotDeviceMapper iotDeviceMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public ReposeDTO<Integer> increaseDeviceInfo(IotDeviceDTO iotDeviceDTO) {
		ReposeDTO<Integer>  reposeDTO = new ReposeDTO<Integer>();
	    try {
	    	IotDeviceParam iotDeviceParam = new IotDeviceParam();
	    	iotDeviceParam.setDeviceID(iotDeviceDTO.getDeviceID());
//	    	iotDeviceParam.setDeviceIP(iotDeviceDTO.getDeviceIP());
//	    	iotDeviceParam.setDevicePort(iotDeviceDTO.getDevicePort());
	    	int  count = iotDeviceMapper.countIotDeviceDTO(iotDeviceParam);
			if (count == 0) {
				iotDeviceMapper.insertIotDeviceDTO(iotDeviceDTO);
			}else {
				reposeDTO.setRepCode(RepCode.ERROR_CODE);
				reposeDTO.setRepMsg("设备ID重复");
				return reposeDTO;
			}
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			logger.error(e.getMessage());
		}
		return reposeDTO;
	}

	@Override
	public ReposeDTO<Integer> deleteIotDeviceInfoService(IotDeviceParam iotDeviceParam) {
		ReposeDTO<Integer>  reposeDTO = new ReposeDTO<Integer>();
		try {
			iotDeviceMapper.deleteIotDeviceInfo(iotDeviceParam);
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			logger.error(e.getMessage());
		}
		return reposeDTO;
	}
	

	@Override
	public ReposeDTO<Integer> updateIotDeviceInfoService(IotDeviceDTO iotDeviceDTO) {
		ReposeDTO<Integer>  reposeDTO = new ReposeDTO<Integer>();
		try {
			iotDeviceMapper.updateIotDeviceInfo(iotDeviceDTO);
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			logger.error(e.getMessage());
		}
		return reposeDTO;
	}

	
	@Override
	public PageBean<IotDeviceDTO> queryIotDevicePageListService(PageBean<IotDeviceDTO> pageBean) {
		 try {
			 if ( pageBean.getPageNum() < 0 ) {
					return pageBean;
				}
				int totalRecord = 0;
				if (pageBean.getTotalRecord() == 0) {
					totalRecord = iotDeviceMapper.countIotDeviceByPage(pageBean);
				}else {
					totalRecord = pageBean.getTotalRecord();
				}
				PageBean<IotDeviceDTO> pageParam = new PageBean<>(pageBean.getPageNum(),pageBean.getPageSize(),totalRecord);
				pageParam.setDeviceName(pageBean.getDeviceName());
				pageParam.setDeviceIP(pageBean.getDeviceIP());
				pageParam.setDeviceRoom(pageBean.getDeviceRoom());
				List<IotDeviceDTO>  dataList = iotDeviceMapper.queryIotDevicePageList(pageParam);
				pageParam.setTotalRecord(totalRecord);
				pageParam.setData(dataList);
				return pageParam;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return pageBean;
	}
	
	
}
