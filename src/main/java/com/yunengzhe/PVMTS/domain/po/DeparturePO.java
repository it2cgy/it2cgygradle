package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:DeparturePO
 * @Description:TODO(离场申请基本信息)
 * @author chenguiyang
 * @date 2017年6月7日下午4:32:29
 */
public class DeparturePO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//离场申请id
    private String orderNum;//工单编号
    private String conclusion;//检查结论
    private List<DepartmappingPO> examineInfo;
    
    

	public List<DepartmappingPO> getExamineInfo() {
		return examineInfo;
	}
	public void setExamineInfo(List<DepartmappingPO> examineInfo) {
		this.examineInfo = examineInfo;
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

}
