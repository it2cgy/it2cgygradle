package com.yunengzhe.PVMTS.domain.vo;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;


/**
 * @ClassName:FaultInfoVO
 * @Description:TODO(返回vo)
 * @author chenguiyang
 * @date 2017年6月27日下午6:52:46
 */
public class FaultInfoVO {
	 private Integer id;
	 private String state;
	 private Integer faultStatus;
	 private List<AttachmentPO> attachmentList;
	 private String filename;
	 private String filepath;
	 
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<AttachmentPO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentPO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public Integer getFaultStatus() {
		return faultStatus;
	}
	public void setFaultStatus(Integer faultStatus) {
		this.faultStatus = faultStatus;
	}
	 
}
