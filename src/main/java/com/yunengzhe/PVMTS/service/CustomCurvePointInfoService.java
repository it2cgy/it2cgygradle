package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.CustomCurvePointInfoDao;
import com.yunengzhe.PVMTS.domain.po.CustomCurvePointInfoPO;


@Service("customCurvePointInfoService")
public class CustomCurvePointInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CustomCurvePointInfoService.class);
	
	@Autowired
	private CustomCurvePointInfoDao customCurvePointInfoDaoImpl;
	/**
	 * 插入测点
	 * @param list
	 */
	public void insertCustemCurvePoint(List<CustomCurvePointInfoPO> list) {
		customCurvePointInfoDaoImpl.insertBatch(list);
	}
	/**
	 * 删除测点
	 * @param pointIds
	 */
	public void deleteCurvePoint(String pointIds) {
		customCurvePointInfoDaoImpl.deleteByIds(pointIds);
	}
	/**
	 * 删除指定曲线的所有测点
	 * @param curveId
	 */
	public void deleteCurveAllPoint(String curveId) {
		CustomCurvePointInfoPO customCurvePointInfoPO = new CustomCurvePointInfoPO();
		customCurvePointInfoPO.setCurveId(Integer.valueOf(curveId));
		customCurvePointInfoDaoImpl.delete(customCurvePointInfoPO);
	}
}
