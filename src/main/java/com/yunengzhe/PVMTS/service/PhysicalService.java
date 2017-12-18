package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.PhysicalDao;
import com.yunengzhe.PVMTS.domain.dto.PhysicalDTO;
import com.yunengzhe.PVMTS.domain.dto.PhysicalExamineDTO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.PhysicalPO;
import com.yunengzhe.PVMTS.domain.vo.ExamineInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PhysicalVO;
import com.yunengzhe.PVMTS.util.FileUtil;


@Service("physicalService")
public class PhysicalService {

	private static final Logger logger = LoggerFactory.getLogger(PhysicalService.class);
	
	@Autowired
	private PhysicalDao physicalDaoImpl;
	 
	@Autowired
	private AttachmentService attachmentService;
	/**
	 * @Title:saveExamine
	 * @Description:TODO(保存电站体检检查项目) 
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int editExamine(PhysicalDTO physicalDTO,int userId){
		int flag = 0;
		try {
				PhysicalPO physicalPO = new PhysicalPO();
				physicalPO.setIsSolve(physicalDTO.getIsSolve());
				physicalPO.setExplainInfo(physicalDTO.getExplainInfo());
				physicalPO.setExamineId(physicalDTO.getExamineId());
				physicalPO.setWorkorderNum(physicalDTO.getWorkorderNum());
				if(physicalDTO.getId()==null){ 
					physicalDaoImpl.insert(physicalPO);
				}else{
					physicalPO.setId(physicalDTO.getId());
					flag = physicalDaoImpl.update4Selective(physicalPO);
				}
				if(physicalDTO.getFileInfo().size()>0){//上传附件
					for(AttachmentPO attachment : physicalDTO.getFileInfo()){
						attachment.setUploadTime(new Date());
						attachment.setFiletype(FileUtil.PHYSICAL);
						attachment.setFormId(physicalPO.getId().toString()+"_"+physicalPO.getExamineId().toString());
						attachment.setUploadUser(userId);
						attachmentService.saveAttachment(attachment);
					}
				}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return flag;
	}
	
	
	/**
	 * @Title:physicalInfo
	 * @Description:TODO(获取电站体检信息) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	public List<PhysicalVO> physicalInfo(String orderNum){
		List<PhysicalPO> physicalInfo = physicalDaoImpl.getPhysicalList(orderNum);
		List<PhysicalVO> resultList = new ArrayList<PhysicalVO>();
		if(physicalInfo.size()>0){
			for(PhysicalPO physical : physicalInfo){
				PhysicalVO physicalVO = new PhysicalVO();
				physicalVO.setId(physical.getId());
				physicalVO.setExamineId(physical.getExamineId());
				physicalVO.setWorkorderNum(physical.getWorkorderNum());
				physicalVO.setCheckupContent(physical.getCheckupContent());
				physicalVO.setCheckupName(physical.getCheckupName());
				List<PhysicalPO> result =  physicalDaoImpl.findBy("examineId",physical.getExamineId());
				List<ExamineInfoVO> list = new ArrayList<ExamineInfoVO>();
				for(PhysicalPO resultPhy : result){
					ExamineInfoVO examine =new  ExamineInfoVO();
					examine.setIsSolve(resultPhy.getIsSolve());
					examine.setExplainInfo(resultPhy.getExplainInfo());
					AttachmentPO attachment = new AttachmentPO();
					attachment.setFiletype(FileUtil.PHYSICAL);
					attachment.setFormId(resultPhy.getId().toString()+"_"+resultPhy.getExamineId().toString());
					List<AttachmentPO> file = attachmentService.getAttaList(attachment);
					examine.setAttachmentList(file);
					list.add(examine);
				}
				physicalVO.setExamineInfo(list);
				resultList.add(physicalVO);
			}
		}
		return resultList;
	}
	
	
	/**
	 * @Title:savePhysicalInfo
	 * @Description:TODO(生成工单-保存电站体检检查项目) 
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int savePhysicalInfo(List<PhysicalPO>  physicalInfo,String orderNum){
		logger.debug("生成工单-----保存电站体检检查项目");
		try {
			physicalDaoImpl.insertBatch(physicalInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return 0;
	}
}
