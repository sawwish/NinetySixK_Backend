package com.ninetysixk.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninetysixk.DAO.UserProfilesJDBCTemplate;
import com.ninetysixk.domain.UserProfile;

@Service
public class UserProfilesService {
	public UserProfilesJDBCTemplate getUserProfilesJDBCTemplate() {
		return UserProfilesJDBCTemplate;
	}

	public void setUserProfilesJDBCTemplate(UserProfilesJDBCTemplate userProfilesJDBCTemplate) {
		UserProfilesJDBCTemplate = userProfilesJDBCTemplate;
	}

	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserProfilesJDBCTemplate UserProfilesJDBCTemplate;
	
	public List<UserProfile> getUserProfiles(String userId){
		UserProfile userProf= this.UserProfilesJDBCTemplate.getUserProfile(userId);
		String gender= userProf.getGender();
		return this.UserProfilesJDBCTemplate.getUserProfiles(userId,gender);
	} 
}
