package com.yunengzhe.PVMTS.util;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.RolePO;

public class UserUtil {
	public static String getRolesName(List<RolePO> list){
		if(list==null||list.size()<=0){
			return "";
		}else{
			String name = "";
			for(int i=0;i<list.size();i++){
				if(i>0){
					name +=",";
				}
				name += list.get(i).getRoleName();
			}
			return name;
		}
	}
}
