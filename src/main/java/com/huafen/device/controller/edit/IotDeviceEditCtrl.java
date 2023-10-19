package com.huafen.device.controller.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.device.model.device.IotDeviceDTO;
import com.huafen.device.model.page.PageBean;
import com.huafen.device.model.param.IotDeviceParam;
import com.huafen.device.model.req.ReposeDTO;
import com.huafen.device.service.IotDeviceEditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"设备编辑相关接口"})
@RestController
@RequestMapping("/IotDeviceEditCtrl")
public class IotDeviceEditCtrl {

	@Autowired
	@Qualifier("iotDeviceEditService")
	private IotDeviceEditService ioTDeviBorroSerivce;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "新增设备", notes = "新增设备")
    @PostMapping("/increaseDeviceInfo")
    @ResponseBody
    public ReposeDTO<Integer> increaseDeviceInfo(@RequestBody IotDeviceDTO iotDeviceDTO) {
		return ioTDeviBorroSerivce.increaseDeviceInfo(iotDeviceDTO);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "删除设备", notes = "删除设备")
    @PostMapping("/deleteIotDeviceInfo")
    @ResponseBody
    public ReposeDTO<Integer> deleteIotDevice(@RequestBody IotDeviceParam iotDeviceParam) {
		return ioTDeviBorroSerivce.deleteIotDeviceInfoService(iotDeviceParam);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "更新设备信息", notes = "更新设备信息")
    @PostMapping("/updateIotDeviceInfo")
    @ResponseBody
    public ReposeDTO<Integer> updateIotDeviceInfo(@RequestBody IotDeviceDTO iotDeviceDTO) {
		return ioTDeviBorroSerivce.updateIotDeviceInfoService(iotDeviceDTO);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = PageBean.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "分页查询设备信息", notes = "分页查询设备信息")
    @PostMapping("/queryIotDevicePageList")
    @ResponseBody
    public PageBean<IotDeviceDTO> queryIotDevicePageList(@RequestBody PageBean<IotDeviceDTO> pageBean) {
		return ioTDeviBorroSerivce.queryIotDevicePageListService(pageBean);
	}
}
