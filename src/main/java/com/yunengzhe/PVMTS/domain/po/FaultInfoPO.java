package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;

public class FaultInfoPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String faultNumber;
    private Integer equipmentId;
    private Integer equipmentType;
    private String equipmentNumber;
    private Integer faultGrade;
    private String faultMessage;
    private Integer faultStatus;
    private Integer faultSource;
    private Integer powerStationId;
    private Integer alarmId;
    private Integer userId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String state;
    private String maintenanceUser;//运维人员
    private String description;
    private PowerStationBaseInfoVO powerStationBaseInfo;
    private List<AttachmentPO> attachmentList;
    /**
     * 工单处理使用
     */
    private Integer solveId;//故障处理id
    private Integer solveUser;//运维处理人员id1
    private java.util.Date solveTime;//处理时间
    private Integer solveResult;//是否已解决
    private String solveInfo;//故障处理说明
    private String workorderNum;//工单编号
    private Integer dispatcherId;//调度员id
    private String dispatcherState;//调度员说明
    private java.util.Date dispatcherTime;//调度员分配时间
    
    private String createuser;//提交故障的人员名称
    private String dispatcherName;//调度员名称
    private String solveName;//处理人员名称
    
    
    
    public Integer getSolveId() {
		return solveId;
	}
	public void setSolveId(Integer solveId) {
		this.solveId = solveId;
	}
	private List<AttachmentPO> dispatcherList;//调度员上传附件
    
    private List<AttachmentPO> solveList;//处理员上传附件 
    
	public List<AttachmentPO> getDispatcherList() {
		return dispatcherList;
	}
	public void setDispatcherList(List<AttachmentPO> dispatcherList) {
		this.dispatcherList = dispatcherList;
	}
	public List<AttachmentPO> getSolveList() {
		return solveList;
	}
	public void setSolveList(List<AttachmentPO> solveList) {
		this.solveList = solveList;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getDispatcherName() {
		return dispatcherName;
	}
	public void setDispatcherName(String dispatcherName) {
		this.dispatcherName = dispatcherName;
	}
	public String getSolveName() {
		return solveName;
	}
	public void setSolveName(String solveName) {
		this.solveName = solveName;
	}
	public Integer getSolveUser() {
		return solveUser;
	}
	public void setSolveUser(Integer solveUser) {
		this.solveUser = solveUser;
	}
	public java.util.Date getSolveTime() {
		return solveTime;
	}
	public void setSolveTime(java.util.Date solveTime) {
		this.solveTime = solveTime;
	}
	public Integer getSolveResult() {
		return solveResult;
	}
	public void setSolveResult(Integer solveResult) {
		this.solveResult = solveResult;
	}
	public String getSolveInfo() {
		return solveInfo;
	}
	public void setSolveInfo(String solveInfo) {
		this.solveInfo = solveInfo;
	}
	public String getWorkorderNum() {
		return workorderNum;
	}
	public void setWorkorderNum(String workorderNum) {
		this.workorderNum = workorderNum;
	}
	public Integer getDispatcherId() {
		return dispatcherId;
	}
	public void setDispatcherId(Integer dispatcherId) {
		this.dispatcherId = dispatcherId;
	}
	public String getDispatcherState() {
		return dispatcherState;
	}
	public void setDispatcherState(String dispatcherState) {
		this.dispatcherState = dispatcherState;
	}
	public java.util.Date getDispatcherTime() {
		return dispatcherTime;
	}
	public void setDispatcherTime(java.util.Date dispatcherTime) {
		this.dispatcherTime = dispatcherTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFaultNumber() {
		return faultNumber;
	}
	public void setFaultNumber(String faultNumber) {
		this.faultNumber = faultNumber;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Integer getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getEquipmentNumber() {
		return equipmentNumber;
	}
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	public Integer getFaultGrade() {
		return faultGrade;
	}
	public void setFaultGrade(Integer faultGrade) {
		this.faultGrade = faultGrade;
	}
	public String getFaultMessage() {
		return faultMessage;
	}
	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
	public Integer getFaultStatus() {
		return faultStatus;
	}
	public void setFaultStatus(Integer faultStatus) {
		this.faultStatus = faultStatus;
	}
	public Integer getFaultSource() {
		return faultSource;
	}
	public void setFaultSource(Integer faultSource) {
		this.faultSource = faultSource;
	}
	public Integer getPowerStationId() {
		return powerStationId;
	}
	public void setPowerStationId(Integer powerStationId) {
		this.powerStationId = powerStationId;
	}
	public Integer getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public PowerStationBaseInfoVO getPowerStationBaseInfo() {
		return powerStationBaseInfo;
	}
	public void setPowerStationBaseInfo(PowerStationBaseInfoVO powerStationBaseInfo) {
		this.powerStationBaseInfo = powerStationBaseInfo;
	}
	public List<AttachmentPO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentPO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public String getMaintenanceUser() {
		return maintenanceUser;
	}
	public void setMaintenanceUser(String maintenanceUser) {
		this.maintenanceUser = maintenanceUser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public FaultInfoPO(Integer id, String faultNumber, Integer equipmentId,
			Integer equipmentType, String equipmentNumber, Integer faultGrade,
			String faultMessage, Integer faultStatus, Integer faultSource,
			Integer powerStationId, Integer alarmId, Integer userId,
			Date createTime, Date updateTime, String state,
			PowerStationBaseInfoVO powerStationBaseInfo,
			List<AttachmentPO> attachmentList,Integer solveUser,
			Date solveTime,Integer solveResult,String solveInfo,
			String workorderNum,
			Integer dispatcherId,String dispatcherState,
			Date dispatcherTime,String createuser,String dispatcherName,String solveName,List<AttachmentPO> dispatcherList,
			List<AttachmentPO> solveList,Integer solveId) {
		super();
		this.id = id;
		this.faultNumber = faultNumber;
		this.equipmentId = equipmentId;
		this.equipmentType = equipmentType;
		this.equipmentNumber = equipmentNumber;
		this.faultGrade = faultGrade;
		this.faultMessage = faultMessage;
		this.faultStatus = faultStatus;
		this.faultSource = faultSource;
		this.powerStationId = powerStationId;
		this.alarmId = alarmId;
		this.userId = userId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.state = state;
		this.powerStationBaseInfo = powerStationBaseInfo;
		this.attachmentList = attachmentList;
		
		this.solveUser = solveUser;
		this.solveTime = solveTime;
		this.solveResult = solveResult;
		this.solveInfo = solveInfo;
		this.workorderNum = workorderNum;
		this.dispatcherId = dispatcherId;
		this.dispatcherState = dispatcherState;
		this.dispatcherTime = dispatcherTime;
		
		this.createuser = createuser;
		this.dispatcherName = dispatcherName;
		this.solveName = solveName;
		
		this.dispatcherList = dispatcherList;
		this.solveList = solveList;
		this.solveId = solveId;
	}
	
	public FaultInfoPO() {
		super();
	}
	@Override
	public String toString() {
		return "FaultInfoPO [id=" + id + ", faultNumber=" + faultNumber
				+ ", equipmentId=" + equipmentId + ", equipmentType="
				+ equipmentType + ", equipmentNumber=" + equipmentNumber
				+ ", faultGrade=" + faultGrade + ", faultMessage="
				+ faultMessage + ", faultStatus=" + faultStatus
				+ ", faultSource=" + faultSource + ", powerStationId="
				+ powerStationId + ", alarmId=" + alarmId + ", userId="
				+ userId + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", state=" + state + ", powerStationBaseInfo="
				+ powerStationBaseInfo + ", attachmentList="
				+ attachmentList+","
				+ "solveUser="+solveUser+","
				+ "solveTime="+solveTime+","
				+ "solveResult="+solveResult+","
				+ "solveInfo="+solveInfo+","
				+ "workorderNum="+workorderNum+","
				+ "dispatcherId="+dispatcherId+","
				+ "dispatcherState="+dispatcherState+","
				+ "dispatcherName="+dispatcherName+","
				+ "solveName="+solveName+","
				+ "createuser="+createuser+","
				+ "dispatcherList="+dispatcherList+","
				+ "solveList="+solveList+","
				+ "solveId="+solveId+","
				+ "dispatcherTime="+dispatcherTime+""
				+ "]";
	}
	
   
}
