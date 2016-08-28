package io.egen.movieflix.service;

import io.egen.movieflix.entity.UserEntity;

public interface IUserService {
	
	public UserEntity signUpUser(UserEntity user);
	
	public UserEntity authenticate(UserEntity user);
	
	public UserEntity updateUserDetail(UserEntity oldUser,UserEntity newUser);
	
	public UserEntity findUserByEmail(UserEntity user);
		
}
