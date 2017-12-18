package com.yunengzhe.PVMTS.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.FaultSolveDao;
import com.yunengzhe.PVMTS.domain.dto.FaultSolveDTO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.FaultInfoPO;
import com.yunengzhe.PVMTS.domain.po.FaultSolvePO;
import com.yunengzhe.PVMTS.domain.vo.FaultSolveVO;
import com.yunengzhe.PVMTS.util.FileUtil;


@Service("faultSolveService")
public class FaultSolveService {

	private static final Logger logger = LoggerFactory.getLogger(FaultSolveService.class);
	
	@Autowired
	private FaultSolveDao faultSolveDaoImpl;
	
	@Autowired
	private FaultInfoService faultInfoService;
	
	@Autowired
	private AttachmentService attachmentService;
	/**
	 * @Title:getSolveInfo
	 * @Description:TODO(获取工单详情接口  获取故障的处理人员处理时间处理说明) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public FaultSolveVO getSolveInfo(int faultId){
		List<FaultSolvePO> solveList = faultSolveDaoImpl.findBy("faultId",faultId);
		if(solveList.size()>0){
				FaultSolvePO solve = solveList.get(0);
				FaultInfoPO faultInfo = faultInfoService.getFaultInfo(solve.getFaultId());
				FaultSolveVO faultSolveVO = new FaultSolveVO();
				faultSolveVO.setFaultInfo(faultInfo);
				faultSolveVO.setDispatcherId(solve.getDispatcherId());
				faultSolveVO.setDispatcherState(solve.getDispatcherState());
				faultSolveVO.setDispatcherTime(solve.getDispatcherTime());
				faultSolveVO.setFaultId(solve.getFaultId());
				faultSolveVO.setSolveInfo(faultSolveVO.getSolveInfo());
				faultSolveVO.setSolveResult(solve.getSolveResult());
				faultSolveVO.setSolveTime(solve.getSolveTime());
				faultSolveVO.setSolveUser(solve.getSolveUser());
				faultSolveVO.setWorkorderNum(solve.getWorkorderNum());
				return faultSolveVO;
		}
		return null;
	}
	
	
	/**
	 * @Title:faultSolve
	 * @Description:TODO(工单处理故障) 
	 * @param  
	 * @return void
	 * @throws 
	 */
	public void updateSolve(FaultSolveDTO param,int userId){
		try {
			logger.info("-----------#保存故障反馈处理信息#-----------");
			FaultSolvePO faultSolve = new FaultSolvePO();
			faultSolve.setId(param.getSolveId());
			faultSolve.setSolveResult(param.getSolveResult());
			faultSolve.setSolveInfo(param.getSolveInfo());
			faultSolveDaoImpl.update4Selective(faultSolve);
			if(param.getFileInfo().size()>0){
				for(AttachmentPO attachment : param.getFileInfo()){
					attachment.setUploadTime(new Date());
					attachment.setFiletype(FileUtil.WORKORDER_SOLVE);
					attachment.setFormId(param.getFaultId().toString());
					attachment.setUploadUser(userId);
					attachmentService.saveAttachment(attachment);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	 
}
