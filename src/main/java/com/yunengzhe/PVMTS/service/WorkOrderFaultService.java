package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.WorkorderfaultDao;
import com.yunengzhe.PVMTS.domain.dto.FaultInfoDTO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderFaultPO;


/**
 * @ClassName:WorkOrderFaultService
 * @Description:TODO(工单下的故障信息处理)
 * @author chenguiyang
 * @date 2017年6月7日上午11:33:09
 */
@Service("workOrderFaultService")
public class WorkOrderFaultService {

	private static final Logger logger = LoggerFactory.getLogger(WorkOrderFaultService.class);
	
	@Autowired
	private WorkorderfaultDao workOrderFaultDaoImpl;
	 
	@Autowired
	private FaultInfoService faultInfoService;
	
	
	/**
	 * @Title:getFaultInfo
	 * @Description:TODO(获取故障信息列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<WorkOrderFaultPO> getFaultInfo(String orderNum){
		List<WorkOrderFaultPO> orderPO = workOrderFaultDaoImpl.findBy("workorderId", orderNum);//获取工单下的故障信息列表
		return orderPO;
	}
	
	
	
	/**
	 * @Title:saveFaultInfo
	 * @Description:TODO(生成工单  保存故障信息) 
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int saveFaultInfo(List<FaultInfoDTO> faultInfo,String orderNum){
		List<WorkOrderFaultPO> faultList = new ArrayList<WorkOrderFaultPO>();//故障信息列表
		if(faultInfo.size()>0){
			for(FaultInfoDTO faultPO : faultInfo){
				WorkOrderFaultPO orderAndFault = new WorkOrderFaultPO();
				orderAndFault.setFaultId(faultPO.getFaultId());
				orderAndFault.setWorkorderId(orderNum);
				orderAndFault.setIsSolve(0);
				faultList.add(orderAndFault);
				faultInfoService.updateFaultInfo(faultPO.getFaultId(), 1);//更新故障信息的状态为1   0-未解决 1-已分配 2-已解决 3-未解决
			}
		}
		workOrderFaultDaoImpl.insertBatch(faultList);//生成工单与故障信息映射表信息
		return 0;
	}
	
}
