package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.ThirdEquipmentsPO;
import com.yunengzhe.PVMTS.domain.po.ThirdPointsPO;

public class ThirdVO {
	private List<ThirdEquipmentsPO> eqlist;
	private List<ThirdPointsPO> plist;
	public List<ThirdEquipmentsPO> getEqlist() {
		return eqlist;
	}
	public void setEqlist(List<ThirdEquipmentsPO> eqlist) {
		this.eqlist = eqlist;
	}
	public List<ThirdPointsPO> getPlist() {
		return plist;
	}
	public void setPlist(List<ThirdPointsPO> plist) {
		this.plist = plist;
	}
	
}
