package io.egen.movieflix.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.egen.movieflix.constants.SessionFinalVariables;
import io.egen.movieflix.entity.CommentEntity;
import io.egen.movieflix.entity.MovieEntity;
import io.egen.movieflix.entity.UserEntity;
import io.egen.movieflix.exception.LoginException;
import io.egen.movieflix.service.ICommentService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

	@Autowired
	private ICommentService commentService;
	
	@RequestMapping(method=RequestMethod.POST,value="/insertComments/{movieId}",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CommentEntity insertUserComment(HttpServletRequest request, @RequestBody CommentEntity comment, @PathVariable("movieId") String movieId)
	{
		//CommentEntity commentEntity = comment.getComment();
		if(request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		UserEntity userEntity = (UserEntity)request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY);
		//UserEntity userEntity = new UserEntity();
		//userEntity.setId(userId);
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setId(movieId);
		comment.setMovie(movieEntity);
		comment.setUser(userEntity);
		comment.setTimeStamp(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		return commentService.insertUserComment(comment);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getComments/{movieId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CommentEntity> getCommentsOnTitle(HttpServletRequest request,@PathVariable("movieId") String movieId)
	{
		if(request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return commentService.getCommentsOnTitle(movieId);
	}
}
