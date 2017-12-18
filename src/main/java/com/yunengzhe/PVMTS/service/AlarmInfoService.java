package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.AlarmInfoDao;
import com.yunengzhe.PVMTS.dao.FaultInfoDao;
import com.yunengzhe.PVMTS.dao.MessageSystemInfoDao;
import com.yunengzhe.PVMTS.dao.MessageUserInfoDao;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.po.MessageUserInfoPO;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserAndPowerPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountByUipmentVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmInfoVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmVO;
import com.yunengzhe.PVMTS.domain.vo.Counts;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.TimeUtil;


@Service("alarmInfoService")
public class AlarmInfoService {
	private static final Logger logger = LoggerFactory.getLogger(AlarmInfoService.class);
	private static int ORDER_NUM=001;
	@Autowired
	private AlarmInfoDao alarmInfoDaoImpl;
	@Autowired
	private FaultInfoDao faultInfoDaoImpl;
	@Autowired
	private MessageSystemInfoDao messageSystemInfoDaoImpl;
	@Autowired
	private MessageUserInfoDao messageUserInfoDaoImpl;
	@Autowired
	private UserAndPowerService userAndPowerService;
	@Autowired
	private SettingInfoService settingInfoService;
	@Autowired
	private PowerStationService powerStationService;
	/**
	 * 获取报警详情
	 * 参数报警表id
	 * 返回值报警详情表(电站信息,近三个月历史报警信息，附件)
	 * @param id
	 * @return已测
	 */
	public AlarmInfoPO getAlarmByid(int id) {
		return alarmInfoDaoImpl.get(id);
	}
	public AlarmInfoPO getAlarmDispose(Integer id) {
		if(id==null || id==0){
			return null;
		}
		return alarmInfoDaoImpl.getAlarmDispose(id);
	}
	/**
	 * 获取报警列表
	 * 参数int alarmGrade报警级别,String alarmMessage报警原因,String beforeTime报警时间范围前搜索,String lateTime报警时间范围之后搜索,int pageSize显示条数,int powerStationId电站
	 * 返回值 报警详情List和未提交总条数
	 * @param userId 
	 * @param status 
	 * @param
	 * @return已测
	 */
	public ResultListVO<AlarmInfoVO> getAlarmList(AlarmVO alarm, Integer userid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("alarmGrade", alarm.getAlarmGrade()==""?null: alarm.getAlarmGrade());
		map.put("alarmMessage", alarm.getAlarmMessage()==""?null:alarm.getAlarmMessage());
		map.put("powerStationId", alarm.getPowerStationId()==""?null:alarm.getPowerStationId());
		map.put("beforeTime", alarm.getBeforeTime()==""?null:alarm.getBeforeTime());
		map.put("lateTime", alarm.getLateTime()==""?null: alarm.getLateTime());
		map.put("userId", userid);
		map.put("status", alarm.getStatus());
		PageBean<MessageSystemInfoPO> pageBean = new PageBean<MessageSystemInfoPO>();
		pageBean.setCurrentPage(alarm.getPage());
		pageBean.setPageRecorders(alarm.getPagesize());
		map.put("startRow", pageBean.getSimpleStartRow());
		map.put("pageSize", pageBean.getPageRecorders());
		List<AlarmInfoPO> findByMap = alarmInfoDaoImpl.findList(map);
		List<AlarmInfoVO> infoList = new ArrayList<AlarmInfoVO>();
		if(findByMap.size()>0){
			for (AlarmInfoPO alarmInfoPO : findByMap) {
				AlarmInfoVO info = new AlarmInfoVO();
				if(alarmInfoPO.getUserId()==userid){
					info.setBooread(1);
				}else{
					info.setBooread(0);
				}
				if(alarmInfoPO.getPowerStationId()!=null){
					PowerStationBaseInfoVO powerStationInfo = powerStationService.getPowerStationInfo(alarmInfoPO.getPowerStationId()+"");
					if(powerStationInfo!=null){
						info.setAddress(powerStationInfo.getAddress());
						info.setName(powerStationInfo.getName());
					}
				}
				info.setId(alarmInfoPO.getId());
				info.setAlarmGrade(alarmInfoPO.getAlarmGrade());
				info.setAlarmMessage(alarmInfoPO.getAlarmMessage());
				info.setAlarmType(alarmInfoPO.getAlarmType());
				info.setAlarmValue(alarmInfoPO.getAlarmValue());
				info.setEventTime(alarmInfoPO.getEventTime());
				info.setPowerStationId(alarmInfoPO.getPowerStationId());
				info.setStatus(alarmInfoPO.getStatus());
				info.setUserId(alarmInfoPO.getUserId());
				info.setOpreateMessage(alarmInfoPO.getOpreateMessage());
				infoList.add(info);
			}
		}
		ResultListVO<AlarmInfoVO> vo = new ResultListVO<AlarmInfoVO>();
		vo.setCounts(alarmInfoDaoImpl.getcount(map));
		vo.setResultList(infoList);
		logger.debug(vo.toString());
		return vo;
	}
	/**
	 * 删除报警
	 * 参数 报警表id
	 * 返回值 是否成功
	 * @param
	 * @return
	 */
	public boolean delete(int id) {
		return alarmInfoDaoImpl.delete(id)==1;
	}
	/**
	 * 添加报警
	 * @param
	 * @return已测
	 */
	public boolean insert(AlarmInfoPO alarm,int userId) {
		String orderNumTemp ="BJ"+TimeUtil.date2String(new Date(),"yyyyMMddHHmm")+ORDER_NUM;
		alarm.setStatus(0);
		alarm.setUserId(userId);
		alarm.setAlarmNumber(orderNumTemp);
		boolean flag = alarmInfoDaoImpl.insert(alarm)==1;
		ORDER_NUM++;
		if(flag){
			if(alarm.getPowerStationId()!=null){
				alarm.setPowerStationBaseInfo(powerStationService.getPowerStationInfo(alarm.getPowerStationId()+""));
				boolean addMessage = addMessage(alarm);
				logger.info(addMessage==true?"消息发送成功":"消息发送失败");
			}
		}
		return flag;
	}
	/**
	 * 确认报警
	 * 参数 说明及附件  报警id
	 * @param
	 * @return已测
	 */
	public AlarmInfoPO affirm(AlarmInfoPO alarmInfoPO) {
		AlarmInfoPO alarmInfoPO2 = null;
		try {
			alarmInfoDaoImpl.update4Selective(alarmInfoPO);
		} catch (Exception e) {
			e.printStackTrace();
			return alarmInfoPO2;
		}
		alarmInfoPO2 = alarmInfoDaoImpl.get(alarmInfoPO.getId());
		if(alarmInfoPO2.getPowerStationId()!=null){
			alarmInfoPO2.setPowerStationBaseInfo(powerStationService.getPowerStationInfo(alarmInfoPO2.getPowerStationId()+""));
			boolean addMessage = addMessage(alarmInfoPO2);
			logger.info(addMessage==true?"消息发送成功":"消息发送失败");
		}
		return alarmInfoPO2;
	}
	/**
	 * 报警解除
	 * 参数  报警id
	 * @param
	 * @return
	 */
	public boolean relieve(int id,int userId) {
		AlarmInfoPO alarmInfoPO = new AlarmInfoPO();
		alarmInfoPO.setId(id);
		alarmInfoPO.setStatus(2);
		alarmInfoPO.setUserId(userId);//TODO
		boolean flag = alarmInfoDaoImpl.update4Selective(alarmInfoPO)==1;
		if(flag){
			AlarmInfoPO alarmInfoPO2 = alarmInfoDaoImpl.get(id);
			if(alarmInfoPO2.getPowerStationId()!=null){
				alarmInfoPO2.setPowerStationBaseInfo(powerStationService.getPowerStationInfo(alarmInfoPO2.getPowerStationId()+""));
				boolean addMessage = addMessage(alarmInfoPO2);
				logger.info(addMessage==true?"消息发送成功":"消息发送失败");
			}
		}
		return flag;
	}
	
	/**
	 * 该电站近三个月的历史报警信息
	 * @param powerStationId
	 * @return已测
	 */
	public List<AlarmInfoPO> getAlarmHostoryList(Integer powerStationId) {
		return alarmInfoDaoImpl.getAlarmHostoryList(powerStationId);
	}
	/**
	 * 根据电站ids获取未确认的报警数量
	 * 参数 电站id
	 * 返回值 List<id,count>
	 * @param 
	 * @return已测
	 */
	public List<AlarmCountVO> getAlarmCount(String ids){
		List<AlarmCountVO> alarmCount = new ArrayList<AlarmCountVO>();
		if(ids!=""&&!StringUtils.isBlank(ids)){
			String[] split = ids.split(",");
			AlarmCountVO alarmCountVO = new AlarmCountVO();
			for (int i = 0; i < split.length; i++) {
				int alarm = alarmInfoDaoImpl.getAlarmCount(Integer.parseInt(split[i]));
				alarmCountVO.setPowerStationId(Integer.parseInt(split[i]));
				alarmCountVO.setPowerAlarmCount(alarm==0?0:alarm);
				alarmCount.add(alarmCountVO);
			}
		}
		return alarmCount;
	}
	/**
	 * 根据设备类型和设备id获取未确认的报警数量
	 * 参数 电站id
	 * 返回值 List<id,count>
	 * @param 
	 * @return
	 */
	public List<AlarmCountByUipmentVO> getAlarmCountByUipment(int uipmentId,int uipmentType){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("equipmentId", uipmentId);
		map.put("equipmentType", uipmentType);
		List<AlarmCountByUipmentVO> alarmCount = alarmInfoDaoImpl.getAlarmCountByUipment(map);
		return alarmCount;
	}
	/**
	 * 根据搜索内容查询列表
	 * @param search
	 * @param status
	 * @param rows 
	 * @param page 
	 * @param third 
	 * @return
	 */
	public List<AlarmInfoPO> getAlarmListBySearch(String search, Integer status,Integer powerStationId, Integer page, Integer pagesize,
			String third,String equipmentName,String pointName,String appSeachName) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(search)){
			map.put("search", search);
		}
		if(StringUtils.isNotBlank(equipmentName)){
			map.put("equipmentName", equipmentName);
		}
		if(StringUtils.isNotBlank(pointName)){
			map.put("pointName", pointName);
		}
		if(StringUtils.isNotBlank(appSeachName)){
			if("一级报警".contains(appSeachName)&&appSeachName.contains("一")){
				map.put("alarmGrade", 1);
			}else if("二级报警".contains(appSeachName)&&appSeachName.contains("二")){
				map.put("alarmGrade", 2);
			}else if("三级报警".contains(appSeachName)&&appSeachName.contains("三")){
				map.put("alarmGrade", 3);
			}else{
				map.put("equipmentName", appSeachName);
			}
			
		}
		map.put("third", third);
		map.put("status", status);
		map.put("powerStationId", powerStationId);
		PageBean<MessageSystemInfoPO> pageBean = new PageBean<MessageSystemInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		map.put("startRow", pageBean.getSimpleStartRow());
		map.put("pageSize", pageBean.getPageRecorders());
		List<AlarmInfoPO> res = alarmInfoDaoImpl.getAlarmListBySearch(map);
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<res.size();i++){
				AlarmInfoPO ap = res.get(i);
				ap.setMeasurePointDiscription(ap.getPointEnglishName());
				ap.setName(ap.getEnglishname());
			}
		}
		return res;
	}
	/**
	 * 根据电站查询报警总数  故障总数  已解决故障率
	 * @param search
	 * @param status
	 * @return
	 */
	public Counts getCounts(Integer powerStationId) {
		Counts counts = new Counts();
		counts.setAlarmSum(alarmInfoDaoImpl.getAlarmSum(powerStationId));
		int faultSum = faultInfoDaoImpl.getFaultSum(powerStationId);
		counts.setFaultSum(faultSum);
		if(faultSum!=0){
			counts.setFaultProcess(faultInfoDaoImpl.getFaultProcess(powerStationId)/faultSum*100);
		}
		return counts;
	}
	/**
	 * 添加修改报警之后添加消息
	 * @param alarm
	 * @param userId
	 * @return已测
	 */
	public boolean addMessage(AlarmInfoPO alarm){
			MessageSystemInfoPO info = new MessageSystemInfoPO();
			info.setTitle(alarm.getPowerStationBaseInfo().getName());
			info.setContent(alarm.getAlarmMessage());
			info.setMassageStatus(alarm.getStatus());
			info.setMessageId(alarm.getId());
			info.setMessageType(1);
			info.setForcedPush(0);
			info.setAddressUser("报警消息");
			boolean flag = false;
			flag = messageSystemInfoDaoImpl.insert(info)>0;
			if(flag){
				List<UserAndPowerPO> userAndPower = userAndPowerService.getUserList(alarm.getPowerStationId());
				List<MessageUserInfoPO> messageUserList = new ArrayList<MessageUserInfoPO>();
				if(userAndPower.size()>0){
					for (UserAndPowerPO userAndPowerPO : userAndPower) {
						MessageUserInfoPO userinfo = new MessageUserInfoPO();
						userinfo.setId(null);
						userinfo.setIsread(0);
						userinfo.setMid(info.getId());
						userinfo.setUserId(userAndPowerPO.getUserId());
						messageUserList.add(userinfo);
					}
				}
				if(messageUserList.size()>0){
					messageUserInfoDaoImpl.insertBatch(messageUserList);
				}
			}
			return flag;
	}
	public int getNoReadCount(Integer userid) {
		// TODO Auto-generated method stub
		return alarmInfoDaoImpl.getNoReadCount(userid);
	}
	
	/**
	 * @Title:confirmAlarm
	 * @Description:TODO(确认报警) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public boolean confirmAlarm(AlarmInfoPO alarm){
		
		try {
			logger.info("确认报警 根据报警id");
			return alarmInfoDaoImpl.update4Selective(alarm)>0;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return false;
	}
	public int getCountBySearch(String search, Integer status,
			Integer powerStationId, String third,String equipmentName,String pointName,String appSeachName) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(search)){
			map.put("search", search);
		}
		if(StringUtils.isNotBlank(equipmentName)){
			map.put("equipmentName", equipmentName);
		}
		if(StringUtils.isNotBlank(pointName)){
			map.put("pointName", pointName);
		}
		if(StringUtils.isNotBlank(appSeachName)){
			if("一级报警".contains(appSeachName)&&appSeachName.contains("一")){
				map.put("alarmGrade", 1);
			}else if("二级报警".contains(appSeachName)&&appSeachName.contains("二")){
				map.put("alarmGrade", 2);
			}else if("三级报警".contains(appSeachName)&&appSeachName.contains("三")){
				map.put("alarmGrade", 3);
			}else{
				map.put("equipmentName", appSeachName);
			}
			
		}
		map.put("status", status);
		map.put("third", third);
		map.put("powerStationId", powerStationId);
		return alarmInfoDaoImpl.getCountBySearch(map);
	}
	
}
