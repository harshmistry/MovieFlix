package io.egen.movieflix.controller;

import io.egen.movieflix.constants.SessionFinalVariables;
import io.egen.movieflix.entity.UserEntity;
import io.egen.movieflix.exception.LoginException;
import io.egen.movieflix.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping(method=RequestMethod.POST,value="/authenticateLogin",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity authenticateLogin(HttpServletRequest request, @RequestBody UserEntity pUserEntity)
	{
		UserEntity userEntity=userService.authenticate(pUserEntity);
		HttpSession session=request.getSession(true);
		session.setAttribute(SessionFinalVariables.SESSION_USER_ENTITY,userEntity);
		session.setMaxInactiveInterval(SessionFinalVariables.SESSION_MAX_INACTIVE_TIME);
		return userEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/signup",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity signUpUser(HttpServletRequest request, @RequestBody UserEntity pUserEntity)
	{
		HttpSession session=request.getSession(true);
		UserEntity userEntity = userService.signUpUser(pUserEntity);
		session.setAttribute(SessionFinalVariables.SESSION_USER_ENTITY, userEntity);
		return userEntity;
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/updateUserDetail",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity updateUserDetail(HttpServletRequest request, @RequestBody UserEntity pUserEntity)
	{
		HttpSession session=request.getSession(true);
		//check if user has logged in
		UserEntity sessionUserEntity=(UserEntity)session.getAttribute(SessionFinalVariables.SESSION_USER_ENTITY);
		//UserEntity userEntity = userService.signUpUser(pUserEntity);
		System.out.println("OldUserId:"+sessionUserEntity.getId()+"-----------NewUserId:"+pUserEntity.getId());
		if(null == sessionUserEntity || !(sessionUserEntity.getId().equals(pUserEntity.getId())))
			throw new LoginException("Please login first");
		UserEntity userEntity = userService.updateUserDetail(sessionUserEntity,pUserEntity);
		session.setAttribute(SessionFinalVariables.SESSION_USER_ENTITY, userEntity);
		return userEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/signOut")
	public void signOutUser(HttpServletRequest request)
	{
		request.getSession().invalidate();
	}
}
