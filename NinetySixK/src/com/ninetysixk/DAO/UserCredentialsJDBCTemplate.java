package com.ninetysixk.DAO;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ninetysixk.DAO.mapper.UserAuthenticationMapper;
import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.domain.UserProfile;
@Repository
public class UserCredentialsJDBCTemplate implements UserAuthenticationDAO {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public JdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}

	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}

	

	@Override
	public UserCredential getUserCredential(String username) {
		String SQL="select * from User_Credentials where Username=?";
		UserCredential uc=null;
		try{
			uc=(UserCredential)this.jdbcTemplateObject.queryForObject(SQL, new Object[]{username}, new UserAuthenticationMapper());
		}catch(EmptyResultDataAccessException ex){
			return uc;
		}
		
		return uc;		
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public boolean createUser(UserProfile userCredential) {
		try{
			String SQL="Insert Into User_Credentials values(?,?)";
			this.getJdbcTemplateObject().update(SQL, new Object[]{userCredential.getUsername(),userCredential.getPassword()});	
		}
		catch(Exception exp){
			return false;
		}
		return true;
	}

	
}
