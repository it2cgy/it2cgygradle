package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.MessageSystemInfoDao;
import com.yunengzhe.PVMTS.dao.MessageUserInfoDao;
import com.yunengzhe.PVMTS.dao.NoticeInfoDao;
import com.yunengzhe.PVMTS.dao.UserDao;
import com.yunengzhe.PVMTS.domain.dto.NoticeInfoDTO;
import com.yunengzhe.PVMTS.domain.dto.NoticeUpdateInfoDTO;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.po.MessageUserInfoPO;
import com.yunengzhe.PVMTS.domain.po.NoticeInfoPO;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.vo.NoticeEditInfoVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeInfoVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;


@Service("noticeInfoService")
public class NoticeInfoService {

	private static final Logger logger = LoggerFactory.getLogger(NoticeInfoService.class);
	
	@Autowired
	private NoticeInfoDao noticeInfoDaoImpl;
	@Autowired
	private MessageSystemInfoDao messageSystemInfoDaoImpl;
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private MessageUserInfoDao messageUserInfoDaoImpl;
	@Autowired
	private UserAndPowerService userAndPowerService;
	@Autowired
	private SettingInfoService settingInfoService;
	//添加公告
	public NoticeInfoVO addNotice(UserVO user, NoticeInfoDTO noticeInfoDTO) {
		Date now = new Date();
		NoticeInfoPO NoticeInfoPO = new NoticeInfoPO();
		NoticeInfoPO.setCompanyId(user.getCompanyId());
//		NoticeInfoPO.setContent();
		NoticeInfoPO.setContentHtml(noticeInfoDTO.getBodyHtmlText());
		NoticeInfoPO.setCreateTime(now);
		NoticeInfoPO.setName(noticeInfoDTO.getName());
		NoticeInfoPO.setNoticeStatus(0);//默认是0，可用；1表示删除。
		NoticeInfoPO.setPublisher(user.getUserid());
		int publishType = Integer.valueOf(noticeInfoDTO.getStatus());//0表示保存，1表示发布
		NoticeInfoPO.setPublishStatus(publishType);
		NoticeInfoPO.setPublishTime(now);
		NoticeInfoPO.setRemarks(noticeInfoDTO.getRemarks());
//		NoticeInfoPO.setThumbnallUrl(thumbnallUrl);
		NoticeInfoPO.setTypeId(Integer.valueOf(noticeInfoDTO.getTypeId()));
		noticeInfoDaoImpl.insert(NoticeInfoPO);
		if(noticeInfoDTO.getStatus()=="1"){
			boolean addMessage = addMessage(NoticeInfoPO);
			logger.info(addMessage==true?"消息发送成功":"消息发送失败");
		}
		return null;
	}
	//编辑铺数据
	public NoticeEditInfoVO queryNoticeOne(String id) {
		NoticeInfoPO NoticeInfoPO = noticeInfoDaoImpl.get(Integer.valueOf(id));
		NoticeEditInfoVO NoticeEditInfoVO = new NoticeEditInfoVO();
		NoticeEditInfoVO.setType(NoticeInfoPO.getTypeId());
		NoticeEditInfoVO.setContentHtml(NoticeInfoPO.getContentHtml());
		NoticeEditInfoVO.setName(NoticeInfoPO.getName());
		NoticeEditInfoVO.setRemarks(NoticeInfoPO.getRemarks());
		return NoticeEditInfoVO;
	}
	//校验公告是不是被删除
	public NoticeInfoVO checkid(String id) {
		NoticeInfoPO NoticeInfoPO = noticeInfoDaoImpl.get(id);
		NoticeInfoVO NoticeInfoVO = new NoticeInfoVO();
		if(NoticeInfoPO.getNoticeStatus()==1){//已经被删除
			NoticeInfoVO.setResult(false);
		}else{
			NoticeInfoVO.setResult(true);
		}
		return NoticeInfoVO;
	}
	//更新公告（发布）
	public NoticeInfoVO publishNotice(String id,String status) {
		NoticeInfoPO NoticeInfoPO = noticeInfoDaoImpl.get(id);
		NoticeInfoPO.setPublishStatus(Integer.valueOf(status));
		NoticeInfoPO.setPublishTime(new Date());
		NoticeInfoPO.setUpdateTime(new Date());
		noticeInfoDaoImpl.update(NoticeInfoPO);
		if(status=="1"){
			boolean addMessage = addMessage(NoticeInfoPO);
			logger.info(addMessage==true?"消息发送成功":"消息发送失败");
		}
	return null;
	}
	//更新公告（编辑保存、编辑发布）
	public NoticeInfoVO updateNotice(NoticeUpdateInfoDTO NoticeUpdateInfoDTO) {
		NoticeInfoPO NoticeInfoPO = noticeInfoDaoImpl.get(NoticeUpdateInfoDTO.getId());
		if(NoticeUpdateInfoDTO.getName()!=null){
			NoticeInfoPO.setName(NoticeUpdateInfoDTO.getName());
		}
		if(NoticeUpdateInfoDTO.getBodyHtmlText()!=null){
			NoticeInfoPO.setContentHtml(NoticeUpdateInfoDTO.getBodyHtmlText());
		}
		if(NoticeUpdateInfoDTO.getStatus()!=null){
			NoticeInfoPO.setPublishStatus(Integer.valueOf(NoticeUpdateInfoDTO.getStatus()));
			NoticeInfoPO.setPublishTime(new Date());
			if(NoticeUpdateInfoDTO.getStatus()=="1"){
				boolean addMessage = addMessage(NoticeInfoPO);
				logger.info(addMessage==true?"消息发送成功":"消息发送失败");
			}
		}
		if(NoticeUpdateInfoDTO.getRemarks()!=null){
			NoticeInfoPO.setRemarks(NoticeUpdateInfoDTO.getRemarks());
		}
		if(NoticeUpdateInfoDTO.getTypeId()!=null){
			NoticeInfoPO.setTypeId(Integer.valueOf(NoticeUpdateInfoDTO.getTypeId()));
		}
		NoticeInfoPO.setUpdateTime(new Date());
		noticeInfoDaoImpl.update(NoticeInfoPO);
		return null;
	}
	//更新公告（删除）
	public void deleteNotice(String ids) {
		noticeInfoDaoImpl.updateToDelete(ids);
	}
	
	/**
	 * 添加发布公告后添加到消息中心
	 * @param alarm
	 * @param userId
	 * @return已测
	 */
	public boolean addMessage(NoticeInfoPO noticeInfo){
			MessageSystemInfoPO info = new MessageSystemInfoPO();
			info.setTitle(noticeInfo.getName());//公告主题
			String replace = noticeInfo.getContentHtml().replace("<p>", "").replace("<p/>", "");
			info.setContent(replace);//公告内容
			info.setMassageStatus(0);//公告状态 除了报警状态是123  其他都是0
			info.setMessageId(noticeInfo.getId());//公告id
			info.setMessageType(3);//系统公告3
			info.setForcedPush(0);//是否强制推送
			info.setAddressUser("公告");//发件人
			boolean flag = false;
			flag = messageSystemInfoDaoImpl.insert(info)==1;
			if(flag){
				List<UserPO> findAll = userDaoImpl.findAll();//获取所有用户
				List<MessageUserInfoPO> messageUserList = new ArrayList<MessageUserInfoPO>();
				if(findAll.size()>0){
					for (UserPO user : findAll) {
						SettingInfoPO setting = settingInfoService.getSetting(user.getId());//如果用户设置了不推送消息，将不存入数据库
						if(setting.getSystem()==1){
							MessageUserInfoPO userinfo = new MessageUserInfoPO();
							userinfo.setId(null);
							userinfo.setIsread(0);//是否已读
							userinfo.setMid(info.getId());//消息列表id
							userinfo.setUserId(user.getId());//收件人id  将所有用户都存入
							messageUserList.add(userinfo);
						}
					}
				}
				if(messageUserList.size()>0){
					messageUserInfoDaoImpl.insertBatch(messageUserList);
				}
			}
			return flag;
	}
}
