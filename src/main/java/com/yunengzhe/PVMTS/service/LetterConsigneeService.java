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
import com.yunengzhe.PVMTS.dao.LetterConsigneeDao;
import com.yunengzhe.PVMTS.dao.LetterDao;
import com.yunengzhe.PVMTS.dao.UserDao;
import com.yunengzhe.PVMTS.domain.po.LetterConsigneePO;
import com.yunengzhe.PVMTS.domain.po.LetterPO;
import com.yunengzhe.PVMTS.domain.vo.LetterConsigneeListVO;
import com.yunengzhe.PVMTS.domain.vo.LetterConsigneeReadVO;
import com.yunengzhe.PVMTS.domain.vo.LetterConsigneeVO;
import com.yunengzhe.PVMTS.domain.vo.LetterVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO; 


@Service("consigneeService")
public class LetterConsigneeService {

	private static final Logger logger = LoggerFactory.getLogger(LetterConsigneeService.class);
	
	@Autowired
	private LetterConsigneeDao consigneeDaoImpl;
	@Autowired
	private LetterDao letterDaoImpl;
	@Autowired
	private UserDao userDaoImpl;
	 
	/**
	 * 加载收件箱信息
	 * @param userId
	 * @param page
	 * @param rows
	 * @return
	 */
	public ResultListVO getConsignee(Integer userId,int page,int pageSize){
		List<LetterConsigneeVO> list = new ArrayList<LetterConsigneeVO>();
		PageBean <LetterConsigneePO> pageBean = new PageBean <LetterConsigneePO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("disabled", 0);//展示有效数据
		SortBy sortBy = SortBy.倒序;
		consigneeDaoImpl.find4Page(pageBean, map, "id", sortBy);
		ResultListVO resultListVO = new ResultListVO();
		if(pageBean.getTotalRows()!=0){
			List <LetterConsigneePO> Consigneelist = pageBean.getObjList();
			for(int j =0;j<Consigneelist.size();j++){
				LetterConsigneeVO ConsigneeVO = new LetterConsigneeVO();
				ConsigneeVO.setLetterId(Consigneelist.get(j).getId());
				ConsigneeVO.setSendTime(Consigneelist.get(j).getSendTime());
				ConsigneeVO.setSenderUid(Consigneelist.get(j).getSenderUid());
				ConsigneeVO.setSenderName(Consigneelist.get(j).getSenderName());
				ConsigneeVO.setTopic(Consigneelist.get(j).getTopic());
				ConsigneeVO.setType(Consigneelist.get(j).getType());
				list.add(ConsigneeVO);
			}
			resultListVO.setResultList(list);
			resultListVO.setCounts(pageBean.getTotalRows());
		}else{
			resultListVO.setResultList(list);
			resultListVO.setCounts(0);
		}
		return resultListVO;
	}
	/**
	 * 查看
	 * @param id
	 * @return
	 */
	public LetterConsigneeReadVO readLetter(String id) {
		LetterConsigneeReadVO LetterConsigneeReadVO = new LetterConsigneeReadVO();
		LetterConsigneePO ConsigneePO = consigneeDaoImpl.get(Integer.valueOf(id));
		if(ConsigneePO!=null){
			Integer letterId =  ConsigneePO.getLetterId();
			LetterPO LetterPO = letterDaoImpl.get(letterId);
			if(ConsigneePO.getType()==0){
				Date now = new Date();
				ConsigneePO.setUpdateTime(now);//插入更新时间
				ConsigneePO.setType(1);//修改信件查看后状态
				consigneeDaoImpl.update(ConsigneePO);
			}
			LetterConsigneeReadVO.setSenderUid(LetterPO.getSenderUid());
			LetterConsigneeReadVO.setSendTime(LetterPO.getSendTime());
			LetterConsigneeReadVO.setContent(LetterPO.getContent());
			LetterConsigneeReadVO.setTopic(LetterPO.getTopic());
			LetterConsigneeReadVO.setSenderName(LetterPO.getSenderName());
			LetterConsigneeReadVO.setUserName(userDaoImpl.get(LetterPO.getSenderUid()).getUsername());
		}
		return LetterConsigneeReadVO;
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public LetterVO delLetter(String ids) {
		LetterVO LetterVO = new LetterVO();
		if(ids.indexOf(",")==-1){
			LetterConsigneePO ConsigneePO = consigneeDaoImpl.get(Integer.valueOf(ids));
			Date now = new Date();
			ConsigneePO.setUpdateTime(now);//插入更新时间
			ConsigneePO.setDisabled(1);//设置信息无效
			consigneeDaoImpl.update(ConsigneePO);
			LetterConsigneePO ConsigneePO1 = consigneeDaoImpl.get(ids);//判断是否删除成功
			if(ConsigneePO1.getDisabled()==1){
				LetterVO.setResult(true);
			}else{
				LetterVO.setResult(false);
			}
			return LetterVO;
		}else{
			consigneeDaoImpl.updateToDelete(ids);
			List<LetterConsigneePO> ConsigneePO1 = consigneeDaoImpl.findByIds(ids);//判断是否删除成功
			LetterVO.setResult(true);
			for(int i=0;i<ConsigneePO1.size();i++){
				if(ConsigneePO1.get(i).getDisabled()==0){
					LetterVO.setResult(false);
				}
			}
			return LetterVO;
		}
	}
}
