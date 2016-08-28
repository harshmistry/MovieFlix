package io.egen.movieflix.constants;

public final class FinalNamedQueries {

	//Query for MovieEntity
	public static final String MOVIE_FIND_BY_YEAR		=	"SELECT u FROM MovieEntity u WHERE u.year=:pYear ORDER BY u.year DESC";
	public static final String MOVIE_FIND_BY_YEAR_ASC	=	"SELECT u FROM MovieEntity u WHERE u.year=:pYear ORDER BY u.year ASC";
	public static final String MOVIE_FIND_BY_TITLE		=	"SELECT u FROM MovieEntity u WHERE u.title=:pTitle";
	
	public static final String MOVIE_FIND_BY_GENRE		=	"SELECT * FROM MovieEntity u WHERE u.genre LIKE %:pGenre%";
	
	public static final String MOVIE_FIND_BY_TYPE		=	"SELECT u FROM MovieEntity u WHERE u.type=:pType";
	public static final String MOVIE_SORT_BY_VOTE		=	"SELECT u FROM MovieEntity u ORDER BY u.imdbVotes DESC";
	public static final String MOVIE_SORT_BY_VOTE_ASC	=	"SELECT u FROM MovieEntity u ORDER BY u.imdbVotes ASC";
	
	public static final String MOVIE_SORT_BY_RATING		=	"SELECT u FROM MovieEntity u ORDER BY u.imdbRating DESC";
	public static final String MOVIE_SORT_BY_RATING_ASC	=	"SELECT u FROM MovieEntity u ORDER BY u.imdbRating ASC";
	public static final String MOVIE_SORT_BY_RATING_MOVIE_SERIES		=	"SELECT u FROM MovieEntity u WHERE u.type=:pType ORDER BY u.imdbRating DESC";
	
	public static final String MOVIE_SORT_BY_YEAR		=	"SELECT u FROM MovieEntity u ORDER BY u.year DESC";
	public static final String MOVIE_SORT_BY_YEAR_ASC	=	"SELECT u FROM MovieEntity u ORDER BY u.year ASC";
	
	public static final String MOVIE_FIND_BY_IMDB_ID	=	"SELECT u FROM MovieEntity u WHERE u.imdbID=:pImdbId";
	
	//Query for Comments
	public static final String COMMENTS_ON_TITLE	=	"SELECT u FROM CommentEntity u WHERE u.movie_id=:pMovieId";
}
