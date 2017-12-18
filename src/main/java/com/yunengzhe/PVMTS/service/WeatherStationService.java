package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.WeatherStationDao;
import com.yunengzhe.PVMTS.domain.po.WeatherStationPO;


@Service("weatherStationService")
public class WeatherStationService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherStationService.class);
	
	@Autowired
	private WeatherStationDao weatherStationDaoImpl;
	/**
	 * 获取指定电站气象站数据
	 * @param powerStationId
	 * @return 
	 */
	public WeatherStationPO getWeatherStationInfo(String powerStationId) {
		WeatherStationPO WeatherStationPO = new WeatherStationPO();
		List<WeatherStationPO> list = weatherStationDaoImpl.findBy("farmid", powerStationId);
		if(list.size()!=0){
			WeatherStationPO = list.get(0);
		}
		return WeatherStationPO;
	}
	 
}
