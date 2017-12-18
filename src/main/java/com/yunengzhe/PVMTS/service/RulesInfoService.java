package com.yunengzhe.PVMTS.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.RulesInfoDao;
import com.yunengzhe.PVMTS.domain.dto.NoticeInfoDTO;
import com.yunengzhe.PVMTS.domain.dto.NoticeReaderDTO;
import com.yunengzhe.PVMTS.domain.dto.RulesInfoDTO;
import com.yunengzhe.PVMTS.domain.dto.RulesUpdateInfoDTO;
import com.yunengzhe.PVMTS.domain.po.NoticeInfoPO;
import com.yunengzhe.PVMTS.domain.po.RulesInfoPO;
import com.yunengzhe.PVMTS.domain.vo.NoticeReadInfoVO;
import com.yunengzhe.PVMTS.domain.vo.RulesInfoVO;
import com.yunengzhe.PVMTS.domain.vo.RulesReaderEditVO;
import com.yunengzhe.PVMTS.domain.vo.RulesReaderVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO; 
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.TimeUtil;


@Service("rulesInfoService")
public class RulesInfoService {

	private static final Logger logger = LoggerFactory.getLogger(RulesInfoService.class);
	
	@Autowired
	private RulesInfoDao rulesInfoDaoImpl;
	//添加制度
	public RulesInfoVO addRules(UserVO user, RulesInfoDTO RulesInfoDTO) {
		Date now = new Date();
		RulesInfoPO RulesInfoPO = new RulesInfoPO();
		RulesInfoPO.setCompanyId(user.getCompanyId());
		RulesInfoPO.setCreateTime(now);
		RulesInfoPO.setFileName(RulesInfoDTO.getFileName());
		RulesInfoPO.setFileUrl(RulesInfoDTO.getFileUrl());
		RulesInfoPO.setName(RulesInfoDTO.getName());
		RulesInfoPO.setPublisherId(user.getUserid());
		RulesInfoPO.setPublishStatus(Integer.valueOf(RulesInfoDTO.getStatus()));
		RulesInfoPO.setPublishTime(now);
		RulesInfoPO.setRemarks(RulesInfoDTO.getRemarks());
		RulesInfoPO.setRuleStatus(0);//默认有效状态；1表示删除
		RulesInfoPO.setVersion(RulesInfoDTO.getVersion());
		rulesInfoDaoImpl.insert(RulesInfoPO);
		RulesInfoVO RulesInfoVO = new RulesInfoVO();
		RulesInfoVO.setResult(true);
		return RulesInfoVO;
	}
	//更新发布状态
	public RulesInfoVO updatePublishStatus(String id,String status) {
		if(id.indexOf(",")!=-1){
			rulesInfoDaoImpl.updateToDelete(id);
		}else{
			RulesInfoPO RulesInfoPO = rulesInfoDaoImpl.get(id);
			if(!StringUtil.empty(status)){
				RulesInfoPO.setPublishStatus(Integer.valueOf(status));
				RulesInfoPO.setPublishTime(new Date());
			}
			RulesInfoPO.setUpdateTime(new Date());
			rulesInfoDaoImpl.update(RulesInfoPO);
		}
		return null;
	}
	
	//编辑铺数据
	public RulesReaderEditVO queryNoticeOne(String id) {
		RulesInfoPO RulesInfoPO = rulesInfoDaoImpl.get(Integer.valueOf(id));
		RulesReaderEditVO RulesReaderEditVO = new RulesReaderEditVO();
		RulesReaderEditVO.setId(RulesInfoPO.getId());
		RulesReaderEditVO.setVersion(RulesInfoPO.getVersion());
		RulesReaderEditVO.setPublishTime(TimeUtil.date2String(RulesInfoPO.getPublishTime(),"yyyy-MM-dd HH:mm:ss"));
		RulesReaderEditVO.setName(RulesInfoPO.getName());
		RulesReaderEditVO.setFileName(RulesInfoPO.getFileName());
		RulesReaderEditVO.setFileUrl(RulesInfoPO.getFileUrl());
		RulesReaderEditVO.setRemarks(RulesInfoPO.getRemarks());
		return RulesReaderEditVO;
	}
	//编辑保存、发布
	public RulesInfoVO updateRules(RulesUpdateInfoDTO RulesUpdateInfoDTO) {
		Date now = new Date();
		RulesInfoPO RulesInfoPO = rulesInfoDaoImpl.get(RulesUpdateInfoDTO.getId());
		RulesInfoPO.setFileName(RulesUpdateInfoDTO.getFileName());
		RulesInfoPO.setFileUrl(RulesUpdateInfoDTO.getFileUrl());
		RulesInfoPO.setName(RulesUpdateInfoDTO.getName());
		RulesInfoPO.setVersion(RulesUpdateInfoDTO.getVersion());
		RulesInfoPO.setRemarks(RulesUpdateInfoDTO.getRemarks());
		RulesInfoPO.setPublishStatus(Integer.valueOf(RulesUpdateInfoDTO.getStatus()));
		RulesInfoPO.setPublishTime(now);
		RulesInfoPO.setUpdateTime(now);
		rulesInfoDaoImpl.update(RulesInfoPO);
		return null;
	}
	//删除
	public RulesInfoVO deleteRules(String id) {
		Date now = new Date();
//		if(id.indexOf(",")!=-1){
			rulesInfoDaoImpl.updateToDelete(id);
//		}else{
//			RulesInfoPO RulesInfoPO = rulesInfoDaoImpl.get(id);
//			RulesInfoPO.setRuleStatus(1);//逻辑删除
//			RulesInfoPO.setUpdateTime(now);
//			rulesInfoDaoImpl.update(RulesInfoPO);
//		}
		return null;
	}
}
