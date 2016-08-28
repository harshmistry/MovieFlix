package io.egen.movieflix.controller;

import java.util.List;

import io.egen.movieflix.constants.SessionFinalVariables;
import io.egen.movieflix.entity.MovieEntity;
import io.egen.movieflix.entity.MovieList;
import io.egen.movieflix.entity.UserEntity;
import io.egen.movieflix.exception.LoginException;
import io.egen.movieflix.exception.MovieException;
import io.egen.movieflix.service.IMovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	@Autowired
	IMovieService movieService;
	
	@RequestMapping(method=RequestMethod.POST,value="/insertMovieDetail",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void insertMovieDetail(HttpServletRequest request, @RequestBody MovieList moviesList)
	{
		if(!((UserEntity)request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY)).getRole().equalsIgnoreCase("ADMIN"))
			throw new LoginException("Only admin can insert movie details");
		List<MovieEntity> movieEntityList=null;
		if(moviesList!=null)
		{
			movieEntityList=moviesList.getMovieList();
			if(!movieEntityList.isEmpty())
			{
				for(MovieEntity movie: movieEntityList)
					movieService.insertMovieDetail(movie);
			}
			else
				throw new MovieException("Movie list was null");
		}
		else
			throw new MovieException("Movie in request body was null");
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/deleteMovies",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteMovies(HttpServletRequest request,@RequestBody MovieList moviesList)
	{
		if(!((UserEntity)request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY)).getRole().equalsIgnoreCase("ADMIN"))
			throw new LoginException("Only admin can delete movie details");
		List<MovieEntity> movieEntityList=null;
		if(moviesList!=null)
		{
			movieEntityList=moviesList.getMovieList();
			if(!movieEntityList.isEmpty())
			{
				for(MovieEntity movie: movieEntityList)
					movieService.deleteMovie(movie);
			}
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/updateMovieDetail",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MovieEntity updateMovieDetail(HttpServletRequest request, @RequestBody MovieEntity movieEntity)
	{
		if(request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.updateMovieDetail(movieEntity);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getAllMovies",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MovieEntity> getAllMovies(HttpServletRequest request)
	{
		if(request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.getAllMovies();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getImdbIdMovies/{imdbId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MovieEntity getMovieByImbdId(HttpServletRequest request,@PathVariable("imdbId") String imdbMovieId)
	{
		if(request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.findByImdbId(imdbMovieId);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getAllMovies/{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MovieEntity getMovieById(HttpServletRequest request,@PathVariable("id") String movieId)
	{
		if(request.getSession().getAttribute(SessionFinalVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.findOne(movieId);
	}
	
//	@RequestMapping(params={"filterType","filterValue"},method=RequestMethod.POST,value="/filterMovies",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public List<MovieEntity> filterMovies(@RequestParam(value="filterType") String filterType, @RequestParam(value="filterValue") String filterValue)
//	{
//		return movieService.filterMovies(filterType, filterValue);
//	}
	
	@RequestMapping(method=RequestMethod.GET,value="/filterMovies/{filterType}/{filterValue}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MovieEntity> filterMovies(@PathVariable("filterType") String filterType, @PathVariable("filterValue") String filterValue)
	{
		return movieService.filterMovies(filterType, filterValue);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/sortMovies/{filterType}/{sortOrder}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MovieEntity> sortMovies(@PathVariable(value="filterType") String filterType, @PathVariable(value="sortOrder") String sortOrder)
	{
		return movieService.sortMovies(filterType, sortOrder);
	}
	
}
