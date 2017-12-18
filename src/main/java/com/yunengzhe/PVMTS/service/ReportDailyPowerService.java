package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.ReportDailyPowerDao;
import com.yunengzhe.PVMTS.domain.po.ReportDailyPowerPO;


@Service("reportDailyPowerService")
public class ReportDailyPowerService {

	private static final Logger logger = LoggerFactory.getLogger(ReportDailyPowerService.class);
	
	@Autowired
	private ReportDailyPowerDao reportDailyPowerDaoImpl;
	
	public List<ReportDailyPowerPO> getReportDailyPower(){
		return reportDailyPowerDaoImpl.findBy("iscreate", "1");
	}
	 
}
