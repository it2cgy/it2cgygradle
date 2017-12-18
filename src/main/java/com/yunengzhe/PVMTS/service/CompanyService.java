package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.CompanyDao;
import com.yunengzhe.PVMTS.domain.po.CompanyPO;

/**
 * @ClassName:CompanyService
 * @Description:TODO(公司处理service)
 * @author chenguiyang
 * @date 2017年6月14日上午9:28:18
 */
@Service("companyService")
public class CompanyService {

	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private CompanyDao companyDaoImpl;
	 
	/**
	 * @Title:getCompanies
	 * @Description:TODO(加载公司列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<CompanyPO> getCompanies(){
		logger.debug("加载公司列表");
		List<CompanyPO> listData = companyDaoImpl.findAll();
		return listData;
	}
}
