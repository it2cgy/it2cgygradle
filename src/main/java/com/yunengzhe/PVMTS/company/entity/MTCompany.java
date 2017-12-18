package com.yunengzhe.PVMTS.company.entity;

public class MTCompany {
	private Integer id;
	private Integer user_id;                    //用户ID
	private String user_name;					//管理员账号
	private String user_realname;				//管理员姓名
	private String cellphone;					//管理员电话
	private String password;					//管理员密码
	private String name;						//公司名称
	private String address;						//公司地址
	private String contract_code;				//合同编号
	private String owner_firm_contact_name; 	//公司联系人姓名
	private String owner_firm_contact_position;	//公司联系人职务
	private String owner_firm_contact_mobile;	//公司电话
	private String create_time;				 	//创建时间
	private String last_opreate_time;		 	//最后操作时间
	private String remarks;					 	//备注
	private String versionType;                //用户版本类型
	private Integer  totalCount;//可用账户总个数
	private Integer  lostCount;//即将到期个数
	private Integer  usingCount;//正在使用中个数
	private Integer accountType; //当前使用套餐版本

	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getLostCount() {
		return lostCount;
	}
	public void setLostCount(Integer lostCount) {
		this.lostCount = lostCount;
	}
	public Integer getUsingCount() {
		return usingCount;
	}
	public void setUsingCount(Integer usingCount) {
		this.usingCount = usingCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwner_firm_contact_name() {
		return owner_firm_contact_name;
	}
	public void setOwner_firm_contact_name(String owner_firm_contact_name) {
		this.owner_firm_contact_name = owner_firm_contact_name;
	}
	public String getOwner_firm_contact_position() {
		return owner_firm_contact_position;
	}
	public void setOwner_firm_contact_position(String owner_firm_contact_position) {
		this.owner_firm_contact_position = owner_firm_contact_position;
	}
	public String getOwner_firm_contact_mobile() {
		return owner_firm_contact_mobile;
	}
	public void setOwner_firm_contact_mobile(String owner_firm_contact_mobile) {
		this.owner_firm_contact_mobile = owner_firm_contact_mobile;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_opreate_time() {
		return last_opreate_time;
	}
	public void setLast_opreate_time(String last_opreate_time) {
		this.last_opreate_time = last_opreate_time;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public String getContract_code() {
		return contract_code;
	}
	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getUser_realname() {
		return user_realname;
	}
	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public String getVersionType() {
		return versionType;
	}
	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	@Override
	public String toString() {
		return "MTCompany [id=" + id + ", user_id=" + user_id + ", user_name="
				+ user_name + ", user_realname=" + user_realname
				+ ", cellphone=" + cellphone + ", password=" + password
				+ ", name=" + name + ", address=" + address
				+ ", contract_code=" + contract_code
				+ ", owner_firm_contact_name=" + owner_firm_contact_name
				+ ", owner_firm_contact_position="
				+ owner_firm_contact_position + ", owner_firm_contact_mobile="
				+ owner_firm_contact_mobile + ", create_time=" + create_time
				+ ", last_opreate_time=" + last_opreate_time + ", remarks="
				+ remarks + ", versionType=" + versionType + "]";
	}

	
}
