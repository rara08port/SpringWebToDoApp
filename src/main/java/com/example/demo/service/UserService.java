package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	

	int findRegisterUser(User user);
	
	User findLoginUser(User user);
	
	void insert(User user);

}
