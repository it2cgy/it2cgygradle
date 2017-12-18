package com.yunengzhe.PVMTS.domain.response;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;

/**
 * @ClassName:RestResponse
 * @Description:TODO(响应参数实体)
 * @author chenguiyang
 * @date 2017年6月19日下午3:49:20
 */
@JsonInclude(Include.NON_NULL)
public class RestResponse {
	
	public static Logger logger = LoggerFactory.getLogger(RestResponse.class);
	
	/**
	 * @Title:response
	 * @Description:TODO(返回状态信息以及对象数据) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseCode(int status,int errorCode,String message,Object resultObj){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();   
	    RestResponseVO result = new RestResponseVO();  
		response.setStatus(status);
		result.setCode(errorCode);
		result.setMessage(message);
		result.setData(resultObj);
		return result;
	}

	/**
	 * @Title:response
	 * @Description:TODO(返回状态信息以及对象数据) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO response(int status,int errorCode,String message,Object resultObj){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse(); 
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(status);
		result.setCode(errorCode);
		result.setMessage(message);
		result.setData(resultObj);
		return result;
	}


	/**
	 * @Title:response
	 * @Description:TODO(返回数据对象) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseCode(int errorCode,String message,Object resultObj){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();  
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(200);
		result.setCode(errorCode);
		result.setMessage(message);
		result.setData(resultObj);
		return result;
	}



	/**
	 * @Title:response
	 * @Description:TODO(返回错误信息) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseCode(int errorCode,String message){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse(); 
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(200);
		result.setCode(errorCode);
		result.setMessage(message);
		return result;
	}


	/**
	 * @Title:response
	 * @Description:TODO(返回对象) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseCode(int errorCode,Object resultObj){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(200);
		result.setCode(errorCode);
		result.setMessage("success");
		result.setData(resultObj);
		return result;
	}

	/**
	 * @Title:response
	 * @Description:TODO(返回错误信息) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseError(int errorCode,int staus,String message){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(staus);
		result.setCode(errorCode);
		result.setMessage("success");
		return result;
	}
	
	/**
	 * @Title:response
	 * @Description:TODO(返回错误信息) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseError(){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse(); 
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(500);
		result.setMessage("系统异常");
		return result;
	}


	/**
	 * @Title:response
	 * @Description:TODO(返回错误信息) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO response(int staus,String message){
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(staus);
		result.setMessage(message);
		return result;
	}

	/**
	 * @Title:response
	 * @Description:TODO(返回信息) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseOk(){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();  
		RestResponseVO result = new RestResponseVO();  
		response.setStatus(200);
		result.setMessage("success");
		return result;
	}

	/**
	 * @Title:response
	 * @Description:TODO(返回信息) 
	 * @param @param status
	 * @param @param message
	 * @param @param resultObj
	 * @param @return
	 * @return RestResponseVO
	 * @throws
	 */
	public static RestResponseVO responseOk(Object data){
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse(); 
	    RestResponseVO result = new RestResponseVO();  
	    response.setStatus(200);
		result.setMessage("success");
		result.setData(data);
		return result;
	}

	/**
	 * 返回列表
	 * @param request
	 * @param totalCount
	 * @param resultObjList
	 * @return
	 */
	public static Object responseList(HttpServletRequest request,int totalCount,List resultObjList){
		try {
		Paginator paginator = new Paginator(request,totalCount);   
		ListResult<Object> result = new ListResult<Object>();
		result.setCounts(totalCount);
		result.setNext(paginator.getNextUrl());
		result.setPrivious(paginator.getPreviousUrl());
		result.setResults(resultObjList);
		result.setPageCounts(paginator.getTotalPage());
		result.setPagesize(paginator.getPageSize()); 
		result.setCurrentPage(paginator.getCurrentPage());
		result.setFirst(paginator.getFirstUrl());
		result.setLast(paginator.getLastUrl());

		return result;
		} catch (Exception e) { 
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			
			return response(500,"分页异常");
		} 
	}
}
