package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.FaultInfoDao;
import com.yunengzhe.PVMTS.domain.po.FaultInfoPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.util.TimeUtil;


@Service("faultInfoService")
public class FaultInfoService {
	private static int ORDER_NUM=001;
	private static final Logger logger = LoggerFactory.getLogger(FaultInfoService.class);
	
	@Autowired
	private FaultInfoDao faultInfoDaoImpl;
	@Autowired
	private PowerStationService powerStationService;
	/**
	 * 请求故障详情接口
	 * 参数故障表id
	 * 返回故障对象
	 */
	public FaultInfoPO getFaultInfo(int id) {
		return faultInfoDaoImpl.get(id);
	}
	 
	/**
	 * 修改故障状态
	 * 参数  故障表id ， 故障状态 0默认未分配 1 已分配  2 已解决 3 未解决
	 */
	public boolean updateFaultInfo(int id,int status){
		FaultInfoPO faultInfo = new FaultInfoPO();
		faultInfo.setId(id);
		faultInfo.setFaultStatus(status);
		return faultInfoDaoImpl.update4Selective(faultInfo)==1;
	}
	/**
	 * 修改故障信息
	 * 参数  说明  附件
	 */
	public boolean updateFault(FaultInfoPO faultInfoPO) {
		return faultInfoDaoImpl.update4Selective(faultInfoPO)==1;
	}
	/**
	 * 
	 * @param faultInfoPO
	 * @return
	 */
	public boolean insert(FaultInfoPO faultInfoPO) {
		String orderNumTemp ="GZ"+TimeUtil.date2String(new Date(),"yyyyMMddHHmm")+ORDER_NUM;
		//1 来源于报警    2 来源于用户
		faultInfoPO.setFaultNumber(orderNumTemp);
		faultInfoPO.setFaultStatus(0);
		boolean flag = faultInfoDaoImpl.insert(faultInfoPO)==1;
		ORDER_NUM++;
		return flag;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultListVO getUserFaultList(int userId, Integer page, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		PageBean<FaultInfoPO> pageBean = new PageBean<FaultInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		map.put("userId", userId);
		faultInfoDaoImpl.find4Page(pageBean, map);
		List<FaultInfoPO> objList = pageBean.getObjList();
		for (FaultInfoPO faultInfoPO : objList) {
			faultInfoPO.setPowerStationBaseInfo(powerStationService.getPowerStationInfo(faultInfoPO.getPowerStationId()+""));
		}
		ResultListVO vo = new ResultListVO();
		vo.setCounts(pageBean.getTotalRows());
		vo.setResultList(objList);
		logger.debug(vo.toString());
		return vo;
	}

	public List<FaultInfoPO> getFaultListBySearch(String search) {
		// TODO Auto-generated method stub
		return faultInfoDaoImpl.getFaultListBySearch(search);
	}

	public List<FaultInfoPO> selectHistoryList(String search) {
		// TODO Auto-generated method stub
		return faultInfoDaoImpl.selectHistoryList(search);
	}
	
	
}
