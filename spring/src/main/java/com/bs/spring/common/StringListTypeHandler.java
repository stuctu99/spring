package com.bs.spring.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class StringListTypeHandler implements TypeHandler<List<String>> {

	//alt shift s v
	
	@Override
	public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType)
			throws SQLException {
		
		String data =null;
		
		if(parameter!=null) {
		
			data = String.join(",",parameter.toArray(new String[parameter.size()]));
		} 
		ps.setString(i, data);		
	}

	@Override
	public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String[] data = rs.getString(columnName).split(",");
		
		return data!=null?Arrays.asList(data):Collections.emptyList();
	}

	@Override
	public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
		String[] data = rs.getString(columnIndex).split(",");
		
		return data!=null?Arrays.asList(data):Collections.emptyList();
	
	}

	@Override
	public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
String[] data = cs.getString(columnIndex).split(",");
		
		return data!=null?Arrays.asList(data):Collections.emptyList();
	}

	
	
}
