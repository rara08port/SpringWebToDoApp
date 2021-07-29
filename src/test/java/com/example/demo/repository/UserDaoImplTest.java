package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;

@SpringBootTest
public class UserDaoImplTest {
	
	@Autowired
	private UserDaoImpl userDao;
	
	/*
	 * @Test
	 * 
	 * @DisplayName("findAllのテスト") void testfindAll() {
	 * System.out.println("TaskDaoImplTest findAll Start"); List<Task> list =
	 * taskDao.findAll(); System.out.println(list.get(0).getTitle());
	 * System.out.println(list.size());
	 * System.out.println(list.get(0).getTaskType().getType());
	 * 
	 * //件数のチェック assertEquals(2,list.size());
	 * 
	 * //各カラムの値が正しくセットされているか assertEquals(1,list.get(0).getId());
	 * assertEquals(1,list.get(0).getUserId());
	 * assertEquals(1,list.get(0).getTypeId());
	 * assertEquals("JUnitを学習",list.get(0).getTitle());
	 * assertEquals("テストの仕方を学習する",list.get(0).getDetail()); String date =
	 * "2020-07-07 15:00:00"; DateTimeFormatter dtFt =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	 * assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
	 * assertEquals("緊急",list.get(0).getTaskType().getType());
	 * assertEquals("最優先で取り掛かるべきタスク",list.get(0).getTaskType().getContent());
	 * 
	 * }
	 */
	
	
	/*
	 * @Test
	 * 
	 * @DisplayName("findByIdのテスト") void testfindById() {
	 * System.out.println("TaskDaoImplTest findById Start"); Optional<Task> taskOpt
	 * = taskDao.findById(1); Task task = new Task(); if(taskOpt.isPresent()) { task
	 * = taskOpt.get(); System.out.println("isPresent OK"); }
	 * 
	 * //各カラムの値が正しくセットされているか assertEquals(1,task.getId());
	 * assertEquals(1,task.getUserId()); assertEquals(1,task.getTypeId());
	 * assertEquals("JUnitを学習",task.getTitle());
	 * assertEquals("テストの仕方を学習する",task.getDetail()); String date =
	 * "2020-07-07 15:00:00"; DateTimeFormatter dtFt =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	 * assertEquals(LocalDateTime.parse(date, dtFt),task.getDeadline());
	 * assertEquals("緊急",task.getTaskType().getType());
	 * assertEquals("最優先で取り掛かるべきタスク",task.getTaskType().getContent());
	 * 
	 * }
	 */
	
	@Test
    @DisplayName("insertのテスト")
    void testInsert() {
		
		System.out.println("UserDaoImplTest testInsert Start");
		User user = new User();
		user.setUsername("blue");
		user.setPassword("blue");
		user.setAdmin_flg(0);
		userDao.insert(user);
		
		List<User> list = userDao.findAll();
		
		//件数チェック
		assertEquals(3,list.size());

    }
	
	
	@Test
    @DisplayName("findRegisterUserのテスト")
    void testfindRegisterUser() {
		
		System.out.println("UserDaoImplTest testfindRegisterUser Start");
		User user = new User();
		user.setUsername("blue");
		user.setPassword("blue");
		user.setAdmin_flg(0);
		int count=10;
		count = userDao.findRegisterUser(user);
		
		System.out.println(count);
		
		//件数チェック
        assertEquals(3,count);

    }
	
	@Test
    @DisplayName("findLoginUserのテスト")
    void testfindLoginUser() {
		
		System.out.println("UserDaoImplTest testfindLoginUser Start");
		User user = new User();
		user.setId(1);
		user.setUsername("dog");
		user.setPassword("dog");
		user.setAdmin_flg(0);
		System.out.println("Set");
		
		User userExpect = new User();
		userExpect = userDao.findLoginUser(user);
		
		
		assertEquals(user.getUsername(),userExpect.getUsername());
		assertEquals(user.getPassword(),userExpect.getPassword());
		assertEquals(user.getAdmin_flg(),userExpect.getAdmin_flg());
		
		

    }
	

	
	
	

}
