package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipPoints;
import com.yunengzhe.PVMTS.util.PointCacheUtil;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.PVMTS.util.TimeUtil;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.commons.util.PropertiesUtil;
import com.yunengzhe.PVMTS.domain.vo.AnalogStructureParam;
import com.yunengzhe.PVMTS.domain.vo.PointsData;
 
@Service("pointCacheService")
public class PointCacheService {

	public static final String DATA_COMPANY = PropertiesUtil.getString("datacompany");
	public static final String CACHE_PREFIX_POINT = PropertiesUtil.getString("datacompany")+"_COLLECT_POINT_ALL";
	public static final String CACHE_POINT_NAME = "POINTS";  
	public static final String CACHE_PREFIX_COMPANY=  PropertiesUtil.getString("datacompany")+"_COLLECT_POINT";

	private static final Logger logger = LoggerFactory.getLogger(PointCacheService.class);

	public static final String CACHE_PREFIX_EQUIP= PropertiesUtil.getString("datacompany")+"_EQUIP_POINTS";
 
	@Autowired
	private PointInfoService pointInfoService;


	public void cacheAllPoint(){ 
		long startTime = System.currentTimeMillis();
		logger.info("start cache equip**********************************time:"+TimeUtil.convertMillisecondToStr(startTime, "yyyy-MM-dd HH:mm:ss"));
		List<PointInfoPO> points = pointInfoService.getPoints();  
		Map<String,EquipPoints> equipPointsMap = new HashMap<String,EquipPoints>();//设备和测点 
	
		for(PointInfoPO po:points){
			if(po.getEquipmentcontainerTableid() != null || po.getEquipmentcontainerId() != null || po.getMeasurementtypeName()!=null || po.getId() !=null){

				String key = PointUtil.getKey(po.getEquipmentcontainerTableid(),po.getEquipmentcontainerId()); 


				EquipPoints equipP = equipPointsMap.get(key);
				if(equipP==null){
					equipP = new EquipPoints();
					equipP.setName(po.getEquipmentcontainerName());
					equipP.setNameEn(po.getEquipmentcontainerNameEn());
					equipP.setEquipmentcontainerId(po.getEquipmentcontainerId());
					equipP.setEquipmentcontainerTableid(po.getEquipmentcontainerTableid());
					equipP.setPoints(new ArrayList<PointInfoPO>());
					equipPointsMap.put(key, equipP);
				} 
				equipP.getPoints().add(po);

			}
		}
	 	Collection<EquipPoints> equipDatas = equipPointsMap.values();	
	 	for(EquipPoints equipData:equipDatas){ 
	 		String key = PointUtil.getKey(equipData.getEquipmentcontainerTableid(),equipData.getEquipmentcontainerId());  
		 	CacheManUtil.addObj2Json(CACHE_PREFIX_EQUIP, key, equipData);
	 	} 
		long endTime = System.currentTimeMillis();
		logger.info("end cache equip**********************************time:"+TimeUtil.convertMillisecondToStr(endTime, "yyyy-MM-dd HH:mm:ss"));
		logger.info("end-start:"+(endTime-startTime)); 
	} 
	
	public EquipPoints getEquipPoints(String key){
		EquipPoints result  = CacheManUtil.getObjectFromJson(CACHE_PREFIX_EQUIP, key, EquipPoints.class);
		return result;
	}
	
	public void cacheAllPointByEquip(){ 
		long startTime = System.currentTimeMillis();
		logger.info("start cache points**********************************time:"+TimeUtil.convertMillisecondToStr(startTime, "yyyy-MM-dd HH:mm:ss"));
		List<PointInfoPO> points = pointInfoService.getPoints();
		PointsData pointsData = new PointsData(); 
		Map<String,String> equipPowerMap = new HashMap<String,String>();//设备和电站
		Map<String,String> pointsMap = new HashMap<String,String>();//设备和测点
		Map<String,AnalogStructureParam> analogStructureMap = new HashMap<String, AnalogStructureParam>();//所有测量类型
		//Map<String,List<PointBaseInfoPO>> equipmentcontainer = new HashMap<String, List<PointBaseInfoPO>>();
		Map<String,PointInfoPO> pointInfoMap = new HashMap<String,PointInfoPO>();
		for(PointInfoPO po:points){
			if(po.getEquipmentcontainerTableid() != null || po.getEquipmentcontainerId() != null || po.getMeasurementtypeName()!=null || po.getId() !=null){

				String key = PointCacheUtil.getKey(po.getEquipmentcontainerTableid(),po.getEquipmentcontainerId());
				String point_key = PointCacheUtil.getKey(po.getEquipmentcontainerTableid(),po.getEquipmentcontainerId(),po.getMeasurementtypeName());
				if(po.getId()==null){
					logger.error(point_key+"getId is null");
				}
				pointsMap.put(point_key, ""+po.getId());
				if(!equipPowerMap.containsKey(key) && po.getPowerStationId()!=null){
					equipPowerMap.put(key, po.getPowerStationId().toString());
				} 
				
				AnalogStructureParam analogStructure = analogStructureMap.get(key);
				if(analogStructure==null){ 
					analogStructure = new AnalogStructureParam();
					analogStructure.setEquipcontainerTableID(po.getEquipmentcontainerTableid());
					analogStructure.setEquipcontainerID(po.getEquipmentcontainerId());
					analogStructure.setMeasuerNames(po.getMeasurementtypeName());
					analogStructureMap.put(key, analogStructure);
				}else{
					analogStructure.setMeasuerNames(analogStructure.getMeasuerNames()+","+po.getMeasurementtypeName());
				}
				
				pointInfoMap.put(""+po.getId(), po);
			}
		}
		pointsData.setPointInfoMap(pointInfoMap);
		pointsData.setPointMap(pointsMap);
		pointsData.setEquipPowerMap(equipPowerMap);
		pointsData.setAnalogStructureList(new ArrayList<AnalogStructureParam>(analogStructureMap.values()));
		CacheManUtil.addObj2Json(CACHE_PREFIX_POINT,CACHE_POINT_NAME, pointsData);
		//CacheManUtil.addObj2Json(CACHE_PREFIX_POINT,CACHE_POWER_NAME, powerStationData);
		
		long endTime = System.currentTimeMillis();
		logger.info("end cache points**********************************time:"+TimeUtil.convertMillisecondToStr(endTime, "yyyy-MM-dd HH:mm:ss"));
		logger.info("end-start:"+(endTime-startTime)); 
	}

	public PointsData getAllPoint(){
		PointsData points = CacheManUtil.getObjectFromJson(CACHE_PREFIX_POINT,CACHE_POINT_NAME, PointsData.class);
		return points;
	}
}
