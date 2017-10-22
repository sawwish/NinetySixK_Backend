package com.ninetysixk.DAO.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.jdbc.core.RowMapper;

import com.ninetysixk.domain.UserCredential;

public class UserAuthenticationMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserCredential uc= new UserCredential();
		uc.setUsername(rs.getString("Username"));
		uc.setPassword(rs.getString("Password"));
		return uc;
	}

}
 