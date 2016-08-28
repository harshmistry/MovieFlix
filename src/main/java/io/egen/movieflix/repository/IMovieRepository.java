package io.egen.movieflix.repository;

import java.util.List;

import io.egen.movieflix.entity.MovieEntity;

public interface IMovieRepository {

	public void insertMovieDetail(MovieEntity movie);
	
	public List<MovieEntity> getAllMovies();
	
	public List<MovieEntity> filterMovies(String filterType, String filterValue);
	
	public List<MovieEntity> sortMovies(String filterType,String sortOrder);
	
	public MovieEntity findByImdbId(String imdbId);
	
	public MovieEntity findOne(String id);//searh by primary key
	
	public MovieEntity updateMovieDetail(MovieEntity movie);
	
	public void deleteMovie(MovieEntity movie);
	
}
