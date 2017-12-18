package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;

public class AnaloginputPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String aliasname;
    private String description;
    private String name;
    private String pathname;
    private java.math.BigDecimal type;
    private java.math.BigDecimal rtuid;
    private java.math.BigDecimal pointnum;
    private java.math.BigDecimal equipmentcontainerId;
    private java.math.BigDecimal equipmentcontainerTableid;
    private java.math.BigDecimal datasource;
    private java.math.BigDecimal value;
    private java.math.BigDecimal qualitycode;
    private java.util.Date timestamp;
    private java.math.BigDecimal rateofchange;
    private java.math.BigDecimal violationflags;
    private java.math.BigDecimal taggingflags;
    private java.math.BigDecimal controlinhibitflag;
    private java.math.BigDecimal alarmprocmode;
    private java.math.BigDecimal alarmpriority;
    private java.math.BigDecimal tpinvolvedflag;
    private java.math.BigDecimal alarmoptionflags;
    private java.math.BigDecimal authorityarea;
    private java.math.BigDecimal limitsetid;
    private java.math.BigDecimal reportdeadband;
    private java.math.BigDecimal sensormaximum;
    private java.math.BigDecimal sensorminimum;
    private java.math.BigDecimal zeroclampping;
    private java.math.BigDecimal scalefactor;
    private java.math.BigDecimal scaleoffset;
    private java.math.BigDecimal smoothingfactor;
    private java.math.BigDecimal pointaddress;
    private java.math.BigDecimal groupaddress;
    private java.math.BigDecimal rtuaddress;
    private java.math.BigDecimal alarmdelayedtime;
    private java.math.BigDecimal saveperiodicity;
    private java.math.BigDecimal measidx;
    private java.math.BigDecimal alarmtype;
    private java.math.BigDecimal operationallow;
    private java.math.BigDecimal operationalhigh;
    private java.math.BigDecimal warninglow;
    private java.math.BigDecimal warninghigh;
    private java.math.BigDecimal emergencylow;
    private java.math.BigDecimal emergencyhigh;
    private java.math.BigDecimal rateofchangelimit;
    private java.math.BigDecimal ispercentagelimits;
    private java.math.BigDecimal nominal;
    private java.math.BigDecimal deadband;
    private java.math.BigDecimal substationId;
    private java.math.BigDecimal rAWVALUE0;
    private java.math.BigDecimal rAWQUALITYCODE0;
    private java.util.Date rAWTIMESTAMP0;
    private java.math.BigDecimal rAWVALUE1;
    private java.math.BigDecimal rAWQUALITYCODE1;
    private java.util.Date rAWTIMESTAMP1;
    private java.math.BigDecimal rAWVALUE2;
    private java.math.BigDecimal rAWQUALITYCODE2;
    private java.util.Date rAWTIMESTAMP2;
    private java.math.BigDecimal rAWVALUE3;
    private java.math.BigDecimal rAWQUALITYCODE3;
    private java.util.Date rAWTIMESTAMP3;
    private java.math.BigDecimal currouteno;
    private java.math.BigDecimal alarmcount;
    private java.math.BigDecimal rating;
    private java.math.BigDecimal cycle;
    private java.math.BigDecimal funcode;
    private java.math.BigDecimal bitcount;
    private java.math.BigDecimal datatype;
    private java.math.BigDecimal alarmstatusitem;
    private java.math.BigDecimal collectid;
    private java.math.BigDecimal devtype;
    private java.math.BigDecimal devid;
    private java.math.BigDecimal devpointnum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPathname() {
		return pathname;
	}
	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	public java.math.BigDecimal getType() {
		return type;
	}
	public void setType(java.math.BigDecimal type) {
		this.type = type;
	}
	public java.math.BigDecimal getRtuid() {
		return rtuid;
	}
	public void setRtuid(java.math.BigDecimal rtuid) {
		this.rtuid = rtuid;
	}
	public java.math.BigDecimal getPointnum() {
		return pointnum;
	}
	public void setPointnum(java.math.BigDecimal pointnum) {
		this.pointnum = pointnum;
	}
	public java.math.BigDecimal getEquipmentcontainerId() {
		return equipmentcontainerId;
	}
	public void setEquipmentcontainerId(java.math.BigDecimal equipmentcontainerId) {
		this.equipmentcontainerId = equipmentcontainerId;
	}
	public java.math.BigDecimal getEquipmentcontainerTableid() {
		return equipmentcontainerTableid;
	}
	public void setEquipmentcontainerTableid(java.math.BigDecimal equipmentcontainerTableid) {
		this.equipmentcontainerTableid = equipmentcontainerTableid;
	}
	public java.math.BigDecimal getDatasource() {
		return datasource;
	}
	public void setDatasource(java.math.BigDecimal datasource) {
		this.datasource = datasource;
	}
	public java.math.BigDecimal getValue() {
		return value;
	}
	public void setValue(java.math.BigDecimal value) {
		this.value = value;
	}
	public java.math.BigDecimal getQualitycode() {
		return qualitycode;
	}
	public void setQualitycode(java.math.BigDecimal qualitycode) {
		this.qualitycode = qualitycode;
	}
	public java.util.Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}
	public java.math.BigDecimal getRateofchange() {
		return rateofchange;
	}
	public void setRateofchange(java.math.BigDecimal rateofchange) {
		this.rateofchange = rateofchange;
	}
	public java.math.BigDecimal getViolationflags() {
		return violationflags;
	}
	public void setViolationflags(java.math.BigDecimal violationflags) {
		this.violationflags = violationflags;
	}
	public java.math.BigDecimal getTaggingflags() {
		return taggingflags;
	}
	public void setTaggingflags(java.math.BigDecimal taggingflags) {
		this.taggingflags = taggingflags;
	}
	public java.math.BigDecimal getControlinhibitflag() {
		return controlinhibitflag;
	}
	public void setControlinhibitflag(java.math.BigDecimal controlinhibitflag) {
		this.controlinhibitflag = controlinhibitflag;
	}
	public java.math.BigDecimal getAlarmprocmode() {
		return alarmprocmode;
	}
	public void setAlarmprocmode(java.math.BigDecimal alarmprocmode) {
		this.alarmprocmode = alarmprocmode;
	}
	public java.math.BigDecimal getAlarmpriority() {
		return alarmpriority;
	}
	public void setAlarmpriority(java.math.BigDecimal alarmpriority) {
		this.alarmpriority = alarmpriority;
	}
	public java.math.BigDecimal getTpinvolvedflag() {
		return tpinvolvedflag;
	}
	public void setTpinvolvedflag(java.math.BigDecimal tpinvolvedflag) {
		this.tpinvolvedflag = tpinvolvedflag;
	}
	public java.math.BigDecimal getAlarmoptionflags() {
		return alarmoptionflags;
	}
	public void setAlarmoptionflags(java.math.BigDecimal alarmoptionflags) {
		this.alarmoptionflags = alarmoptionflags;
	}
	public java.math.BigDecimal getAuthorityarea() {
		return authorityarea;
	}
	public void setAuthorityarea(java.math.BigDecimal authorityarea) {
		this.authorityarea = authorityarea;
	}
	public java.math.BigDecimal getLimitsetid() {
		return limitsetid;
	}
	public void setLimitsetid(java.math.BigDecimal limitsetid) {
		this.limitsetid = limitsetid;
	}
	public java.math.BigDecimal getReportdeadband() {
		return reportdeadband;
	}
	public void setReportdeadband(java.math.BigDecimal reportdeadband) {
		this.reportdeadband = reportdeadband;
	}
	public java.math.BigDecimal getSensormaximum() {
		return sensormaximum;
	}
	public void setSensormaximum(java.math.BigDecimal sensormaximum) {
		this.sensormaximum = sensormaximum;
	}
	public java.math.BigDecimal getSensorminimum() {
		return sensorminimum;
	}
	public void setSensorminimum(java.math.BigDecimal sensorminimum) {
		this.sensorminimum = sensorminimum;
	}
	public java.math.BigDecimal getZeroclampping() {
		return zeroclampping;
	}
	public void setZeroclampping(java.math.BigDecimal zeroclampping) {
		this.zeroclampping = zeroclampping;
	}
	public java.math.BigDecimal getScalefactor() {
		return scalefactor;
	}
	public void setScalefactor(java.math.BigDecimal scalefactor) {
		this.scalefactor = scalefactor;
	}
	public java.math.BigDecimal getScaleoffset() {
		return scaleoffset;
	}
	public void setScaleoffset(java.math.BigDecimal scaleoffset) {
		this.scaleoffset = scaleoffset;
	}
	public java.math.BigDecimal getSmoothingfactor() {
		return smoothingfactor;
	}
	public void setSmoothingfactor(java.math.BigDecimal smoothingfactor) {
		this.smoothingfactor = smoothingfactor;
	}
	public java.math.BigDecimal getPointaddress() {
		return pointaddress;
	}
	public void setPointaddress(java.math.BigDecimal pointaddress) {
		this.pointaddress = pointaddress;
	}
	public java.math.BigDecimal getGroupaddress() {
		return groupaddress;
	}
	public void setGroupaddress(java.math.BigDecimal groupaddress) {
		this.groupaddress = groupaddress;
	}
	public java.math.BigDecimal getRtuaddress() {
		return rtuaddress;
	}
	public void setRtuaddress(java.math.BigDecimal rtuaddress) {
		this.rtuaddress = rtuaddress;
	}
	public java.math.BigDecimal getAlarmdelayedtime() {
		return alarmdelayedtime;
	}
	public void setAlarmdelayedtime(java.math.BigDecimal alarmdelayedtime) {
		this.alarmdelayedtime = alarmdelayedtime;
	}
	public java.math.BigDecimal getSaveperiodicity() {
		return saveperiodicity;
	}
	public void setSaveperiodicity(java.math.BigDecimal saveperiodicity) {
		this.saveperiodicity = saveperiodicity;
	}
	public java.math.BigDecimal getMeasidx() {
		return measidx;
	}
	public void setMeasidx(java.math.BigDecimal measidx) {
		this.measidx = measidx;
	}
	public java.math.BigDecimal getAlarmtype() {
		return alarmtype;
	}
	public void setAlarmtype(java.math.BigDecimal alarmtype) {
		this.alarmtype = alarmtype;
	}
	public java.math.BigDecimal getOperationallow() {
		return operationallow;
	}
	public void setOperationallow(java.math.BigDecimal operationallow) {
		this.operationallow = operationallow;
	}
	public java.math.BigDecimal getOperationalhigh() {
		return operationalhigh;
	}
	public void setOperationalhigh(java.math.BigDecimal operationalhigh) {
		this.operationalhigh = operationalhigh;
	}
	public java.math.BigDecimal getWarninglow() {
		return warninglow;
	}
	public void setWarninglow(java.math.BigDecimal warninglow) {
		this.warninglow = warninglow;
	}
	public java.math.BigDecimal getWarninghigh() {
		return warninghigh;
	}
	public void setWarninghigh(java.math.BigDecimal warninghigh) {
		this.warninghigh = warninghigh;
	}
	public java.math.BigDecimal getEmergencylow() {
		return emergencylow;
	}
	public void setEmergencylow(java.math.BigDecimal emergencylow) {
		this.emergencylow = emergencylow;
	}
	public java.math.BigDecimal getEmergencyhigh() {
		return emergencyhigh;
	}
	public void setEmergencyhigh(java.math.BigDecimal emergencyhigh) {
		this.emergencyhigh = emergencyhigh;
	}
	public java.math.BigDecimal getRateofchangelimit() {
		return rateofchangelimit;
	}
	public void setRateofchangelimit(java.math.BigDecimal rateofchangelimit) {
		this.rateofchangelimit = rateofchangelimit;
	}
	public java.math.BigDecimal getIspercentagelimits() {
		return ispercentagelimits;
	}
	public void setIspercentagelimits(java.math.BigDecimal ispercentagelimits) {
		this.ispercentagelimits = ispercentagelimits;
	}
	public java.math.BigDecimal getNominal() {
		return nominal;
	}
	public void setNominal(java.math.BigDecimal nominal) {
		this.nominal = nominal;
	}
	public java.math.BigDecimal getDeadband() {
		return deadband;
	}
	public void setDeadband(java.math.BigDecimal deadband) {
		this.deadband = deadband;
	}
	public java.math.BigDecimal getSubstationId() {
		return substationId;
	}
	public void setSubstationId(java.math.BigDecimal substationId) {
		this.substationId = substationId;
	}
	public java.math.BigDecimal getrAWVALUE0() {
		return rAWVALUE0;
	}
	public void setrAWVALUE0(java.math.BigDecimal rAWVALUE0) {
		this.rAWVALUE0 = rAWVALUE0;
	}
	public java.math.BigDecimal getrAWQUALITYCODE0() {
		return rAWQUALITYCODE0;
	}
	public void setrAWQUALITYCODE0(java.math.BigDecimal rAWQUALITYCODE0) {
		this.rAWQUALITYCODE0 = rAWQUALITYCODE0;
	}
	public java.util.Date getrAWTIMESTAMP0() {
		return rAWTIMESTAMP0;
	}
	public void setrAWTIMESTAMP0(java.util.Date rAWTIMESTAMP0) {
		this.rAWTIMESTAMP0 = rAWTIMESTAMP0;
	}
	public java.math.BigDecimal getrAWVALUE1() {
		return rAWVALUE1;
	}
	public void setrAWVALUE1(java.math.BigDecimal rAWVALUE1) {
		this.rAWVALUE1 = rAWVALUE1;
	}
	public java.math.BigDecimal getrAWQUALITYCODE1() {
		return rAWQUALITYCODE1;
	}
	public void setrAWQUALITYCODE1(java.math.BigDecimal rAWQUALITYCODE1) {
		this.rAWQUALITYCODE1 = rAWQUALITYCODE1;
	}
	public java.util.Date getrAWTIMESTAMP1() {
		return rAWTIMESTAMP1;
	}
	public void setrAWTIMESTAMP1(java.util.Date rAWTIMESTAMP1) {
		this.rAWTIMESTAMP1 = rAWTIMESTAMP1;
	}
	public java.math.BigDecimal getrAWVALUE2() {
		return rAWVALUE2;
	}
	public void setrAWVALUE2(java.math.BigDecimal rAWVALUE2) {
		this.rAWVALUE2 = rAWVALUE2;
	}
	public java.math.BigDecimal getrAWQUALITYCODE2() {
		return rAWQUALITYCODE2;
	}
	public void setrAWQUALITYCODE2(java.math.BigDecimal rAWQUALITYCODE2) {
		this.rAWQUALITYCODE2 = rAWQUALITYCODE2;
	}
	public java.util.Date getrAWTIMESTAMP2() {
		return rAWTIMESTAMP2;
	}
	public void setrAWTIMESTAMP2(java.util.Date rAWTIMESTAMP2) {
		this.rAWTIMESTAMP2 = rAWTIMESTAMP2;
	}
	public java.math.BigDecimal getrAWVALUE3() {
		return rAWVALUE3;
	}
	public void setrAWVALUE3(java.math.BigDecimal rAWVALUE3) {
		this.rAWVALUE3 = rAWVALUE3;
	}
	public java.math.BigDecimal getrAWQUALITYCODE3() {
		return rAWQUALITYCODE3;
	}
	public void setrAWQUALITYCODE3(java.math.BigDecimal rAWQUALITYCODE3) {
		this.rAWQUALITYCODE3 = rAWQUALITYCODE3;
	}
	public java.util.Date getrAWTIMESTAMP3() {
		return rAWTIMESTAMP3;
	}
	public void setrAWTIMESTAMP3(java.util.Date rAWTIMESTAMP3) {
		this.rAWTIMESTAMP3 = rAWTIMESTAMP3;
	}
	public java.math.BigDecimal getCurrouteno() {
		return currouteno;
	}
	public void setCurrouteno(java.math.BigDecimal currouteno) {
		this.currouteno = currouteno;
	}
	public java.math.BigDecimal getAlarmcount() {
		return alarmcount;
	}
	public void setAlarmcount(java.math.BigDecimal alarmcount) {
		this.alarmcount = alarmcount;
	}
	public java.math.BigDecimal getRating() {
		return rating;
	}
	public void setRating(java.math.BigDecimal rating) {
		this.rating = rating;
	}
	public java.math.BigDecimal getCycle() {
		return cycle;
	}
	public void setCycle(java.math.BigDecimal cycle) {
		this.cycle = cycle;
	}
	public java.math.BigDecimal getFuncode() {
		return funcode;
	}
	public void setFuncode(java.math.BigDecimal funcode) {
		this.funcode = funcode;
	}
	public java.math.BigDecimal getBitcount() {
		return bitcount;
	}
	public void setBitcount(java.math.BigDecimal bitcount) {
		this.bitcount = bitcount;
	}
	public java.math.BigDecimal getDatatype() {
		return datatype;
	}
	public void setDatatype(java.math.BigDecimal datatype) {
		this.datatype = datatype;
	}
	public java.math.BigDecimal getAlarmstatusitem() {
		return alarmstatusitem;
	}
	public void setAlarmstatusitem(java.math.BigDecimal alarmstatusitem) {
		this.alarmstatusitem = alarmstatusitem;
	}
	public java.math.BigDecimal getCollectid() {
		return collectid;
	}
	public void setCollectid(java.math.BigDecimal collectid) {
		this.collectid = collectid;
	}
	public java.math.BigDecimal getDevtype() {
		return devtype;
	}
	public void setDevtype(java.math.BigDecimal devtype) {
		this.devtype = devtype;
	}
	public java.math.BigDecimal getDevid() {
		return devid;
	}
	public void setDevid(java.math.BigDecimal devid) {
		this.devid = devid;
	}
	public java.math.BigDecimal getDevpointnum() {
		return devpointnum;
	}
	public void setDevpointnum(java.math.BigDecimal devpointnum) {
		this.devpointnum = devpointnum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
