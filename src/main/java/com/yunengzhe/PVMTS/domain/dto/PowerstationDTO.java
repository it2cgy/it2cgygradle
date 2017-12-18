package com.yunengzhe.PVMTS.domain.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName:PowerstationDTO
 * @Description:TODO(电站信息dto)
 * @author chenguiyang
 * @date 2017年6月30日上午10:53:50
 */
public class PowerstationDTO {
	  	private Integer id;
	    private String powerStationName;//电站名称
	    private String powerstationCode;//电站编号
	    private String province;//省份id
	    private String city;//城市id
	    private String county;//区县id
	    private String occupation;//详细地址
		private java.math.BigDecimal lng;//经度
	    private java.math.BigDecimal lat;//纬度
	    private java.math.BigDecimal installCapacity;//电站容量
	    private String investFirmName;//投资单位
	    private String investFirmContactName;//投资单位负责人
	    private String picUrl;//电站图片
	    private String description;
	    private Integer companyId;
	    private String monitoring;//视频监控地址
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date startProduceTime;//投产日期
	    
		public Date getStartProduceTime() {
			return startProduceTime;
		}
		public void setStartProduceTime(Date startProduceTime) {
			this.startProduceTime = startProduceTime;
		}
		public String getMonitoring() {
			return monitoring;
		}
		public void setMonitoring(String monitoring) {
			this.monitoring = monitoring;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getCompanyId() {
			return companyId;
		}
		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getPowerStationName() {
			return powerStationName;
		}
		public void setPowerStationName(String powerStationName) {
			this.powerStationName = powerStationName;
		}
		public String getPowerstationCode() {
			return powerstationCode;
		}
		public void setPowerstationCode(String powerstationCode) {
			this.powerstationCode = powerstationCode;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}

		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public java.math.BigDecimal getLng() {
			return lng;
		}
		public void setLng(java.math.BigDecimal lng) {
			this.lng = lng;
		}
		public java.math.BigDecimal getLat() {
			return lat;
		}
		public void setLat(java.math.BigDecimal lat) {
			this.lat = lat;
		}
		public java.math.BigDecimal getInstallCapacity() {
			return installCapacity;
		}
		public void setInstallCapacity(java.math.BigDecimal installCapacity) {
			this.installCapacity = installCapacity;
		}
		public String getInvestFirmName() {
			return investFirmName;
		}
		public void setInvestFirmName(String investFirmName) {
			this.investFirmName = investFirmName;
		}
		public String getInvestFirmContactName() {
			return investFirmContactName;
		}
		public void setInvestFirmContactName(String investFirmContactName) {
			this.investFirmContactName = investFirmContactName;
		}
	    
	    
	    
	    
}
