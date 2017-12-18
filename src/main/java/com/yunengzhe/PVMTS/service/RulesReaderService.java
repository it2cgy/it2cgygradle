package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;

import com.yunengzhe.PVMTS.dao.RulesInfoDao;
import com.yunengzhe.PVMTS.dao.RulesReaderDao;
import com.yunengzhe.PVMTS.domain.dto.RulesOwnerDTO;
import com.yunengzhe.PVMTS.domain.dto.RulesSearchDTO;
import com.yunengzhe.PVMTS.domain.po.RulesInfoPO;
import com.yunengzhe.PVMTS.domain.po.RulesReaderPO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.RulesReaderListVO;
import com.yunengzhe.PVMTS.domain.vo.RulesReaderVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;


@Service("rulesReaderService")
public class RulesReaderService {

	private static final Logger logger = LoggerFactory.getLogger(RulesReaderService.class);
	
	@Autowired
	private RulesReaderDao rulesReaderDaoImpl;
	@Autowired
	private RulesInfoDao rulesInfoDaoImpl;
	
	/**
	 * 获取制度列表
	 * @param user
	 * @param page
	 * @param pagesize
	 * @param rulesSearchDTO
	 * @return
	 */
	public ResultListVO queryNoticeList(UserVO user, Integer page, Integer pagesize,
			RulesSearchDTO rulesSearchDTO) {
		ResultListVO resultListVO = new ResultListVO();
		List<RulesReaderVO> llist = new ArrayList<RulesReaderVO>();
		PageBean <RulesInfoPO> pageBean = new PageBean <RulesInfoPO>();
		pageBean.setCurrentPage(Integer.valueOf(page));
		pageBean.setPageRecorders(Integer.valueOf(pagesize));
		Map map = new HashMap();
		Boolean flag1 = false;
		if(user!=null && user.getRoleList()!=null){
			for(int i=0;i<user.getRoleList().size();i++){
	        	UserAndRolePO UserAndRolePO = user.getRoleList().get(i);
	        	if(UserAndRolePO.getRoleId()==1){
	        		flag1=true;
	        	}
	        }
		}
        
        if(flag1){
			map.put("companyId", user.getCompanyId());
			map.put("ruleStatus", 0);
			map.put("name", rulesSearchDTO.getName());
			map.put("version", rulesSearchDTO.getVersion());
			map.put("startTime", rulesSearchDTO.getStartTime());
			map.put("endTime", rulesSearchDTO.getEndTime());
			
		}else{
			map.put("companyId", user.getCompanyId());
			map.put("ruleStatus", 0);
			map.put("publishStatus", 1);
			map.put("name", rulesSearchDTO.getName());
			map.put("version", rulesSearchDTO.getVersion());
			map.put("startTime", rulesSearchDTO.getStartTime());
			map.put("endTime", rulesSearchDTO.getEndTime());
		}
		SortBy sortBy = SortBy.倒序;
		rulesInfoDaoImpl.find4Page(pageBean, map, true,"publish_time", sortBy);
		List<RulesInfoPO> list = pageBean.getObjList();
		String ids = "";
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				if(ids.equals("")){
					ids = ids+list.get(i).getId();
				}else{
					ids = ids+","+list.get(i).getId();
				}
			}
			RulesOwnerDTO RulesOwnerDTO = new RulesOwnerDTO();
			RulesOwnerDTO.setIds(ids);
			RulesOwnerDTO.setUserId(user.getUserid());
			List<RulesReaderPO> list1 = rulesReaderDaoImpl.findByRulesIds(RulesOwnerDTO);
			if(list1.size()==0){
				for(int j = 0;j<list.size();j++){
					RulesInfoPO np = list.get(j);
					RulesReaderVO RulesReaderVO = new RulesReaderVO();
					RulesReaderVO.setName(np.getName());
					RulesReaderVO.setId(np.getId());
					RulesReaderVO.setPublishStatus(np.getPublishStatus());
					if(np.getPublishTime()==null){
						RulesReaderVO.setPublishTime(np.getCreateTime());
					}else{
						RulesReaderVO.setPublishTime(np.getPublishTime());
					}
					RulesReaderVO.setRemarks(np.getRemarks());
					RulesReaderVO.setType(0);//0表示未读，1表示已读
					RulesReaderVO.setVersion(np.getVersion());
					RulesReaderVO.setFileName(np.getFileName());
					RulesReaderVO.setFileUrl(np.getFileUrl());
					llist.add(RulesReaderVO);
				}
			}else{
				int[]arr = new int[list1.size()];
				for(int k =0;k<list1.size();k++){
					arr[k]=list1.get(k).getRuleId();
				}
				for(int m=0;m<list.size();m++){
					RulesInfoPO np = list.get(m);
					RulesReaderVO RulesReaderVO = new RulesReaderVO();
					RulesReaderVO.setName(np.getName());
					RulesReaderVO.setId(np.getId());
					RulesReaderVO.setPublishStatus(np.getPublishStatus());
					RulesReaderVO.setPublishTime(np.getPublishTime());
					RulesReaderVO.setRemarks(np.getRemarks());
					int num = list.get(m).getId();
					Boolean flag = true;
					for(int n =0;n<arr.length;n++){
						if(num==arr[n]){
							flag = false;
						}
					}
					if(flag){
						RulesReaderVO.setType(0);//0表示未读，1表示已读
					}else{
						RulesReaderVO.setType(1);//0表示未读，1表示已读
					}
					RulesReaderVO.setVersion(np.getVersion());
					RulesReaderVO.setFileName(np.getFileName());
					RulesReaderVO.setFileUrl(np.getFileUrl());
					llist.add(RulesReaderVO);
				}
			}
			resultListVO.setResultList(llist);
			resultListVO.setCounts(pageBean.getTotalRows());
		}else{
			resultListVO.setResultList(llist);
			resultListVO.setCounts(0);
		}
		return resultListVO;
	}

	//查看
	public void queryRuleOne(UserVO user,Integer id) {
		Date now = new Date();
		Map map = new HashMap();
		map.put("userId", user.getUserid());
		map.put("ruleId", id);
		List<RulesReaderPO> list = rulesReaderDaoImpl.findByMap(map);
		if(list.size()==0){
			RulesReaderPO RulesReaderPO = new RulesReaderPO();
			RulesReaderPO.setCreateTime(now);
			RulesReaderPO.setRuleId(id);
			RulesReaderPO.setReadTime(now);
			RulesReaderPO.setType(1);//修改当前用户显示公告的状态 1表示已读
			RulesReaderPO.setUpdateTime(now);
			RulesReaderPO.setUserId(user.getUserid());
			rulesReaderDaoImpl.insert(RulesReaderPO);
		}else{
			RulesReaderPO RulesReaderPO =list.get(0);
			RulesReaderPO.setUpdateTime(now);
			rulesReaderDaoImpl.update(RulesReaderPO);
		}
	}
}
