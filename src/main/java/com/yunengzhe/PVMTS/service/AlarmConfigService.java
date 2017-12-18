package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.AlarmConfigDao;
import com.yunengzhe.PVMTS.domain.dto.UserSearchKey;
import com.yunengzhe.PVMTS.domain.dto.alarm.AddAlarmConfigDTO;
import com.yunengzhe.PVMTS.domain.po.AlarmConfigPO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserListVO;


@Service("alarmConfigService")
public class AlarmConfigService {

	private static final Logger logger = LoggerFactory.getLogger(AlarmConfigService.class);
	
	@Autowired
	private AlarmConfigDao alarmConfigDaoImpl;
	
	public void delConfig(String ids){
		if(StringUtils.isBlank(ids)){
			return ;
		}
		alarmConfigDaoImpl.deleteByIds(ids);
	}
	public ResultListVO<AlarmConfigPO> getConfigList(int powerStationId,int page,int pageSize,String equiptype,String measurementtype, String equipnameKey){
		ResultListVO<AlarmConfigPO> results = new ResultListVO<AlarmConfigPO>();
		PageBean<AlarmConfigPO> pageBean = new PageBean<AlarmConfigPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(equiptype)){
			param.put("equiptype", equiptype);
		}

		if(StringUtils.isNotBlank(measurementtype)){
			param.put("measurementtype", measurementtype);
		}

		if(StringUtils.isNotBlank(equipnameKey)){
			param.put("equipnamekey", equipnameKey);
		}

		param.put("powerStationId", powerStationId);
		alarmConfigDaoImpl.find4Page(pageBean, param,"create_date",SortBy.倒序);
		List<AlarmConfigPO> userList = pageBean.getObjList();
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<userList.size();i++){
				AlarmConfigPO cv = userList.get(i);
				cv.setEquipmentcontainerName(cv.getEquipmentcontainerNameEn());
				cv.setMeasurePointDiscription(cv.getPointEnglishName());
			}
		}
		results.setResultList(userList);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	
	public int addConfig(AddAlarmConfigDTO addConfigDTO,int userid){
		List<AlarmConfigPO> configPOList = new ArrayList<AlarmConfigPO>(); 
		Date dateNow = new Date();
		for(Integer pointid :addConfigDTO.getPointids()){  
			AlarmConfigPO configPO = new AlarmConfigPO();
			configPO.setPointId(pointid);
			configPO.setType(addConfigDTO.getType());
			configPO.setValueOne(addConfigDTO.getValueOne());
			configPO.setValueThree(addConfigDTO.getValueThree());
			configPO.setValueTwo(addConfigDTO.getValueTwo());
			configPO.setMessage(addConfigDTO.getMessage());
			configPO.setCreateDate(dateNow);
			configPO.setUpdateDate(dateNow);
			configPO.setForbidden(0);
			configPO.setUserId(userid);
			
			configPOList.add(configPO); 
		}
		alarmConfigDaoImpl.insertBatch(configPOList);
		return configPOList.size();
	}
	 
}
