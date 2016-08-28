package io.egen.movieflix.entity;

import io.egen.movieflix.constants.FinalNamedQueries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.FieldResult;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="MovieDetail")
@NamedQueries(
		{
			@NamedQuery(name="Movie.findByYear",query=FinalNamedQueries.MOVIE_FIND_BY_YEAR),
			@NamedQuery(name="Movie.findByYearAsc",query=FinalNamedQueries.MOVIE_FIND_BY_YEAR_ASC),
			@NamedQuery(name="Movie.findByTitle",query=FinalNamedQueries.MOVIE_FIND_BY_TITLE),
			//@NamedQuery(name="Movie.findByGenre",query=FinalNamedQueries.MOVIE_FIND_BY_GENRE),
			@NamedQuery(name="Movie.findByType",query=FinalNamedQueries.MOVIE_FIND_BY_TYPE),
			@NamedQuery(name="Movie.sortByVote",query=FinalNamedQueries.MOVIE_SORT_BY_VOTE),
			@NamedQuery(name="Movie.sortByVoteAsc",query=FinalNamedQueries.MOVIE_SORT_BY_VOTE_ASC),
			@NamedQuery(name="Movie.sortByRating",query=FinalNamedQueries.MOVIE_SORT_BY_RATING),
			@NamedQuery(name="Movie.sortByRatingAsc",query=FinalNamedQueries.MOVIE_SORT_BY_RATING_ASC),
			@NamedQuery(name="Movie.sortByYear",query=FinalNamedQueries.MOVIE_SORT_BY_YEAR),
			@NamedQuery(name="Movie.sortByYearAsc",query=FinalNamedQueries.MOVIE_SORT_BY_YEAR_ASC),
			@NamedQuery(name="Movie.findByImdbId",query=FinalNamedQueries.MOVIE_FIND_BY_IMDB_ID),
			@NamedQuery(name="Movie.filterByRatingMovieSeries",query=FinalNamedQueries.MOVIE_SORT_BY_RATING_MOVIE_SERIES)
		}
)
@SqlResultSetMapping(
        name = "MovieEntityMapping",
        entities = {
            @EntityResult(
                    entityClass = MovieEntity.class,
                    fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "title", column = "title"),
                        @FieldResult(name = "year", column = "year"),
                        @FieldResult(name = "rated", column = "rated"),
                        @FieldResult(name = "released", column = "released"),
                        @FieldResult(name = "runtime", column = "runtime"),
                        @FieldResult(name = "genre", column = "genre"),
                        @FieldResult(name = "director", column = "director"),
                        @FieldResult(name = "writer", column = "writer"),
                        @FieldResult(name = "actors", column = "actors"),
                        @FieldResult(name = "plot", column = "plot"),
                        @FieldResult(name = "language", column = "language"),
                        @FieldResult(name = "country", column = "country"),
                        @FieldResult(name = "awards", column = "awards"),
                        @FieldResult(name = "poster", column = "poster"),
                        @FieldResult(name = "metascore", column = "metascore"),
                        @FieldResult(name = "imdbRating", column = "imdbRating"),
                        @FieldResult(name = "imdbVotes", column = "imdbVotes"),
                        @FieldResult(name = "imdbID", column = "imdbID"),
                        @FieldResult(name = "type", column = "type")})
           })
public @Data class MovieEntity {

	@Id
	@GenericGenerator(strategy="uuid2",name="myuuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
    private String year;
	@JsonProperty("Rated")
    private String rated;
	@JsonProperty("Released")
    private String released;
	@JsonProperty("Runtime")
    private String runtime;
	@JsonProperty("Genre")
    private String genre;
	@JsonProperty("Director")
    private String director;
	@JsonProperty("Writer")
	@Column(columnDefinition="varchar(2000)")
    private String writer;
	@JsonProperty("Actors")
    private String actors;
	@JsonProperty("Plot")
	@Column(columnDefinition="varchar(800)")
    private String plot;
	@JsonProperty("Language")
    private String language;
	@JsonProperty("Country")
    private String country;
	@JsonProperty("Awards")
    private String awards;
	@JsonProperty("Poster")
    private String poster;
	@JsonProperty("Metascore")
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    @Column(unique=true)
    private String imdbID;
    @JsonProperty("Type")
    private String type;
    
}
