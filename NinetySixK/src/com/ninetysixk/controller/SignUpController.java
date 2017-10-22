package com.ninetysixk.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ninetysixk.domain.Response;
import com.ninetysixk.domain.UserProfile;
import com.ninetysixk.service.UserAuthenticationValidatorService;

@Controller
@RequestMapping("/signup.html")
public class SignUpController {
	private final Logger logger = Logger.getLogger(SignUpController.class
	            .getName());
	@Autowired
	private UserAuthenticationValidatorService userAuthenticationService;
	public UserAuthenticationValidatorService getUserAuthenticationService() {
		return userAuthenticationService;
	}
	public void setUserAuthenticationService(UserAuthenticationValidatorService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}
	@RequestMapping( method=RequestMethod.POST)
	@ResponseBody
	public Response onSubmit( HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		String userInfo=request.getParameter("userInfo");
		JSONParser parser = new JSONParser();
		JSONObject userProfileJSON= (JSONObject) parser.parse(userInfo);
		UserProfile userProfile= setUserProfileInfo(userProfileJSON);				
		boolean profileCreated=this.getUserAuthenticationService().createUser(userProfile);
		response.setHeader("access-control-allow-origin", "http://localhost:4200");
		response.setHeader("access-Control-Allow-Credentials", "true");
		if(profileCreated){
			 return new Response("success","User profile created");
		 }else{
			 return new Response("failure","User profile creation failed");
		 }
	}
	
	public UserProfile setUserProfileInfo(JSONObject userProfileJSON) throws ParseException{
		UserProfile userProfile= new UserProfile();
		userProfile.setUsername((String)userProfileJSON.get("usernameSignUp"));
		userProfile.setUserID((String)userProfileJSON.get("usernameSignUp"));
		userProfile.setPassword((String)userProfileJSON.get("passwordSignUp"));
		userProfile.setAddress((String)userProfileJSON.get("address"));
		userProfile.setBirthPlace((String)userProfileJSON.get("birthPlace"));
		DateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");
		Date myDate = formatter.parse("02-09-1999");
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		userProfile.setDateOfBirth(sqlDate);
		userProfile.setEmailID((String)userProfileJSON.get("emailId"));
		userProfile.setGender((String)userProfileJSON.get("gender"));
		userProfile.setHobbies((String)userProfileJSON.get("hobbies"));
		Long income=(Long)userProfileJSON.get("income");
		userProfile.setIncome(income.doubleValue());
		userProfile.setName((String)userProfileJSON.get("fullName"));
		userProfile.setNativePlace((String)userProfileJSON.get("native"));
		userProfile.setPhoneNumber((Long)userProfileJSON.get("phoneNumber"));
		userProfile.setProfileID((String)userProfileJSON.get("profileID"));
		userProfile.setRelatives((String)userProfileJSON.get("relatives"));
		userProfile.setUserID((String)userProfileJSON.get("usernameSignUp"));
		return userProfile;
	}
}
