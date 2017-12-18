package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.MenusDao;
import com.yunengzhe.PVMTS.domain.po.MenusPO;


@Service("menusService")
public class MenusService {

	private static final Logger logger = LoggerFactory.getLogger(MenusService.class);
	
	@Autowired
	private MenusDao menusDaoImpl;
	
	
	/**
	 * @Title:getMenusList
	 * @Description:TODO(获取菜单列表) 
	 * @param @return
	 * @return List<MenusPO>
	 * @throws
	 */
	public List<MenusPO> getMenusList(){
		return menusDaoImpl.findAll();
	}
	 
}
