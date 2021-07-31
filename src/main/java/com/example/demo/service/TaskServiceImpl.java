package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskDao;

@Service
public class TaskServiceImpl implements TaskService {
	
	private final TaskDao dao;
	
	public TaskServiceImpl(TaskDao dao) {
		this.dao = dao;
	}
	

	@Override
	public List<Task> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public Optional<Task> getTask(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(id);
	}

	@Override
	public void insert(Task task) {
		// TODO 自動生成されたメソッド・スタブ
		dao.insert(task);

	}

	@Override
	public void update(Task task) {
		// TODO 自動生成されたメソッド・スタブ
		dao.update(task);

	}

	@Override
	public void deleteById(int id) {
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteById(id);

	}

	@Override
	public List<Task> findByType(int typeId) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findByType(typeId);
	}
	
	@Override
	public List<Task> findListByUserId(int userId) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findListByUserId(userId);
	}
	
	@Override
	public List<Task> findByTypeUserId(int typeId,int userId) {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findByTypeUserId(typeId,userId);
	}
	

}
