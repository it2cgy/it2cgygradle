/** 
 * 项目:PVMTS 
 * 文件名:DataSourceInitListener.java 
 * 包名:com.yunengzhe.PVMTS.util.Timer 
 * 创建日期:2017年3月28日下午4:52:09 
 */ 
package com.yunengzhe.PVMTS.util.Timer;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


@Service
public class InitDataListener implements ApplicationListener<ApplicationEvent>{

	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getClass().getName());
		if(event instanceof ContextRefreshedEvent){
//				timer.timer();
		}
		
	}
	
//	private CompanyServiceImpl productService = null;

	
}
