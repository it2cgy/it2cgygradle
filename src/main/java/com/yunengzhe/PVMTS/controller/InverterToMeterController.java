package com.yunengzhe.PVMTS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.service.InverterToMeterService;

@Controller
@RequestMapping("/inverterToMeter")
public class InverterToMeterController {
	
	private static final Logger logger = LoggerFactory.getLogger(InverterToMeterController.class);
	
	@Autowired
	private InverterToMeterService inverterToMeterService;

}
