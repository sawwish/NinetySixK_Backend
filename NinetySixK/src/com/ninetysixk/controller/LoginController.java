package com.ninetysixk.controller;

import com.ninetysixk.domain.Response;
import com.ninetysixk.domain.UserCredential;
import com.ninetysixk.service.UserAuthenticationValidatorService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/login.html")
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class.getName());
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
        logger.info("My first log12345");  
    	logger.info("onsubmit login!!");
		logger.info(request.getParameter("username"));
		UserCredential userCerential= new UserCredential();
		userCerential.setUsername(request.getParameter("username"));
		userCerential.setPassword(request.getParameter("password"));
		boolean isValidUser=this.getUserAuthenticationService().authenticateUser(userCerential);
		logger.info("validation::"+isValidUser);
		response.setHeader("access-control-allow-origin", "http://localhost:4200");
		response.setHeader("access-Control-Allow-Credentials", "true");
		if(isValidUser){
			 HttpSession session=request.getSession();
			 session.setMaxInactiveInterval(30*60);
			 session.setAttribute("userName", request.getParameter("username"));	
			 return new Response("success","valid user");
		}else{
			 return new Response("failure","invalid user");
		}
	}
}
