package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.AddCurveDTO;
import com.yunengzhe.PVMTS.domain.dto.AddCurvePointsDTO;
import com.yunengzhe.PVMTS.domain.dto.UpdateCurveDTO;
import com.yunengzhe.PVMTS.domain.po.ColorInfoPO;
import com.yunengzhe.PVMTS.domain.po.CurveInfoPO;
import com.yunengzhe.PVMTS.domain.po.CurvePointInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.ColorInfoVO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.ColorInfoService;
import com.yunengzhe.PVMTS.service.CurveInfoService;
import com.yunengzhe.PVMTS.service.CurvePointInfoService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.PVMTS.service.monitor.StationMonitorService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

@Controller
@RequestMapping("/curveInfo")
public class CurveInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurveInfoController.class);
	
	@Autowired
	private CurveInfoService curveInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private CurvePointInfoService curvePointInfoService;
	@Autowired
	public StationMonitorService stationMonitorService;
	@Autowired
	private ColorInfoService colorInfoService;
	/**
	 * 添加曲线
	 * @param request
	 * @param response
	 * @param AddCurveDTO
	 * @return
	 */
	String token = "DA58A5485E52B2A5DA3EA90F367A1636";
	@RequestMapping(value="/addCurve",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addCurve(HttpServletRequest request,HttpServletResponse response,@RequestBody AddCurveDTO addCurveDTO){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			int type = addCurveDTO.getType();
			CurveInfoPO curveInfoPO = new CurveInfoPO();
			curveInfoPO.setFirstYaxisName(addCurveDTO.getFirstYaxisName());
			curveInfoPO.setName(addCurveDTO.getName());
			curveInfoPO.setPowerStationId(addCurveDTO.getPowerStationId());
			curveInfoPO.setTimeSpan(addCurveDTO.getTimeSpan());
			curveInfoPO.setType(addCurveDTO.getType());
			curveInfoPO.setCreateuser(user.getUserid());
			curveInfoPO.setCreateTime(new Date());
			if(type==2){//双轴曲线
				curveInfoPO.setSecondYaxisName(addCurveDTO.getSecondYaxisName());
			}
			//添加曲线
			curveInfoPO = curveInfoService.addCurve(curveInfoPO);
			if(addCurveDTO.getPointData().size()!=0){
				List<CurvePointInfoPO> list = new ArrayList<CurvePointInfoPO>();
				for(int i=0;i<addCurveDTO.getPointData().size();i++){
					AddCurvePointsDTO addCurvePointsDTO = addCurveDTO.getPointData().get(i);
					CurvePointInfoPO curvePointInfoPO = new CurvePointInfoPO();
					curvePointInfoPO.setAnaloginputId(addCurvePointsDTO.getAnaloginputId());
					curvePointInfoPO.setColorCode(addCurvePointsDTO.getColorCode());
					curvePointInfoPO.setName(addCurvePointsDTO.getName());
					if(type==1){//单轴曲线
						curvePointInfoPO.setYaxisType(0);
					}else{//双轴曲线
						curvePointInfoPO.setYaxisType(addCurvePointsDTO.getYaxisType());
					}
					curvePointInfoPO.setCurveId(curveInfoPO.getId());
					list.add(curvePointInfoPO);
				}
				//添加测点
				curvePointInfoService.addCurvePoint(list);
			}
			return RestResponse.responseCode(1, "添加成功");
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 删除曲线
	 * @param request
	 * @param response
	 * @param curveId
	 * @return
	 */
	@RequestMapping(value="/deleteCurve/{curveId}")
	@ResponseBody
	public RestResponseVO deleteCurve(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer curveId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			curveInfoService.deleteCurve(curveId);
			curvePointInfoService.deleteCurvePoint(curveId);
			return RestResponse.responseCode(1, "删除成功");
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 修改曲线
	 * @param request
	 * @param response
	 * @param updateCurveDTO
	 * @return
	 */
	@RequestMapping(value="/updateCurve",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO updateCurve(HttpServletRequest request,HttpServletResponse response,@RequestBody UpdateCurveDTO updateCurveDTO){
		try{
			if(updateCurveDTO.getId()==null || updateCurveDTO.getId()<=0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "错误的请求");
			}
						
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			CurveInfoPO curveInfoPO = new CurveInfoPO();
			curveInfoPO.setId(updateCurveDTO.getId());
			curveInfoPO.setFirstYaxisName(updateCurveDTO.getFirstYaxisName());
			curveInfoPO.setName(updateCurveDTO.getName());
			curveInfoPO.setPowerStationId(updateCurveDTO.getPowerStationId());
			curveInfoPO.setTimeSpan(updateCurveDTO.getTimeSpan());
			curveInfoPO.setType(updateCurveDTO.getType());
			curveInfoPO.setUpdateTime(new Date());
			curveInfoPO.setUpdateUserId(user.getUserid());
			if(updateCurveDTO.getType()==1){
				curveInfoPO.setSecondYaxisName("");
			}else{
				curveInfoPO.setSecondYaxisName(updateCurveDTO.getSecondYaxisName());
			}
			//修改曲线
			curveInfoService.updateCurve(curveInfoPO);
			//删除测点
			curvePointInfoService.deleteCurvePoint(updateCurveDTO.getId());
			List<CurveDetailVO> listpo = curveInfoService.getCurveDetail(updateCurveDTO.getId()+"");
			CurveDetailVO CurveDetailVO = listpo.get(0);
			if(updateCurveDTO.getPointData().size()!=0){
				List<CurvePointInfoPO> list = new ArrayList<CurvePointInfoPO>();
				for(int i=0;i<updateCurveDTO.getPointData().size();i++){
					AddCurvePointsDTO addCurvePointsDTO = updateCurveDTO.getPointData().get(i);
					CurvePointInfoPO curvePointInfoPO = new CurvePointInfoPO();
					curvePointInfoPO.setAnaloginputId(addCurvePointsDTO.getAnaloginputId());
					curvePointInfoPO.setColorCode(addCurvePointsDTO.getColorCode());
					curvePointInfoPO.setName(addCurvePointsDTO.getName());
					if(StringUtil.empty(CurveDetailVO.getSecondYaxisName())){
						curvePointInfoPO.setYaxisType(0);
					}else{
						curvePointInfoPO.setYaxisType(addCurvePointsDTO.getYaxisType());
					}
					curvePointInfoPO.setCurveId(updateCurveDTO.getId());
					list.add(curvePointInfoPO);
				}
				//添加测点
				curvePointInfoService.addCurvePoint(list);
			}
			return RestResponse.responseCode(1, "修改成功");
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 获取曲线列表
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getCurveList",method=RequestMethod.POST)
	@ResponseBody
	public Object getCurveList(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String>map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Integer powerStationId = Integer.valueOf(map.get("powerStationId")+"");
			String name = map.get("name");
			String userName = map.get("userName");
			String uids = "";
			if(!StringUtil.empty(userName)){
				List<UserPO> ulist = userService.findUserList(userName);
				if(ulist.size()>0){
					for(UserPO up:ulist){
						if(StringUtil.empty(uids)){
							uids+=up.getId();
						}else{
							uids+=","+up.getId();
						}
					}
				}
			}
			Integer page = Integer.valueOf(map.get("page")+"");
			Integer pagesize = Integer.valueOf(map.get("pagesize")+"");
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			Integer createuser =null;
			if(user.getRoleList().get(0).getRoleId()==4){
				createuser = user.getUserid();
			}
			PageBean<CurveInfoPO> pageBean = curveInfoService.getCurveList(powerStationId,name,page,pagesize,createuser,uids);
			List<CurveInfoPO> list = pageBean.getObjList();
			logger.debug(list.toString());
			if(list.size()==0){
				return RestResponse.responseList(request, pageBean.getTotalRows(), list);
			}
			String ids = "";
			for(int i=0;i<list.size();i++){
				CurveInfoPO c = list.get(i);
				if(i==0){
					ids+= list.get(i).getId();	
				}else{
					ids+= ","+list.get(i).getId();
				}
			}
			String local = (String)HttpSessionUtil.getAttribute("local");
			List<CurveDetailVO> curvelist = curveInfoService.getCurveDetail(ids);
			if("en_US".equals(local)){
				for(int i=0;i<curvelist.size();i++){
					CurveDetailVO cv = curvelist.get(i);
					for(int j=0;j<cv.getPointList().size();j++){
						cv.getPointList().get(j).setPointName(cv.getPointList().get(j).getPointEnglishName());
					}
				}
			}
			return RestResponse.responseList(request, pageBean.getTotalRows(), curvelist);
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取曲线详情
	 * @param request
	 * @param response
	 * @param curveId
	 * @return
	 */
	@RequestMapping(value="/getCurveDetail/{curveId}")
	@ResponseBody
	public RestResponseVO getCurveDetail(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer curveId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<CurveDetailVO> list = curveInfoService.getCurveDetail(curveId+"");
			if(list.size()==0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "曲线无测点或者曲线id有错");
			}
			String local = (String)HttpSessionUtil.getAttribute("local");
			if("en_US".equals(local)){
				for(int i=0;i<list.size();i++){
					CurveDetailVO cv = list.get(i);
					for(int j=0;j<cv.getPointList().size();j++){
						cv.getPointList().get(j).setPointName(cv.getPointList().get(j).getPointEnglishName());
					}
				}
			}
			CurveDetailVO curveDetailVO = list.get(0);
			logger.debug(curveDetailVO.toString());
			return RestResponse.responseOk(curveDetailVO);
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取测点线颜色信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getColorList")
	@ResponseBody
	public RestResponseVO getColorList(HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<ColorInfoPO> list = colorInfoService.getColorList();
			if(list.size()==0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "颜色数据为空");
			}
			List<ColorInfoVO> result = new ArrayList<ColorInfoVO>();
			for(int i=0;i<list.size();i++){
				ColorInfoPO colorInfoPO = list.get(i);
				ColorInfoVO colorInfoVO = new ColorInfoVO();
				colorInfoVO.setColorCode(colorInfoPO.getColorCode());
				colorInfoVO.setId(colorInfoPO.getId());
				colorInfoVO.setName(colorInfoPO.getName());
				result.add(colorInfoVO);
			}
			return RestResponse.responseOk(result);
		}catch(Exception e){
			logger.debug(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
