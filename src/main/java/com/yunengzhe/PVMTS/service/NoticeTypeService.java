package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.NoticeTypeDao;
import com.yunengzhe.PVMTS.domain.po.NoticeTypePO;
import com.yunengzhe.PVMTS.domain.vo.NoticeTypeVO;


@Service("noticeTypeService")
public class NoticeTypeService {

	private static final Logger logger = LoggerFactory.getLogger(NoticeTypeService.class);
	
	@Autowired
	private NoticeTypeDao noticeTypeDaoImpl;
	
	//获取公告类型
	public List<NoticeTypeVO> queryNoticeType() {
		List<NoticeTypePO> list = noticeTypeDaoImpl.findAll();
		List<NoticeTypeVO> list1 = new ArrayList<NoticeTypeVO>(); 
		for(int i = 0;i<list.size();i++){
			NoticeTypeVO NoticeTypeVO = new NoticeTypeVO();
			NoticeTypeVO.setId(list.get(i).getId());
			NoticeTypeVO.setName(list.get(i).getName());
			list1.add(NoticeTypeVO);
		}
		return list1;
	}
	 
}
