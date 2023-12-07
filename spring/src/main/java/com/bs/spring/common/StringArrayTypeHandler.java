package com.bs.spring.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class StringArrayTypeHandler implements TypeHandler<String[]> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, parameter!=null?String.join(",",parameter):null);
		
		
	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String data=rs.getString(columnName);
		return data!=null?data.split(","):null;
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		String data=rs.getString(columnIndex);
		return data!=null?data.split(","):null;
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String data=cs.getString(columnIndex);
		return data!=null?data.split(","):null;
	}

	
}
