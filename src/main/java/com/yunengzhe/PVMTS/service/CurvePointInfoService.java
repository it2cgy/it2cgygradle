package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.CurvePointInfoDao;
import com.yunengzhe.PVMTS.domain.po.CurvePointInfoPO;


@Service("curvePointInfoService")
public class CurvePointInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CurvePointInfoService.class);
	
	@Autowired
	private CurvePointInfoDao curvePointInfoDaoImpl;
	/**
	 * 插入测点
	 * @param list
	 */
	public void addCurvePoint(List<CurvePointInfoPO> list) {
		Date date = new Date();
		for(int i=0;i<list.size();i++){
			CurvePointInfoPO curvePointInfoPO = list.get(i);
			curvePointInfoPO.setCreateTime(date);
			curvePointInfoPO.setUpdateTime(date);
		}
		curvePointInfoDaoImpl.insertBatch(list);
	}
	/**
	 * 删除指定曲线下的所有测点
	 * @param curveId
	 */
	public void deleteCurvePoint(Integer curveId) { 
		if(curveId==null)
			return;
		
		CurvePointInfoPO curvePointInfoPO = new CurvePointInfoPO();
		curvePointInfoPO.setCurveId(curveId);
		curvePointInfoDaoImpl.delete(curvePointInfoPO);
	}
	public List<CurvePointInfoPO> getCurvePointDetail(Integer curveId) {
		return curvePointInfoDaoImpl.findBy("curveId", curveId);
	}
	 
}
