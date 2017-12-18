package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;

public class EquipmentPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String equipmentType;
    private String equipmentModel;//型号
    private String equipmentNumber;//编号
    private String factory;
    private String powerstationid;//电站id
    private String powerstationname;//电站名称
    private String englishname;//电站名称
    private java.util.Date producedDate;
    private java.util.Date factoryDate;
    private Integer lifetime;
    private Integer serviceExpense;
    private Integer price;
    private java.math.BigDecimal deprecition;
    private String remark;
    private Integer userId;
    private String nickname;
    private Date createTime;
    private Date updateTime;
   
    
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
   
	public String getPowerstationname() {
		return powerstationname;
	}
	public void setPowerstationname(String powerstationname) {
		this.powerstationname = powerstationname;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public java.util.Date getProducedDate() {
		return producedDate;
	}
	public void setProducedDate(java.util.Date producedDate) {
		this.producedDate = producedDate;
	}
	public java.util.Date getFactoryDate() {
		return factoryDate;
	}
	public void setFactoryDate(java.util.Date factoryDate) {
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
}
