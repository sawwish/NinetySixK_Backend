package com.ninetysixk.DAO.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.ninetysixk.domain.UserProfile;

public class UserProfileMapper implements  RowMapper{
	private Logger logger =Logger.getLogger(getClass().getName());
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserProfile userProfile= new UserProfile();
		userProfile.setAddress(rs.getString("Address"));
		userProfile.setBirthPlace(rs.getString("BirthPlace"));
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("DD/MM/YYYY");
		try {
			userProfile.setDateOfBirth(formatter.parse(rs.getString("DateOfBirth")));
		} catch (ParseException e) {
			logger.error(e.getStackTrace());
		}
		userProfile.setEmailID(rs.getString("EmailID"));
		userProfile.setGender(rs.getString("Gender"));
		userProfile.setHobbies(rs.getString("Hobbies"));
		userProfile.setIncome(rs.getDouble("Income"));
		userProfile.setName(rs.getString("Name"));
		userProfile.setNativePlace(rs.getString("Native"));
		userProfile.setPhoneNumber(rs.getLong("PhoneNumber"));
		userProfile.setProfileID(rs.getString("ProfileID"));
		userProfile.setRelatives(rs.getString("Relatives"));
		userProfile.setUserID(rs.getString("UserID"));
		return userProfile;
	}
}
