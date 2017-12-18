package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ColorInfoDao;
import com.yunengzhe.PVMTS.domain.po.ColorInfoPO;


@Service("colorInfoService")
public class ColorInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ColorInfoService.class);
	
	@Autowired
	private ColorInfoDao colorInfoDaoImpl;
	/**
	 * 获取颜色信息
	 * @return
	 */
	public List<ColorInfoPO> getColorList() {
		return colorInfoDaoImpl.findAll();
	}
	 
}
