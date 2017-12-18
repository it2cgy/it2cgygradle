package com.yunengzhe.PVMTS.domain.vo;

/**
 * @ClassName:IvEquipmentVO
 * @Description:TODO(iv多通道测试返回)
 * @author chenguiyang
 * @date 2017年7月11日下午2:10:10
 */
public class IvEquipmentVO {
    private Integer id;
    private String name;
    private Integer farmid;
    private String equipnum;
    private String model;
    
    private float vVal;
    private float aVal;
    private float wVal;
    private float monthVal;
    private float yearVal;
    private float dayVal;
    private float totalVal;
    private float unifiedPowerGenerationDaily;
    private float unifiedPowerGenerationMonth;
    private float unifiedPowerGenerationYear;
    private float unifiedGenerationGross;
    
    private double ratedPower;//装机容量
	
	
	public double getRatedPower() {
		return ratedPower;
	}
	public void setRatedPower(double ratedPower) {
		this.ratedPower = ratedPower;
	}
    
    
	public float getUnifiedPowerGenerationDaily() {
		return unifiedPowerGenerationDaily;
	}
	public void setUnifiedPowerGenerationDaily(float unifiedPowerGenerationDaily) {
		this.unifiedPowerGenerationDaily = unifiedPowerGenerationDaily;
	}
	public float getUnifiedPowerGenerationMonth() {
		return unifiedPowerGenerationMonth;
	}
	public void setUnifiedPowerGenerationMonth(float unifiedPowerGenerationMonth) {
		this.unifiedPowerGenerationMonth = unifiedPowerGenerationMonth;
	}
	public float getUnifiedPowerGenerationYear() {
		return unifiedPowerGenerationYear;
	}
	public void setUnifiedPowerGenerationYear(float unifiedPowerGenerationYear) {
		this.unifiedPowerGenerationYear = unifiedPowerGenerationYear;
	}
	public float getUnifiedGenerationGross() {
		return unifiedGenerationGross;
	}
	public void setUnifiedGenerationGross(float unifiedGenerationGross) {
		this.unifiedGenerationGross = unifiedGenerationGross;
	}
	public float getvVal() {
		return vVal;
	}
	public void setvVal(float vVal) {
		this.vVal = vVal;
	}
	public float getaVal() {
		return aVal;
	}
	public void setaVal(float aVal) {
		this.aVal = aVal;
	}
	public float getwVal() {
		return wVal;
	}
	public void setwVal(float wVal) {
		this.wVal = wVal;
	}
	public float getMonthVal() {
		return monthVal;
	}
	public void setMonthVal(float monthVal) {
		this.monthVal = monthVal;
	}
	public float getYearVal() {
		return yearVal;
	}
	public void setYearVal(float yearVal) {
		this.yearVal = yearVal;
	}
	public float getDayVal() {
		return dayVal;
	}
	public void setDayVal(float dayVal) {
		this.dayVal = dayVal;
	}
	public float getTotalVal() {
		return totalVal;
	}
	public void setTotalVal(float totalVal) {
		this.totalVal = totalVal;
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
	public Integer getFarmid() {
		return farmid;
	}
	public void setFarmid(Integer farmid) {
		this.farmid = farmid;
	}
	public String getEquipnum() {
		return equipnum;
	}
	public void setEquipnum(String equipnum) {
		this.equipnum = equipnum;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
    
    
}
