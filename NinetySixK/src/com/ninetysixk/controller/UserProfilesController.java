package com.ninetysixk.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ninetysixk.domain.Response;
import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.domain.UserProfile;
import com.ninetysixk.domain.UserProfilesResponse;
import com.ninetysixk.service.UserProfilesService;

@Controller
@RequestMapping("/getUserProfiles.html")
public class UserProfilesController {
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserProfilesService userProfilesService;
	public UserProfilesService getUserProfilesService() {
		return userProfilesService;
	}
	public void setUserProfilesService(UserProfilesService userProfilesService) {
		this.userProfilesService = userProfilesService;
	}
	
	@RequestMapping( method=RequestMethod.POST)
	@ResponseBody
	public Response onSubmit( HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		 logger.info("onsubmit get profiles!!");
		 logger.info(request.getParameter("userId"));
		 HttpSession session = request.getSession(false);
		 System.out.println("User from session:"+session.getAttribute("userName"));
		 String userId=(String) session.getAttribute("userName");
		 List<UserProfile> userprofiles=this.getUserProfilesService().getUserProfiles(userId);
		 response.setHeader("access-control-allow-origin", "http://localhost:4200");
		 response.setHeader("access-Control-Allow-Credentials", "true");
		 UserProfilesResponse userProfileResponse= new UserProfilesResponse("success", "got profiles!!!", userprofiles);
		 return userProfileResponse;
	}
}
