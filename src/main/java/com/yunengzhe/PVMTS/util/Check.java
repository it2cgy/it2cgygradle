package com.yunengzhe.PVMTS.util;

import java.util.HashMap;
import java.util.Map;

public class Check {
	
	public static final Map<String, HashMap<String, Boolean>> rolePrivilegeMap = InitRole.rolePrivilege;

	public static final Map<String, HashMap<String, HashMap<String, Boolean>>> PrivilegeMap = InitRole.privilege;

	public static boolean checkLimit(String privilege, String role) {
		//String role = roleid;//String.valueOf(roleid);
		//String privilege = privilegeid;//String.valueOf(privilegeid);
		/*for(String key : rolePrivilegeMap.keySet()){
			System.out.println("key="+key + "  value"+ rolePrivilegeMap.get(key));
			//System.out.println((PrivilegeMap.get(key).get("1") != null));
		}*/
		//System.out.println(role+ "  " + privilege);
		/*System.out.println(role+ "  " + privilege);
		System.out.println(rolePrivilegeMap.get(privilege).get(role));*/
		//boolean flag = rolePrivilegeMap.get(privilege).get(role);
		if(rolePrivilegeMap.get(privilege) != null){
			if(rolePrivilegeMap.get(privilege).get(role) != null && rolePrivilegeMap.get(privilege).get(role)){
				return true;
			}else{
				return false;
			}	
		}else{
			return false;
		}
	}
	
	public static boolean checkLimitName(String privilege, String role) {
		
		
		return false;
	}
	
	public boolean checkSort(int privilegeid , int roleid) {

		return false;
	}
	
	public boolean checkSortName(String privilege, String role) {
			
		return false;
	}
}
