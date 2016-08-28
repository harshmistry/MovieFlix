package io.egen.movieflix.repository;

import io.egen.movieflix.entity.UserEntity;

public interface IUserRepository {

	public UserEntity signUpUser(UserEntity user);
	
	public UserEntity authenticate(UserEntity user);
	
	public UserEntity updateUserDetail(UserEntity user);
	
	public UserEntity findUserByEmail(UserEntity user);
}
