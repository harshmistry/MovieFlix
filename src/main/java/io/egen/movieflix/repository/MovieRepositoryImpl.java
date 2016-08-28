package io.egen.movieflix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.movieflix.constants.FinalNamedQueries;
import io.egen.movieflix.entity.MovieEntity;

@Repository
public class MovieRepositoryImpl implements IMovieRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insertMovieDetail(MovieEntity movie) {
		em.persist(movie);
	}

	@Override
	public List<MovieEntity> getAllMovies() {
		return em.createQuery("from MovieEntity", MovieEntity.class).getResultList();
	}

	@Override
	public List<MovieEntity> filterMovies(String filterType, String filterValue) {
		TypedQuery<MovieEntity> query=null;
		if(filterType.equalsIgnoreCase("YEAR"))
		{
			query=em.createNamedQuery("Movie.findByYear", MovieEntity.class);
			query.setParameter("pYear", filterValue);
		}
		else if(filterType.equalsIgnoreCase("TITLE"))
		{
			Query query1=em.createNativeQuery("SELECT u.* from MovieDetail u WHERE u.title LIKE '%"+filterValue+"%'", "MovieEntityMapping");
			return query1.getResultList();
		}
		else if(filterType.equalsIgnoreCase("GENRE"))
		{	
			Query query1=em.createNativeQuery("SELECT u.* from MovieDetail u WHERE u.genre LIKE '%"+filterValue+"%'", "MovieEntityMapping");
			return query1.getResultList();
		}
		else if(filterType.equalsIgnoreCase("TYPE"))
		{
			query=em.createNamedQuery("Movie.findByType", MovieEntity.class);
			query.setParameter("pType", filterValue);
		}
		else if(filterType.equalsIgnoreCase("MOVIE_SERIES_RATING"))
		//to find top rated movies or series based on imdbRatings
		{
			query=em.createNamedQuery("Movie.filterByRatingMovieSeries", MovieEntity.class);
			query.setParameter("pType", filterValue);
		}
		return query.getResultList();
	}

	@Override
	public List<MovieEntity> sortMovies(String filterType, String sortOrder) 
	{
		TypedQuery<MovieEntity> query=null;
		if(filterType.equalsIgnoreCase("VOTE"))
		{
			if(sortOrder.equalsIgnoreCase("ASC"))
				query=em.createNamedQuery("Movie.sortByVoteAsc", MovieEntity.class);
			else if(sortOrder.equalsIgnoreCase("DESC"))
				query=em.createNamedQuery("Movie.sortByVote", MovieEntity.class);
		}
		else if(filterType.equalsIgnoreCase("RATING"))
		{
			if(sortOrder.equalsIgnoreCase("ASC"))
				query=em.createNamedQuery("Movie.sortByRatingAsc", MovieEntity.class);
			else if(sortOrder.equalsIgnoreCase("DESC"))
				query=em.createNamedQuery("Movie.sortByRating", MovieEntity.class);
		}
		else if(filterType.equalsIgnoreCase("YEAR"))
		{
			if(sortOrder.equalsIgnoreCase("ASC"))
				query=em.createNamedQuery("Movie.sortByYearAsc", MovieEntity.class);
			else if(sortOrder.equalsIgnoreCase("DESC"))
				query=em.createNamedQuery("Movie.sortByYear", MovieEntity.class);
		}
		return query.getResultList();
	}

	@Override
	public MovieEntity findByImdbId(String imdbId) {
		TypedQuery<MovieEntity> query=em.createNamedQuery("Movie.findByImdbId", MovieEntity.class);
		query.setParameter("pImdbId", imdbId);
		return query.getSingleResult();
	}

	@Override
	//search by primary key
	public MovieEntity findOne(String id) {
		return em.find(MovieEntity.class, id);
	}

	@Override
	public MovieEntity updateMovieDetail(MovieEntity movie) {
		return em.merge(movie);
	}

	@Override
	public void deleteMovie(MovieEntity movie) {
		em.remove(movie);
	}

}
