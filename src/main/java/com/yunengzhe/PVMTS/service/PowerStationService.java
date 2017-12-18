package com.yunengzhe.PVMTS.service;

import java.math.BigDecimal;
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

import com.yunengzhe.PVMTS.dao.AmmeterDao;
import com.yunengzhe.PVMTS.dao.GenerationMonthDao;
import com.yunengzhe.PVMTS.dao.InverterDao;
import com.yunengzhe.PVMTS.dao.PowerStationDao;
import com.yunengzhe.PVMTS.dao.RoleAndPowerDao;
import com.yunengzhe.PVMTS.domain.dto.PowerstationDTO;
import com.yunengzhe.PVMTS.domain.po.AmmeterPO;
import com.yunengzhe.PVMTS.domain.po.GenerationMonthPO;
import com.yunengzhe.PVMTS.domain.po.InverterPO;
import com.yunengzhe.PVMTS.domain.po.PowerStationPO;
import com.yunengzhe.PVMTS.domain.po.RoleAndPowerPO;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.vo.AmmeterBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentTypeVO;
import com.yunengzhe.PVMTS.domain.vo.InverterBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationListVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.TimeUtil;


@Service("powerStationService")
public class PowerStationService {

	private static final Logger logger = LoggerFactory.getLogger(PowerStationService.class);
	
	@Autowired
	private PowerStationDao powerStationDaoImpl;
	@Autowired
	private AmmeterDao ammeterDaoImpl;
	@Autowired
	private InverterDao inverterDaoImpl;
	@Autowired
	private GenerationMonthDao generationMonthDaoImpl;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleAndPowerDao roleAndPowerDaoImpl;
	@Autowired
	private AttachmentService attachmentService;
	/**
	 * 根据角色id查找电站，用户角色id和电站关联
	 * @param roleid
	 * @return
	 */
	public List<PowerStationBaseInfoVO> getPowerStationByRole(int roleid){
		List<PowerStationBaseInfoVO> results = new ArrayList<PowerStationBaseInfoVO>();
		List<RoleAndPowerPO>  rolePowerList = roleAndPowerDaoImpl.findBy("roleId",roleid);
		for(RoleAndPowerPO rpPO:rolePowerList){
			int stationID = rpPO.getPowerstationId();
			PowerStationBaseInfoVO stationVO = this.getPowerStationInfo(""+stationID); 
			if(stationVO!=null){
				results.add(stationVO);
			}
		}
		
		return results;
	}
	
	/**
	 * 获取电站基本信息列表（不包含实时信息）
	 * @param companyId
	 * @return
	 */
	public List<PowerStationBaseInfoVO> getPowerStationBaseInfo(String ids,String companyId,String searchstr,String roleid) {
		List<PowerStationBaseInfoVO> result = new ArrayList<PowerStationBaseInfoVO>();
		Map<String,Object> map = new HashMap<String,Object>();
		List<PowerStationPO> list = new ArrayList<PowerStationPO>();
		if(StringUtil.empty(searchstr)){
			searchstr = null;
		}
		if(StringUtils.isBlank(ids)){
			if(roleid!=null){ 
				map.put("roleid", roleid);
			}
			map.put("companyId", companyId);
			map.put("searchstr", searchstr);
			map.put("effectivity", 1);//1表示电站有效
			list = powerStationDaoImpl.findByMap(map, true);
		}else{
			list = powerStationDaoImpl.findByIds(ids);
		}
		String local = (String)HttpSessionUtil.getAttribute("local");
		
		for(int i=0;i<list.size();i++){
			PowerStationPO PowerStationPO = list.get(i);
			PowerStationBaseInfoVO BaseInfo = new PowerStationBaseInfoVO();
			if("en_US".equals(local)){
				BaseInfo.setName(PowerStationPO.getEnglishname());
			}else{
				BaseInfo.setName(PowerStationPO.getName());
			}
			BaseInfo.setId(PowerStationPO.getId());
			BaseInfo.setCode(PowerStationPO.getCode());
			BaseInfo.setInstallCapacity(PowerStationPO.getInstallCapacity());
			BaseInfo.setLat(PowerStationPO.getLat());
			BaseInfo.setLng(PowerStationPO.getLng());
			BaseInfo.setManager(PowerStationPO.getInvestFirmContactName());
			BaseInfo.setAddress(PowerStationPO.getProvinceName()+PowerStationPO.getCityName()+
					PowerStationPO.getCountyName()+PowerStationPO.getLocation());
			BaseInfo.setInvestFirmName(PowerStationPO.getInvestFirmName());
			BaseInfo.setStartPruduceTime(PowerStationPO.getStartPruduceTime());
			BaseInfo.setImgUrl(PowerStationPO.getImage());
			BaseInfo.setMonitoring(PowerStationPO.getMonitoring());
			result.add(BaseInfo);
		}
		return result;
	}
	
	/**
	 * 获取点表基本信息列表（不包含实时信息）
	 * @param powerStationId
	 * @return
	 */
	public List<AmmeterBaseInfoVO> getAmmeterBaseInfo(String powerStationId) {
		List<AmmeterPO> list = ammeterDaoImpl.findBy("farmid", powerStationId);
		List<AmmeterBaseInfoVO> result = new ArrayList<AmmeterBaseInfoVO>();
		for(int i=0;i<list.size();i++){
			AmmeterPO AmmeterPO = list.get(i);
			AmmeterBaseInfoVO AmmeterBaseInfoVO = new AmmeterBaseInfoVO();
			AmmeterBaseInfoVO.setId(AmmeterPO.getId());
			AmmeterBaseInfoVO.setModel(AmmeterPO.getModel());
			AmmeterBaseInfoVO.setName(AmmeterPO.getName());
			AmmeterBaseInfoVO.setMeterType(AmmeterPO.getMeterType());
			if(StringUtil.empty(AmmeterPO.getSerialNumber())){
				AmmeterBaseInfoVO.setSerialNumber(AmmeterPO.getName());
			}else{
				AmmeterBaseInfoVO.setSerialNumber(AmmeterPO.getSerialNumber());
			}
			result.add(AmmeterBaseInfoVO);
		}
		return result;
	}

	/**
	 * 获取逆变器基本信息列表（不包含实时数据）
	 * @param powerStationId
	 * @return
	 */
	public List<InverterBaseInfoVO> getInverterBaseInfo(String powerStationId) {
		List<InverterPO> list = inverterDaoImpl.findBy("farmid", powerStationId);
		List<InverterBaseInfoVO> result = new ArrayList<InverterBaseInfoVO>();
		for(int i=0;i<list.size();i++){
			InverterPO InverterPO = list.get(i);
			InverterBaseInfoVO InverterBaseInfoVO = new InverterBaseInfoVO();
			InverterBaseInfoVO.setId(InverterPO.getId());
			InverterBaseInfoVO.setModel(InverterPO.getModel());
			InverterBaseInfoVO.setName(InverterPO.getName());
			if(StringUtil.empty(InverterPO.getSerialNumber())){
				InverterBaseInfoVO.setSerialNumber(InverterPO.getName());
			}else{
				InverterBaseInfoVO.setSerialNumber(InverterPO.getSerialNumber());
			}
			result.add(InverterBaseInfoVO);
		}
		return result;
	}
	/**
	 * 获取指定电站id的电站基本信息
	 * @param powerStationId
	 * @return
	 */
	public PowerStationBaseInfoVO getPowerStationInfo(String powerStationId) {
		if(StringUtils.isBlank(powerStationId)){
			return null;
		}
		
		PowerStationPO PowerStationPO = powerStationDaoImpl.get(powerStationId);
		if(PowerStationPO==null){
			return null;
		}
		PowerStationBaseInfoVO BaseInfo = new PowerStationBaseInfoVO();
		BaseInfo.setHavebuzhi(PowerStationPO.getHavebuzhi());
		BaseInfo.setHavetuopu(PowerStationPO.getHavetuopu());
		BaseInfo.setId(PowerStationPO.getId());
		BaseInfo.setInstallCapacity(PowerStationPO.getInstallCapacity());
		BaseInfo.setLat(PowerStationPO.getLat());
		BaseInfo.setLng(PowerStationPO.getLng());
		BaseInfo.setManager(PowerStationPO.getMaintainerName());
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			BaseInfo.setName(PowerStationPO.getEnglishname());
		}else{
			BaseInfo.setName(PowerStationPO.getName());
		}
		BaseInfo.setAddress(PowerStationPO.getProvinceName()+PowerStationPO.getCityName()+
				PowerStationPO.getCountyName()+PowerStationPO.getLocation());
		BaseInfo.setStartPruduceTime(PowerStationPO.getStartPruduceTime());
		BaseInfo.setManagerMobile(PowerStationPO.getInvestFirmContactMobile());
		BaseInfo.setImgUrl(PowerStationPO.getImage());
		BaseInfo.setDescription(PowerStationPO.getDescription());
		BaseInfo.setMonitoring(PowerStationPO.getMonitoring());
		return BaseInfo;
	}
	/**
	 * 获取指定电站id的电站基本信息
	 * @param powerStationId
	 * @return
	 */
	public PowerStationPO getPowerStationdetailInfo(String powerStationId) {
		if(StringUtils.isBlank(powerStationId)){
			return null;
		}
		PowerStationPO PowerStationPO = powerStationDaoImpl.get(powerStationId);
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			PowerStationPO.setName(PowerStationPO.getEnglishname());
		}
		return PowerStationPO;
	}
	/**
	 * 获取指定电站指点时间段内的日发电量
	 * @param powerStationId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<GenerationMonthPO> getGenerationDailyList(String powerStationId, String startTime, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("endTime", endTime);
		map.put("startTime", startTime);
		map.put("formId", powerStationId);
		List<GenerationMonthPO> list = generationMonthDaoImpl.findByMap(map);
		return list;
	}
	/**
	 * 获取逆变器列表（分页）
	 * @param powerStationId
	 * @param equipmentStatus
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public ResultListVO<InverterBaseInfoVO> getInverterPageInfo(String powerStationId, Integer page,
			Integer pagesize,String inverterId,int thirdUserid ,String seachName ) {
		List<InverterBaseInfoVO> result = new ArrayList<InverterBaseInfoVO>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("farmid", powerStationId);
 
		if(thirdUserid>0){
			map.put("thirdUserid", thirdUserid);
		}
		
		if(StringUtils.isNotBlank(inverterId)){

			map.put("id", inverterId);
		}
		if(StringUtils.isNotBlank(seachName)){
			map.put("name", seachName);
		}
		PageBean<InverterPO> pageBean = new PageBean<InverterPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		inverterDaoImpl.find4Page(pageBean, map,true,"sequence",SortBy.正序);
		List<InverterPO> list = pageBean.getObjList();
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				InverterPO inverterPO = list.get(i);
				InverterBaseInfoVO inverterBaseInfoVO = new InverterBaseInfoVO();
				inverterBaseInfoVO.setId(inverterPO.getId());
				inverterBaseInfoVO.setModel(inverterPO.getModel());
				inverterBaseInfoVO.setName(inverterPO.getName());
				inverterBaseInfoVO.setSerialNumber(inverterPO.getSerialNumber());
				result.add(inverterBaseInfoVO);
			}
		}
		ResultListVO<InverterBaseInfoVO> resultListVO =new ResultListVO<InverterBaseInfoVO>();
		resultListVO.setCounts(pageBean.getTotalRows());
		resultListVO.setResultList(result);
		return resultListVO;
	}
	
	
	/**
	 * @Title:insertPowerstation
	 * @Description:TODO(保存电站信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void insertPowerstation(PowerstationDTO param){
		PowerStationPO powerstation = new PowerStationPO();
		powerstation.setCompanyId(new BigDecimal(param.getCompanyId()));
		powerstation.setName(param.getPowerStationName());
		powerstation.setCode(param.getPowerstationCode());
		powerstation.setProvinceName(param.getProvince());
		powerstation.setCityName(param.getCity());
		powerstation.setCountyName(param.getCounty());
		powerstation.setLocation(param.getOccupation());
		powerstation.setLng(param.getLng());
		powerstation.setLat(param.getLat());
		powerstation.setInstallCapacity(param.getInstallCapacity());
		powerstation.setInvestFirmName(param.getInvestFirmName());
		powerstation.setInvestFirmContactName(param.getInvestFirmContactName());
		powerstation.setLastOpreateTime(new Date());
		powerstation.setEffectivity(new BigDecimal(1));
		powerstation.setDescription(param.getDescription());
		powerstation.setImage(param.getPicUrl());
		powerstation.setMonitoring(param.getMonitoring());
		powerstation.setStartPruduceTime(param.getStartProduceTime());
		if(param.getId()!=null){
			powerstation.setId(param.getId());
			powerStationDaoImpl.update4Selective(powerstation);
		}else{
			powerStationDaoImpl.insert(powerstation);
			/**
			 * 新增电站时 增加相同名称角色
			 */
//			RolePO role = new RolePO();
//			role.setRoleName(param.getPowerStationName());
//			role.setCreateTime(new Date());
//			role.setUpdateTime(new Date());
//			role.setRoleType(1);
//			roleService.insertRole(role);
//			RoleAndPowerPO roleAndPower = new RoleAndPowerPO();
//			roleAndPower.setPowerstationId(powerstation.getId());
//			roleAndPower.setRoleId(role.getId());
//			roleAndPowerDaoImpl.insert(roleAndPower);
		}

		
	}
	
	
	
	/**
	 * @Title:getPowerstationList
	 * @Description:TODO(得到当前公司下的所有电站信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public ResultListVO<PowerStationListVO> getPowerstationList(int companyId,String key,int page,int pageSize){
		ResultListVO<PowerStationListVO>  results = new ResultListVO<PowerStationListVO>();
		PageBean<PowerStationPO> pageBean = new PageBean<PowerStationPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("companyId",companyId);
		if(StringUtils.isNotBlank(key)){ 
			powerStationDaoImpl.find4Page(pageBean, paramMap,key);
		}else{ 
			powerStationDaoImpl.find4Page(pageBean, paramMap,null);
		}
		String local = (String)HttpSessionUtil.getAttribute("local");
		List<PowerStationListVO> resultList = new ArrayList<PowerStationListVO>();
		List<PowerStationPO> list = pageBean.getObjList();
		if(list.size()>0){
			for(PowerStationPO po : list){
				PowerStationListVO returnList =new  PowerStationListVO();
				returnList.setId(po.getId());
				returnList.setInstallCapacity(po.getInstallCapacity());
				returnList.setInvestFirmContactName(po.getInvestFirmContactName());
				returnList.setInvestFirmName(po.getInvestFirmName());
				returnList.setLat(po.getLat());
				returnList.setLng(po.getLng());
				returnList.setPowerstationCode(po.getCode());
				if("en_US".equals(local)){
					if(!StringUtil.empty(po.getEnglishname())){
						returnList.setPowerStationName(po.getEnglishname());
					}else{
						returnList.setPowerStationName(po.getName());
					}
				}else{
					returnList.setPowerStationName(po.getName());
				}
				returnList.setCreatetime(po.getLastOpreateTime());
				returnList.setEffectivity(po.getEffectivity());
				returnList.setMonitoring(po.getMonitoring());
				String provinceName = (!"".equals(po.getProvinceName())&&po.getProvinceName()!=null)?po.getProvinceName():"";
				String cityName =(!"".equals( po.getCityName())&&po.getCityName()!=null)? po.getCityName():"";
				String countyName = (!"".equals(po.getCountyName())&&po.getCountyName()!=null)?po.getCountyName():"";
				String location = (!"".equals(po.getLocation())&&po.getLocation()!=null)?po.getLocation():"";
				if("市辖区".equals(cityName)||"县".equals(cityName)){
					returnList.setAddress(provinceName+countyName+location);
				}else{
					returnList.setAddress(provinceName+cityName+countyName+location);
				}
				resultList.add(returnList);
			}
		}
		results.setResultList(resultList);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	
	public PowerStationListVO getPowerInfoById(String ids){
		List<PowerStationPO> poList = powerStationDaoImpl.findByIds(ids);
		PowerStationListVO result =new  PowerStationListVO();
		if(poList.size()>0){
			PowerStationPO po = poList.get(0);
			result.setId(po.getId());
			result.setInstallCapacity(po.getInstallCapacity());
			result.setInvestFirmContactName(po.getInvestFirmContactName());
			result.setInvestFirmName(po.getInvestFirmName());
			result.setLat(po.getLat());
			result.setLng(po.getLng());
			result.setPowerstationCode(po.getCode());
			result.setPowerStationName(po.getName());
			result.setCreatetime(po.getLastOpreateTime());
			result.setProvinceName(po.getProvinceName());
			result.setCityName(po.getCityName());
			result.setCountyName(po.getCountyName());
			result.setLocation(po.getLocation());
			result.setImgUrl("".equals(po.getImage())?null:po.getImage());
			result.setDescription(po.getDescription());
			result.setMonitoring(po.getMonitoring());
			result.setStartProduceTime(po.getStartPruduceTime());
		}
		return result;
	}
	
	/**
	 * @Title:delPowerstation
	 * @Description:TODO(根据id删除电站信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void delPowerstation(Integer id){
		powerStationDaoImpl.delete(id);
	}
	
	/**
	 * @Title:delPowerstation
	 * @Description:TODO(启用/禁用电站) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void updateStatus(Integer id,Integer effectivity){
		PowerStationPO param = new PowerStationPO();
		param.setId(id);
		param.setEffectivity(new BigDecimal(effectivity));
		powerStationDaoImpl.update4Selective(param);
	}
	
	
	/**
	 * @Title:getEquipmentNum
	 * @Description:TODO(获取电站下的逆变器数量) 
	 * @param @return
	 * @return Map
	 * @throws
	 */
	public List getEquipmentNum(int powerstationId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("powerstationId", powerstationId);
		return powerStationDaoImpl.getEquipmentNum(param);
	}

	public List<EquipmentTypeVO> getEquipmentsList(Integer powerStationId) {
		return powerStationDaoImpl.getEquipmentsList(powerStationId);
	}
	/**
	 * 获取所有电站
	 * @return
	 */
	public Map<String,String> getAllPowers(){
		Map<String,String> result = new HashMap<String,String>();
		List<PowerStationPO> list = powerStationDaoImpl.findAll();
		if(list.size()>0){
			for(PowerStationPO p : list){
				result.put(p.getId()+"", p.getName());
			}
		}
		return result;
	}
}
