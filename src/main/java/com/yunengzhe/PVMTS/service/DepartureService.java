package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.DepartmappingDao;
import com.yunengzhe.PVMTS.dao.DepartureDao;
import com.yunengzhe.PVMTS.dao.WorkOrderInfoDao;
import com.yunengzhe.PVMTS.domain.dto.DepartureSaveDTO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.DepartmappingPO;
import com.yunengzhe.PVMTS.domain.po.DeparturePO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;
import com.yunengzhe.PVMTS.util.FileUtil;

/**
 * @ClassName:DepartureService
 * @Description:TODO(离场申请处理)
 * @author chenguiyang
 * @date 2017年6月7日下午4:50:06
 */
@Service("departureService")
public class DepartureService {

	private static final Logger logger = LoggerFactory.getLogger(DepartureService.class);
	
	@Autowired
	private DepartureDao departureDaoImpl;
	
	@Autowired
	private WorkOrderInfoDao workOrderInfoDaoImpl;
	
	@Autowired
	private DepartmappingDao departmappingDaoImpl;
	
	 
	@Autowired
	private AttachmentService attachmentService;
	/**
	 * @Title:departureInfo
	 * @Description:TODO(离场申请) 
	 * @param @return
	 * @return DeparturePO
	 * @throws
	 */
	public DeparturePO departureInfo(String orderNum){
		List<DeparturePO> departure = departureDaoImpl.findBy("orderNum",orderNum);
		return departure.size()>0?departure.get(0):null;
	}
	
	
	/**
	 * @Title:physicalSubmit
	 * @Description:TODO(完成体检 更新工单信息表中电站体检状态为已处理) 
	 * @param @param orderNum
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int departureSubmit(int orderId){
		WorkOrderInfoPO orderPO = new WorkOrderInfoPO();
		orderPO.setId(orderId);
		orderPO.setDepartureState(1);//设置离场申请为已完成处理状态 
		return workOrderInfoDaoImpl.update4Selective(orderPO);
	}
	
	/**
	 * @Title:saveDeparture
	 * @Description:TODO(保存离场信息) 
	 * @param @param param
	 * @return void
	 * @throws
	 */
	public void saveDeparture(DepartureSaveDTO param,int userId){
		DeparturePO departure = new DeparturePO();
		departure.setId(param.getId());
		departure.setConclusion(param.getConclusion());
		departure.setOrderNum(param.getOrderNum());
		departureDaoImpl.update4Selective(departure);
		if(param.getDepartureExamine().size()>0){
			for(DepartmappingPO departureMappingPO: param.getDepartureExamine()){
				departmappingDaoImpl.update4Selective(departureMappingPO);
			}
		}
		if(param.getAttachmentList().size()>0){
			for(AttachmentPO file : param.getAttachmentList()){
				file.setFormId(""+param.getId());
				file.setFiletype(FileUtil.DEPARTURE);
				file.setFilename(file.getFilename());
				file.setFilepath(file.getFilepath());
				file.setUploadTime(new Date());
				file.setUploadUser(userId);
				attachmentService.saveAttachment(file);
			}
		}
		
	}
}
