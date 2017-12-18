package com.yunengzhe.PVMTS.util;

import java.io.File;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.mail.util.MailSSLSocketFactory;
import com.yunengzhe.PVMTS.domain.dto.MailDTO;






/**
 * @ClassName:MailUtils
 * @Description:TODO(邮件服务工具类)
 * @author chenguiyang
 * @date 2017年6月19日上午9:50:38
 */
public class MailUtils {
	
	    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);
	    private String host = ""; // smtp服务器
	    private String from = ""; // 发件人地址
	    private String to = ""; // 收件人地址
	    private String affix = ""; // 附件地址
	    private String affixName = ""; // 附件名称
	    private String user = ""; // 用户名
	    private String pwd = ""; // 密码
	    private String subject = ""; // 邮件标题

	    /**
	     * @Title:setAddress
	     * @Description:TODO(设置邮箱地址以及邮件标题) 
	     * @param @param from
	     * @param @param to
	     * @param @param subject
	     * @return void
	     * @throws
	     */
	    public void setAddress(String from, String to, String subject) {
	        this.from = from;
	        this.to = to;
	        this.subject = subject;
	    }

	    /**
	     * @Title:setAffix
	     * @Description:TODO(设置附件) 
	     * @param @param affix
	     * @param @param affixName
	     * @return void
	     * @throws
	     */
	    public void setAffix(String affix, String affixName) {
	        this.affix = affix;
	        this.affixName = affixName;
	    }

	    private enum DeliveredState {
	        INITIAL, MESSAGE_DELIVERED, MESSAGE_NOT_DELIVERED, MESSAGE_PARTIALLY_DELIVERED,
	    }
	    
	    private static class DeliveredStateFuture {
		        private DeliveredState state = DeliveredState.INITIAL;
		        synchronized void waitForReady() throws InterruptedException {
		            if (state == DeliveredState.INITIAL) {
		                wait();
		            }
		        }
		        synchronized DeliveredState getState() {
		            return state;
		        }
		        synchronized void setState(DeliveredState newState) {
		            state = newState;
		            notifyAll();
		        }
		}
	

    	/**
    	 * @Title:send
    	 * @Description:TODO(发送邮件) 
    	 * @param @param host
    	 * @param @param user
    	 * @param @param pwd
    	 * @return void
    	 * @throws
    	 */
	    public void send(MailDTO param) {
	        this.host = param.getEmailSMTPHost();//发件人邮箱服务器地址
	        this.user = param.getFromMailAccount();//发件人账户
	        this.pwd  = param.getFromMailPassword();//发件人邮箱密码
	        this.subject=param.getEmailSubject();//附件标题
	        logger.info("------------#开始发送邮件");
	        
	        String messge = "server:"+this.host+" port:"+param.getSmtpPort()
	        +" usessl:" +param.getUseSsl()+ " mailType:"+param.getMailType()
	        +" account:"+this.user + " mailPassword:"+this.pwd;
	        logger.info(messge);
	        logger.info("affix:"+affix);
	        try {
	        	logger.info("receive account:"+JsonUtil.beanToJson(param.getReceiveMailAccount())); 
	        } catch (Exception e) {
	        	// TODO Auto-generated catch block
	        	e.printStackTrace();
	        }
	        logger.info("content:"+param.getEmailContent());

	        Properties props = new Properties();//设置参数
	        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.auth", "true"); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
	        props.put("mail.transport.protocol",param.getMailType());   // 使用的协议（JavaMail规范要求）
	        props.setProperty("mail.smtp.port", param.getSmtpPort());//设置smtp服务器端口号

	       
	        try {
	        	
	        	
		        MailSSLSocketFactory sf = new MailSSLSocketFactory();
		        sf.setTrustAllHosts(true);
		        /**
		         * 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
		         * SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接, 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
		         *		 QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
		         */
//		        /*
		        if(param.getUseSsl()==1){//使用ssl
			        props.put("mail.smtp.ssl.socketFactory", sf);
			        props.setProperty("mail.smtp.socketFactory.fallback", "false");
			        props.setProperty("mail.smtp.socketFactory.port", param.getSmtpPort());
			        props.setProperty("mail.smtp.ssl.enable","true");
		        }
//		        */
		        
		        
		        
		        
		        // 用刚刚设置好的props对象构建一个session
		        Session session = Session.getDefaultInstance(props);
		        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		        // 用（你可以在控制台（console)上看到发送邮件的过程）
		        session.setDebug(true);

		        // 用session为参数定义消息对象
	            //MimeMessage message = new MimeMessage(session);
		        final DeliveredStateFuture future = new DeliveredStateFuture();
	        	
	        	 Multipart multipart = new MimeMultipart();
	 	        
	        	  // 3. 创建一封邮件
		        MimeMessage message = createMimeMessage(session,param);
		        
		        
		        /**
		         * 邮件正文
		         */
		        BodyPart contentPart = new MimeBodyPart();  
	 	        contentPart.setText(param.getEmailContent());  
	 	        multipart.addBodyPart(contentPart);  
	            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
	 	        /**
	 	         * 邮件附件
	 	         */
	            File file = new File(affix);
	            BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(affix);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(MimeUtility.encodeText(file.getName()));
                multipart.addBodyPart(attachmentBodyPart);

	            // 将multipart对象放到message中
	            message.setContent(multipart);
	            // 保存邮件
	            message.saveChanges();
	            // 发送邮件
	            Transport transport = session.getTransport(param.getMailType());
	            
	            
	            transport.addTransportListener(new TransportListener() {
		            public void messageDelivered(TransportEvent arg0) {
		            	logger.info("邮件发送成功");
		                future.setState(DeliveredState.MESSAGE_DELIVERED);
		            }
		            public void messageNotDelivered(TransportEvent arg0) {
		            	logger.error("邮件发送失败");
		                future.setState(DeliveredState.MESSAGE_NOT_DELIVERED);
		            }
		            public void messagePartiallyDelivered(TransportEvent arg0) {
		            	logger.error("邮件部分发送成功");
		                future.setState(DeliveredState.MESSAGE_PARTIALLY_DELIVERED);
		            }
		        });
	            
	            
	            // 连接服务器的邮箱
	            transport.connect(host, user, pwd);
	            // 把邮件发送出去
//	            transport.sendMessage(message, message.getRecipients(message.RecipientType.TO));
	            transport.sendMessage(message, message.getAllRecipients());
	            future.waitForReady();
	            logger.info("邮件是否发送成功"+future.getState());
	            transport.close();
	            logger.info("发送邮件成功"+System.currentTimeMillis());
	        } catch (Exception e) {
	        	logger.info("----------mail--from#"+from+"------to#"+to+"-------#邮件发送失败");
	        	logger.error(e.getMessage(),e);
	        }
	    }
	    
	    /**
	     * 创建一封只包含文本的简单邮件
	     *
	     * @param session 和服务器交互的会话
	     * @param sendMail 发件人邮箱
	     * @param receiveMail 收件人邮箱
	     * @return
	     * @throws Exception
	     */
	    public static MimeMessage createMimeMessage(Session session, MailDTO param) throws Exception {
	        // 1. 创建一封邮件
	        MimeMessage message = new MimeMessage(session);
	        // 2. From: 发件人(必填)
	        message.setFrom(new InternetAddress(param.getFromMailAccount(),param.getEmailTitle(),"UTF-8"));
	        // 3. To: 收件人（可以增加多个收件人、抄送、密送）(必填)
	        // message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(param.getReceiveMailAccount().get(i), "驭能者用户", "UTF-8"));
	        for(int i=0;i<param.getReceiveMailAccount().size();i++){
	        	 message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(param.getReceiveMailAccount().get(i)));
	        }
	        // 4.Cc: 抄送（选填）
//	        if(param.getMsgCc().size()>0){
//	        	for(String receive :param.getMsgCc()){
//	        	   message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(receive));
//	        	}
//	        }
//	        //    Bcc: 密送（可选）
//	        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

	        // 4. Subject: 邮件主题
	        message.setSubject(param.getEmailSubject(), "UTF-8");
	        // 5. Content: 邮件正文（可以使用html标签）
	        message.setContent(param.getEmailContent(), "text/html;charset=UTF-8");
	        // 6. 设置发件时间
	        message.setSentDate(new Date());
	        // 7. 保存设置
	        message.saveChanges();
	        return message;
	    }
	    
	    public static boolean isEmail(String email){
	    	if(StringUtils.isBlank(email)){
	    		return false;
	    	}
	    	String str="^([a-zA-Z0-9]*[-_.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	    	return email.matches(str);
	    }
	    public static void main(String[] args) {
	    	System.out.println(isEmail("test@yunegnzhel.com"));
	    	System.out.println(isEmail("test@yunegnzhelcom.cn"));
	    	System.out.println(isEmail("guiyang.chen@guonengrixin.com"));

//	        MailUtils cn = new MailUtils();
//	        // 设置要发送附件的位置和标题
//	        cn.setAffix("f:/file.txt", "123.txt");
//	        
	        /**
	         * 设置smtp服务器以及邮箱的帐号和密码
	         * 用QQ 邮箱作为发生者不好使 （原因不明）
	         * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
	         * 因为程序属于第三方登录，所以登录密码必须使用163的授权码  
	         */
           List<String> receiveAccount = new ArrayList<String>(); 
//	　　　　　// 注意： [授权码和你平时登录的密码是不一样的]
//	        MailDTO mail = new MailDTO();
//	        mail.setFromMailAccount("guiyang.chen@guonengrixin.com");
//	        mail.setFromMailPassword("123456");
//	        mail.setEmailTitle("电站报警");
//	        mail.setEmailContent("测试邮件发送内容");
//	        mail.setEmailSubject("测试邮件发送标题");
//	        receiveAccount.add("guiyang.chen@guonengrixin.com");
//	        mail.setReceiveMailAccount(receiveAccount);
//	        mail.setEmailSMTPHost("mail.guonengrixin.com");
//	        cn.send(mail);
	    }
	}