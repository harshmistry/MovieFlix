package io.egen.movieflix.entity;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public @Data class MovieList {

	private List<MovieEntity> movieList;
}
