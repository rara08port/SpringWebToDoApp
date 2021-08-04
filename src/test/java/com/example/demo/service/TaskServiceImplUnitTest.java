package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    @Test
    @DisplayName("idに対応したTask取得のテスト")
    void testgetTask() {

        //モックから返すListセット
        
        Task task = new Task();
        task.setTitle("title");
        Optional<Task> taskOpt = Optional.ofNullable(task);
        int id = 1;
        
        
        when(dao.findById(id)).thenReturn(taskOpt);

        Optional<Task> actualList= taskServiceImpl.getTask(id);

        verify(dao, times(1)).findById(id);
        
        assertEquals("title", actualList.get().getTitle());
    }
    
    @Test
    @DisplayName("Insertのテスト")
    void testInsert() {
    	
    	Task task = new Task();
    	task.setId(5);
    	task.setUserId(1);
    	task.setTypeId(2);
    	task.setTitle("testInsertTitle");
    	task.setDetail("testInsertDetail");
    	String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        task.setDeadline(LocalDateTime.parse(date, dtFt));
    	//モックから返すListセット
        List<Task> list = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        list.add(task1);
        list.add(task2);
        
        doNothing().when(dao).insert(task);
        taskServiceImpl.insert(task);
        
        verify(dao, times(1)).insert(task);
      
        
    }
    
    @Test
    @DisplayName("Updateのテスト")
    void testUpdate() {
    	
    	Task task = new Task();
    	task.setId(5);
    	task.setUserId(1);
    	task.setTypeId(2);
    	task.setTitle("testUpdateTitle");
    	task.setDetail("testUpdateDetail");
    	String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        task.setDeadline(LocalDateTime.parse(date, dtFt));
    	
        
        doNothing().when(dao).update(task);
        taskServiceImpl.update(task);
        
        verify(dao, times(1)).update(task);

        
    }
    
    @Test
    @DisplayName("idに対応したDeleteのテスト")
    void testDeleteById() {
    	int id = 1;
    	
    	doNothing().when(dao).deleteById(id);
    	taskServiceImpl.deleteById(id);
    	
        verify(dao, times(1)).deleteById(id);
        
    }
    
    @Test
    @DisplayName("タイプ別タスク取得のテスト")
	void testfindByType() {
		// TODO 自動生成されたメソッド・スタブ
    	int type = 2;
    	//モックから返すListセット
        List<Task> list = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        list.add(task1);
        list.add(task2);
        
        when(dao.findByType(type)).thenReturn(list);

        List<Task> actualList= taskServiceImpl.findByType(type);

        verify(dao, times(1)).findByType(type);
        
        assertEquals(2, actualList.size());
		
	}
	
    @Test
    @DisplayName("UserIdに対応したTaskリスト取得のテスト")
	void testfindListByUserId() {
		// TODO 自動生成されたメソッド・スタブ
    	int userId = 1;
    	List<Task> list = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        list.add(task1);
        list.add(task2);
        
        when(dao.findListByUserId(userId)).thenReturn(list);

        List<Task> actualList= taskServiceImpl.findListByUserId(userId);

        verify(dao, times(1)).findListByUserId(userId);
        
        assertEquals(2, actualList.size());

	}
	
    @Test
    @DisplayName("UserIdとタイプidに対応したTask取得のテスト")
	void testfindByTypeUserId() {
		// TODO 自動生成されたメソッド・スタブ
    	int userId = 1;
    	int typeId = 2;
    	List<Task> list = new ArrayList<>();
        Task task1 = new Task();
        Task task2 = new Task();
        list.add(task1);
        list.add(task2);
        
        when(dao.findByTypeUserId(typeId,userId)).thenReturn(list);

        List<Task> actualList= taskServiceImpl.findByTypeUserId(typeId, userId);

        verify(dao, times(1)).findByTypeUserId(typeId, userId);
        
        assertEquals(2, actualList.size());

		
	}
	




}
