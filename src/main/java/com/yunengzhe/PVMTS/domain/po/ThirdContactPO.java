package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class ThirdContactPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer isneedthird;
    private String  thirdCompany;
    private String  thirdIdentity;
    private String  thirdUsername;
    private String  thirdPosition;
    private String  thirdContact;
    private String  workcontent;
    private String  workorderNum;
	public Integer  getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIsneedthird() {
		return isneedthird;
	}
	public void setIsneedthird(Integer isneedthird) {
		this.isneedthird = isneedthird;
	}
	public String getThirdCompany() {
		return thirdCompany;
	}
	public void setThirdCompany(String thirdCompany) {
		this.thirdCompany = thirdCompany;
	}
	public String getThirdIdentity() {
		return thirdIdentity;
	}
	public void setThirdIdentity(String thirdIdentity) {
		this.thirdIdentity = thirdIdentity;
	}
	public String getThirdUsername() {
		return thirdUsername;
	}
	public void setThirdUsername(String thirdUsername) {
		this.thirdUsername = thirdUsername;
	}
	public String getThirdPosition() {
		return thirdPosition;
	}
	public void setThirdPosition(String thirdPosition) {
		this.thirdPosition = thirdPosition;
	}
	public String getThirdContact() {
		return thirdContact;
	}
	public void setThirdContact(String thirdContact) {
		this.thirdContact = thirdContact;
	}
	public String getWorkcontent() {
		return workcontent;
	}
	public void setWorkcontent(String workcontent) {
		this.workcontent = workcontent;
	}
	public String getWorkorderNum() {
		return workorderNum;
	}
	public void setWorkorderNum(String workorderNum) {
		this.workorderNum = workorderNum;
	}
}
