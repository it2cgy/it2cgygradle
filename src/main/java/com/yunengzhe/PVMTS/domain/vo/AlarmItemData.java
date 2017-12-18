package com.yunengzhe.PVMTS.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yunengzhe.PVMTS.util.AlarmForextend.SDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmItemData {
	  public long m_ID ;		//告警ID
      public short m_type;	//告警类型
		public short	m_reasonCode;					//告警原因码
		public SDateTime.ByValue m_timeStamp;				//时标
		public SDateTime.ByValue m_ackTime;					//确认时间
		public String	m_ackFlag;						//确认标志
		public int	m_objectID;							//主对象ID
		public String m_objectName;				//报警对象名称
		public float	m_objectValue;					//报警对象值
		public int	m_objTabID;							//报警对象表ID
		public int	m_priority;							//报警优先级
		public String	m_alarmorEvent;					//事项状态
		public int	m_containerObj1Id;					//设备一ID
		public short	m_containerObj1TabId;			//设备类型一ID
		public int	m_qualityCode;						//质量码
		public String m_alarmstring;				//报警串
		public long getM_ID() {
			return m_ID;
		}
		public void setM_ID(long m_ID) {
			this.m_ID = m_ID;
		}
		public short getM_type() {
			return m_type;
		}
		public void setM_type(short m_type) {
			this.m_type = m_type;
		}
		public short getM_reasonCode() {
			return m_reasonCode;
		}
		public void setM_reasonCode(short m_reasonCode) {
			this.m_reasonCode = m_reasonCode;
		}
		public SDateTime.ByValue getM_timeStamp() {
			return m_timeStamp;
		}
		public void setM_timeStamp(SDateTime.ByValue m_timeStamp) {
			this.m_timeStamp = m_timeStamp;
		}
		public SDateTime.ByValue getM_ackTime() {
			return m_ackTime;
		}
		public void setM_ackTime(SDateTime.ByValue m_ackTime) {
			this.m_ackTime = m_ackTime;
		}
		public String getM_ackFlag() {
			return m_ackFlag;
		}
		public void setM_ackFlag(String m_ackFlag) {
			this.m_ackFlag = m_ackFlag;
		}
		public int getM_objectID() {
			return m_objectID;
		}
		public void setM_objectID(int m_objectID) {
			this.m_objectID = m_objectID;
		}
		public String getM_objectName() {
			return m_objectName;
		}
		public void setM_objectName(String m_objectName) {
			this.m_objectName = m_objectName;
		}
		public float getM_objectValue() {
			return m_objectValue;
		}
		public void setM_objectValue(float m_objectValue) {
			this.m_objectValue = m_objectValue;
		}
		public int getM_objTabID() {
			return m_objTabID;
		}
		public void setM_objTabID(int m_objTabID) {
			this.m_objTabID = m_objTabID;
		}
		public int getM_priority() {
			return m_priority;
		}
		public void setM_priority(int m_priority) {
			this.m_priority = m_priority;
		}
		public String getM_alarmorEvent() {
			return m_alarmorEvent;
		}
		public void setM_alarmorEvent(String m_alarmorEvent) {
			this.m_alarmorEvent = m_alarmorEvent;
		}
		public int getM_containerObj1Id() {
			return m_containerObj1Id;
		}
		public void setM_containerObj1Id(int m_containerObj1Id) {
			this.m_containerObj1Id = m_containerObj1Id;
		}
		public short getM_containerObj1TabId() {
			return m_containerObj1TabId;
		}
		public void setM_containerObj1TabId(short m_containerObj1TabId) {
			this.m_containerObj1TabId = m_containerObj1TabId;
		}
		public int getM_qualityCode() {
			return m_qualityCode;
		}
		public void setM_qualityCode(int m_qualityCode) {
			this.m_qualityCode = m_qualityCode;
		}
		public String getM_alarmstring() {
			return m_alarmstring;
		}
		public void setM_alarmstring(String m_alarmstring) {
			this.m_alarmstring = m_alarmstring;
		}
		
		
		
}
