package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;


/**
 * @ClassName:DepartmappingPO
 * @Description:TODO(离场检查项目信息)
 * @author chenguiyang
 * @date 2017年6月7日下午4:33:22
 */
public class DepartmappingPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//主键
    private String orderNum;//工单编号
    private Integer examineId;//检查项目id
    private Integer isQualified;//是否合格
    private String  examineName;//检查项目名称
    
    
	public String getExamineName() {
		return examineName;
	}
	public void setExamineName(String examineName) {
		this.examineName = examineName;
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
	public Integer getExamineId() {
		return examineId;
	}
	public void setExamineId(Integer examineId) {
		this.examineId = examineId;
	}
	public Integer getIsQualified() {
		return isQualified;
	}
	public void setIsQualified(Integer isQualified) {
		this.isQualified = isQualified;
	}

}
