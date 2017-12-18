package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.CustomCurveInfoDao;
import com.yunengzhe.PVMTS.domain.po.CustomCurveInfoPO;
import com.yunengzhe.PVMTS.domain.vo.CustomCurveInfoVO;


@Service("customCurveInfoService")
public class CustomCurveInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CustomCurveInfoService.class);
	
	@Autowired
	private CustomCurveInfoDao customCurveInfoDaoImpl;
	
	/**
	 * 添加自定义曲线
	 * @param customCurveInfoPO
	 * @return
	 */
	public CustomCurveInfoPO insertCustemCurve(CustomCurveInfoPO customCurveInfoPO) {
		customCurveInfoDaoImpl.insert(customCurveInfoPO);		
		return customCurveInfoPO;
	}
	/**
	 * 获取自定义曲线列表
	 * @param powerStationId
	 * @param searchName
	 * @return
	 */
	public List<CustomCurveInfoVO> getCustemCurveList(String powerStationId, String searchName) {
		Map map = new HashMap();
		map.put("powerStationId", powerStationId);
//		map.put(arg0, arg1);
		List<CustomCurveInfoVO> list = customCurveInfoDaoImpl.findCurveList(map);
		return list;
	}
	/**
	 * 删除自定义曲线
	 * @param curveId
	 * @return
	 */
	public void deletCustemCurve(String curveId) {
		customCurveInfoDaoImpl.delete(Integer.valueOf(curveId));
	}
}
