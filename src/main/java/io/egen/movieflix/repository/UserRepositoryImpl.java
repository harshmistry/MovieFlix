package io.egen.movieflix.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.movieflix.entity.UserEntity;

@Repository
public class UserRepositoryImpl implements IUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserEntity signUpUser(UserEntity user) {
		em.persist(user);
		return user;
	}

	@Override
	public UserEntity authenticate(UserEntity user) {
		Query query=em.createNativeQuery("SELECT u.* FROM UserDetail u WHERE BINARY u.username=? AND u.password=?", "UserEntityMapping");
		//Query query=em.createNamedQuery("User.authenticateNative");
		query.setParameter(1, user.getUsername());
		query.setParameter(2,user.getPassword());
		return (UserEntity) query.getSingleResult();
	}

	@Override
	public UserEntity updateUserDetail(UserEntity user) {
		return em.merge(user);
	}

	@Override
	public UserEntity findUserByEmail(UserEntity user) {
		TypedQuery<UserEntity> query=em.createNamedQuery("User.findByEmail", UserEntity.class);
		query.setParameter("pEmail", user.getEmail());
		return query.getSingleResult();
	}

}
