package com.yunengzhe.PVMTS;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.domain.dto.MailDTO;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.MailConfigPO;
import com.yunengzhe.PVMTS.domain.vo.UserEmailVO;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.MailConfigService;
import com.yunengzhe.PVMTS.service.PushMailService;
import com.yunengzhe.PVMTS.util.CreateDateExcel;
import com.yunengzhe.PVMTS.util.JsonUtil;
import com.yunengzhe.PVMTS.util.MailUtils;
import com.yunengzhe.commons.util.SpringContextUtil;
import com.yunengzhe.commons.util.TimeUtil;

/**
 * @ClassName:AlarmThread
 * @Description:TODO(报警发送邮件处理)
 * @author chenguiyang
 * @date 2017年9月12日下午5:05:14
 */
public class AlarmThread extends Thread{


	private static final Logger logger = LoggerFactory.getLogger(AlarmThread.class);
	private AlarmInfoService alarmInfoService;
	private MailConfigService mailConfigService;
	private PushMailService pushMailService;
	private static long datetime = 0;
	private static String mailUsername;
	private static String mailPassword;
	private static String serverPath;
	private static String port;
	private static String mailType;
	private static int usessl;

	@Override
	public void run() {

		long pushTime =0; 
		while (true) {
			try {
				if(alarmInfoService==null){
					alarmInfoService = (AlarmInfoService) SpringContextUtil.getBean("alarmInfoService");
				}
				if(pushMailService==null){
					pushMailService = (PushMailService) SpringContextUtil.getBean("pushMailService");
				}
				if(mailConfigService==null){
					mailConfigService = (MailConfigService) SpringContextUtil.getBean("mailConfigService");
				}
				logger.info("------need refresh config:"+mailConfigService.isRefresh());
				if(mailConfigService.isRefresh()==true){
					logger.info("query config**************");
					MailConfigPO config = mailConfigService.getConfigInfo();
					datetime = config.getPushTime()*60*1000;//后台存储为10分钟 60分钟 120分钟 转换为毫秒值
					this.mailUsername=config.getMailUsername();
					this.mailPassword=config.getMailPassword();
					this.serverPath = config.getServerPath();
					this.port = config.getMailPort();
					this.usessl=config.getUsessl();
					this.mailType=config.getMailType();
					mailConfigService.setRefresh(false);
				}
				Date date = new Date(System.currentTimeMillis());
				if((date.getTime()-pushTime)>=datetime){
					logger.info("----begin push email **************"); 
					List<UserEmailVO> emails = pushMailService.getUserEmail();
					if(emails!=null && emails.size()>0){
						List<AlarmInfoPO> alarmList = alarmInfoService.getAlarmListBySearch("", 0,null, 0, 1000,null,null,null,null);
						if(alarmList.size()>0){
							List<List<String>> columns = new ArrayList<List<String>>();
							List<List<String>> titleList = new ArrayList<List<String>>();
							List<String> title = new ArrayList<String>();//行
							title.add("报警序号");
							title.add("故障等级");
							title.add("故障发生时间");
							title.add("故障设备");
							title.add("故障详情");
							int i =1;
							titleList.add(title);

							for(AlarmInfoPO p : alarmList){
								List<String> row = new ArrayList<String>();//行
								logger.info("数据"+p.getAlarmMessage());
								row.add(p.getId()+"");
								if (p.getAlarmGrade().intValue()<=1) { 
									row.add("I");
								}
								else if (p.getAlarmGrade().intValue()==2) {  
									row.add("II");
								}
								else if (p.getAlarmGrade().intValue()>=3) {  
									row.add("III");
								} 
								row.add(TimeUtil.date2String(p.getEventTime(),"yyyy.MM.dd HH:mm"));
								row.add(p.getMeasurePointDiscription()+"--"+p.getAlarmNumber());
								row.add(p.getAlarmMessage());
								i++;
								columns.add(row);
							}
							SimpleDateFormat formar = new SimpleDateFormat("yyyyMMddHHmmss");
							String format = formar.format(date);
							Map<String,Object> map = new HashMap<String,Object>();  //给format传数据
							map.put("list",columns);
							map.put("list2",titleList);
							map.put("column",title.size());
							map.put("row",columns.size()+10);
							logger.info("begin create excel!");
							File file =CreateDateExcel.createAlarmExcel(map, "报警信息"+format,"alarmMail");
							logger.info("create excel success,file path:"+file.getPath());
							String url = file.getAbsolutePath();

							logger.info("时间差"+(date.getTime()-pushTime));
							logger.info("推送时间"+datetime);
							logger.info("推送时间未到"+new Date(System.currentTimeMillis()));
							long time = createMail(url,emails);
						} 
						pushTime=System.currentTimeMillis(); 
					}
				}else{
					logger.info("no alarm");
				}

				logger.info("sleep 60000ms");
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}


	/**
	 * @Title:createMail
	 * @Description:TODO(发送邮件) 
	 * @param @param config
	 * @param @param url
	 * @param @param emails
	 * @return void
	 * @throws
	 */
	public long createMail(String url,List<UserEmailVO> emails ){
		logger.info("开始发送邮件");
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formar = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String format = formar.format(date);
		MailUtils cn = new MailUtils();
		// 设置要发送附件的位置和标题
		cn.setAffix(url,format+"-电站报警");
		/**
		 * 设置smtp服务器以及邮箱的帐号和密码
		 * 用QQ 邮箱作为发生者不好使 （原因不明）
		 * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
		 * 因为程序属于第三方登录，所以登录密码必须使用163的授权码  
		 */
		// 注意： [授权码和你平时登录的密码是不一样的]
		MailDTO mail = new MailDTO();
		mail.setFromMailAccount(this.mailUsername.trim());//发件人账户
		mail.setFromMailPassword(this.mailPassword.trim());//账户密码
		mail.setEmailTitle(format+"-电站报警");//邮件标题
		mail.setEmailContent("当前电站发生报警，请及时处理！详情见附件！");//邮件正文内容
		mail.setEmailSubject("发生报警，请及时处理");//附件标题
		mail.setEmailSMTPHost(this.serverPath.trim());//发件邮箱服务器地址
		mail.setSmtpPort(this.port.trim());
		mail.setUseSsl(this.usessl);
		mail.setMailType(this.mailType.trim());
		/**
		 * 遍历收件人
		 */

		List<String> receiveAccount = new ArrayList<String>(); 
		for(UserEmailVO v : emails){
			String email = v.getEmail();
			if(email==null){
				logger.error(v.getUsername()+" email is null!*****");
			}else{
				if(MailUtils.isEmail(email.trim())){ 
					receiveAccount.add(email.trim());
				}else{
					logger.error(email+" is not a email!*****");
				}
			}

		}
		if(receiveAccount.size()>0){ 
			mail.setReceiveMailAccount(receiveAccount);
			cn.send(mail);
			return date.getTime();//返回当前邮件推送的时间
		}else{    
			logger.error("receive is null,请设置收件人！************");
			return 0;
		}
	}


	public static void main(String[] args){


		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formar = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String format = formar.format(date);
		System.out.println(format);
		//	Date date = TimeUtil.string2Date("2017-09-14 14:00:00","yyyy-MM-dd HH:mm:ss");
		//		System.out.println("date1"+date.getTime());
		//		Date date2 = TimeUtil.string2Date("2017-09-14 14:00:01","yyyy-MM-dd HH:mm:ss");
		//		System.out.println("date2"+date2.getTime());
		//		System.out.println("------"+(date2.getTime()-date.getTime()));

	}
}
