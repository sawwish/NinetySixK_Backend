package com.ninetysixk.DAO;

import java.util.List;

import javax.sql.DataSource;

import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.domain.UserProfile;

public interface UserAuthenticationDAO {

		public void setDataSource(DataSource ds);

		public DataSource getDataSource();

		public boolean createUser(UserProfile userCredential);
  
		public UserCredential getUserCredential(String username);
  
		//public List<UserCredential> listUserCredentials();
  
		//public void delete(String username);
  
		//public void update(String username, String password);
}
