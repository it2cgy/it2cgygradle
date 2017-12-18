package com.yunengzhe.PVMTS.interceptor;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class FilterEncodeURI implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
	    HttpServletResponse httpServletResponse = (HttpServletResponse)response;
	    
	    HttpSession session = httpServletRequest.getSession();
	    String path = httpServletRequest.getRequestURI();
	    //httpServletRequest.
	    System.out.println(path);
	    System.out.println(URLDecoder.decode(path, "utf-8"));
	    //request.getRequestDispatcher("./upload/3/2/70dd3667-4ac4-4d50-ba6d-c6dc21bb6848_运维管理平台12表单.pdf").forward(request, response);
	    chain.doFilter(request, response);
		/*UserBean userBean = (UserBean)session.getAttribute("user");
		if(userBean == null){
			//System.out.println("----> " + hashMap);
			httpServletResponse.sendRedirect("/login.jsp");
		}*/
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
