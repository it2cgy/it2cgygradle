package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ThirdContactDao;
import com.yunengzhe.PVMTS.domain.po.ThirdContactPO;



/**
 * @ClassName:ThirdContactService
 * @Description:TODO(第三方联系人处理)
 * @author chenguiyang
 * @date 2017年6月7日下午2:02:53
 */
@Service("thirdContactService")
public class ThirdContactService {

	private static final Logger logger = LoggerFactory.getLogger(ThirdContactService.class);
	
	@Autowired
	private ThirdContactDao thirdContactDaoImpl;
	
	
	/**
	 * @Title:thirdContact
	 * @Description:TODO(获取第三方联系人信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<ThirdContactPO> thirdContact(String orderNum){
		List<ThirdContactPO> thirdInfo = thirdContactDaoImpl.findBy("workorderNum", orderNum);
		return thirdInfo;
	}
	 
}
