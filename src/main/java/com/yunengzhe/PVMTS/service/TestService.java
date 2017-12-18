package com.yunengzhe.PVMTS.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.TestDao;
import com.yunengzhe.PVMTS.domain.po.TestPO;


@Service("testService")
public class TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestService.class);
	
	@Autowired
	private TestDao testDaoImpl;
	
	
	public void insertTestInfo(String info){
		TestPO p = new TestPO();
		p.setDatetime(new Date());
		p.setDescription(info);
		testDaoImpl.insert(p);
	}
	 
}
