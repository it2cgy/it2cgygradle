package com.yunengzhe.PVMTS.domain.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

public class EquipmentVO {
    private Integer id;
    private String equipmentType;
    private String equipmentModel;//型号
    private String equipmentNumber;//编号
    private String factory;
    private String powerstationid;
    private String powerstationName;
    private String producedDate;
    private String factoryDate;
    private Integer lifetime;
    private Integer serviceExpense;
    private Integer price;
    private java.math.BigDecimal deprecition;
    private String remark;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
    private String filename;
    private String filepath;
    private String remarks;
    private List<AttachmentPO> attachmentPO;
    
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPowerstationName() {
		return powerstationName;
	}
	public void setPowerstationName(String powerstationName) {
		this.powerstationName = powerstationName;
	}
	public List<AttachmentPO> getAttachmentPO() {
		return attachmentPO;
	}
	public void setAttachmentPO(List<AttachmentPO> attachmentPO) {
		this.attachmentPO = attachmentPO;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public String getEquipmentNumber() {
		return equipmentNumber;
	}
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getPowerstationid() {
		return powerstationid;
	}
	public void setPowerstationid(String powerstationid) {
		this.powerstationid = powerstationid;
	}
	public java.util.Date getProducedDate() throws ParseException {
		if(producedDate!=null&&producedDate!=""){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(producedDate);
		}
		return null;
	}
	public void setProducedDate(String producedDate) {
		this.producedDate = producedDate;
	}
	public java.util.Date getFactoryDate() throws ParseException {
		if(factoryDate!=null&&factoryDate!=""){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(factoryDate);
		}
		return null;
	}
	public void setFactoryDate(String factoryDate) {
		this.factoryDate = factoryDate;
	}
	public Integer getLifetime() {
		return lifetime;
	}
	public void setLifetime(Integer lifetime) {
		this.lifetime = lifetime;
	}
	public Integer getServiceExpense() {
		return serviceExpense;
	}
	public void setServiceExpense(Integer serviceExpense) {
		this.serviceExpense = serviceExpense;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public java.math.BigDecimal getDeprecition() {
		return deprecition;
	}
	public void setDeprecition(java.math.BigDecimal deprecition) {
		this.deprecition = deprecition;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
    
}
