package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.LetterConsigneeDao;
import com.yunengzhe.PVMTS.dao.LetterDao;
import com.yunengzhe.PVMTS.dao.LetterSenderDao;
import com.yunengzhe.PVMTS.dao.MessageSystemInfoDao;
import com.yunengzhe.PVMTS.dao.MessageUserInfoDao;
import com.yunengzhe.PVMTS.dao.UserDao;
import com.yunengzhe.PVMTS.domain.dto.LetterDTO;
import com.yunengzhe.PVMTS.domain.po.LetterConsigneePO;
import com.yunengzhe.PVMTS.domain.po.LetterPO;
import com.yunengzhe.PVMTS.domain.po.LetterSenderPO;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.po.MessageUserInfoPO;
import com.yunengzhe.PVMTS.domain.po.NoticeInfoPO;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.vo.UserVO; 
import com.yunengzhe.PVMTS.util.TimeUtil;


@Service("letterService")
public class LetterService {

	private static final Logger logger = LoggerFactory.getLogger(LetterService.class);
	
	@Autowired
	private LetterDao letterDaoImpl;
	@Autowired
	private LetterSenderDao senderDaoImpl;
	@Autowired
	private LetterConsigneeDao consigneeDaoImpl;
	@Autowired
	private UserDao userDaoImpl;
	@Autowired
	private MessageSystemInfoDao messageSystemInfoDaoImpl;
	@Autowired
	private MessageUserInfoDao messageUserInfoDaoImpl;
	@Autowired
	private SettingInfoService settingInfoService;
	/**
	 * 发送站内信
	 * @param userId
	 * @param letterDTO
	 */
	public void addLetter(Integer userId,LetterDTO letterDTO){
		LetterPO letter = new LetterPO();
		letter.setTopic(letterDTO.getTopic());
		letter.setContent(letterDTO.getLetterBody());
		Date now = new Date();
		letter.setSendTime(now);
		letter.setSenderUid(userId);
		letter.setSenderName(userDaoImpl.get(userId).getNickname());
		List<UserPO>Userlist = userDaoImpl.findByIds(letterDTO.getConsigneeIds());
		String name = "";
		for(int i=0;i<Userlist.size();i++){
			if(i>0){
				name +=",";
			}
			name += Userlist.get(i).getNickname();
		}
		letter.setConsigneeName(name);	
		letter.setConsigneeUid(letterDTO.getConsigneeIds());
		letter.setCreateTime(now);
		//保存信件内容
		letterDaoImpl.insert(letter);
		LetterSenderPO senderPO = new LetterSenderPO();
		senderPO.setLetterId(letter.getId());
		senderPO.setUserId(userId);
		senderPO.setCreateTime(now);
		senderPO.setDisabled(0);//默认信息有效
		//保存发件人
		int j = senderDaoImpl.insert(senderPO);
		logger.debug(j+"");
		String[]arr = letterDTO.getConsigneeIds().split(",");
		List<LetterConsigneePO> list = new ArrayList<LetterConsigneePO>();
		for(int i=0;i<arr.length;i++){
			LetterConsigneePO consigneePO = new LetterConsigneePO();
			consigneePO.setLetterId(letter.getId());
			consigneePO.setType(0);//默认未读状态
			consigneePO.setUserId(Integer.valueOf(arr[i]));
			consigneePO.setCreateTime(now);
			consigneePO.setDisabled(0);//默认信息有效
			list.add(i, consigneePO);
		}
		//保存收件人
		consigneeDaoImpl.insertBatch(list);
		boolean addMessage = addMessage(letter,arr);
		logger.info(addMessage==true?"消息发送成功":"消息发送失败");
	}

	private boolean addMessage(LetterPO letter, String[] arr) {
		MessageSystemInfoPO info = new MessageSystemInfoPO();
		info.setTitle(letter.getTopic());//站内信主题
		info.setContent(letter.getContent());//站内信内容
		info.setMassageStatus(0);//站内信状态 除了报警状态是123  其他都是0
		info.setMessageId(letter.getId());//站内信id
		info.setMessageType(2);//站内信2
		info.setForcedPush(0);//是否强制推送
		info.setAddressUser(letter.getSenderUid()+"");//发件人
		boolean flag = false;
		flag = messageSystemInfoDaoImpl.insert(info)==1;
		if(flag){
			List<MessageUserInfoPO> messageUserList = new ArrayList<MessageUserInfoPO>();
			if(arr.length>0){
				for (int i = 0; i < arr.length; i++) {
					SettingInfoPO setting = settingInfoService.getSetting(Integer.parseInt(arr[i]));//如果用户设置了不推送消息，将不存入数据库
					if(setting.getLetter()==1){
						MessageUserInfoPO userinfo = new MessageUserInfoPO();
						userinfo.setId(null);
						userinfo.setIsread(0);//是否已读
						userinfo.setMid(info.getId());//消息列表id
						userinfo.setUserId(Integer.parseInt(arr[i]));//收件人id  将所有用户都存入
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
