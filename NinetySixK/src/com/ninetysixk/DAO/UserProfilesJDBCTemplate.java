package com.ninetysixk.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ninetysixk.DAO.mapper.UserProfileMapper;
import com.ninetysixk.domain.UserProfile;
@Repository
public class UserProfilesJDBCTemplate implements UserProfilesDAO {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	private Logger logger =Logger.getLogger(getClass().getName());
	public JdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}

	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfile> getUserProfiles(String userId,String gender) {
		String SQL="SELECT * FROM User_Profiles WHERE NOT UserID=? AND NOT Gender=?";
		List<UserProfile> userProfiles=null;
		try{
			userProfiles=this.jdbcTemplateObject.query(SQL, new Object[]{userId,gender},new UserProfileMapper());
		}catch(EmptyResultDataAccessException ex){
			logger.error("Exception occured ::::::::\n"+ex.getStackTrace());
			return userProfiles;
		}
		
		return userProfiles;	
	}

	@Override
	public UserProfile getUserProfile(String userId) {
		String SQL="select * from User_Profiles where UserID=?";
		UserProfile userProfile=null;
		try{
			userProfile=(UserProfile) this.jdbcTemplateObject.queryForObject(SQL,new Object[]{userId},new UserProfileMapper());
		}catch(EmptyResultDataAccessException ex){
			logger.error("Exception occured ::::::::\n"+ex.getStackTrace());
			return userProfile;
		}
		
		return userProfile;
	}

	@Override
	public boolean createUserProfile(UserProfile userProfile) {
		try{
			String SQL ="Insert Into User_Profiles(ProfileID,UserID,Name,Address,DateOfBirth,Hobbies,Relatives,Native,BirthPlace,Income,PhoneNumber,EmailID,Gender) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			this.jdbcTemplateObject.update(SQL, new Object[]{userProfile.getProfileID(),userProfile.getUserID(),userProfile.getName(),userProfile.getAddress(),userProfile.getDateOfBirth(),userProfile.getHobbies(),userProfile.getRelatives(),userProfile.getNativePlace(),userProfile.getBirthPlace(),userProfile.getIncome(),userProfile.getPhoneNumber(),userProfile.getEmailID(),userProfile.getGender()}); 		
		}
		catch(Exception exp){
			logger.error("Exception occured ::::::::\n"+exp.getStackTrace());
			return false;
		}
		return true;
	}

}
