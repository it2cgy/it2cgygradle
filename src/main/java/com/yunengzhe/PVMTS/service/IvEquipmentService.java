package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.IvEquipmentDao;
import com.yunengzhe.PVMTS.dao.PointInfoDao;
import com.yunengzhe.PVMTS.domain.po.IvEquipmentPO;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.vo.IvEquipmentVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.util.PropertiesUtil;


@Service("ivEquipmentService")
public class IvEquipmentService {

	private static final Logger logger = LoggerFactory.getLogger(IvEquipmentService.class);
	public static final String CACHE_PREFIX_COMPANY=  PropertiesUtil.getString("datacompany")+"_COLLECT_POINT";
	
	@Autowired
	private IvEquipmentDao ivEquipmentDaoImpl;
	
	@Autowired
	private PointInfoDao pointInfoDaoImpl;
	
	@Autowired
	public StationMonitorService stationMonitorService; 
	/**
	 * @Title:getIvEquipments
	 * @Description:TODO(获取iv曲线列表) 
	 * @param @return
	 * @return ResultListVO<T>
	 * @throws
	 */
	public ResultListVO<IvEquipmentVO> getIvEquipments(int powerstationId,int page,int pageSize,String ids){
		ResultListVO<IvEquipmentVO>  results = new ResultListVO<IvEquipmentVO>();
		Map<String,Object> param = new HashMap<String,Object>();
		PageBean<IvEquipmentPO> pageBean = new PageBean<IvEquipmentPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		param.put("farmid",powerstationId);
		if(StringUtils.isNotBlank(ids)){
			param.put("isThird",true);
		}
	    ivEquipmentDaoImpl.find4Page(pageBean, param);
	    List<IvEquipmentPO> resultList = pageBean.getObjList();	
	    List<IvEquipmentVO> list = new ArrayList<IvEquipmentVO>();
	    if(resultList.size()>0){
	    	for(IvEquipmentPO po : resultList){
	    		IvEquipmentVO vo = new IvEquipmentVO();
	    		AnalogStructureDatas resultData = stationMonitorService.getMonitorEquipRealData(10,po.getId());
	    		List<AnalogStructureData>  result = resultData.getData();
	    		for(AnalogStructureData data : result){
	    			switch (data.getMeasuerName()) {
					case "DCV1":
						vo.setvVal(data.getValue());
						break;
					case "DCI1":
						vo.setaVal(data.getValue());					
						break;
					case "PVInputPower_1":
						vo.setwVal(data.getValue());
						break;
					case "TotalPowerGenerationPV1":
						vo.setTotalVal(data.getValue());
						break;
					case "DailyPowerGenerationPV1":
						vo.setDayVal(data.getValue());
						break;
					case "MonthPowerGenerationPV1":
						vo.setMonthVal(data.getValue());
						break;
					case "YearPowerGenerationPV1":
						vo.setYearVal(data.getValue());
						break;
					default:
						break;
					}
	    		}
	    		vo.setName(po.getName());
	    		list.add(vo);
	    	}
	    }
	    results.setResultList(list);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	 
	/**
	 * @Title:getIvEquipmentInfo
	 * @Description:TODO(获取通道详情) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public Map<String,Object> getIvEquipmentInfo(int containerId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("equipmentcontainerTableid", 10);
		param.put("equipmentcontainerId", containerId);
		List<PointInfoPO> result = pointInfoDaoImpl.findByMap(param);//获取测点列表  通道列表
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(result.size()>0){
			for(PointInfoPO po : result){
				int analogId = po.getId();
				MonitorRealData resultData = stationMonitorService.getMonitorRealData(analogId);
				resultMap.put(po.getMeasurementtypeName(),resultData.getValue());
			}
			
		}
		return resultMap;
	}
	/**
	 * @Title:getIvEquipments
	 * @Description:TODO(获取iv曲线列表) 
	 * @param @return
	 * @return ResultListVO<T>
	 * @throws
	 */
	public List<IvEquipmentVO> getIvEquipmentThird(int powerstationId,String ids){
//		String[] str = ids.split(",");
//		List<String> idsList = new ArrayList<String>();
//		for(String s : str){
//			idsList.add(s);
//		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("farmid",powerstationId);
		List<IvEquipmentPO> resultList  = ivEquipmentDaoImpl.findByMap(param);
	    List<IvEquipmentVO> list = new ArrayList<IvEquipmentVO>();
	    if(resultList.size()>0){
	    	for(IvEquipmentPO po : resultList){
	    		if(po.getId()==15 || po.getId()==16){
		    		IvEquipmentVO vo = new IvEquipmentVO();
		    		AnalogStructureDatas resultData = stationMonitorService.getMonitorEquipRealData(10,po.getId());
		    		List<AnalogStructureData>  result = resultData.getData();
		    		for(AnalogStructureData data : result){
		    			switch (data.getMeasuerName()) {
						case "DCV1":
							vo.setvVal(data.getValue());
							break;
						case "DCI1":
							vo.setaVal(data.getValue());					
							break;
						case "PVInputPower_1":
							vo.setwVal(data.getValue());
							break;
						case "TotalPowerGenerationPV1":
							vo.setTotalVal(data.getValue());
							break;
						case "DailyPowerGenerationPV1":
							vo.setDayVal(data.getValue());
							break;
						case "MonthPowerGenerationPV1":
							vo.setMonthVal(data.getValue());
							break;
						case "YearPowerGenerationPV1":
							vo.setYearVal(data.getValue());
							break;
						default:
							break;
						}
		    		}
		    		vo.setName(po.getName());
		    		list.add(vo);
	    		}
	    	}
	    }
		return list;
	}
	
	public List<IvEquipmentVO> getIvEquipmentDetail(int powerstationId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("farmid",powerstationId);
		List<IvEquipmentPO> resultList  = ivEquipmentDaoImpl.findByMap(param);
	    List<IvEquipmentVO> list = new ArrayList<IvEquipmentVO>();
	    if(resultList.size()>0){
	    	for(IvEquipmentPO po : resultList){
		    		IvEquipmentVO vo = new IvEquipmentVO();
		    		AnalogStructureDatas resultData = stationMonitorService.getMonitorEquipRealData(10,po.getId());
		    		List<AnalogStructureData>  result = resultData.getData();
		    		for(AnalogStructureData data : result){
		    			switch (data.getMeasuerName()) {
						case "DCV1":
							vo.setvVal(data.getValue());
							break;
						case "DCI1":
							vo.setaVal(data.getValue());					
							break;
						case "PVInputPower_1":
							vo.setwVal(data.getValue());
							break;
						case "TotalPowerGenerationPV1":
							vo.setTotalVal(data.getValue());
							break;
						case "DailyPowerGenerationPV1":
							vo.setDayVal(data.getValue());
							break;
						case "MonthPowerGenerationPV1":
							vo.setMonthVal(data.getValue());
							break;
						case "YearPowerGenerationPV1":
							vo.setYearVal(data.getValue());
							break;
						case "UnifiedPowerGenerationDaily":
							vo.setUnifiedPowerGenerationDaily(data.getValue());
							break;
						case "UnifiedPowerGenerationMonth":
							vo.setUnifiedPowerGenerationMonth(data.getValue());
							break;
						case "UnifiedPowerGenerationYear":
							vo.setUnifiedPowerGenerationYear(data.getValue());
							break;
						case "UnifiedGenerationGross":
							vo.setUnifiedGenerationGross(data.getValue());
							break;
						default:
							break;
		    		}
	    		}
	    		vo.setName(po.getName());
	    		vo.setId(po.getId());
	    		list.add(vo);
	    	}
	    }
		return list;
	}
}
