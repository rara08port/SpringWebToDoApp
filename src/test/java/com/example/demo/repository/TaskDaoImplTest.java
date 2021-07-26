package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Task;

@SpringBootTest
public class TaskDaoImplTest {
	
	@Autowired
	private TaskDaoImpl taskDao;
	
	@Test
    @DisplayName("findAllのテスト")
    void findAll() {
		System.out.println("TaskDaoImplTest findAll Start");
		List<Task> list = taskDao.findAll();
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //件数のチェック
        assertEquals(2,list.size());
        
        //各カラムの値が正しくセットされているか
        assertEquals(1,list.get(0).getId());
        assertEquals(1,list.get(0).getUserId());
        assertEquals(1,list.get(0).getTypeId());
        assertEquals("JUnitを学習",list.get(0).getTitle());
        assertEquals("テストの仕方を学習する",list.get(0).getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
        assertEquals("緊急",list.get(0).getTaskType().getType());
        assertEquals("最優先で取り掛かるべきタスク",list.get(0).getTaskType().getContent());

    }
	
	

}
