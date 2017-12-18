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
import org.springframework.web.bind.annotation.PathVariable;

import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.PVMTS.dao.NoticeInfoDao;
import com.yunengzhe.PVMTS.dao.NoticeReaderDao;
import com.yunengzhe.PVMTS.domain.dto.NoticeOwnerDTO;
import com.yunengzhe.PVMTS.domain.dto.NoticeReaderDTO;
import com.yunengzhe.PVMTS.domain.dto.NoticeSearchDTO;
import com.yunengzhe.PVMTS.domain.po.NoticeInfoPO;
import com.yunengzhe.PVMTS.domain.po.NoticeReaderPO;
import com.yunengzhe.PVMTS.domain.vo.NoticeInfoListVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeReadInfoVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeReaderVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO; 
import com.yunengzhe.PVMTS.util.TimeUtil;


@Service("noticeReaderService")
public class NoticeReaderService {

	private static final Logger logger = LoggerFactory.getLogger(NoticeReaderService.class);

	@Autowired
	private NoticeReaderDao noticeReaderDaoImpl;
	@Autowired
	private NoticeInfoDao noticeInfoDaoImpl;

	//获取公告列表
	public ResultListVO queryNoticeList(UserVO user, Integer page, Integer pagesize,NoticeSearchDTO NoticeSearchDTO) {
		ResultListVO resultListVO = new ResultListVO();
		List<NoticeReaderVO> llist = new ArrayList<NoticeReaderVO>();
		PageBean <NoticeInfoPO> pageBean = new PageBean <NoticeInfoPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pagesize);
		Map map = new HashMap();
		Boolean flag1 = false;

		if(user!=null && user.getRoleList()!=null){
			for(int i=0;i<user.getRoleList().size();i++){
				if(user.getRoleList().get(i).getRoleId()==1){
					flag1 = true;
				}
			}
		}
		if(flag1){
			map.put("companyId", user.getCompanyId());
			map.put("noticeStatus", 0);
			map.put("name", NoticeSearchDTO.getName());
			map.put("startTime", NoticeSearchDTO.getStartTime());
			map.put("endTime", NoticeSearchDTO.getEndTime());

		}else{
			map.put("companyId", user.getCompanyId());
			map.put("noticeStatus", 0);
			map.put("publishStatus", 1);
			map.put("name", NoticeSearchDTO.getName());
			map.put("startTime", NoticeSearchDTO.getStartTime());
			map.put("endTime", NoticeSearchDTO.getEndTime());
		}
		SortBy sortBy = SortBy.倒序;
		noticeInfoDaoImpl.find4Page(pageBean, map, true,"publish_time", sortBy);
		List<NoticeInfoPO> list = pageBean.getObjList();
		String ids = "";
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				if(ids.equals("")){
					ids = ids+list.get(i).getId();
				}else{
					ids = ids+","+list.get(i).getId();
				}
			}
			NoticeOwnerDTO noticeOwnerDTO = new NoticeOwnerDTO();
			noticeOwnerDTO.setIds(ids);
			noticeOwnerDTO.setUserId(user.getUserid());
			List<NoticeReaderPO> list1 = noticeReaderDaoImpl.findByNoticeIds(noticeOwnerDTO);
			if(list1.size()==0){
				for(int j = 0;j<list.size();j++){
					NoticeInfoPO np = list.get(j);
					NoticeReaderVO NoticeReaderVO = new NoticeReaderVO();
					NoticeReaderVO.setName(np.getName());
					NoticeReaderVO.setId(np.getId());
					NoticeReaderVO.setPublishStatus(np.getPublishStatus());
					if(np.getPublishTime()==null){
						NoticeReaderVO.setPublishTime(np.getCreateTime());
					}else{
						NoticeReaderVO.setPublishTime(np.getPublishTime());
					}
					NoticeReaderVO.setRemarks(np.getRemarks());
					NoticeReaderVO.setType(0);//0表示未读，1表示已读
					NoticeReaderVO.setTypeId(np.getTypeId());
					llist.add(NoticeReaderVO);
				}
			}else{
				int[]arr = new int[list1.size()];
				for(int k =0;k<list1.size();k++){
					arr[k]=list1.get(k).getNoticeId();
				}
				for(int m=0;m<list.size();m++){
					NoticeInfoPO np = list.get(m);
					NoticeReaderVO NoticeReaderVO = new NoticeReaderVO();
					NoticeReaderVO.setName(np.getName());
					NoticeReaderVO.setId(np.getId());
					NoticeReaderVO.setPublishStatus(np.getPublishStatus());
					NoticeReaderVO.setPublishTime(np.getPublishTime());
					NoticeReaderVO.setRemarks(np.getRemarks());
					NoticeReaderVO.setTypeId(np.getTypeId());
					int num = list.get(m).getId();
					Boolean flag = true;
					for(int n =0;n<arr.length;n++){
						if(num==arr[n]){
							flag = false;
						}
					}
					if(flag){
						NoticeReaderVO.setType(0);//0表示未读，1表示已读
					}else{
						NoticeReaderVO.setType(1);//0表示未读，1表示已读
					}
					llist.add(NoticeReaderVO);
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
	public NoticeReadInfoVO queryNoticeOne(Integer userId, Integer id) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("noticeId", id);
		List<NoticeReaderPO> list = noticeReaderDaoImpl.findByMap(map);
		Date now = new Date();
		if(list.size()==0){
			NoticeReaderPO NoticeReaderPO = new NoticeReaderPO();
			NoticeReaderPO.setCounts(1);//修改当前用户阅读此公告的次数
			NoticeReaderPO.setCreateTime(now);
			NoticeReaderPO.setNoticeId(id);
			NoticeReaderPO.setReadTime(now);
			NoticeReaderPO.setType(1);//修改当前用户显示公告的状态 1表示已读
			NoticeReaderPO.setUpdateTime(now);
			NoticeReaderPO.setUserId(userId);
			noticeReaderDaoImpl.insert(NoticeReaderPO);
		}else{
			NoticeReaderPO NoticeReaderPO=list.get(0);
			NoticeReaderPO.setUpdateTime(now);
			NoticeReaderPO.setCounts(NoticeReaderPO.getCounts()+1);
			noticeReaderDaoImpl.update(NoticeReaderPO);
		}
		NoticeInfoPO NoticeInfoPO = noticeInfoDaoImpl.get(id);
		NoticeInfoPO.setUpdateTime(now);//更新信件查看时间
		if(NoticeInfoPO.getCounts()==null){
			NoticeInfoPO.setCounts(1);
		}else{
			NoticeInfoPO.setCounts(NoticeInfoPO.getCounts()+1);//修改公告总阅读次数
		}
		noticeInfoDaoImpl.update(NoticeInfoPO);
		NoticeReadInfoVO NoticeReadInfoVO = new NoticeReadInfoVO();
		NoticeReadInfoVO.setContentHtml(NoticeInfoPO.getContentHtml());
		if(NoticeInfoPO.getPublishStatus()==0){//未发布
			NoticeReadInfoVO.setPublishTime(NoticeInfoPO.getCreateTime());
		}else{
			NoticeReadInfoVO.setPublishTime(NoticeInfoPO.getPublishTime());
		}
		NoticeReadInfoVO.setName(NoticeInfoPO.getName());
		return NoticeReadInfoVO;
	}
}
