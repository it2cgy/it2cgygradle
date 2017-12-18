package com.yunengzhe.PVMTS.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 电站详情
 * @author dell
 *
 */
public class PowerStationDetailsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private double installCapacity;
    private String address;
	private double lat;
	private double lng;
	private String manager;
	private Integer runDays;
	private double generationYear;
	private double generationMonth;
	private double rainFall;
	private double radiantExposure;
	private String managerMobile;
	private String  monitoring;
	//运行开始时间
	private Date runTime;
	//直辐射
	private double directRadiation;
	//散辐射
	private double scateredRadiation;
	//斜辐射
	private double inclinedRadiation;
	//累计发电量
	private double generationGross;
	//归一化
	private double unifiedPowerGenerationDaily;
	//归一化
	private double unifiedPowerGenerationMonth;
	//归一化
	private double unifiedPowerGenerationYear;
	
	
	
	
	public double getUnifiedPowerGenerationDaily() {
		return unifiedPowerGenerationDaily;
	}
	public void setUnifiedPowerGenerationDaily(double unifiedPowerGenerationDaily) {
		this.unifiedPowerGenerationDaily = unifiedPowerGenerationDaily;
	}
	public double getUnifiedPowerGenerationMonth() {
		return unifiedPowerGenerationMonth;
	}
	public void setUnifiedPowerGenerationMonth(double unifiedPowerGenerationMonth) {
		this.unifiedPowerGenerationMonth = unifiedPowerGenerationMonth;
	}
	public double getUnifiedPowerGenerationYear() {
		return unifiedPowerGenerationYear;
	}
	public void setUnifiedPowerGenerationYear(double unifiedPowerGenerationYear) {
		this.unifiedPowerGenerationYear = unifiedPowerGenerationYear;
	}
	public Date getRunTime() {
		return runTime;
	}
	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}
	public double getDirectRadiation() {
		return directRadiation;
	}
	public void setDirectRadiation(double directRadiation) {
		this.directRadiation = directRadiation;
	}
	public double getScateredRadiation() {
		return scateredRadiation;
	}
	public void setScateredRadiation(double scateredRadiation) {
		this.scateredRadiation = scateredRadiation;
	}
	public double getInclinedRadiation() {
		return inclinedRadiation;
	}
	public void setInclinedRadiation(double inclinedRadiation) {
		this.inclinedRadiation = inclinedRadiation;
	}
	public double getGenerationGross() {
		return generationGross;
	}
	public void setGenerationGross(double generationGross) {
		this.generationGross = generationGross;
	}
	public String getManagerMobile() {
		return managerMobile;
	}
	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}
	/**
     * 通讯异常
     */
    private Integer communicationAnomaly;
    /**
     * 功率
     */
    private double generatedActivePower;
    /**
     * 日发电量数组	
     */
    private PointHistoryVO[] generationDailyList;
    /**
     * 功率时间曲线图
     */
    private Integer powerId;
    private Integer generationDailyId;
    
    /**
     * PR值	
     */
    private double pr;
    /**
     * 停机数	
     */
    private Integer stop;
    /**
     * 日发电量	
     */
    private double generationDaily;
    /**
     * 逆变器总数
     */
    private Integer InverterCounts;
    /**
     * 故障运行
     */
    private Integer faultOperation;
    
    /**
     * 辐照度	
     */
    private double irradiance;
    private double directIrradiance;
    private double scateredIrradiance;
    private double inclinedIrradiance;
    /**
     * 温度
     */
    private double temperature;
    /**
     * 风速
     */
    private double windSpeed;
    /**
     * 组件温度
     */
    private double temperatureofModules;
    /**
     * 空气湿度
     */
    private double humidity;
    /**
     * 风向
     */
    private double windDirection;
    /**
     * 总报警次数
     */
    private Integer totalAlarmsCounts;
    /**
     * 实际故障次数
     */
    private Integer faultCounts;
    /**
     * 体检次数
     */
    private Integer physicalExaminationCounts;
    /**
     * 故障排除比例
     */
    private double faultSaved;
    /**
     * 最后一次体检时间
     */
    private Date lastPhysicalExaminationTime;
    
    
	
	public Integer getGenerationDailyId() {
		return generationDailyId;
	}
	public void setGenerationDailyId(Integer generationDailyId) {
		this.generationDailyId = generationDailyId;
	}
	public double getDirectIrradiance() {
		return directIrradiance;
	}
	public void setDirectIrradiance(double directIrradiance) {
		this.directIrradiance = directIrradiance;
	}
	public double getScateredIrradiance() {
		return scateredIrradiance;
	}
	public void setScateredIrradiance(double scateredIrradiance) {
		this.scateredIrradiance = scateredIrradiance;
	}
	public double getInclinedIrradiance() {
		return inclinedIrradiance;
	}
	public void setInclinedIrradiance(double inclinedIrradiance) {
		this.inclinedIrradiance = inclinedIrradiance;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public Integer getPowerId() {
		return powerId;
	}
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	public double getRadiantExposure() {
		return radiantExposure;
	}
	public void setRadiantExposure(double radiantExposure) {
		this.radiantExposure = radiantExposure;
	}
	public double getGenerationYear() {
		return generationYear;
	}
	public void setGenerationYear(double generationYear) {
		this.generationYear = generationYear;
	}
	public double getGenerationMonth() {
		return generationMonth;
	}
	public void setGenerationMonth(double generationMonth) {
		this.generationMonth = generationMonth;
	}
	public double getRainFall() {
		return rainFall;
	}
	public void setRainFall(double rainFall) {
		this.rainFall = rainFall;
	}
	public Integer getRunDays() {
		return runDays;
	}
	public void setRunDays(Integer runDays) {
		this.runDays = runDays;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public double getInstallCapacity() {
		return installCapacity;
	}
	public void setInstallCapacity(double installCapacity) {
		this.installCapacity = installCapacity;
	}
	public Integer getTotalAlarmsCounts() {
		return totalAlarmsCounts;
	}
	public void setTotalAlarmsCounts(Integer totalAlarmsCounts) {
		this.totalAlarmsCounts = totalAlarmsCounts;
	}
	public Integer getFaultCounts() {
		return faultCounts;
	}
	public void setFaultCounts(Integer faultCounts) {
		this.faultCounts = faultCounts;
	}
	public double getFaultSaved() {
		return faultSaved;
	}
	public void setFaultSaved(double faultSaved) {
		this.faultSaved = faultSaved;
	}
	public Integer getPhysicalExaminationCounts() {
		return physicalExaminationCounts;
	}
	public void setPhysicalExaminationCounts(Integer physicalExaminationCounts) {
		this.physicalExaminationCounts = physicalExaminationCounts;
	}
	public Date getLastPhysicalExaminationTime() {
		return lastPhysicalExaminationTime;
	}
	public void setLastPhysicalExaminationTime(Date lastPhysicalExaminationTime) {
		this.lastPhysicalExaminationTime = lastPhysicalExaminationTime;
	}
	public double getTemperatureofModules() {
		return temperatureofModules;
	}
	public void setTemperatureofModules(double temperatureofModules) {
		this.temperatureofModules = temperatureofModules;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(double windDirection) {
		this.windDirection = windDirection;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getGenerationDaily() {
		return generationDaily;
	}
	public void setGenerationDaily(double generationDaily) {
		this.generationDaily = generationDaily;
	}
	public Integer getInverterCounts() {
		return InverterCounts;
	}
	public void setInverterCounts(Integer inverterCounts) {
		InverterCounts = inverterCounts;
	}
	public Integer getFaultOperation() {
		return faultOperation;
	}
	public void setFaultOperation(Integer faultOperation) {
		this.faultOperation = faultOperation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCommunicationAnomaly() {
		return communicationAnomaly;
	}
	public void setCommunicationAnomaly(Integer communicationAnomaly) {
		this.communicationAnomaly = communicationAnomaly;
	}
	public double getGeneratedActivePower() {
		return generatedActivePower;
	}
	public void setGeneratedActivePower(double generatedActivePower) {
		this.generatedActivePower = generatedActivePower;
	}
	public PointHistoryVO[] getGenerationDailyList() {
		return generationDailyList;
	}
	public void setGenerationDailyList(PointHistoryVO[] generationDailyList) {
		this.generationDailyList = generationDailyList;
	}
	public double getPr() {
		return pr;
	}
	public void setPr(double pr) {
		this.pr = pr;
	}
	public double getIrradiance() {
		return irradiance;
	}
	public void setIrradiance(double irradiance) {
		this.irradiance = irradiance;
	}
	public Integer getStop() {
		return stop;
	}
	public void setStop(Integer stop) {
		this.stop = stop;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
