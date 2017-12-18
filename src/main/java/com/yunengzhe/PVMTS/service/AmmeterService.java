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
import com.yunengzhe.PVMTS.dao.AmmeterDao;
import com.yunengzhe.PVMTS.domain.po.AmmeterPO;
import com.yunengzhe.PVMTS.domain.po.InverterPO;


@Service("ammeterService")
public class AmmeterService {

	private static final Logger logger = LoggerFactory.getLogger(AmmeterService.class);
	
	@Autowired
	private AmmeterDao ammeterDaoImpl;
	/**
	 * 获取制定电站下各种电表的数量
	 * @param powerStationId 
	 * @return
	 */
	public Integer[] getAmmeterCounts(Integer powerStationId,int thirdUserid) {
		Map map = new HashMap();
		map.put("farmid", powerStationId);
		if(thirdUserid>0){
			map.put("thirdUserid", thirdUserid);
		}
		List<AmmeterPO> list = ammeterDaoImpl.findByMap(map);
		Integer[] arr = new Integer[3];
		int ammeter1 = 0;//直流电表
		int ammeter2 = 0;//单相交流电表
		int ammeter3 = 0;//三相交流电表
		for(int i=0;i<list.size();i++){
			AmmeterPO ammeterPO = list.get(i);
			if(Integer.valueOf(ammeterPO.getMeterType()+"")==1){
				ammeter1++;
			}
			if(Integer.valueOf(ammeterPO.getMeterType()+"")==2){
				ammeter2++;
			}
			if(Integer.valueOf(ammeterPO.getMeterType()+"")==3){
				ammeter3++;
			}
		}
		arr[0]=ammeter1;
		arr[1]=ammeter2;
		arr[2]=ammeter3;
		return arr;
	}
	/**
	 * 获取电表基本信息
	 * @param ammeterId
	 * @return 
	 */
	public AmmeterPO getAmmeterInfo(String ammeterId) {
		return ammeterDaoImpl.get(ammeterId);
	}
	 
}
