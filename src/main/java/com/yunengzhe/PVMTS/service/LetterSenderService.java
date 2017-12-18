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
import com.yunengzhe.PVMTS.dao.LetterDao;
import com.yunengzhe.PVMTS.dao.LetterSenderDao;
import com.yunengzhe.PVMTS.domain.po.LetterPO;
import com.yunengzhe.PVMTS.domain.po.LetterSenderPO;
import com.yunengzhe.PVMTS.domain.vo.LetterSenderListVO;
import com.yunengzhe.PVMTS.domain.vo.LetterSenderReadVO;
import com.yunengzhe.PVMTS.domain.vo.LetterSenderVO;
import com.yunengzhe.PVMTS.domain.vo.LetterVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO; 
import com.yunengzhe.PVMTS.util.TimeUtil;


@Service("senderService")
public class LetterSenderService {

	private static final Logger logger = LoggerFactory.getLogger(LetterSenderService.class);
	
	@Autowired
	private LetterSenderDao senderDaoImpl;
	@Autowired
	private LetterDao letterDaoImpl;
	
	/**
	 * 加载发件箱
	 * @param userId
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public ResultListVO getSender(Integer userId,Integer page,Integer pagesize){
		List<LetterSenderVO> list = new ArrayList<LetterSenderVO>();
		PageBean <LetterSenderPO> pageBean = new PageBean <LetterSenderPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("disabled", 0);//展示有效数据
		SortBy sortBy = SortBy.倒序;
		senderDaoImpl.find4Page(pageBean, map, "id", sortBy);
		ResultListVO resultListVO = new ResultListVO();
		if(pageBean.getTotalRows()!=0){
			List <LetterSenderPO> Senderlist = pageBean.getObjList();
			for(int j =0;j<Senderlist.size();j++){
				LetterSenderVO SenderVO = new LetterSenderVO();
				SenderVO.setLetterId(Senderlist.get(j).getId());
				SenderVO.setConsigneeUid(Senderlist.get(j).getConsigneeUid());
				SenderVO.setSendTime(Senderlist.get(j).getSendTime());
				SenderVO.setTopic(Senderlist.get(j).getTopic());
				SenderVO.setConsigneeName(Senderlist.get(j).getConsigneeName());
				list.add(SenderVO);
			}
			resultListVO.setResultList(list);
			resultListVO.setCounts(pageBean.getTotalRows());
		}else{
			resultListVO.setResultList(list);
			resultListVO.setCounts(0);
		}
		return resultListVO;
	}
	//查看
	public LetterSenderReadVO readLetter(String id) {
		LetterSenderReadVO LetterSenderReadVO = new LetterSenderReadVO();
		LetterSenderPO SenderPO = senderDaoImpl.get(Integer.valueOf(id));
		if(SenderPO!=null){
			Integer letterId =  SenderPO.getLetterId();
			LetterPO LetterPO = letterDaoImpl.get(letterId);
			LetterSenderReadVO.setConsigneeUid(LetterPO.getConsigneeUid());
			LetterSenderReadVO.setSendTime(LetterPO.getSendTime());
			LetterSenderReadVO.setContent(LetterPO.getContent());
			LetterSenderReadVO.setTopic(LetterPO.getTopic());
			LetterSenderReadVO.setConsigneeName(LetterPO.getConsigneeName());
		}
		return LetterSenderReadVO;
	}
	//删除
	public LetterVO delLetter(String ids) {
		LetterVO LetterVO = new LetterVO();
		if(ids.indexOf(",")==-1){
			LetterSenderPO SenderPO = senderDaoImpl.get(Integer.valueOf(ids));
			Date now = new Date();
			SenderPO.setUpdateTime(now);//插入更新时间
			SenderPO.setDisabled(1);//设置信息无效
			senderDaoImpl.update(SenderPO);
			LetterSenderPO SenderPO1 = senderDaoImpl.get(ids);//判断是否删除成功
			if(SenderPO1.getDisabled()==1){
				LetterVO.setResult(true);
			}else{
				LetterVO.setResult(false);
			}
			return LetterVO;
		}else{
			senderDaoImpl.updateToDelete(ids);
			List<LetterSenderPO> SenderPO1 = senderDaoImpl.findByIds(ids);//判断是否删除成功
			LetterVO.setResult(true);
			for(int i=0;i<SenderPO1.size();i++){
				if(SenderPO1.get(i).getDisabled()==0){
					LetterVO.setResult(false);
				}
			}
			return LetterVO;
		}
	}
	 
}
