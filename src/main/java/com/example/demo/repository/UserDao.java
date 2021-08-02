package com.example.demo.repository;


import java.util.List;

import com.example.demo.entity.User;

public interface UserDao {
	
	List<User> findAll();
	
	int findRegisterUser(User user);
	
	User findLoginUser(User user);
	
	void insert(User user);
	
	int findLoginUserCheck(User user);
	

}
