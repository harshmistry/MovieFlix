package io.egen.movieflix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.movieflix.entity.CommentEntity;

@Repository
public class CommentRepositoryImpl implements ICommentRepository {

	@PersistenceContext
	private EntityManager em;
	private TypedQuery<CommentEntity> query;
	@Override
	public CommentEntity insertUserComment(CommentEntity comment) {
		em.persist(comment);
		return comment;
	}

	@Override
	public List<CommentEntity> getCommentsOnTitle(String movieId) {
		Query query1=em.createNativeQuery("SELECT u.* FROM Comments u WHERE u.movie_id=?", "CommentEntityMapping");
		query1.setParameter(1, movieId);
		return query1.getResultList();
	}

	
}
