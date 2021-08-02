package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

import com.example.demo.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {
	

	private final UserDao dao;
	
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public int findRegisterUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findRegisterUser(user);
		
	}

	@Override
	public User findLoginUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findLoginUser(user);
	}

	@Override
	public void insert(User user) {
		// TODO 自動生成されたメソッド・スタブ
		dao.insert(user);

	}
	
	@Override
	public int findLoginUserCheck(User user) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findLoginUserCheck(user);
		
	}

}
