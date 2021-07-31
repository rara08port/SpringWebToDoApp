package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Task;

public interface TaskDao{

	List<Task> findAll();

	Optional<Task> findById(int id);

	void insert(Task task);

	void update(Task task);

	void deleteById(int id);

	List<Task> findByType(int typeId);
	
	List<Task> findListByUserId(int userId);
	
	List<Task> findByTypeUserId(int typeId,int userId);

}