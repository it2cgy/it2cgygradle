package com.yunengzhe.PVMTS.domain.po;

import java.io.Serializable;
import java.util.Date;

public class AttachmentPO implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer id;//附件id
    private String filename;//附件名称
    private String filepath;//附件路径
    private java.util.Date uploadTime;//上传时间
    private Integer uploadUser;//上传人员
    private String filetype;//附件类型   physical-电站体检附件 departure-离场申请附件    fault-故障   alarm-报警  equipment-设备
    private String formId;//表单id
    private String remarks;
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public java.util.Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(Integer uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AttachmentPO(Integer id, String filename, String filepath,
			Date uploadTime, Integer uploadUser, String filetype, String formId) {
		super();
		this.id = id;
		this.filename = filename;
		this.filepath = filepath;
		this.uploadTime = uploadTime;
		this.uploadUser = uploadUser;
		this.filetype = filetype;
		this.formId = formId;
	}
	public AttachmentPO() {
		super();
	}
	@Override
	public String toString() {
		return "AttachmentPO [id=" + id + ", filename=" + filename
				+ ", filepath=" + filepath + ", uploadTime=" + uploadTime
				+ ", uploadUser=" + uploadUser + ", filetype=" + filetype
				+ ", formId=" + formId + "]";
	}


}
