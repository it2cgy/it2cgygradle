package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.SparepartMappingDao;
import com.yunengzhe.PVMTS.domain.po.SparepartMappingPO;



/**
 * @ClassName:SparepartMappingService
 * @Description:TODO(备品备件处理)
 * @author chenguiyang
 * @date 2017年6月7日下午1:49:31
 */
@Service("sparepartMappingService")
public class SparepartMappingService {

	private static final Logger logger = LoggerFactory.getLogger(SparepartMappingService.class);
	
	@Autowired
	private SparepartMappingDao sparepartMappingDaoImpl;
	
	
	/**
	 * @Title:getSparepart
	 * @Description:TODO(获取备品备件列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<SparepartMappingPO> getSparepart(String orderNum){
		List<SparepartMappingPO> listData = sparepartMappingDaoImpl.findBy("workorderNum", orderNum);
		logger.info("获取备品备件列表#"+listData);
		return listData;
	}
}
