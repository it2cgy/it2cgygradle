package com.yunengzhe.PVMTS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.po.SyncDatalogPO;
import com.yunengzhe.PVMTS.hbase.HbaseClient;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.service.SyncDatalogService;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.commons.util.PropertiesUtil;
import com.yunengzhe.commons.util.SpringContextUtil;
import com.yunengzhe.commons.util.hbase.HbaseCellData;
import com.yunengzhe.commons.util.hbase.HbasePutData;
import com.yunengzhe.commons.util.hbase.HbaseResultData; 

public class SyncThread extends Thread{
	
	public static final String sync_form = PropertiesUtil.getString("sync_form");
	public static final String sync_to = PropertiesUtil.getString("sync_to");
	public static final String DATA_COMPANY = PropertiesUtil.getString("datacompany");
	private static final Logger logger = LoggerFactory.getLogger(SyncThread.class);

	private SyncDatalogService syncDatalogService;
	private PointInfoService pointInfoService;

	private String name;
	private Date startDate;
	private Date endDate;
	private Integer userid;
	public SyncThread(Integer userid,String name, Date startDate, Date endDate){
		this.userid = userid;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public void run() { 
		try { 
			if(syncDatalogService==null){
				syncDatalogService = (SyncDatalogService) SpringContextUtil.getBean("syncDatalogService");
			}
			if(pointInfoService==null){
				pointInfoService = (PointInfoService) SpringContextUtil.getBean("pointInfoService");
			}
			Date dateNow = new Date();
			SyncDatalogPO syncLog = new SyncDatalogPO();
			syncLog.setName(this.name);
			syncLog.setStartDate(this.startDate);
			syncLog.setEndDate(this.endDate);
			syncLog.setStatus(0);
			syncLog.setCreateDate(dateNow);
			syncLog.setUpdateDate(dateNow);
			syncLog.setUserId(this.userid);

			syncDatalogService.saveSyncDataLog(syncLog);

			HbaseClient hbase2 = new HbaseClient(sync_to);
			HbaseClient hbase1 = new HbaseClient(sync_form);
			logger.info("sync_form:"+sync_form+" sync_to:"+sync_to);
			List<PointInfoPO> points = pointInfoService.getPoints(); 
			int i=0;
			int oldStatus=0;
			try { 
				for(PointInfoPO po:points){
					i++;
					String startKey = PointUtil.getKey(""+po.getId(), this.startDate.getTime()/1000);
					String endKey = PointUtil.getKey(""+po.getId(), this.endDate.getTime()/1000);

					List<HbaseResultData> results = hbase1.getResultScann(DATA_COMPANY+":analoginputhistory", startKey, endKey);

					List<HbasePutData> datas = new ArrayList<HbasePutData>();
					for(HbaseResultData result:results){
						for(HbaseCellData cell:result.getCells()){
							HbasePutData data = new HbasePutData();
							data.setKey(result.getKey());
							data.setFamilyName(cell.getFamilyName());
							data.setQualifier(cell.getQualifier());
							data.setValue(cell.getValue());
							datas.add(data);
						}
					}
					if(datas.size()>0){
						hbase2.putBatch(DATA_COMPANY+":analoginputhistory", datas);
					}
					int status=i*100/(points.size()-1);
					if(status-oldStatus>=1){
						syncLog.setStatus(status);
						syncLog.setUpdateDate(new Date());
						syncDatalogService.updateSyncDataLog(syncLog);
						oldStatus = status;
					}
					
					logger.info("sync_form:"+sync_form+" sync_to:"+sync_to +" index:"+i +" of "+points.size() +" datasize:"+datas.size());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			} 
			syncLog.setStatus(100);
			syncLog.setUpdateDate(new Date());
			syncDatalogService.updateSyncDataLog(syncLog);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		} 
	}

	public static void main(String[] args){ 

	}
}
