package io.egen.movieflix.service;

import io.egen.movieflix.exception.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.movieflix.entity.UserEntity;
import io.egen.movieflix.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserEntity signUpUser(UserEntity user) {
		return userRepository.signUpUser(user);
	}

	@Override
	public UserEntity authenticate(UserEntity user) {
		return userRepository.authenticate(user);
	}

	@Override
	public UserEntity updateUserDetail(UserEntity oldUser,UserEntity newUser) {
		UserEntity existing=userRepository.findUserByEmail(oldUser);
		if(existing == null)
			throw new LoginException("User with email:"+oldUser.getEmail()+" does not exist");
		return userRepository.updateUserDetail(newUser);
	}

	@Override
	public UserEntity findUserByEmail(UserEntity user) {
		return userRepository.findUserByEmail(user);
	}

}
