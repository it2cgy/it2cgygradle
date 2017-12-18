package com.yunengzhe.PVMTS.util;



import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
/**
 * 测试 java调用dll接口文件  rtdbfinterface
 * 本次测试的 rtdbfinterface文件 需要在本地搭建数据库环境
 * dll文件放在工程目录下
 * @author Administrator
 *
 */
public interface AlarmForextend extends Library {
	//第一个参数是 dll文件所在的绝对路径  可自行更改
	//Properties prop = PropertiesLoad.getProperties("config/paramsConfig.properties");
	
	//第一个参数是 dll文件所在的绝对路径  可自行更改
	//AlarmForextend INSTANCE = (AlarmForextend)Native.loadLibrary(prop.getProperty("AlarmForextendPath"), AlarmForextend.class);
	//AlarmForextend INSTANCE = (AlarmForextend)Native.loadLibrary(InitRtdbFinterFace.scadaPathA, AlarmForextend.class);
	//AlarmForextend INSTANCE =null;
	
	
	/**
	 * 时标结构体  java对应类
	 * @author Administrator
	 *
	 */
	public static class SDateTime extends Structure{
		public static class ByReference extends SDateTime implements Structure.ByReference{              
        }  
          
        public static class ByValue extends SDateTime implements Structure.ByValue{  
        }
		
		public int m_seconds;
		public int m_microseconds;
		@Override
		protected List getFieldOrder() {
			return Arrays.asList("m_seconds","m_microseconds");
		}
	}
	
	
	/**
	 * 调用GetAnalogValue方法定义的java类
	 * 作用:接受C方法返回的结构体
	 * @author Administrator
	 *
	 */
	public static class AlarmItemsStructureArrays extends Structure{
		public static class ByReference extends AlarmItemsStructureArrays implements Structure.ByReference{              
        }  
          
        public static class ByValue extends AlarmItemsStructureArrays implements Structure.ByValue{  
        }  
        
        public long m_ID ;		//告警ID
        public short m_type;	//告警类型
		public short	m_reasonCode;					//告警原因码
		public SDateTime.ByValue m_timeStamp;				//时标
		public SDateTime.ByValue m_ackTime;					//确认时间
		public byte	m_ackFlag;						//确认标志
		public int	m_objectID;							//主对象ID
		public byte[] m_objectName = new byte[64];				//报警对象名称
		public float	m_objectValue;					//报警对象值
		public int	m_objTabID;							//报警对象表ID
		public int	m_priority;							//报警优先级
		public byte	m_alarmorEvent;					//事项状态
		public int	m_containerObj1Id;					//设备一ID
		public short	m_containerObj1TabId;			//设备类型一ID
		public int	m_qualityCode;						//质量码
		public byte[] m_alarmstring = new byte[128];				//报警串
		@Override
		protected List getFieldOrder() {
			
			return Arrays.asList("m_ID","m_type","m_reasonCode","m_timeStamp","m_ackTime","m_ackFlag",
					"m_objectID","m_objectName","m_objectValue","m_objTabID","m_priority","m_alarmorEvent",
					"m_containerObj1Id","m_containerObj1TabId","m_qualityCode","m_alarmstring");
		}
        
	}
	
//	/*
//	*  含义：初始化报警接口，注册组件和相关服务
//	*		所有报警查询，确认，删除操作之前都要调用此函数，并且只初始化一次就可以了
//	*/
//	public void InitAlarmInterface();
//	/*
//	*  含义：如果不再使用报警接口，请调用此接口释放资源。
//	*/
//	public void FreeAlarmInterface();
//
//	/*
//	*  含义：按照报警消息的id，确认报警
//	*  参数：num			获取告警条目总数量，即ret数组的长度
//	*        ret		输出参数，返回的条目放入数组中
//	*  返回值：0 表示失败，1 表示成功
//	*/
//	public int call_getAlarmItem(int num, AlarmItemsStructureArrays[] results);
//
//
// 	/*
// 	*  含义：按照报警消息的id，确认报警
// 	*  参数：alarmid			被确认的报警消息id
// 	*        username		确认者姓名
// 	*  返回值：0 表示失败，1 表示成功
// 	*/
//	public void call_ackalarmbyid(long alarmid,String username,int nameLength);
// 
//	/*
// 	*  含义：按照报警消息id，删除报警
// 	*  参数：alarmid			被删除报警id
// 	*        username		删除者姓名
// 	*  返回值：0 表示失败，1 表示成功
// 	*/
//	public void call_deletealarmbyid(long alarmid,String username,int nameLength);
//	
//	

}
