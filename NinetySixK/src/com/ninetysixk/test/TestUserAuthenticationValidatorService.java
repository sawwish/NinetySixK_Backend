package com.ninetysixk.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.service.UserAuthenticationValidatorService;
import org.junit.Assert.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/ninetysixk-servlet.xml"})
public class TestUserAuthenticationValidatorService {
	@Autowired
	private UserAuthenticationValidatorService userAuthenticationService;
	
	@Test
	public void testAuthenticateUser(){
		UserCredential uc= new UserCredential();
		uc.setUsername("vishal");
		uc.setPassword("vishal123");
		assertTrue(userAuthenticationService.authenticateUser(uc));
	}

}
