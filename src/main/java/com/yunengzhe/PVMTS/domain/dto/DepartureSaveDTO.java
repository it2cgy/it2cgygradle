package com.yunengzhe.PVMTS.domain.dto;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.DepartmappingPO;

/**
 * @ClassName:DepartureSaveDTO
 * @Description:TODO(保存离场申请信息)
 * @author chenguiyang
 * @date 2017年6月29日下午4:39:49
 */
public class DepartureSaveDTO {
	
    private Integer id;//离场申请id
    private String orderNum;//工单编号
    private String conclusion;//检查结论
    private List<DepartmappingPO> departureExamine;
    private List<AttachmentPO> attachmentList;
    
	public List<AttachmentPO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentPO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public List<DepartmappingPO> getDepartureExamine() {
		return departureExamine;
	}
	public void setDepartureExamine(List<DepartmappingPO> departureExamine) {
		this.departureExamine = departureExamine;
	}
    
    

}
