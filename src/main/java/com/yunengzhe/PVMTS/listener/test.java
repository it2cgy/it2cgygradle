package com.yunengzhe.PVMTS.listener;

import com.yunengzhe.PVMTS.util.DES;
import com.yunengzhe.common.util.DESUtil;

public class test {
	 private static final String key = "0002000200020002";  
	public static void main(String[] args) throws Exception {
		DES des = new DES();
		System.out.println(des.encrypt("com.mysql.jdbc.Driver",key));
		System.out.println(des.encrypt("jdbc:mysql://192.168.101.222:3306/longieb_db?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&autoReconnect=true",key));
		System.out.println(des.encrypt("root",key));
		System.out.println(des.encrypt("123456",key));
		System.out.println(des.decrypt("H0vCMIC3DCg9sgHggcc6s7qM0AW59ZoT",key));
		System.out.println(des.decrypt("t3ZumzfKLD9lanIC36D1p2DWXs2LEg8bez6OBKNeLOqvDW2PVcP8oivy8GA9b1mMt2o9ugI+Vx5q364rPGkzUkwTPyzBTMwCiInyXJ2+zk+MMU4/o8Nm77HVGsRceoP2",key));
		System.out.println(des.decrypt("0yi/uLeJKSA=",key));
		System.out.println(des.decrypt("aStyrGs4YhLd4x5bDUYumw==",key));
	}
}
