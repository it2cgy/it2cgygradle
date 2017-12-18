package com.yunengzhe.PVMTS.service.monitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.SyncThread;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureData;
import com.yunengzhe.PVMTS.domain.vo.monitor.AnalogStructureDatas;
import com.yunengzhe.PVMTS.domain.vo.monitor.EquipPoints;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorHistoryPointData;
import com.yunengzhe.PVMTS.domain.vo.monitor.MonitorRealData;
import com.yunengzhe.PVMTS.hbase.HbaseClient;
import com.yunengzhe.PVMTS.service.PointCacheService;
import com.yunengzhe.PVMTS.service.PointInfoService;
import com.yunengzhe.PVMTS.util.PointUtil;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.TimeUtil;
import com.yunengzhe.commons.util.BigDecimalUtils;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.commons.util.JsonUtil;
import com.yunengzhe.commons.util.PropertiesUtil;
import com.yunengzhe.commons.util.hbase.HbaseCellData;
import com.yunengzhe.commons.util.hbase.HbaseManUtil;
import com.yunengzhe.commons.util.hbase.HbasePutData;
import com.yunengzhe.commons.util.hbase.HbaseResultData; 

@Service("stationMonitorService")
public class StationMonitorService {
	public static final String DATA_COMPANY = PropertiesUtil.getString("datacompany");
	public static final String CACHE_PREFIX_POINT=  PropertiesUtil.getString("datacompany")+"_COLLECT_POINT"; 
	
	@Autowired
	private PointInfoService pointInfoService;
	
	public void syncHistoryData(int userid,String name,Date start,Date end){ 
		SyncThread thread = new SyncThread(userid,name,start,end);
		thread.start();
	}

	/**
	 * 数据库里什么样子，就是什么样子的
	 * @param pointid 点id
	 * @param start 开始时间
	 * @param end 结束时间 
	 * @return
	 */
	public MonitorHistoryPointData getMonitorHistoryData(int pointid,Date start,Date end,boolean haveReal){
		String startKey = PointUtil.getKey(""+pointid, start.getTime()/1000);
		String endKey = PointUtil.getKey(""+pointid, end.getTime()/1000);



		List<MonitorHistoryData> datas = new ArrayList<MonitorHistoryData>();
		List<HbaseResultData> queryResults = HbaseManUtil.find(DATA_COMPANY+":analoginputhistory", startKey,endKey);
		List<String> listDate = TimeUtil.getSpaceDateList(start,end);
		try {
			System.out.println("list-------"+JsonUtil.beanToJson(listDate));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(queryResults.isEmpty()){
			for(String temp : listDate){
				MonitorHistoryData hisData = new MonitorHistoryData();
				hisData.setTime(TimeUtil.string2Date(temp).getTime());
				hisData.setValue(Double.valueOf("0.00"));
				datas.add(hisData);
			}
		}else{
			for(HbaseResultData hbaseData:queryResults){
//				MonitorHistoryData hisData = new MonitorHistoryData();
//				String key = hbaseData.getKey();
//				String value = hbaseData.getCells().get(0).getValue();
//				hisData.setTime(1000L*PointUtil.getTimeFormKey(key));
//				if(BigDecimalUtils.isNumber(value)){
//					hisData.setValue(Double.valueOf(value));
//				}
//				
				MonitorHistoryData hisData=	MonitorHistoryData.getHistoryData(hbaseData);
				if(hisData!=null){
					datas.add(hisData);
				}
			}
		}

		//是否补充实时数据
		if(haveReal){
			MonitorRealData realdata = this.getMonitorRealData(pointid); 
			if(realdata!=null){
				MonitorHistoryData hisData = new MonitorHistoryData();
				hisData.setTime(realdata.getTime()*1000);
				hisData.setValue(realdata.getValue());
				datas.add(hisData);
			}
		}

		MonitorHistoryPointData pointData = new MonitorHistoryPointData();
		pointData.setAnaloginputId(pointid);
		pointData.setHistoryDatas(datas);
		return pointData;
	}
	/**
	 * 单点实时数据查询
	 */
	public AnalogStructureDatas getMonitorEquipRealData(int equipmentcontainerTableid,int equipmentcontainerId){
		AnalogStructureDatas datas = new AnalogStructureDatas();
		List<AnalogStructureData> list = new ArrayList<AnalogStructureData>();
		datas.setData(list);
		String key = PointUtil.getKey(equipmentcontainerTableid,equipmentcontainerId);  
		EquipPoints equipPoints = (EquipPoints) CacheManUtil.getObjectFromJson(PointCacheService.CACHE_PREFIX_EQUIP, key,EquipPoints.class);

		if(equipPoints!=null && equipPoints.getPoints()!=null && equipPoints.getPoints().size()>0){
			List<PointInfoPO> resultList = equipPoints.getPoints();

			//List<PointInfoPO> resultList = pointInfoService.getPointListByTableAndContainer(""+equipmentcontainerTableid,""+equipmentcontainerId);
			if(resultList.size()>0){
				for(PointInfoPO p : resultList){
					AnalogStructureData analog = new AnalogStructureData();
					analog.setAvaiabletag(0);
					analog.setMeasuerName(p.getMeasurementtypeName());
					analog.setMtime(0);
					analog.setQualitycode(0);
					String value = CacheManUtil.getString(CACHE_PREFIX_POINT, ""+p.getId());
					MonitorRealData realData = PointUtil.getCachePointValue(value);
					analog.setTime(realData.getTime());
					analog.setValue(Float.parseFloat(""+realData.getValue()));
					list.add(analog);
				}
			}
		}

		return datas;
	}



	/**
	 * 单点实时数据查询
	 */
	public MonitorRealData getMonitorRealData(int pointid){

		String value = CacheManUtil.getString(CACHE_PREFIX_POINT, ""+pointid);
		MonitorRealData realData = PointUtil.getCachePointValue(value);
		if(realData!=null){
			realData.setPointid(pointid);
		}else{
			realData = new MonitorRealData();
			realData.setPointid(pointid);
			realData.setTime(0);
			realData.setValue(0);
		}
		if(realData.getTime()>1000000000000L){//防止时间不统一的问题
			realData.setTime(realData.getTime()/1000);
		}
		return realData;

	}



	/**
	 * 按规定的时间间隔返回数据
	 * @param pointid 点id
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param dateSpan 秒
	 * @return
	 */
	public MonitorHistoryPointData getMonitorHistoryData(int pointid,Date start,Date end,long timeSpan,boolean haveReal){

		if(timeSpan<=60){
			timeSpan = 60;//时间间隔必须1分钟以上 乐叶要求1分钟
		}
		Date dateNow = new Date();
		if(end.getTime()>dateNow.getTime()){
			end = dateNow;
		}
		String startKey = PointUtil.getKey(""+pointid, start.getTime()/1000);
		String endKey = PointUtil.getKey(""+pointid, end.getTime()/1000);

		double preValue = 0;
		long preTime = (start.getTime()/1000/timeSpan)*timeSpan;
		long lastTime = (end.getTime()/1000/timeSpan)*timeSpan;
		List<MonitorHistoryData> datas = new ArrayList<MonitorHistoryData>();
		List<HbaseResultData> queryResults = HbaseManUtil.find(DATA_COMPANY+":analoginputhistory", startKey,endKey);
		if(queryResults==null){
			queryResults = new ArrayList<HbaseResultData>();
		}

		for(HbaseResultData hbaseData:queryResults){
			String key = hbaseData.getKey();
			long time = PointUtil.getTimeFormKey(key);
			time = (time/timeSpan)*timeSpan;//按时间间隔转换一下时间
			long dffTime = (time-preTime);
			if(dffTime > timeSpan){//当前点时间和上一个点时间差>timeSpan,说明需要补点
				//补偿中间缺失的数据
				List<MonitorHistoryData> redressDatas = getRedress(preTime,time,timeSpan,preValue);
				preTime = time;
				datas.addAll(redressDatas);
			}

			if(dffTime >= timeSpan ){//正好是需要的点
				preTime = time;
				MonitorHistoryData hisData = MonitorHistoryData.getHistoryData(hbaseData);
				hisData.setTime(1000*time);
				preValue = hisData.getValue(); 
				datas.add(hisData);
			}

		}

		MonitorRealData realdata = this.getMonitorRealData(pointid);
		// 还需要最后补偿 因为查询出来的数据可能是缺失的
		long dffTime = (lastTime-preTime);
		if(dffTime >= timeSpan){
			if(realdata!=null && realdata.getTime()>lastTime){
				List<MonitorHistoryData> redressDatas = getRedress(preTime,lastTime,timeSpan,preValue);
				datas.addAll(redressDatas);
			}else if(realdata!=null && realdata.getTime()>preTime && realdata.getTime()<=lastTime){
				List<MonitorHistoryData> redressDatas = getRedress(preTime,realdata.getTime(),timeSpan,preValue);
				datas.addAll(redressDatas);
			}
		}
		//是否补充实时数据
		if(haveReal && realdata!=null){ 
			if(realdata.getTime()*1000>start.getTime()&&realdata.getTime()*1000<end.getTime()){
				if(datas.size()<=0){
					MonitorHistoryData hisData = new MonitorHistoryData();
					hisData.setTime(realdata.getTime()*1000);
					hisData.setValue(realdata.getValue());
					datas.add(hisData);
				} else{
					long lastDataTime = datas.get(datas.size()-1).getTime();
					if(lastDataTime < realdata.getTime()){
						MonitorHistoryData hisData = new MonitorHistoryData();
						hisData.setTime(realdata.getTime()*1000);
						hisData.setValue(realdata.getValue());
						datas.add(hisData);
					}
				}
			}



		}


		MonitorHistoryPointData pointData = new MonitorHistoryPointData();
		pointData.setAnaloginputId(pointid);
		pointData.setHistoryDatas(datas);
		return pointData;
	}
	/**
	 * startTime  到 endTime 之间，每隔 endTime 秒，一个数据 返回的数据一定不包含 <startTime和>=endTime的数据
	 * @param startTime
	 * @param endTime
	 * @param timeSpan
	 * @return
	 */
	private  List<MonitorHistoryData>  getRedress(long startTime,long endTime,long timeSpan,double defaultValue){
		List<MonitorHistoryData> datas = new ArrayList<MonitorHistoryData>();
		if(timeSpan<1){ //时间间隔不能小于5分钟
			timeSpan=1;
		}
		long time = (startTime/timeSpan)*timeSpan;

		while(time<endTime){
			MonitorHistoryData data = new MonitorHistoryData();
			data.setTime(time*1000);
			data.setValue(defaultValue);
			data.setValueOriginal(defaultValue);
			datas.add(data);
			time = time+timeSpan;
		}
		return datas;
	}


}
