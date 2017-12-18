package com.yunengzhe.PVMTS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.AttacheDTO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.service.AttachmentService;

/**
 * @ClassName:AttachmentController
 * @Description:TODO(描述)
 * @author chenguiyang
 * @date 2017年7月7日上午10:09:59
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
	
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(value="/deleteFile")
	@ResponseBody
	public void deleteFile(@RequestBody AttacheDTO param){
		AttachmentPO paramPO =  new AttachmentPO();
		paramPO.setFiletype(param.getFiletype());
		paramPO.setFormId(param.getFormId());
		attachmentService.deleteAttachment(paramPO);
	}

}
