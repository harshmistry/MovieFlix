package io.egen.movieflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.movieflix.entity.CommentEntity;
import io.egen.movieflix.repository.ICommentRepository;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private ICommentRepository commentRepository;
	
	@Override
	public CommentEntity insertUserComment(CommentEntity comment) {
		return commentRepository.insertUserComment(comment);
	}

	@Override
	public List<CommentEntity> getCommentsOnTitle(String movieId) {
		return commentRepository.getCommentsOnTitle(movieId);
	}

}
