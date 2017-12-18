package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.LogsInfoDao;
import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipPoints;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.CacheManUtil;


@Service("logsInfoService")
public class LogsInfoService {

	private static final Logger logger = LoggerFactory.getLogger(LogsInfoService.class);
	
	@Autowired
	private LogsInfoDao logsInfoDaoImpl;
	/**
	 * 添加日志
	 * @param logsInfoPO
	 */
	public void addLogs(LogsInfoPO logsInfoPO) {
		logsInfoDaoImpl.insert(logsInfoPO);
	}
	/**
	 * 查看日志
	 * @param id
	 * @return
	 */
	public LogsInfoPO readLogs(String id) {
		LogsInfoPO logsInfoPO = logsInfoDaoImpl.get(id);
		return logsInfoPO;
	}
	/**
	 * 更新日志
	 * @param logsInfoPO
	 */
	public void updateLogs(LogsInfoPO logsBaseInfoVO) {
		logsInfoDaoImpl.update4Selective(logsBaseInfoVO);
		
	}
	/**
	 *  获取日志列表
	 * @param page
	 * @param pagesize
	 * @param searchstr
	 * @return
	 */
	public ResultListVO<LogsInfoPO> selectLogs(Integer page,Integer pagesize, String searchstr) {
		ResultListVO<LogsInfoPO> resultListVO = new ResultListVO<LogsInfoPO>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("searchstr", searchstr);
		PageBean<LogsInfoPO> pageBean = new PageBean<LogsInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		logsInfoDaoImpl.find4Page(pageBean, map, true, "create_time", SortBy.倒序);
		resultListVO.setResultList(pageBean.getObjList());
		resultListVO.setCounts(pageBean.getTotalRows());
		return resultListVO;
	}
	public ResultListVO<LogsInfoPO> selectLogsSearch(Integer page,
			Integer pagesize, String topic,String discription, String equipment,String equipmentId,
			String powerStationId, String category) {
		ResultListVO<LogsInfoPO> resultListVO = new ResultListVO<LogsInfoPO>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("topic", topic);
		map.put("discription", discription);
		map.put("equipmentName", equipment);
		map.put("equipmentType", equipmentId);
		map.put("powerStationId", powerStationId);
		map.put("category", category);
		PageBean<LogsInfoPO> pageBean = new PageBean<LogsInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		map.put("startRow", pageBean.getSimpleStartRow());
		map.put("pageSize", pageBean.getPageRecorders());
		List<LogsInfoPO> list = logsInfoDaoImpl.selectLogsSearch(map);
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<list.size();i++){
				LogsInfoPO lp = list.get(i);
				String key = PointUtil.getKey(lp.getEquipmentType(),lp.getEquipmentId());  
				EquipPoints equipPoints = (EquipPoints) CacheManUtil.getObjectFromJson(PointCacheService.CACHE_PREFIX_EQUIP, key,EquipPoints.class);
				lp.setEquipmentName(equipPoints.getNameEn());
				lp.setPowerStationName(lp.getEnglishname());
			}
		}
		resultListVO.setResultList(list);
		resultListVO.setCounts(logsInfoDaoImpl.selectLogsSearchCount(map));
		return resultListVO;
	}
	public boolean deleteLogs(Integer id) {
		return logsInfoDaoImpl.delete(id)>0;
	}
	public ResultListVO<LogsInfoPO> selectThirdLogsSearch(Integer page, Integer pagesize, String topic,String discription,
			String equipment, String equipmentId, String powerStationId, String category) {
		ResultListVO<LogsInfoPO> resultListVO = new ResultListVO<LogsInfoPO>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("topic", topic);
		map.put("discription", discription);
		map.put("equipmentName", equipment);
		map.put("equipmentType", equipmentId);
		map.put("powerStationId", powerStationId);
		map.put("category", category);
		PageBean<LogsInfoPO> pageBean = new PageBean<LogsInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		map.put("startRow", pageBean.getSimpleStartRow());
		map.put("pageSize", pageBean.getPageRecorders());
		List<LogsInfoPO> list = logsInfoDaoImpl.selectThirdLogsSearch(map);
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<list.size();i++){
				LogsInfoPO lp = list.get(i);
				String key = PointUtil.getKey(lp.getEquipmentType(),lp.getEquipmentId());  
				EquipPoints equipPoints = (EquipPoints) CacheManUtil.getObjectFromJson(PointCacheService.CACHE_PREFIX_EQUIP, key,EquipPoints.class);
				lp.setEquipmentName(equipPoints.getNameEn());
				lp.setPowerStationName(lp.getEnglishname());
			}
		}
		resultListVO.setResultList(list);
		resultListVO.setCounts(logsInfoDaoImpl.selectThirdLogsSearchCount(map));
		return resultListVO;
	}
	 
}
