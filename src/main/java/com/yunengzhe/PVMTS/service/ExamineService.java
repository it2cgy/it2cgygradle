package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ExamineDao;
import com.yunengzhe.PVMTS.domain.po.ExaminePO;


@Service("examineService")
public class ExamineService {

	private static final Logger logger = LoggerFactory.getLogger(ExamineService.class);
	
	@Autowired
	private ExamineDao examineDaoImpl;
	 
	
	/**
	 * @Title:getExamineInfo
	 * @Description:TODO(获取电站体检检查项目) 
	 * @param @return
	 * @return List<ExaminePO>
	 * @throws
	 */
	public List<ExaminePO> getExamineInfo(){
		List<ExaminePO> result = examineDaoImpl.findBy("type",0);
		return result;
	}
}
