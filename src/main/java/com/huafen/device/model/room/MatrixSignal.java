package com.huafen.device.model.room;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("矩阵信号")
public class MatrixSignal implements Serializable{
	private static final long serialVersionUID = 5061309434767616306L;
    
	@ApiModelProperty(value="矩阵输入区", example = "matrixIput")
	private MatrixIput matrixIput;
	
	@ApiModelProperty(value="矩阵输出区", example = "matrixOutList")
	private List<MatrixOut> matrixOutList;
}
