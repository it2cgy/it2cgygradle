package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.star.uno.Exception;
import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.authentication.CacheUserUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.authentication.vo.CacheUserInfo;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.PVMTS.dao.EquipmentDao;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.EquipmentPO;
import com.yunengzhe.PVMTS.domain.po.LogsInfoPO;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipPoints;
import com.yunengzhe.PVMTS.util.PointUtil;


@Service("equipmentService")
public class EquipmentService {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);
	
	@Autowired
	private EquipmentDao equipmentDaoImpl;

	public List<EquipmentPO> getEquipmentList(Integer page, Integer pagesize,
			Integer powerStationId, String sourse, String third) {
		Map<String,Object> map = new HashMap<String,Object>();
		PageBean<EquipmentPO> pageBean = new PageBean<EquipmentPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		map.put("startRow", pageBean.getSimpleStartRow());
		map.put("pageSize", pageBean.getPageRecorders());
		map.put("third", third);
		if(powerStationId!=null&&powerStationId>0){
			map.put("powerstationid",powerStationId);
		}
		if(StringUtils.isNotBlank(sourse)){
			map.put("sourse", sourse);
		}
		List<EquipmentPO> findByMap = equipmentDaoImpl.findBySourse(map);
		String local = (String)HttpSessionUtil.getAttribute("local");
		for(EquipmentPO po:findByMap){
//			if(po.getUserId()!=null){
//				 CacheUserInfo  userInfo = CacheUserUtil.getCacheUserById(po.getUserId());
//				 if(userInfo!=null){
//					 po.setNickname(po.getNickname());
//				 }
//				 
//			}
			if("en_US".equals(local)){
				po.setPowerstationname(po.getEnglishname());
			}
			
		}
		return findByMap; 
	}

	public boolean addEquipment(EquipmentPO equipmentPO) {
		// TODO Auto-generated method stub
		return equipmentDaoImpl.insert(equipmentPO)>0;
	}

	public boolean updateEquipment(EquipmentPO equipmentPO) {
		// TODO Auto-generated method stub
		return equipmentDaoImpl.update4Selective(equipmentPO)>0;
	}

	public boolean deleteEquipment(Integer id) {
		// TODO Auto-generated method stub
		return equipmentDaoImpl.delete(id)>0;
	}

	public int getCount(Integer powerStationId, String sourse,String third) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(sourse)){
			map.put("sourse", sourse);
		}
		if(powerStationId!=null&&powerStationId>0){
			map.put("powerstationid",powerStationId);
		}
		if(third!=null&&!"".equals(third)){
			map.put("third",third);
		}
		return equipmentDaoImpl.getCount(map);
	}

	public EquipmentPO getEquipment(Integer id) {
		// TODO Auto-generated method stub
		return equipmentDaoImpl.get(id);
	}

	public List<EquipmentPO> checkEquipmentNumber(String number, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("equipmentNumber", number);
		if(id!=""&&id!=null){
			map.put("id", id);
		}
		return equipmentDaoImpl.checkEquipmentNumber(map);
	}
}
