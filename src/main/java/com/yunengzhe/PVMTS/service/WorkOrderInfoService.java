package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.DepartmappingDao;
import com.yunengzhe.PVMTS.dao.WorkOrderInfoDao;
import com.yunengzhe.PVMTS.dao.WorkorderfaultDao;
import com.yunengzhe.PVMTS.domain.dto.AddOrderDTO;
import com.yunengzhe.PVMTS.domain.dto.CompletionDTO;
import com.yunengzhe.PVMTS.domain.po.DepartmappingPO;
import com.yunengzhe.PVMTS.domain.po.DeparturePO;
import com.yunengzhe.PVMTS.domain.po.UserAndPowerPO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderFaultPO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.MaintenanceUserVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.WorkOrderInfoVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst;
import com.yunengzhe.commons.util.TimeUtil;

/**
 * @ClassName:WorkOrderInfoService
 * @Description:TODO(工单处理)
 * @author chenguiyang
 * @date 2017年6月7日上午9:19:07
 */
@Service("workOrderInfoService")
public class WorkOrderInfoService {
    private static int ORDER_NUM=1;
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderInfoService.class);
	
	@Autowired
	private WorkOrderInfoDao workOrderInfoDaoImpl;
	/**
	 * 工单与故障映射处理dao
	 */
	@Autowired
	private WorkorderfaultDao workorderfaultDaoImpl;

	@Autowired
	private WorkOrderFaultService workOrderFaultService;
	
	@Autowired
	private UserAndPowerService userAndPowerService;
	@Autowired
	private DepartmappingDao departmappingDaoImpl;
	
		
	@Autowired
	private FaultInfoService faultInfoService;
	
	/**
	 * @Title:orderList
	 * @Description:TODO(获取工单列表) 
	 * @param @return
	 * @return WorkOrderInfoVO
	 * @throws
	 */
	public ResultListVO<WorkOrderInfoVO> orderList(int userId,String state,int page,int pageSize){
		ResultListVO<WorkOrderInfoVO>  results = new ResultListVO<WorkOrderInfoVO>();
		List<WorkOrderInfoVO> orderVOList = new ArrayList<WorkOrderInfoVO>();
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("maintenance_user", userId);
			if(!"all".equals(state)){
				if(!"3".equals(state)){
					param.put("state" , state);
				}
			}
			PageBean<WorkOrderInfoPO> pageBean = new PageBean<WorkOrderInfoPO>();
			pageBean.setCurrentPage(page);
			pageBean.setPageRecorders(pageSize);
			workOrderInfoDaoImpl.find4Page(pageBean, param);
			List<WorkOrderInfoPO> listData = pageBean.getObjList();
			if(listData.size()>0){
				for(WorkOrderInfoPO order : listData){
					WorkOrderInfoVO orderVO = new WorkOrderInfoVO();
					String orderId = order.getOrderNumber();//工单编号
					Map<String,Object> orderFaultMap = new HashMap<String,Object>();
					orderFaultMap.put("workorderId", orderId);
					/**
					 * 查询当前工单的所有故障信息
					 */
					List<WorkOrderFaultPO> faultList = workorderfaultDaoImpl.findByMap(orderFaultMap);
					int tempNum = 0;
					/**
					 * 设置任务1故障反馈 为已处理 0 为未处理  1为已处理
					 */
					orderVO.setFaultState(1);
					if(faultList.size()>0){
						for(WorkOrderFaultPO orderFault : faultList){
							int isSolve = orderFault.getIsSolve();
							if(isSolve==0){
								tempNum += 1;
							}
						}
					}
					/**
					 * 未处理数量等于故障数量 -任务1未处理
					 */
					if(tempNum==faultList.size()){
						orderVO.setFaultState(0);
					}
					orderVO.setOrderId(order.getId());
					orderVO.setAllocateTime(order.getAllocateTime());
					orderVO.setCreateTime(order.getCreateTime());
					orderVO.setCreateUser(order.getCreateUser());
					orderVO.setDepartureState(order.getDepartureState());
					orderVO.setFaultNum(tempNum);
					orderVO.setOrderNumber(order.getOrderNumber());
					orderVO.setPhysicalState(order.getPhysicalState());
					orderVO.setState(order.getState());
					orderVO.setPowerstationId(order.getPowerstationId());
					orderVO.setPowerstationName(order.getPowerstationName());
					orderVO.setMaintenanceName(order.getMaintenanceName());
					orderVO.setRemarks(order.getRemarks());
					orderVOList.add(orderVO);
				}
			}
			results.setResultList(orderVOList);
			results.setCounts(pageBean.getTotalRows());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return results;
	}
	
	
	/**
	 * @Title:addWorkOrder
	 * @Description:TODO(创建工单) 
	 * @param @param orderDTO
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int addWorkOrder(AddOrderDTO orderDTO){
		int flag = 0;
		WorkOrderInfoPO orderInfo = new WorkOrderInfoPO();
		try {
			String orderNumTemp ="GD"+TimeUtil.date2String(new Date(),"yyyyMMddHHmm")+ORDER_NUM;
			orderInfo.setOrderNumber(orderNumTemp);
			orderInfo.setCreateUser(orderDTO.getCreateUser());
			orderInfo.setCreateTime(new Date());
			orderInfo.setDepartureState(0);
			orderInfo.setPhysicalState(0);
			orderInfo.setMaintenanceUser(orderDTO.getMaintenanceUser());
			orderInfo.setPowerstationId(orderDTO.getPowerstationId());
			orderInfo.setPowerstationName(orderDTO.getPowerstationName());
			orderInfo.setState(0);
			orderInfo.setUpdateTime(new Date());
			workOrderInfoDaoImpl.insert(orderInfo);//生成工单记录
			workOrderFaultService.saveFaultInfo(orderDTO.getFaultInfo(),orderNumTemp);
			List<DeparturePO> departureInfo = orderDTO.getDepartture();//离场申请表检查项目
			List<DepartmappingPO> departureList = new ArrayList<DepartmappingPO>();//离场申请检查项目列表
			/**
			 * 离场申请
			 */
			DeparturePO departurePO = new DeparturePO();
			departurePO.setOrderNum(orderNumTemp);
//			departureDaoImpl.insert(departurePO);//生成工单记录
//			if(departureInfo.size()>0){//离场申请检查项目
//				for(DepartureDTO departureDTO : departureInfo){
//					DepartmappingPO departMapping = new DepartmappingPO();
//					departMapping.setExamineId(departureDTO.getDepartExamineId());
//					departMapping.setOrderNum(orderNumTemp);
//					departMapping.setIsQualified(0);//是否合格
//				}
//			}
			departmappingDaoImpl.insertBatch(departureList);
			ORDER_NUM++;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return flag;
	}
	
	/**
	 * @Title:orderSubmit
	 * @Description:TODO(提交工单)  
	 * 更新工单状态为已完成 0-未发出 1-进行中  2-已完成
	 * @param @return
	 * @return int 
	 * 工单提交与故障解决无关  故障可以是未解决时也可以提交  此方法只更新工单状态
	 * @throws
	 */
	public int orderSubmit(int orderId,String orderNum){
		WorkOrderInfoPO orderPO = new WorkOrderInfoPO();
		orderPO.setId(orderId);
		orderPO.setState(2);
	    int flag =  workOrderInfoDaoImpl.update4Selective(orderPO);
//		List<WorkOrderFaultPO> orderInfo = workOrderFaultService.getFaultInfo(orderNum);
//		if(orderInfo.size()>0){
//			for(WorkOrderFaultPO workOrder : orderInfo){
//				faultInfoService.updateFaultInfo(workOrder.getFaultId(), 2);
//			}	
//		}
		return flag;
	}
	
	
	/**
	 * @Title:physicalSubmit
	 * @Description:TODO(完成体检 更新工单信息表中电站体检状态为已处理) 
	 * @param @param orderNum
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int physicalSubmit(int orderId){
		WorkOrderInfoPO orderPO = new WorkOrderInfoPO();
		orderPO.setId(orderId);
		orderPO.setPhysicalState(1);//设置电站体检装填为已完成体检 
		orderPO.setUpdateTime(new Date());//更新编辑时间
		return workOrderInfoDaoImpl.update4Selective(orderPO);
	}
	
	
	/**
	 * @Title:physicalTime
	 * @Description:TODO(查询电站体检最后一次体检时间) 
	 * @param @param powerstationId
	 * @return void
	 * @throws
	 */
	public Date physicalTime(int powerstationId){
		Date date = null;
		List<WorkOrderInfoPO> result = workOrderInfoDaoImpl.findBy("powerstationId",powerstationId, "update_time", SysConst.SortBy.倒序);
		if(result.size()>0){	
			WorkOrderInfoPO workOrder = result.get(0);
			date = workOrder.getUpdateTime();
		}
		return date;
	}
	
	/**
	 * @Title:physicalTimes
	 * @Description:TODO(查询电站的体检次数) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public int physicalTimes(int powerstationId){
		List<WorkOrderInfoPO> result = workOrderInfoDaoImpl.findBy("powerstationId",powerstationId);
		if(result.size()>0){
			return result.size();
		}
		return 0;
	}
	/**
	 * @Title:getMaintenanceUsers
	 * @Description:TODO(加载电站下的运维人员列表) 
	 * @param @return
	 * @return List
	 * @throws
	 */
	public List<MaintenanceUserVO> getMaintenanceUsers(int powerstationId){
		List<UserAndPowerPO> userAndPowers = userAndPowerService.getUserAndPower(powerstationId);
		if(userAndPowers.size()>0){
			for(UserAndPowerPO userPower : userAndPowers){
				MaintenanceUserVO user = new MaintenanceUserVO();
				user.setUserId(userPower.getUserId());
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("maintenance_user",userPower.getUserId());
				param.put("state",0);
				List<WorkOrderInfoPO> result = workOrderInfoDaoImpl.findByMap(param);
				if(result.size()>0){
					
				}
			}
		}
		return null;
	}
	
	/**
	 * @Title:workOrderCompletion
	 * @Description:TODO(获取时间段内的工单完成量) 
	 * @param @param completionDTO
	 * @param @return
	 * @return Object
	 * @throws
	 */
	public Object workOrderCompletion(CompletionDTO completionDTO){
		try {
			List<String> list = TimeUtil.getSpaceDates(completionDTO.getStartDate(), completionDTO.getEndDate());
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for(String date:list){
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("state",1);
				param.put("updateTime", date);
				param.put("userId", completionDTO.getUserId());
				int resultNum = workOrderInfoDaoImpl.findByTime(param);
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("time",date);
				resultMap.put("val",resultNum);
				result.add(resultMap);
			}
			return RestResponse.responseOk(result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
}
