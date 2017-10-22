package com.ninetysixk.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ninetysixk.DAO.UserCredentialsJDBCTemplate;
import com.ninetysixk.DAO.UserProfilesJDBCTemplate;
import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.domain.UserProfile;
@Service
public class UserAuthenticationValidatorService {
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserCredentialsJDBCTemplate userAuthenticationJDBCTemplate;
	@Autowired
	private UserProfilesJDBCTemplate userProfilesJDBCTemplate;
	public UserCredentialsJDBCTemplate getUserAuthenticationJDBCTemplate() {
		return userAuthenticationJDBCTemplate;
	}
	public void setUserAuthenticationJDBCTemplate(UserCredentialsJDBCTemplate userAuthenticationJDBCTemplate) {
		this.userAuthenticationJDBCTemplate = userAuthenticationJDBCTemplate;
	}
	public boolean authenticateUser(UserCredential uc){
		 UserCredential usercred=this.getUserAuthenticationJDBCTemplate().getUserCredential(uc.getUsername());
		 if(usercred == null){
			 return false;
		 }
		 logger.info("password"+usercred.getPassword() );

		 if(uc.getPassword().equals(usercred.getPassword()) ){
			 return true;
		 }else{
			 return false;
		 }
	}
	public UserProfilesJDBCTemplate getUserProfilesJDBCTemplate() {
		return userProfilesJDBCTemplate;
	}
	public void setUserProfilesJDBCTemplate(UserProfilesJDBCTemplate userProfilesJDBCTemplate) {
		this.userProfilesJDBCTemplate = userProfilesJDBCTemplate;
	}
	public boolean createUser(UserProfile userProfile){
		boolean credentialsCreated=this.userAuthenticationJDBCTemplate.createUser(userProfile);
		boolean profileCreated= this.userProfilesJDBCTemplate.createUserProfile(userProfile);
		return (credentialsCreated && profileCreated);
	}
	

}
