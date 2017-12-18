package com.yunengzhe.PVMTS.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.historyAndReport.ReportsDTO;
import com.yunengzhe.PVMTS.domain.dto.monitor.PointsReqDTO;
import com.yunengzhe.PVMTS.domain.po.ReportsPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.CurvePointDetailVO;
import com.yunengzhe.PVMTS.domain.vo.MonitorHistoryWordVO;
import com.yunengzhe.PVMTS.domain.vo.ReportDownloadVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.ReportsService;
import com.yunengzhe.PVMTS.util.CreateDateExcel;
import com.yunengzhe.PVMTS.util.TimeUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.util.BigDecimalUtils;
import com.yunengzhe.commons.util.PropertiesUtil;



/**
 * @ClassName:ReportsController
 * @Description:TODO(报表管理处理)
 * @author chenguiyang
 * @date 2017年6月26日上午11:07:18
 */
@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);
	
	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private StationMonitorController stationMonitorController;
	@Autowired
	private PointInfoService pointInfoService;
	Map<String,Object> reportInfo = null;
	/**
	 * @Title:getReports
	 * @Description:TODO(获取报表列表) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/getReports",method=RequestMethod.POST)
	@ResponseBody
	public Object getReports(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Integer powerStationId = Integer.valueOf(map.get("powerStationId")+"");
			String name = map.get("name");
			Integer page = Integer.valueOf(map.get("page")+"");
			Integer pagesize = Integer.valueOf(map.get("pagesize")+"");
			Integer isThird = Integer.valueOf(map.get("isThird")+"");
			if(page==null){
				page = 0;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			Integer createuser = null;
			if(isThird == 1){
				createuser = user.getUserid();
			}
			PageBean<ReportsPO> pageBean = reportsService.getReportsList(powerStationId,name,page,pagesize,createuser);
			List<ReportsPO> list = pageBean.getObjList();
			logger.debug(list.toString());
//			if(list.size()==0){
//				RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "错误请求");
//			}
//			String ids = "";
//			for(int i=0;i<list.size();i++){
//				if(i==0){
//					ids+= list.get(i).getId();	
//				}else{
//					ids+= ","+list.get(i).getId();
//				}
//			}
//			List<ReportsPointsPO> pointList = reportsPointsService.getPointsList(ids);
			return RestResponse.responseList(request, pageBean.getTotalRows(), list);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * @Title:saveReport
	 * @Description:TODO(保存报表信息到数据库中) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/saveReport",method=RequestMethod.POST)
	@ResponseBody
	public Object saveReport(@RequestBody ReportsDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			param.setUserId(user.getUserid());
			if(StringUtils.isBlank(param.getReportname())){
				return RestResponse.responseCode(0, "报表名称不能为空！");
			}
			
			if(param.getStartTime()==null){
				return RestResponse.responseCode(0, "开始时间不能为空！");
			}
			if(param.getEndTime()==null){
				return RestResponse.responseCode(0, "截止时间不能为空！");
			}
			if(param.getPowerstationId()==null){
				return RestResponse.responseCode(0, "电站id不能为空！");
			}
			Map<String,Object> map = new HashMap<String,Object>();  //给format传数据
			PointsReqDTO reqParam = new PointsReqDTO();//查数据的参数
			reqParam.setAnaloginputIds(param.getAnaloginputIds());
			reqParam.setEndTime(param.getEndTime());
			reqParam.setHaveReal(param.getHaveReal());
			reqParam.setMinutesSpan(param.getMinutesSpan());
			reqParam.setStartTime(param.getStartTime());
			List<List<String>> y = new ArrayList<List<String>>();
			List<List<String>> titleList = new ArrayList<List<String>>();
			String[] names = param.getReportData().split("\\],\\[");//列名称
			int colmun=0;
			for(int i = 0;i<names.length;i++){
				if(i==0){
					String[] arr = names[i].replaceAll("\\]", "").replaceAll("\\[","").split(",");
					colmun=arr.length;
					List<String> title =  new ArrayList<String>();
					for(int j = 0;j<arr.length;j++){
						title.add(arr[j].replaceAll("\"", ""));
					}
					titleList.add(title);
				}else{
					String[] arr = names[i].replaceAll("\\]", "").replaceAll("\\[","").split(",");
					colmun=arr.length;
					List<String> val =  new ArrayList<String>();
					List<String> title =  new ArrayList<String>();
					for(int j = 0;j<arr.length;j++){
						val.add(arr[j].replaceAll("\"", "").replaceAll("--", ""));
					}
					y.add(val);
				}
			}
			if(names.length>0){
				if(y.size()>0){
					map.put("list", y);
					map.put("list2", titleList);
					map.put("column", y.get(0).size());
					map.put("row", y.size()+100);
					File file = null;  
					// 调用工具类WordGenerator的createDoc方法生成Word文档  
					file = CreateDateExcel.createExcel(map,"数据查询记录", "longieb",null);
					String url = file.getAbsolutePath();
					ReportsPO saveReport = reportsService.saveReport(param,url);//增加路劲信息保存到报表信息
					return RestResponse.responseOk(saveReport.getId());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
		return response;
	}
	
	/**
	 * @Title:saveReport
	 * @Description:TODO(保存报表信息到数据库中) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/saveDataReport",method=RequestMethod.POST)
	@ResponseBody
	public Object saveDataReport(@RequestBody ReportsDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			param.setUserId(user.getUserid());
			if(StringUtils.isBlank(param.getReportname())){
				return RestResponse.responseCode(0, "报表名称不能为空！");
			}
			
			if(param.getStartTime()==null){
				return RestResponse.responseCode(0, "开始时间不能为空！");
			}
			if(param.getEndTime()==null){
				return RestResponse.responseCode(0, "截止时间不能为空！");
			}
			if(param.getPowerstationId()==null){
				return RestResponse.responseCode(0, "电站id不能为空！");
			}
//			if(param.getPoints().size()==0){
//				return RestResponse.responseCode(0, "测点列表不能为空！");
//			}
			Map<String,Object> map = new HashMap<String,Object>();  //给format传数据
			
			PointsReqDTO reqParam = new PointsReqDTO();//查数据的参数
			reqParam.setAnaloginputIds(param.getAnaloginputIds());
			reqParam.setEndTime(param.getEndTime());
			reqParam.setHaveReal(param.getHaveReal());
			reqParam.setMinutesSpan(param.getMinutesSpan());
			reqParam.setStartTime(param.getStartTime());
			
			RestResponseVO pointHistoryPoints = (RestResponseVO)stationMonitorController.getPointHistoryPointsRedress(reqParam, request, response);
			List<MonitorHistoryPointData> data = (List<MonitorHistoryPointData>)pointHistoryPoints.getData();//获取数据
			List<MonitorHistoryWordVO> list = new ArrayList<MonitorHistoryWordVO>();
			for (MonitorHistoryPointData monitorHistoryPointData : data) {
				MonitorHistoryWordVO monitorHistoryWordVO = new MonitorHistoryWordVO();
				List<MonitorHistoryData> historyDatas = monitorHistoryPointData.getHistoryDatas();
				for (MonitorHistoryData monitorHistoryData : historyDatas) {
					monitorHistoryWordVO.setName(param.getPoints().get(0).getAnaloginputName());
					monitorHistoryWordVO.setTime(monitorHistoryData.getTime());
					monitorHistoryWordVO.setValue(monitorHistoryData.getValue());
					list.add(monitorHistoryWordVO);
				}
			}
			if(list!=null){
				List<List<String>> x = new ArrayList<List<String>>();
				List<String> listvalue = new ArrayList<String>();
				listvalue.add("时间");
				for (int i = 0; i < list.size(); i++) {
					if(i==0){
						listvalue.add(list.get(i).getTime());
						x.add(listvalue);
						listvalue = new ArrayList<String>();
						listvalue.add(list.get(i).getName());
					}
					int l = i - 1;
					if(l<0){
						l = 0;
					}
					if(list.get(i).getName()!=list.get(l).getName()){
						x.add(listvalue);
						listvalue = new ArrayList<String>();
						listvalue.add(list.get(i).getName());
						listvalue.add(BigDecimalUtils.round(Double.parseDouble(list.get(i).getValue()+""), 3)+"");
					}else{
						listvalue.add(BigDecimalUtils.round(Double.parseDouble(list.get(i).getValue()+""), 3)+"");
					}
				}
				x.add(listvalue);
				List<List<String>> y = new ArrayList<List<String>>();
				for (int i = 0; i < x.get(0).size(); i++) {
					List<String> val =  new ArrayList<String>();
					for (int j = 0; j < x.size(); j++) {
						val.add(x.get(j).get(i));
					}
					y.add(val);
				}
				if(y.size()>0){
					map.put("list", y);
					map.put("column", y.get(0).size()+10);
					map.put("row", y.size()+10);
					File file = null;  
					InputStream fin = null;  
					Writer writer = null;
					OutputStream out = response.getOutputStream();  
					// 调用工具类WordGenerator的createDoc方法生成Word文档  
					file = CreateDateExcel.createExcel(map,"数据查询记录", "longieb",null);
					String url = file.getAbsolutePath();
					ReportsPO saveReport = reportsService.saveReport(param,url);//增加路劲信息保存到报表信息
					return RestResponse.responseOk(saveReport.getId());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
		return response;
	}
	/**
	 * @Title:getReportDetails
	 * @Description:TODO(根据报表id获取曲线信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/getReportDetails/{reportId}",method=RequestMethod.GET)
	public Object getReportDetails(@PathVariable int reportId,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		ReportsPO result = reportsService.getReportDetails(reportId);
		model.addAttribute("result", result);
		return "/report/reportDetails";
	}
	/**
	 * @Title:getReportDetails
	 * @Description:TODO删除
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/delete/{reportId}",method=RequestMethod.GET)
	@ResponseBody
	public Object delete(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int reportId){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			boolean delete = reportsService.delete(reportId);
			return RestResponse.responseOk(delete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:getReportDetails
	 * @Description:TODO下载
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/downLoadword")
	@ResponseBody
	public Object  downLoadword(int id,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			ReportsPO reportDetails = reportsService.get(id);
			String path = reportDetails.getUrl();
			if(path==null||path==""){
				return null;
			}
            // 以流的形式下载文件。
			File file = new File(path);
			if(!file.exists()){
				return RestResponse.responseError();
			}
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("utf-8");  
			response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(reportDetails.getReportname(), "UTF-8")+".xls");  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/msexcel");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		 	} catch (IOException ex) {
	            ex.printStackTrace();
	        }
		 return null;
	}
	/**
	 * @Title:getReportDetails
	 * @Description:TODO下载
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/downLoadwordReport")
	public HttpServletResponse  downLoadwordReport(ReportDownloadVO param,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();  //给format传数据
		List<ReportDownloadVO> list = new ArrayList<ReportDownloadVO>();
		String[] names = param.getName().split(",");
		String[] times = param.getTime().split(",");
		String[] values = param.getValue().split(",");
		for (int i = 0; i < names.length; i++) {
			ReportDownloadVO vo = new ReportDownloadVO();
			vo.setName(names[i]);
			vo.setTime(times[i]);
			vo.setValue(values[i]);
			list.add(vo);
		}
		if(list!=null){
			List<List<ReportDownloadVO>> x = new ArrayList<List<ReportDownloadVO>>();
			for (int i = 0; i < list.size(); i++) {
				List<ReportDownloadVO> a =  new ArrayList<ReportDownloadVO>();
				for (int j = 1; j < list.size()-i; j++) {
					if(list.get(i).getName() != list.get(j).getName()){
						ReportDownloadVO b = new ReportDownloadVO();
						b.setTime(list.get(j).getTime());
						b.setValue(list.get(j).getValue());
						b.setName(list.get(j).getName());
						a.add(b);
					}
				}
				x.add(a);
			}
		System.out.println(x.toString());
			
//			List<List<SaveReportDailyVO>> x = new ArrayList<List<SaveReportDailyVO>>();
//			for (int i = 0; i < 3; i++) {
//				List<SaveReportDailyVO> a = new ArrayList<SaveReportDailyVO>();
//				for (int j = 0; j < 2; j++) {
//					SaveReportDailyVO vo = new SaveReportDailyVO();
//					vo.setPowerId(j);
//					a.add(vo);
//				}
//				x.add(a);
//			}
		if(list.size()>0){
		map.put("list", list);
		String columeImage = param.getColumeImage();
		columeImage.replace(" ", "+");
		if(columeImage!=null&&columeImage!=""){
			String[] split = columeImage.split("data:image/png;base64,");
			map.put("columeImage", split[1]);
		}
		String curveImage = param.getCurveImage();
		curveImage.replace(" ", "+");
		if(curveImage!=null&&curveImage!=""){
			String[] split = curveImage.split("data:image/png;base64,");
			map.put("curveImage", split[1]);
		}
//		File file = null;  
//		try {
//        // 调用工具类WordGenerator的createDoc方法生成Word文档  
////    	file = CreateWord.createDoc(map,"历史查询记录", "longieb",new PropertiesUtil().getString("download_temp_path"));
//    	String path = file.getAbsolutePath();
//			if(path==null||path==""){
//				return null;
//			}
			// 以流的形式下载文件。
//			InputStream fis = new BufferedInputStream(new FileInputStream(path));
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
			// 清空response
//			response.reset();
			// 设置response的Header
//			response.setCharacterEncoding("utf-8");  
//			response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("历史查询记录", "UTF-8")+".doc");  
//			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//			response.setContentType("application/wsword");
//			toClient.write(buffer);
//			toClient.flush();
//			toClient.close();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}finally{
//			//if(file != null) file.delete();
//		}
		}
		}
		return response;
	}
	/**
	 * @Title:saveReport
	 * @Description:TODO(保存报表信息到数据库中) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/reportInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object reportInfo(@RequestBody ReportsDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			param.setUserId(user.getUserid());
			if(param.getStartTime()==null){
				return RestResponse.responseCode(0, "开始时间不能为空！");
			}
			if(param.getEndTime()==null){
				return RestResponse.responseCode(0, "截止时间不能为空！");
			}
			if(param.getPowerstationId()==null){
				return RestResponse.responseCode(0, "电站id不能为空！");
			}
			Map<String,Object> map = new HashMap<String,Object>();  //给format传数据
			PointsReqDTO reqParam = new PointsReqDTO();//查数据的参数
			reqParam.setAnaloginputIds(param.getAnaloginputIds());
			reqParam.setEndTime(param.getEndTime());
			reqParam.setHaveReal(param.getHaveReal());
			reqParam.setMinutesSpan(param.getMinutesSpan());
			reqParam.setStartTime(param.getStartTime());
			List<List<String>> y = new ArrayList<List<String>>();
			List<List<String>> titleList = new ArrayList<List<String>>();
			String[] names = param.getReportData().split("\\],\\[");//列名称
			int colmun=0;
			for(int i = 0;i<names.length;i++){
				if(i==0){
					String[] arr = names[i].replaceAll("\\]", "").replaceAll("\\[","").split(",");
					colmun=arr.length;
					List<String> title =  new ArrayList<String>();
					for(int j = 0;j<arr.length;j++){
						title.add(arr[j].replaceAll("\"", ""));
					}
					titleList.add(title);
				}else{
					String[] arr = names[i].replaceAll("\\]", "").replaceAll("\\[","").split(",");
					colmun=arr.length;
					List<String> val =  new ArrayList<String>();
					List<String> title =  new ArrayList<String>();
					for(int j = 0;j<arr.length;j++){
						val.add(arr[j].replaceAll("\"", "").replaceAll("--", ""));
					}
					y.add(val);
				}
			}
			if(names.length>0){
				if(y.size()>0){
					map.put("list", y);
					map.put("list2", titleList);
					map.put("column", y.get(0).size());
					map.put("row", y.size()+100);
					reportInfo = map;
					return RestResponse.responseOk(true);
				}else{
					return RestResponse.responseOk(false);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
		return response;
	}
	/**
	 * @Title:getReportDetails
	 * @Description:TODO数据查询下载
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/datadownLoadwordReport")
	public HttpServletResponse  datadownLoadwordReport(Model model,HttpServletRequest request,HttpServletResponse response){
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return null;
			}
			if(!reportInfo.isEmpty()){
				File file = null;  
				try {
					// 调用工具类WordGenerator的createDoc方法生成Word文档  
					file = CreateDateExcel.createExcel(reportInfo,"数据查询记录", "longieb",new PropertiesUtil().getString("download_temp_path"));
					String path = file.getAbsolutePath();
					if(path==null||path==""){
						return null;
					}
					// 以流的形式下载文件。
					InputStream fis = new BufferedInputStream(new FileInputStream(path));
					byte[] buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
					// 清空response
					response.reset();
					// 设置response的Header
					response.setCharacterEncoding("utf-8");  
					response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("历史查询记录", "UTF-8")+".xls");  
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/msexcel");
					toClient.write(buffer);
					toClient.flush();
					toClient.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}finally{
					//if(file != null) file.delete();
					reportInfo = null;
				}
			}
		return response;
	}
}
