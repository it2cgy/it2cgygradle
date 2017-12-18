package com.yunengzhe.PVMTS.domain.dto;

import java.util.List;


/**
 * @ClassName:MailDTO
 * @Description:TODO(邮件发送dto)
 * @author chenguiyang
 * @date 2017年6月19日上午10:08:07
 */
public class MailDTO {
	
	private String fromMailAccount;//发件人地址
	private String fromMailPassword;//发件人邮箱密码
	private List<String> receiveMailAccount;  //收件人地址  【集合】
	private String emailSMTPHost;//发件人smtp服务器地址
	private String emailTitle;   //邮件标题
	private String emailSubject; //邮件发送主题
	private String emailFilePath;//附件路径
	private String emailFileName;//附件名称
	private String emailContent;//邮件内容
	private String smtpPort;//使用ssl连接的端口号
	private List<String> msgCc;//抄送人地址  【集合，以逗号分割】
	private List<String> blindCarbonCopy;//密送
	private String mailType;
	
	
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	private int   useSsl;
	
	public int getUseSsl() {
		return useSsl;
	}
	public void setUseSsl(int useSsl) {
		this.useSsl = useSsl;
	}
	public List<String> getBlindCarbonCopy() {
		return blindCarbonCopy;
	}
	public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
		this.blindCarbonCopy = blindCarbonCopy;
	}
	public List<String> getReceiveMailAccount() {
		return receiveMailAccount;
	}
	public void setReceiveMailAccount(List<String> receiveMailAccount) {
		this.receiveMailAccount = receiveMailAccount;
	}
	public List<String> getMsgCc() {
		return msgCc;
	}
	public void setMsgCc(List<String> msgCc) {
		this.msgCc = msgCc;
	}
	public String getEmailTitle() {
		return emailTitle;
	}
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getEmailFilePath() {
		return emailFilePath;
	}
	public void setEmailFilePath(String emailFilePath) {
		this.emailFilePath = emailFilePath;
	}
	public String getEmailFileName() {
		return emailFileName;
	}
	public void setEmailFileName(String emailFileName) {
		this.emailFileName = emailFileName;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getFromMailAccount() {
		return fromMailAccount;
	}
	public void setFromMailAccount(String fromMailAccount) {
		this.fromMailAccount = fromMailAccount;
	}
	public String getFromMailPassword() {
		return fromMailPassword;
	}
	public void setFromMailPassword(String fromMailPassword) {
		this.fromMailPassword = fromMailPassword;
	}
	public String getEmailSMTPHost() {
		return emailSMTPHost;
	}
	public void setEmailSMTPHost(String emailSMTPHost) {
		this.emailSMTPHost = emailSMTPHost;
	}
	
	
	
	
	
	
}
