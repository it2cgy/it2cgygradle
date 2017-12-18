package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.MailConfigDao;
import com.yunengzhe.PVMTS.domain.dto.MailConfigDTO;
import com.yunengzhe.PVMTS.domain.po.MailConfigPO;


@Service("mailConfigService")
public class MailConfigService {

	private static final Logger logger = LoggerFactory.getLogger(MailConfigService.class);
	private static boolean refresh = true;
	@Autowired
	private MailConfigDao mailConfigDaoImpl;
	
	
	/**
	 * @Title:getConfigInfo
	 * @Description:TODO(加载推送设置信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public MailConfigPO getConfigInfo(){
		List<MailConfigPO> listData = mailConfigDaoImpl
				.findAll();
		return listData.size()>0?listData.get(0):null;
	}
	public boolean isRefresh() {
		return refresh;
	}
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	
	/**
	 * @Title:update
	 * @Description:TODO(更新数据) 
	 * @param @param mail
	 * @return void
	 * @throws
	 */
	public void update(MailConfigDTO mail){
		logger.info("---------#更新推送设置#----------");
		MailConfigPO p = new MailConfigPO();
		p.setMailAddress(mail.getMailAddress());
		p.setServerPath(mail.getServerPath());
		p.setMailPassword(mail.getMailPassword());
		p.setMailPort(mail.getMailPort());
		p.setMailType(mail.getMailType());
		p.setPushTime(mail.getPushTime());
		p.setMailUsername(mail.getMailUsername());
		p.setUpdateTime(new Date());
		p.setUsessl(Integer.parseInt(mail.getUsessl()));
		if(mail.getId()!=null){
			p.setId(mail.getId());
			mailConfigDaoImpl.update(p);
		}else{
			p.setCreateTime(new Date());
			mailConfigDaoImpl.insert(p);
		}
		refresh=true;
	}
	 
}
