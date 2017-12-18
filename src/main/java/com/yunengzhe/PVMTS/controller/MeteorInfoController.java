package com.yunengzhe.PVMTS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.MeteorInfoPO;
import com.yunengzhe.PVMTS.service.MeteorInfoService;

@Controller
@RequestMapping("/meteorInfo")
public class MeteorInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(MeteorInfoController.class);
	
	@Autowired
	private MeteorInfoService meteorInfoService;
	@RequestMapping(value="/getMeteor")
	public MeteorInfoPO getMeteor(int powerStationId){
		return meteorInfoService.getMeteor(powerStationId);
	}
}
