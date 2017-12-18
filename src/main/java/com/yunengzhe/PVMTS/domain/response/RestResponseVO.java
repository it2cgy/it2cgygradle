package com.yunengzhe.PVMTS.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @ClassName:RestResponseVO
 * @Description:TODO(响应参数实体类)
 * @author chenguiyang
 * @date 2017年6月19日下午4:27:03
 */
@JsonInclude(Include.NON_NULL)
public class RestResponseVO {
    private Integer code; //
    private String message;//响应信息 
    private Object data;   //响应数据 
   
   
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
   
   
   
}
