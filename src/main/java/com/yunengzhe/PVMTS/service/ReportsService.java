package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ReportsDao;
import com.yunengzhe.PVMTS.dao.ReportsPointsDao;
import com.yunengzhe.PVMTS.domain.dto.historyAndReport.ReportsDTO;
import com.yunengzhe.PVMTS.domain.po.ReportsPO;
import com.yunengzhe.PVMTS.domain.po.ReportsPointsPO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;
import com.yunengzhe.PVMTS.domain.vo.CurvePointDetailVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

/**
 * @ClassName:ReportsService
 * @Description:TODO(报表管理)
 * @author chenguiyang
 * @date 2017年6月26日上午11:28:44
 */
@Service("reportsService")
public class ReportsService {

	private static final Logger logger = LoggerFactory.getLogger(ReportsService.class);
	
	@Autowired
	private ReportsDao reportsDaoImpl;
	@Autowired
	private ReportsPointsDao reportsPointsDaoImpl;
	/**
	 * @param createuser 
	 * @Title:getReportsList
	 * @Description:TODO(获取报表列表) 
	 * @param 
	 * @return void 
	 * name=报表名称  powerstationid 电站id  page 当前页   pagesize 每页条数
	 * @throws
	 */
	public PageBean<ReportsPO> getReportsList(Integer powerStationId, String name, Integer page, Integer pagesize, Integer createuser){
		PageBean<ReportsPO> pageBean = new PageBean<ReportsPO>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("powerstationid", powerStationId);
		if(createuser!=null){
			map.put("createuser", createuser);
		}
//		map.put("reportname", name);
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		reportsDaoImpl.find4Page(pageBean, map,true, "createtime", SortBy.倒序);
		System.out.println("----->"+pageBean.getTotalRows());
		String local = (String)HttpSessionUtil.getAttribute("local");
		if("en_US".equals(local)){
			List<ReportsPO> list = pageBean.getObjList();
			for(int i=0;i<list.size();i++){
				ReportsPO rp = list.get(i);
				for(int j=0;j<rp.getPointList().size();j++){
					rp.getPointList().get(j).setAnaloginputName(rp.getPointList().get(j).getPointEnglishName());
				}
			}
		}
		return pageBean;
	}
	
	
	/**
	 * @return 
	 * @param url 
	 * @Title:saveReport
	 * @Description:TODO(保存报表信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public ReportsPO saveReport(ReportsDTO param, String url){
		ReportsPO reportsPO = new ReportsPO();
		try {
			reportsPO.setPowerstationid(param.getPowerstationId());
			reportsPO.setReportname(param.getReportname());
			reportsPO.setStarttime(param.getStartTime());
			reportsPO.setEndtime(param.getEndTime());
			reportsPO.setCreatetime(new Date());
			reportsPO.setUpdatetime(new Date());
			reportsPO.setCreateuser(param.getUserId());
			reportsPO.setUrl(url);
			reportsDaoImpl.insert(reportsPO);
			List<CurvePointDetailVO> curvePoint = param.getCurvePoint();
			if(curvePoint!=null){
				if(curvePoint.size()>0){
					for (CurvePointDetailVO curvePointDetailVO : curvePoint) {
						if(curvePointDetailVO.getAnaloginputId()!=0&&curvePointDetailVO.getAnaloginputId()!=null){
							ReportsPointsPO reportPointsPO = new ReportsPointsPO();
							reportPointsPO.setReportid(reportsPO.getId());
							reportPointsPO.setAnaloginputId(curvePointDetailVO.getAnaloginputId());
							reportPointsPO.setAnaloginputColor(curvePointDetailVO.getColorCode());
							reportPointsPO.setAnaloginputName(curvePointDetailVO.getPointName());
							reportPointsPO.setCreateTime(new Date());
							reportPointsPO.setUpdateTime(new Date());
							reportsPointsDaoImpl.insert(reportPointsPO);
						}
					}
				}
			}else{
				for(ReportsPointsPO reportPoints : param.getPoints()){
					if(reportPoints.getAnaloginputId()!=0&&reportPoints.getAnaloginputId()!=null){
						ReportsPointsPO reportPointsPO = new ReportsPointsPO();
						reportPointsPO.setReportid(reportsPO.getId());
						reportPointsPO.setAnaloginputId(reportPoints.getAnaloginputId());
						reportPointsPO.setAnaloginputColor(reportPoints.getAnaloginputColor());
						reportPointsPO.setAnaloginputName(reportPoints.getAnaloginputName());
						reportPointsPO.setCreateTime(new Date());
						reportPointsPO.setUpdateTime(new Date());
						reportsPointsDaoImpl.insert(reportPointsPO);
					}
				}
			}
		} catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		return reportsPO;
	}
	
	
	/**
	 * @Title:getReportDetails
	 * @Description:TODO(获取单条报表的详情) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public ReportsPO getReportDetails(int reportId){
		ReportsPO result = reportsDaoImpl.findUniqueBy("id", reportId);
		return result;
	}
	public ReportsPO get(int id){
		ReportsPO result = reportsDaoImpl.get(id);
		return result;
	}


	public boolean delete(int reportId) {
		boolean flag = reportsDaoImpl.delete(reportId)==1;
		ReportsPointsPO reportsPointsPO = new ReportsPointsPO();
		reportsPointsPO.setReportid(reportId);
		reportsPointsDaoImpl.delete(reportsPointsPO);
		return flag;
	}
}
