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
import com.yunengzhe.PVMTS.dao.MeasurementBoxDao;
import com.yunengzhe.PVMTS.domain.po.MeasurementBoxPO;
import com.yunengzhe.PVMTS.domain.vo.longi.MeasurementBoxAndInverterVO;


@Service("measurementBoxService")
public class MeasurementBoxService {

	private static final Logger logger = LoggerFactory.getLogger(MeasurementBoxService.class);
	
	@Autowired
	private MeasurementBoxDao measurementBoxDaoImpl;
	/**
	 * 获取指定逆变器所对应的测量箱
	 * @param ids
	 * @return
	 */
	public List<MeasurementBoxAndInverterVO> getMeasureBoxInverter(String ids) {
		return measurementBoxDaoImpl.findMeasureInverter(ids);
	}
	/**
	 * 获取指定电站下的测量箱数量
	 * @param powerStationId
	 * @return
	 */
	public Integer getMeasureBoxCounts(Integer powerStationId,int thirdUserid) {
		Map map = new HashMap();
		map.put("framid", powerStationId);
		if(thirdUserid>0){
			map.put("thirdUserid", thirdUserid);
		}
		List<MeasurementBoxPO> list = measurementBoxDaoImpl.findByMap(map);
		return list.size();
	}
	 
}
