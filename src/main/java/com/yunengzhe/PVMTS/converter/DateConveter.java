package com.yunengzhe.PVMTS.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.yunengzhe.commons.util.TimeUtil;

public class DateConveter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		String pattern="yyyy-MM-dd HH:mm:ss";
		if(source.length()==10){
			pattern = "yyyy-MM-dd";
		}
		
		if(source.length()==13){
			pattern = "yyyy-MM-dd HH";
		}
		
		if(source.length()==16){
			pattern = "yyyy-MM-dd HH:mm";
		}
		
		return TimeUtil.string2Date(source,pattern); 
	}

}
