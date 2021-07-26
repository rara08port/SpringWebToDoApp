package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskDao;


@SpringBootTest
public class TaskServiceImplTest {
    
    @Autowired
    private TaskService taskService;
    
    
    @Test
    @DisplayName("findAll 全件検索のテスト")
    void testfindAll() {
        //全件取得
        List<Task> list = taskService.findAll();
        //Taskテーブルに入っている2件が取得できているか確認
        assertEquals(2, list.size());
    }
    
    
}
