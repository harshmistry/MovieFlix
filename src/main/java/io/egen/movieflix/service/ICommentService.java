package io.egen.movieflix.service;

import java.util.List;

import io.egen.movieflix.entity.CommentEntity;

public interface ICommentService {

	public CommentEntity insertUserComment(CommentEntity comment);
	
	public List<CommentEntity> getCommentsOnTitle(String movieId);
}
