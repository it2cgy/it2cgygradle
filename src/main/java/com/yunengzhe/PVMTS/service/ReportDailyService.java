package com.yunengzhe.PVMTS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.ReportDailyDao;
import com.yunengzhe.PVMTS.domain.po.ReportDailyPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;


@Service("reportDailyService")
public class ReportDailyService {

	private static final Logger logger = LoggerFactory.getLogger(ReportDailyService.class);
	
	@Autowired
	private ReportDailyDao reportDailyDaoImpl;

	public ResultListVO<ReportDailyPO> getReportDailyInfo(Integer page,
			Integer pagesize) {
		ResultListVO<ReportDailyPO>  results = new ResultListVO<ReportDailyPO>();
		PageBean<ReportDailyPO> pageBean = new PageBean<ReportDailyPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		reportDailyDaoImpl.find4Page(pageBean, null, "reporttime", SortBy.倒序);
		results.setResultList(pageBean.getObjList());
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	
	
	public boolean addReportDaily(ReportDailyPO reportDailyPO){
		return reportDailyDaoImpl.insert(reportDailyPO)>0;
	}


	public boolean deleteReportDaily(Integer id) {
		return reportDailyDaoImpl.delete(id)>0;
	}
	
	public boolean updateReportDaily(ReportDailyPO reportDailyPO) {
		return reportDailyDaoImpl.update(reportDailyPO)>0;
	}


	public ReportDailyPO get(Integer id) {
		return reportDailyDaoImpl.get(id);
	}
	
	
}
