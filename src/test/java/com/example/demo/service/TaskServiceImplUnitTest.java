package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskDao;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplUnitTest {
	
	@Mock
    private TaskDao dao;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;
    
    
    @Test
    @DisplayName("Taskテーブルの全件取得で2件の場合のテスト")
    void testfindAllList() {

        //モックから返すListセット
        List<Task> list = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        list.add(task1);
        list.add(task2);
        
        when(dao.findAll()).thenReturn(list);

        List<Task> actualList= taskServiceImpl.findAll();

        verify(dao, times(1)).findAll();
        
        assertEquals(2, actualList.size());
    }



}
