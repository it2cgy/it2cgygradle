package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.AnaloginputDao;
import com.yunengzhe.PVMTS.domain.po.AnaloginputPO;
import com.yunengzhe.PVMTS.domain.vo.MeasurePointInfo;


@Service("analoginputService")
public class AnaloginputService {

	private static final Logger logger = LoggerFactory.getLogger(AnaloginputService.class);
	
	@Autowired
	private AnaloginputDao analoginputDaoImpl;
	/**
	 * 获取对应id电表的所有测点
	 * @param ammeterId
	 * @return
	 */
	public List<MeasurePointInfo> getAmmeterMeasurePoints(String ammeterId) {
		Map map = new HashMap();
		map.put("equipmentcontainerId", ammeterId);
		map.put("equipmentcontainerTableid", 5);//设备类型：电表-5； 
		List<AnaloginputPO> list = analoginputDaoImpl.findByMap(map);
		List<MeasurePointInfo> measureList = new ArrayList<MeasurePointInfo>();
		for(int i=0;i<list.size();i++){
			AnaloginputPO AnaloginputPO = list.get(i);
			MeasurePointInfo MeasurePointInfo = new MeasurePointInfo();
			MeasurePointInfo.setEnglishName(AnaloginputPO.getName());
			MeasurePointInfo.setName(AnaloginputPO.getDescription());
			measureList.add(MeasurePointInfo);
		}
		return measureList;
	}
	/**
	 * 获取对应id逆变器的所有测点
	 * @param inverterId
	 * @return
	 */
	public List<MeasurePointInfo> getInverterMeasurePoints(String inverterId) {
		Map map = new HashMap();
		map.put("equipmentcontainerId", inverterId);
		map.put("equipmentcontainerTableid", 3);//设备类型：逆变器-3； 
		List<AnaloginputPO> list = analoginputDaoImpl.findByMap(map);
		List<MeasurePointInfo> measureList = new ArrayList<MeasurePointInfo>();
		for(int i=0;i<list.size();i++){
			AnaloginputPO AnaloginputPO = list.get(i);
			MeasurePointInfo MeasurePointInfo = new MeasurePointInfo();
			MeasurePointInfo.setEnglishName(AnaloginputPO.getName());
			MeasurePointInfo.setName(AnaloginputPO.getDescription());
			measureList.add(MeasurePointInfo);
		}
		return measureList;
	}
	 
}
