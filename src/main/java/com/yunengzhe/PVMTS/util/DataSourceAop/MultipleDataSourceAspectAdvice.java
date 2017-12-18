package com.yunengzhe.PVMTS.util.DataSourceAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MultipleDataSourceAspectAdvice {
	
	/*@Pointcut("execution(public * com.yunengzhe.PVMTS.userrole.dao.impl.*.*(..))")
	public void aspect(){};
	
	@Before("aspect()")
	public void setDataSource(){
		MultipleDataSource.setDataSourceKey("mySqlDataSource");//oracleDataSource
	}*/
	//添加环绕行通知方法
	/*public void setDataSource(ProceedingJoinPoint jp){
		MultipleDataSource.setDataSourceKey("mySqlDataSource");//oracleDataSource//mySqlDataSource
		try {
			jp.proceed();
		} catch (Throwable e) {
			System.out.println("-----------方法调用失败---------");
			MultipleDataSource.removeDataSource();
			e.printStackTrace();
		}
		MultipleDataSource.removeDataSource();
	}*/
	public void setDataSource(){
		MultipleDataSource.setDataSourceKey("mySqlDataSource");//oracleDataSource//mySqlDataSource
	}
	public void setDataSource2(){
		MultipleDataSource.setDataSourceKey("mySqlDataSource2");//oracleDataSource//mySqlDataSource
	}
	public void removeDataSource(){
		MultipleDataSource.removeDataSource();
	}
	
//	public void aroundDataSource(){
//		MultipleDataSource.setDataSourceKey("mySqlDataSource");//oracleDataSource//mySqlDataSource
//	}
}
