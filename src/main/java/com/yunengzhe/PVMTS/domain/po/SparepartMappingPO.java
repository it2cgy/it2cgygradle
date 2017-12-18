package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;

public class SparepartMappingPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer sparepartId;
    private String workorderNum;//工单编号
    private Integer receivenum;
    private Integer receivetype;
    private String  receiveuser;
    private Integer specificationId;
    private String specificationName;
    private String sparepartName;
    private Date    createtime;
    private Date    updatetime;
    
    
    
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSparepartId() {
		return sparepartId;
	}
	public void setSparepartId(Integer sparepartId) {
		this.sparepartId = sparepartId;
	}
	public Integer getReceivenum() {
		return receivenum;
	}
	public void setReceivenum(Integer receivenum) {
		this.receivenum = receivenum;
	}
	public Integer getReceivetype() {
		return receivetype;
	}
	public void setReceivetype(Integer receivetype) {
		this.receivetype = receivetype;
	}
	public String getReceiveuser() {
		return receiveuser;
	}
	public void setReceiveuser(String receiveuser) {
		this.receiveuser = receiveuser;
	}
	public Integer getSpecificationId() {
		return specificationId;
	}
	public void setSpecificationId(Integer specificationId) {
		this.specificationId = specificationId;
	}
	public String getSpecificationName() {
		return specificationName;
	}
	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}
	public String getSparepartName() {
		return sparepartName;
	}
	public void setSparepartName(String sparepartName) {
		this.sparepartName = sparepartName;
	}
	public String getWorkorderNum() {
		return workorderNum;
	}
	public void setWorkorderNum(String workorderNum) {
		this.workorderNum = workorderNum;
	}
    

}
