package com.yunengzhe.PVMTS.domain.vo;
/**
 * 气象站气象数据
 * @author dell
 *
 */
public class WeatherInfoVO {
	private double temperature;//环境温度
	private double radiantExposure;//日辐射总量
	private double temperatureofModules;//
	private double humidity;//湿度
	private double pressure;//
	private double windSpeed;//风速
	private double windDirection;//风向
	private double irradiance;//总辐射强度
	private double sunshineHours;//日照时数累计
	private double directDailyAccumulation;//直表日累计
	private double scatteredDailyCumulative;//散表日累计
	private double minuteWindSpeed2;//
	private double minuteWindSpeed10;
	private double directRadiation;//直辐射
	private double scateredRadiation;//散辐射
	private double collectorPower;//
	private double rainFall;//雨量累计值
	private double inclinedIrradiance;//斜面辐照强度
	private double inclinedExposure;//斜面总辐射量
	private double directIrradiance;
    private double scateredIrradiance;
    private double radiantExposureMonth;//当月辐射量
    private double inclinedExposureMonth;//当月斜面辐射量
    private double directExposureMonth;//当月直辐射量
    private double scateredExposureMonth;//当月散辐射量
    private double scatteredExposure;//日散射辐照量
	private double directExposure;//日直射辐照量
	private double radiantExposureYear;//当年辐射量
	private double directExposureYear ;//当年直射量
	private double scateredExposureYear;//当年散射量
	private double inclinedExposureYear;//当年斜面辐射量
	private double radiantExposureTotal;//总累计辐射量
	private double inclinedExposureTotal;//总累计斜面辐射量
	
	
    
	public double getInclinedExposureTotal() {
		return inclinedExposureTotal;
	}
	public void setInclinedExposureTotal(double inclinedExposureTotal) {
		this.inclinedExposureTotal = inclinedExposureTotal;
	}
	public double getRadiantExposureTotal() {
		return radiantExposureTotal;
	}
	public void setRadiantExposureTotal(double radiantExposureTotal) {
		this.radiantExposureTotal = radiantExposureTotal;
	}
	public double getInclinedExposure() {
		return inclinedExposure;
	}
	public void setInclinedExposure(double inclinedExposure) {
		this.inclinedExposure = inclinedExposure;
	}
	public double getRadiantExposureYear() {
		return radiantExposureYear;
	}
	public void setRadiantExposureYear(double radiantExposureYear) {
		this.radiantExposureYear = radiantExposureYear;
	}
	public double getDirectExposureYear() {
		return directExposureYear;
	}
	public void setDirectExposureYear(double directExposureYear) {
		this.directExposureYear = directExposureYear;
	}
	public double getScateredExposureYear() {
		return scateredExposureYear;
	}
	public void setScateredExposureYear(double scateredExposureYear) {
		this.scateredExposureYear = scateredExposureYear;
	}
	public double getInclinedExposureYear() {
		return inclinedExposureYear;
	}
	public void setInclinedExposureYear(double inclinedExposureYear) {
		this.inclinedExposureYear = inclinedExposureYear;
	}
	public double getScatteredExposure() {
		return scatteredExposure;
	}
	public void setScatteredExposure(double scatteredExposure) {
		this.scatteredExposure = scatteredExposure;
	}
	public double getDirectExposure() {
		return directExposure;
	}
	public void setDirectExposure(double directExposure) {
		this.directExposure = directExposure;
	}
	public double getRadiantExposureMonth() {
		return radiantExposureMonth;
	}
	public void setRadiantExposureMonth(double radiantExposureMonth) {
		this.radiantExposureMonth = radiantExposureMonth;
	}
	public double getInclinedExposureMonth() {
		return inclinedExposureMonth;
	}
	public void setInclinedExposureMonth(double inclinedExposureMonth) {
		this.inclinedExposureMonth = inclinedExposureMonth;
	}
	public double getDirectExposureMonth() {
		return directExposureMonth;
	}
	public void setDirectExposureMonth(double directExposureMonth) {
		this.directExposureMonth = directExposureMonth;
	}
	public double getScateredExposureMonth() {
		return scateredExposureMonth;
	}
	public void setScateredExposureMonth(double scateredExposureMonth) {
		this.scateredExposureMonth = scateredExposureMonth;
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
	public double getRainFall() {
		return rainFall;
	}
	public void setRainFall(double rainFall) {
		this.rainFall = rainFall;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getRadiantExposure() {
		return radiantExposure;
	}
	public void setRadiantExposure(double radiantExposure) {
		this.radiantExposure = radiantExposure;
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
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public double getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(double windDirection) {
		this.windDirection = windDirection;
	}
	public double getIrradiance() {
		return irradiance;
	}
	public void setIrradiance(double irradiance) {
		this.irradiance = irradiance;
	}
	public double getSunshineHours() {
		return sunshineHours;
	}
	public void setSunshineHours(double sunshineHours) {
		this.sunshineHours = sunshineHours;
	}
	public double getDirectDailyAccumulation() {
		return directDailyAccumulation;
	}
	public void setDirectDailyAccumulation(double directDailyAccumulation) {
		this.directDailyAccumulation = directDailyAccumulation;
	}
	public double getScatteredDailyCumulative() {
		return scatteredDailyCumulative;
	}
	public void setScatteredDailyCumulative(double scatteredDailyCumulative) {
		this.scatteredDailyCumulative = scatteredDailyCumulative;
	}
	public double getMinuteWindSpeed2() {
		return minuteWindSpeed2;
	}
	public void setMinuteWindSpeed2(double minuteWindSpeed2) {
		this.minuteWindSpeed2 = minuteWindSpeed2;
	}
	public double getMinuteWindSpeed10() {
		return minuteWindSpeed10;
	}
	public void setMinuteWindSpeed10(double minuteWindSpeed10) {
		this.minuteWindSpeed10 = minuteWindSpeed10;
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
	public double getCollectorPower() {
		return collectorPower;
	}
	public void setCollectorPower(double collectorPower) {
		this.collectorPower = collectorPower;
	}
	
}
