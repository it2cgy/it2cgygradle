package com.yunengzhe.PVMTS.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class InitRole extends SqlSessionDaoSupport implements ApplicationListener<ContextRefreshedEvent> {
	
	//初始化时加载权限
	public static Map<String, Map<String, String>> roleMap = 
			new HashMap<String, Map<String,String>>();
	
	public final static Map<String, HashMap<String, HashMap<String, Boolean>>> privilege = 
			new HashMap<String, HashMap<String,HashMap<String,Boolean>>>();
	
	public final static Map<String, HashMap<String, Boolean>> rolePrivilege = 
			new HashMap<String, HashMap<String,Boolean>>();
	
	public final static Map<String, HashMap<String, Boolean>> roleUrlMap = 
			new HashMap<String, HashMap<String,Boolean>>();
	
	@SuppressWarnings("rawtypes")
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//root applicationContext(root WebApplicationContext) 如果没有parent，他就是最大的容器
//		if(event.getApplicationContext().getParent() != null){
//			//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
//			List<HashMap> listRole = 
//					getSqlSession().selectList("initRole.queryRole");
//			//System.out.println(listRole);
//			//System.out.println(listRole.size());
//
//			for (int i = 0; i < listRole.size(); i++) {
//				HashMap itemList = listRole.get(i);
//				if((itemList.get("parentid")+"").equals("0")){
//					HashMap<String, HashMap<String, Boolean>> itemPri = 
//							new HashMap<String, HashMap<String,Boolean>>();
//					//System.out.println("itemPri>>> " + itemPri);
//					privilege.put(itemList.get("id")+"", itemPri);
//				}
//				
//				if((itemList.get("url")+"") != null){
//					if(roleUrlMap.get(itemList.get("url")+"") == null){
//						HashMap<String, Boolean> role = new HashMap<String, Boolean>();
//						role.put(itemList.get("roleid")+"", true);
//						roleUrlMap.put(itemList.get("url")+"", role);
//					}else{
//						roleUrlMap.get(itemList.get("url")+"").put(itemList.get("roleid")+"", true);
//					}
//				}
//			}
//			
//			for (int i = 0; i < listRole.size(); i++) {
//				HashMap itemList = listRole.get(i);
//				if(!(itemList.get("parentid")+"").equals("0")){
//					if(privilege.get(itemList.get("parentid")+"").get(itemList.get("id")+"") == null){
//						HashMap<String, Boolean> item = new HashMap<String, Boolean>();
//						item.put(itemList.get("roleid")+"", true);
//						privilege.get(itemList.get("parentid")+"").put(itemList.get("id")+"", item);
//					}else{
//						privilege.get(itemList.get("parentid")+"").
//							get(itemList.get("id")+"").put(itemList.get("roleid")+"", true);
//					}
//				}
//			}
//			
//			for (int i = 0; i < listRole.size(); i++) {
//				HashMap itemList = listRole.get(i);
//				if((rolePrivilege.get(itemList.get("id")+"")) == null){
//					HashMap<String, Boolean> item = new HashMap<String, Boolean>();
//					item.put(itemList.get("roleid")+"", true);
//					rolePrivilege.put(itemList.get("id")+"", item);
//				}else{
//					rolePrivilege.get(itemList.get("id")+"").put(itemList.get("roleid")+"", true);
//				}
//			}
//			
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + rolePrivilege);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + privilege);
//			/*System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + (privilege.get("22") != null));
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + (privilege.get("220") != null));
//			for(String key : privilege.keySet()){
//				System.out.println("key="+key + "  value"+ privilege.get(key));
//			}
//			
//			System.out.println("=================================================================================");
//			
//			for(String key : rolePrivilege.keySet()){
//				System.out.println("key="+key + "  value"+ rolePrivilege.get(key));
//				System.out.println((rolePrivilege.get(key).get("1") != null));
//			}*/
//		}
	}
}
