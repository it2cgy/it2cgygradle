package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.PushMailDao;
import com.yunengzhe.PVMTS.domain.po.PushMailPO;
import com.yunengzhe.PVMTS.domain.vo.UserEmailVO;


@Service("pushMailService")
public class PushMailService {

	private static final Logger logger = LoggerFactory.getLogger(PushMailService.class);
	
	@Autowired
	private PushMailDao pushMailDaoImpl;
	 
	
	public void insert(PushMailPO p){
		pushMailDaoImpl.insert(p);
	}
	
	public void update(PushMailPO p){
		pushMailDaoImpl.update(p);
	}
	
	public PushMailPO getPushMail(Integer roleId){
		PushMailPO result = pushMailDaoImpl.findUniqueBy("roleId", roleId);
		return result;
	}
	
	public List<UserEmailVO> getUserEmail(){
		return pushMailDaoImpl.getUserEmail();
	}
	
	
}
