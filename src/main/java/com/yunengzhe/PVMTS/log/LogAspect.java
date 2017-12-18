package com.yunengzhe.PVMTS.log;  

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yunengzhe.commons.util.IpUtil; 
 

@Aspect  
@Component  
public  class LogAspect {  

	//本地异常日志记录对象  
	private  static  final Logger logger = LoggerFactory.getLogger(LogAspect. class);  
	private  static  final boolean LOG_OPEN = true;  
	
	//Controller层切点  
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public  void controllerAspect() {  
	}  

	
	private String getSubString(String str){
		if(str==null){
			return "null";
		}
		if(str.length()>512){
			return str.substring(0, 512);
		}else{
			return str;
		}
	}
	/** 
	 * 前置通知 用于拦截Controller层记录用户的操作 
	 * 
	 * @param joinPoint 切点 
	 */  
	@Before("controllerAspect()")  
	public  void doBefore(JoinPoint joinPoint) {  
		try {  
			if(!LOG_OPEN)
				return;
			//System.out.println("=====前置通知=====");
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
			//HttpSession session = request.getSession();  
			//Map<String, String[]> map=request.getParameterMap();
			Class<?> clazz = joinPoint.getTarget().getClass(); 
			String targetName = clazz.getName();  
			String methodName = joinPoint.getSignature().getName();  
			//Object[] arguments = joinPoint.getArgs();
			//Method[] methods = clazz.getMethods();  
			String ip = IpUtil.getIpFormRequest(request);
			String description = getControllerMethodDescription(joinPoint);
			
			Map<String,Object> map = new HashMap<String,Object>(); 
			Object[] arguments = joinPoint.getArgs();
			Signature signature = joinPoint.getSignature();
			Object ob = joinPoint.getTarget();
			Object rObj = null;
  
			Enumeration<String> heads = request.getHeaderNames();
			Map<String,Object> headsMap = new HashMap<String,Object>(); 
			while (heads.hasMoreElements()){
				String headName = heads.nextElement();
				String headValue = request.getHeader(headName);
				headsMap.put(headName, headValue);
			} 
			
			HttpServletResponse response = null;
			JSONObject paramJson1 = null;
			List<Object> param = new ArrayList<Object>();
			for(int i=0;i<arguments.length;i++){
				Object o = arguments[i];
				if(o instanceof HttpSession){
					continue;
				}
				
				if(o instanceof ServletResponse){
					continue;
				}
				
				if(o instanceof ServletRequest){
					continue;
				}
				
				if(o instanceof Model){
					continue;
				}
				param.add(o);
			} 
			logger.info(String.format("请求头:%s.%s:%s:%s,%s",targetName,methodName,description,ip,this.getSubString(JSONObject.fromObject(headsMap).toString()))); 
			logger.info(String.format("请求参数:%s.%s:%s:%s,%s",targetName,methodName,description,ip,this.getSubString(JSONArray.fromObject(param).toString()))); 
		}  catch (Exception e) {  
			//记录本地异常日志 
			logger.error(e.getMessage(),e); 
		}  
	}  


	/** 
	 * 前置通知 用于拦截Controller层记录用户的操作 
	 * 
	 * @param joinPoint 切点 
	 */  
	@AfterReturning(pointcut="controllerAspect()", returning="retval")
	public  void doAfterReturning(JoinPoint joinPoint,Object retval) { 
		try {
			if(!LOG_OPEN)
				return;
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			//HttpSession session = request.getSession();
			Class<?> clazz = joinPoint.getTarget().getClass();
			String targetName = clazz.getName();
			String methodName = joinPoint.getSignature().getName();
			
			//Object[] arguments = joinPoint.getArgs();
			//Method[] methods = clazz.getMethods();  
			String description = getControllerMethodDescription(joinPoint);
			//int status = response.getStatus();
			String ip = IpUtil.getIpFormRequest(request);
			logger.info(String.format("返回值:%s.%s:%s:%s:return-%s",targetName,methodName,description,ip,this.getSubString((retval instanceof String?retval.toString():JSONObject.fromObject(retval).toString()))));
			
		}  catch (Exception e) {  
			//记录本地异常日志  
			logger.error(e.getMessage(),e);  
		}  
	}  

	@Around("controllerAspect()")
	public  Object doAround(ProceedingJoinPoint  joinPoint) { 
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			 
			long dateStart = System.currentTimeMillis();
			Object back = joinPoint.proceed();
			long dateEnd = System.currentTimeMillis();
			
			long doTimeLong = dateEnd-dateStart;
			String description = getControllerMethodDescription(joinPoint);
			Class<?> clazz = joinPoint.getTarget().getClass();
			String targetName = clazz.getName();
			String methodName = joinPoint.getSignature().getName();
			String ip = IpUtil.getIpFormRequest(request);
			if(doTimeLong>3000){//请求时间太长
				logger.error(String.format("处理时长>3000:%s.%s:%s:%s:%s",targetName,methodName,description,ip,""+doTimeLong+"毫秒"));			
			}else{
				logger.info(String.format("处理时长:%s.%s:%s:%s:%s",targetName,methodName,description,ip,""+doTimeLong+"毫秒"));			
			}
			
			return back;
			
		} catch (Throwable e) {
			logger.error("处理请求时间发生异常",e);
			e.printStackTrace();
		} 

		return null;
	}
	
	/** 
	 * 获取注解中对方法的描述信息 用于Controller层注解 
	 * 
	 * @param joinPoint 切点 
	 * @return 方法描述 
	 * @throws Exception 
	 */  
	public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {  
//		Class<?> clazz = joinPoint.getTarget().getClass();
//		String name = joinPoint.getSignature().getName();
//		Object[] parameterTypes = joinPoint.getArgs();
//		for (Method method : clazz.getDeclaredMethods()) {
//			if (method.getName().equals(name) && method.getParameterTypes().length == parameterTypes.length) {
//				ControllerLog methodLog = method.getAnnotation(ControllerLog. class);
//				if (methodLog != null) {
//					return methodLog.description();
//				}
//				break;
//			}
//		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String url = "http://" + request.getServerName() //服务器地址
		+ ":" 
		+ request.getServerPort()           //端口号
		+ request.getContextPath()      //项目名称
		+ request.getServletPath()      //请求页面或其他地址
		+ (request.getQueryString()==null?"":"?" + request.getQueryString()); //参数
	 	
		return url;		 
	}  
}  