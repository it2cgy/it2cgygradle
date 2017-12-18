package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

import net.sf.json.JSONObject;

import com.yunengzhe.PVMTS.dao.PointInfoDao;
import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PointBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PointInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.util.StringUtil;


@Service("pointInfoService")
public class PointInfoService {

	private static final Logger logger = LoggerFactory.getLogger(PointInfoService.class);
	
	@Autowired
	private PointInfoDao pointInfoDaoImpl;
	
	
	public List<PointInfoPO> getPoints(){
		return pointInfoDaoImpl.findAll();
	}
	/**
	 * 获取指定电站的所有测点列表
	 * @param powerStationId
	 * @param pagesize 
	 * @param page 
	 * @param measureName 
	 * @param checkedPoint 表示已经被选中测点的analoginputId数组
	 * @return
	 */
	public ResultListVO getPowerStationAllPoint(String powerStationId, String measureName, String checkedPoint, Integer page, Integer pagesize) {
		ResultListVO resultListVO = new ResultListVO();
		PageBean <PointInfoPO> pageBean = new PageBean <PointInfoPO>();
		pageBean.setCurrentPage(Integer.valueOf(page));
		pageBean.setPageRecorders(Integer.valueOf(pagesize));
		Map map = new HashMap();
		map.put("powerStationId", powerStationId);
		map.put("measureName", measureName);
		pointInfoDaoImpl.find4Page(pageBean, map, true);
		List<PointInfoPO> list =  pageBean.getObjList();
		List<PointInfoVO> result = new ArrayList<PointInfoVO>();
		for(int i=0;i<list.size();i++){
			PointInfoPO pointInfoPO = list.get(i);
			//存在已经被选中的测点
			if(!StringUtil.empty(checkedPoint)){
				String[]array = checkedPoint.split(",");
				Boolean flag = false;
				for(int j=0;j<array.length;j++){
					//通过analoginputId判断该测点是否已经被选中
					if(pointInfoPO.getId()==Integer.valueOf(array[j])){
						flag=true;
					}
				}
				//只返回没有被选中的数据
				if(!flag){
					PointInfoVO pointInfoVO = new PointInfoVO();
					pointInfoVO.setId(pointInfoPO.getId());
					pointInfoVO.setMeasurementtypeDescription(pointInfoPO.getMeasurementtypeDescription());
					pointInfoVO.setEquipmentcontainerName(pointInfoPO.getEquipmentcontainerName());
					result.add(pointInfoVO);
				}
			}else{
				PointInfoVO pointInfoVO = new PointInfoVO();
				pointInfoVO.setId(pointInfoPO.getId());
				pointInfoVO.setMeasurementtypeDescription(pointInfoPO.getMeasurementtypeDescription());
				pointInfoVO.setEquipmentcontainerName(pointInfoPO.getEquipmentcontainerName());
				result.add(pointInfoVO);
			}
		}
		resultListVO.setCounts(pageBean.getTotalRows());
		resultListVO.setResultList(result);
		return resultListVO;
	}
	/**
	 * 获取指定电站的所有测点列表(不分页)
	 * @param powerStationId
	 * @return
	 */
	public List<PointBaseInfoVO> getPowerStationAllPointNOpage(String powerStationId) {
		List<PointInfoPO> list = pointInfoDaoImpl.findBy("powerStationId", powerStationId);
		List<PointBaseInfoVO> result = new ArrayList<PointBaseInfoVO>();
		for(int i=0;i<list.size();i++){
			PointInfoPO pointInfoPO = list.get(i);
			PointBaseInfoVO pointBaseInfoVO = new PointBaseInfoVO();
			pointBaseInfoVO.setId(pointInfoPO.getId());
			pointBaseInfoVO.setEquipmentcontainerId(pointInfoPO.getEquipmentcontainerId());
			pointBaseInfoVO.setEquipmentcontainerName(pointInfoPO.getEquipmentcontainerName());
			pointBaseInfoVO.setEquipmentcontainerTableid(pointInfoPO.getEquipmentcontainerTableid());
			pointBaseInfoVO.setMeasurementtypeId(pointInfoPO.getMeasurementtypeId());
			pointBaseInfoVO.setMeasurementtypeName(pointInfoPO.getMeasurementtypeName());
			
			pointBaseInfoVO.setName(pointInfoPO.getEquipmentcontainerName()+"#"+pointInfoPO.getMeasurementtypeDescription());
			result.add(pointBaseInfoVO);
		}
		return result;
	}
	/**
	 * 获取测点信息
	 * @param analoginputId
	 */
	public List<PointInfoPO> getPointInfo(String analoginputId) {
		List<PointInfoPO> list = new ArrayList<PointInfoPO>();
		if(analoginputId.indexOf(",")!=-1){
			list = pointInfoDaoImpl.findByIds(analoginputId);
		}else{
			PointInfoPO pointInfo = pointInfoDaoImpl.get(analoginputId);
			list.add(pointInfo);
		}
		return list;
	}
	/**
	 * 获取测点信息
	 * @param analoginputId
	 */
	public List<PointInfoPO> getPoint(String analoginputId) {
		List<PointInfoPO> list = new ArrayList<PointInfoPO>();
		if(analoginputId.indexOf(",")!=-1){
			list = pointInfoDaoImpl.findByIds(analoginputId);
		}else{
			PointInfoPO pointInfo = pointInfoDaoImpl.get(analoginputId);
			list.add(pointInfo);
		}
		return list;
	}
	
	/**
	 * @Title:getPointListByTableAndContainer
	 * @Description:TODO(获取设备下的所有测点数据) 
	 * @param @param equipcontainerTableID
	 * @param @param equipcontainerID
	 * @param @return
	 * @return List<PointInfoPO>
	 * @throws
	 */
	public List<PointInfoPO> getPointListByTableAndContainer(String equipcontainerTableID,String equipcontainerID){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("equipmentcontainerId", equipcontainerID);
		map.put("equipmentcontainerTableid", equipcontainerTableID);
		List<PointInfoPO> list  = pointInfoDaoImpl.findByMap(map); 
		return list;
	}
	/**
	 * 获取测点信息
	 * @param analoginputId
	 */
	public PointInfoPO getPointInfo(String measuerName,Integer equipcontainerTableID,Integer equipcontainerID) {
		 	 
		Map map = new HashMap();
		map.put("measurementtypeName", measuerName);
		map.put("equipmentcontainerId", equipcontainerID);
		map.put("equipmentcontainerTableid", equipcontainerTableID);
		List<PointInfoPO> list  = pointInfoDaoImpl.findByMap(map); 
		if(list.size()<=0)
			return null;
		return list.get(0);
	}
	
	/**
	 * 获取指定电站的所有设备
	 * @param powerStationId
	 * @return
	 */
	public List<EquipmentInfoVO> getPowerStationEquioments(Integer powerStationId) {
		List<PointInfoPO> list = new ArrayList<PointInfoPO>();
		list = pointInfoDaoImpl.getPowerStationEquioments(powerStationId);
		List<EquipmentInfoVO> result = new ArrayList<EquipmentInfoVO>();
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				PointInfoPO pointInfoPO = list.get(i);
				EquipmentInfoVO EquipmentInfoVO = new EquipmentInfoVO();
				EquipmentInfoVO.setEquipmentId(pointInfoPO.getEquipmentcontainerId());
				EquipmentInfoVO.setEquipmentcontainerName(pointInfoPO.getEquipmentcontainerName());
				EquipmentInfoVO.setEquipmentTableId(pointInfoPO.getEquipmentcontainerTableid());
				result.add(EquipmentInfoVO);
			}
		}
		return result;
	}
	public List<EquipmentInfoVO> getEquioments(String powerStationId, String equipmentType) {
		List<EquipmentInfoVO> list = new ArrayList<EquipmentInfoVO>();
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("powerStationId", powerStationId);
		map.put("equipmentcontainerTableid", equipmentType);
		list = pointInfoDaoImpl.getEquioments(map);
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<list.size();i++){
				EquipmentInfoVO ev = list.get(i);
				ev.setEquipmentcontainerName(ev.getEquipmentcontainerNameEn());
			}
		}
		return list;
	}
	public List<PointInfoPO> getPointsNOpage(String powerStationId, String equipmentcontainerTableid,
			String equipmentcontainerId, String measurementtypeName) {
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("powerStationId", powerStationId);
		map.put("equipmentcontainerTableid", equipmentcontainerTableid);
		map.put("equipmentcontainerId", equipmentcontainerId);
		map.put("searchPoint", measurementtypeName);
		List<PointInfoPO> list = pointInfoDaoImpl.findByMap(map, true,"equipmentcontainer_name",SortBy.正序);
		return list;
	}
	public List<PointInfoPO> getOnePointInequipments(int powerstationId, int equipmentType, int pointType) {
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("powerStationId", powerstationId);
		map.put("equipmentcontainerTableid", equipmentType);
		map.put("measurementtypeId", pointType);
		return pointInfoDaoImpl.findByMap(map);
	}
}
