package com.ninetysixk.DAO;

import java.util.List;

import javax.sql.DataSource;

import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.domain.UserProfile;

public interface UserProfilesDAO {
	public void setDataSource(DataSource ds);

	public DataSource getDataSource();

	public boolean createUserProfile(UserProfile userProfile);

	public List<UserProfile> getUserProfiles(String userId,String gender);
	
	public UserProfile getUserProfile(String userId);
}
