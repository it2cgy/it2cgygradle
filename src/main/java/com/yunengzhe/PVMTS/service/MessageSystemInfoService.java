package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.MessageSystemInfoDao;
import com.yunengzhe.PVMTS.dao.MessageUserInfoDao;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;


@Service("messageSystemInfoService")
public class MessageSystemInfoService {

	private static final Logger logger = LoggerFactory.getLogger(MessageSystemInfoService.class);
	
	@Autowired
	private MessageSystemInfoDao messageSystemInfoDaoImpl;
	
	@Autowired
	private MessageUserInfoDao messageUserInfoDaoImpl;

	public int getNoRead(int massageType,int massageStatus, int userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("massageType", massageType);
		map.put("massageStatus", massageStatus);
		map.put("userId", userId);
		return messageSystemInfoDaoImpl.getNoRead(map);
	}

	public boolean deleteMessage(int id, int userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userId", userId);
		return messageUserInfoDaoImpl.deleteMessage(map)==1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultListVO getMassageByType(int massageType,
			int userId, Integer page, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("massageType", massageType);
		map.put("userId", userId);
		PageBean<MessageSystemInfoPO> pageBean = new PageBean<MessageSystemInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		map.put("startRow", pageBean.getSimpleStartRow());
		map.put("pageSize", pageBean.getPageRecorders());
		ResultListVO vo = new ResultListVO();
		vo.setCounts(messageSystemInfoDaoImpl.getCountByMap(map));
		List<MessageSystemInfoPO> massageByTypeList = messageSystemInfoDaoImpl.getMassageByType(map);
		vo.setResultList(massageByTypeList);
		logger.debug(vo.toString());
		return vo;
	}

	public boolean updateRead(int id, int userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mid", id);
		map.put("userId", userId);
		return messageUserInfoDaoImpl.updateRead(map);
	}
	 
}
