package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.InverterDao;
import com.yunengzhe.PVMTS.domain.po.InverterPO;
import com.yunengzhe.PVMTS.domain.vo.InverterDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.MeasurePointInfo;


@Service("inverterService")
public class InverterService {

	private static final Logger logger = LoggerFactory.getLogger(InverterService.class);
	
	@Autowired
	private InverterDao inverterDaoImpl;
	/**
	 * 获取指定逆变器基本信息
	 * @param inverterId
	 * @return
	 */
	public InverterPO getInverterInfo(String inverterId) {
		InverterPO InverterPO = inverterDaoImpl.get(inverterId);
		return InverterPO;
	}
	/**
	 * 获取指定电站逆变器个数
	 * @param powerStationId
	 * @return
	 */
	public Integer getInverterCounts(Integer powerStationId,int thirdUserid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("farmid", powerStationId);
		if(thirdUserid>0){
			map.put("thirdUserid", thirdUserid);
		}
		List<InverterPO> list = inverterDaoImpl.findByMap(map);
		return list.size();
	}
}
