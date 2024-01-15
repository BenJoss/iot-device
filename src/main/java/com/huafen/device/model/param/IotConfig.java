package com.huafen.device.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("配置参数")
@Data
public class IotConfig implements Serializable{
	
	private static final long serialVersionUID = 897893186794768916L;

	@ApiModelProperty(value="建",example = "E280689400004020F535A17F",required = true)
	private String key;
}
