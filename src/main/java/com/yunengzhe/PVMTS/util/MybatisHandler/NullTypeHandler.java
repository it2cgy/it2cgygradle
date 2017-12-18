package com.yunengzhe.PVMTS.util.MybatisHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class NullTypeHandler implements TypeHandler{

	public Object getResult(ResultSet rs, String columaName) throws SQLException {
		// TODO Auto-generated method stub
		//System.out.println("getResult>>>>>>>>  " );
		return (rs.getString(columaName) == null) ? "" : rs.getString(columaName);
	}

	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		//System.out.println("getResult>>>>>>>>  " );
		return (rs.getString(columnIndex) == null) ? "" : rs.getString(columnIndex);
	}

	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		//System.out.println("getResult>>>>>>>>  " );
		return (cs.getString(columnIndex) == null) ? "" : cs.getString(columnIndex);
	}

	public void setParameter(PreparedStatement ps, int arg1, Object arg2,
			JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		ps.setObject(arg1, arg2);
		//System.out.println("setParameterwwww>>>>>>>>  " );
	}

}
