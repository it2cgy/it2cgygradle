package com.yunengzhe.PVMTS.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.yunengzhe.PVMTS.domain.po.ThirdEquipmentsPO;
import com.yunengzhe.PVMTS.domain.po.ThirdPointsPO;
import com.yunengzhe.PVMTS.domain.vo.ThirdVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.ThirdEquipmentsService;
import com.yunengzhe.PVMTS.service.ThirdPointsService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.SpringContextUtil;

public class FilterLogin implements Filter {

	FilterConfig config = null;
	private ThirdEquipmentsService thirdEquipmentsService;
	private ThirdPointsService thirdPointsService;
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse response1 = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse(); 
		HttpServletRequest request2 = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest(); 
		
 
		if(request2.getMethod().equals("OPTIONS")){
			chain.doFilter(request, response); 
			return;
		}
		
		
		String uri = request2.getRequestURI();
		String noLoginPath = config.getInitParameter("freePages");
		if(noLoginPath!=null){
			String [] noLoginPaths = noLoginPath.split(",");
			for(int i=0;i<noLoginPaths.length;i++){
				String nologinPath = noLoginPaths[i];
				if(StringUtils.isBlank(nologinPath)){
					continue;
				}
				
				if(uri.indexOf(nologinPath) >=0){
					chain.doFilter(request, response); 
					return;
				}
			}
		} 
		//HttpServletResponse response1 = servletWebRequest.getResponse();
		//response1.setStatus(1111);
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response; 
		if(!TokenUtil.isLogin()){ 
			String contentType = httpServletResponse.getContentType();
			String type = request2.getHeader("fromType");
			if(org.apache.commons.lang3.StringUtils.isBlank(type)){
				type=request2.getHeader("Accept")==null?request2.getHeader("accept"):request2.getHeader("Accept");
			}
			if(StringUtils.isNotBlank(contentType) && contentType.toUpperCase().indexOf("JSON")>=0){ 
				httpServletResponse.setStatus(401);
				httpServletResponse.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				httpServletResponse.setContentType("text/html; charset=utf-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.write("{\"message\":\"not login\"}"); 
				return;
			}else if(StringUtils.isNotBlank(type) && (type.toUpperCase().indexOf("JSON")>=0||"app".equals(type)||"web".equals(type))){ 
				httpServletResponse.setStatus(401);
				httpServletResponse.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				httpServletResponse.setContentType("text/html; charset=utf-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.write("{\"message\":\"not login\"}"); 
				return;
			}else{ 
				String path = httpServletRequest.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

				httpServletResponse.sendRedirect(basePath+"user/login"); 
				return;
			} 
		}else{
			UserVO userVO = TokenUtil.getLoginUser();;
			if(userVO.getRoleList().get(0).getRoleId()==4){
				if(thirdEquipmentsService == null){
					thirdEquipmentsService = (ThirdEquipmentsService) SpringContextUtil.getBean("thirdEquipmentsService");
				}
				if(thirdPointsService == null){
					thirdPointsService = (ThirdPointsService) SpringContextUtil.getBean("thirdPointsService");
				}
				List<ThirdEquipmentsPO> eqlist = thirdEquipmentsService.findThirdEquipments();
				List<ThirdPointsPO> plist = thirdPointsService.findThirdPoint();
				ThirdVO thv = new ThirdVO();
				thv.setEqlist(eqlist);
				thv.setPlist(plist);
				request.setAttribute("thirdVO", thv);
			}
		}
	
		
		//HttpSessionUtil.setAttribute("leyepowerStationId", powerStationId);
		chain.doFilter(request, response); 
	}

	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
