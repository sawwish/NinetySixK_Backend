package com.ninetysixk.domain;

import java.util.List;

public class UserProfilesResponse extends Response {
	private List<UserProfile> userProfiles;
	public UserProfilesResponse() {}
	public UserProfilesResponse(String status,String message,List<UserProfile> userProfiles){
		super(status,message);
		this.setUserProfiles(userProfiles);
	}
	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}
	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

}
