package io.egen.movieflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.movieflix.entity.MovieEntity;
import io.egen.movieflix.exception.MovieException;
import io.egen.movieflix.repository.IMovieRepository;

@Service
@Transactional
public class MovieServiceImpl implements IMovieService {

	@Autowired
	IMovieRepository movieRepository;
	
	@Override
	public void insertMovieDetail(MovieEntity movie) {
		movieRepository.insertMovieDetail(movie);
	}

	@Override
	public List<MovieEntity> getAllMovies() {
		return movieRepository.getAllMovies();
	}

	@Override
	public List<MovieEntity> filterMovies(String filterType, String filterValue) {
		return movieRepository.filterMovies(filterType, filterValue);
	}

	@Override
	public List<MovieEntity> sortMovies(String filterType, String sortOrder) {
		return movieRepository.sortMovies(filterType, sortOrder);
	}

	@Override
	public MovieEntity findByImdbId(String imdbId) {
		return movieRepository.findByImdbId(imdbId);
	}

	@Override
	public MovieEntity findOne(String id) {
		return movieRepository.findOne(id);
	}

	@Override
	public MovieEntity updateMovieDetail(MovieEntity movie) {
		MovieEntity existing = movieRepository.findOne(movie.getId());
		if(existing==null)
			throw new MovieException("Movie with ID:"+movie.getId()+"("+movie.getTitle()+") was not found in database");
		return movieRepository.updateMovieDetail(movie);
	}

	@Override
	public void deleteMovie(MovieEntity movie) {
		movieRepository.deleteMovie(movie);
	}

}
