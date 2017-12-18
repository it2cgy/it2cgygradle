package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.DeviceRatedIvDao;
import com.yunengzhe.PVMTS.domain.dto.RatesDTO;
import com.yunengzhe.PVMTS.domain.dto.RatesDTO.RateDTO;
import com.yunengzhe.PVMTS.domain.po.DeviceRatedIvPO;
import com.yunengzhe.PVMTS.domain.po.NormalizationPO;


@Service("deviceRatedIvService")
public class DeviceRatedIvService {

	private static final Logger logger = LoggerFactory.getLogger(DeviceRatedIvService.class);
	
	@Autowired
	private DeviceRatedIvDao deviceRatedIvDaoImpl;
	
	/**
	 * <p>List<DeviceRatedIvPO></p>
	 * <p>Description:获取所有iv通道额定功率列表</p>
	 * @author ynz
	 */
	public List<NormalizationPO> getRatedIvList(){
		List<DeviceRatedIvPO> result = deviceRatedIvDaoImpl.findAll();
		List<NormalizationPO> list = new ArrayList<NormalizationPO>();
		if(result.size()>0){
			for(DeviceRatedIvPO d : result){
				NormalizationPO p = new NormalizationPO();
				p.setId(d.getId());
				p.setRatedPower(d.getRatedPower());
				p.setEquipmentDescription(d.getEquipmentDescription());
				p.setEquipmentId(d.getEquipmentId());
				p.setEquipmentType(d.getEquipmentType());
				list.add(p);
			}
		}
		return list;
	}
	
	public void updateRate(RatesDTO r){
		if(r.getRates().size()>0){
			for(RateDTO param : r.getRates()){
				DeviceRatedIvPO p = new DeviceRatedIvPO();
				p.setId(param.getId());
				p.setRatedPower(param.getValue());
				deviceRatedIvDaoImpl.update4Selective(p);
			}
		}
	}
	 
}
