package io.egen.movieflix.repository;

import java.util.List;

import io.egen.movieflix.entity.CommentEntity;

public interface ICommentRepository {

	public CommentEntity insertUserComment(CommentEntity comment);

	public List<CommentEntity> getCommentsOnTitle(String movieId);
}
