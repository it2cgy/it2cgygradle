package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.AttachmentDao;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

/**
 * @ClassName:AttachmentService
 * @Description:TODO(附件处理)
 * @author chenguiyang
 * @date 2017年6月27日下午4:26:20
 */
@Service("attachmentService")
public class AttachmentService {

	private static final Logger logger = LoggerFactory.getLogger(AttachmentService.class);
	
	
	@Autowired
	private AttachmentDao attachmentDaoImpl;
	
	/**
	 * 查询附件
	 */
	public List<AttachmentPO> getAttaList(AttachmentPO attachment){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("filetype", attachment.getFiletype());
		map.put("formId", attachment.getFormId());
		List<AttachmentPO> findByMap = attachmentDaoImpl.findByMap(map);
		if(findByMap.size()<=0){
			return null;
		}
		logger.info(findByMap.toString());
		return findByMap;
	}
	
	
	/**
	 * @Title:saveAttachment
	 * @Description:TODO(保存附件) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void saveAttachment(AttachmentPO param){
		logger.info("-------------#保存附件#-------------");
		try {
			param.setUploadTime(new Date());
			attachmentDaoImpl.insert(param);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * @Title:updateAttachment
	 * @Description:TODO(删除附件) 
	 * @param @param param
	 * 附件对象需要传递 filetype 和formId两个字段即可
	 * @return void
	 * @throws
	 */
	public boolean deleteAttachment(AttachmentPO param){
		logger.info("-------------#更新附件#-------------");
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			if(!StringUtils.isBlank(param.getFormId())||!StringUtils.isBlank(param.getFiletype())){
				paramMap.put("formId", param.getFormId());
				paramMap.put("filetype", param.getFiletype());
			}
			attachmentDaoImpl.deleteFile(paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
		return true;
	}
	

}
