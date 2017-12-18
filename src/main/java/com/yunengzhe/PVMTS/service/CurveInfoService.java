package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.CurveInfoDao;
import com.yunengzhe.PVMTS.domain.po.CurveInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.commons.authentication.HttpSessionUtil;


@Service("curveInfoService")
public class CurveInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CurveInfoService.class);
	
	@Autowired
	private CurveInfoDao curveInfoDaoImpl;
	/**
	 * 插入曲线
	 * @param curveInfoPO
	 * @return
	 */
	public CurveInfoPO addCurve(CurveInfoPO curveInfoPO) {
		Date date = new Date();
		curveInfoPO.setCreateTime(date);
		curveInfoPO.setUpdateTime(date);
		curveInfoDaoImpl.insert(curveInfoPO);
		return curveInfoPO;
	}
	/**
	 * 删除曲线
	 * @param id
	 */
	public void deleteCurve(Integer id) {
		curveInfoDaoImpl.delete(id);
	}
	/**
	 * 获取曲线详情
	 * @param curveId
	 */
	public List<CurveDetailVO> getCurveDetail(String ids) {
		List<CurveDetailVO> list = curveInfoDaoImpl.getCurveDetail(ids);
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			for(int i=0;i<list.size();i++){
				CurveDetailVO cv = list.get(i);
				for(int j=0;j<cv.getPointList().size();j++){
					cv.getPointList().get(j).setEquipmentName(cv.getPointList().get(j).getEquipmentNameEn());
					cv.getPointList().get(j).setEquipmentNumber(cv.getPointList().get(j).getEquipmentNumberEn());
				}
			}
		}
		return list;
	}
	/**
	 * 更新曲线
	 * @param curveInfoPO
	 * @return
	 */
	public void updateCurve(CurveInfoPO curveInfoPO) {
		Date date = new Date();
		curveInfoPO.setUpdateTime(date);
		curveInfoDaoImpl.update4Selective(curveInfoPO);
	}
	/**
	 * 获取曲线列表
	 * @param powerStationId
	 * @param name 
	 * @param page
	 * @param pagesize
	 * @param createuser 
	 * @param uids 
	 * @return
	 */
	public PageBean<CurveInfoPO> getCurveList(Integer powerStationId, String name, Integer page, Integer pagesize, Integer createuser, String uids) {
		PageBean<CurveInfoPO> pageBean = new PageBean<CurveInfoPO>();
		Map map = new HashMap();
		map.put("powerStationId", powerStationId);
		if(StringUtils.isNotBlank(name)){
			map.put("name", name.trim());
		}
		if(StringUtils.isNotBlank(uids)){
			map.put("uids", uids.trim());
		}
		map.put("createuser", createuser);
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		curveInfoDaoImpl.find4Page(pageBean, map,true, "create_time", SortBy.倒序);;
		return pageBean;
	}
	 
}
