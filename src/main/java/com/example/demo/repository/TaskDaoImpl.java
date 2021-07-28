package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskType;

@Repository
public class TaskDaoImpl implements TaskDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> findAll() {
        // TODO 自動生成されたメソッド・スタブ
        
        String sql = "SELECT task.id, user_id, type_id, title, detail, deadline, "
                + "type, content FROM task "
                + "INNER JOIN task_type ON task.type_id = task_type.id "
                + "ORDER BY type_id ASC, deadline ASC";
        
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
        
        List<Task> list = new ArrayList<>();
        
        for(Map<String, Object> result : resultList) {

            Task task = new Task();
            task.setId((int)result.get("id"));
            task.setUserId((int)result.get("user_id"));
            task.setTypeId((int)result.get("type_id"));
            task.setTitle((String)result.get("title"));
            task.setDetail((String)result.get("detail"));
            task.setDeadline((LocalDateTime)result.get("deadline"));    

            TaskType type = new TaskType();
            type.setId((int)result.get("type_id"));
            type.setType((String)result.get("type"));
            type.setContent((String)result.get("content"));
            task.setTaskType(type);

            list.add(task);
        }
        return list;
        
        
    }

    @Override
    public Optional<Task> findById(int id) {
        
        // TODO 自動生成されたメソッド・スタブ
        String sql = "SELECT task.id, user_id, type_id, title, detail, deadline, "
                + "type, content FROM task "
                + "INNER JOIN task_type ON task.type_id = task_type.id "
                + "WHERE task.id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);

        Task task = new Task();
        task.setId((int)result.get("id"));
        task.setUserId((int)result.get("user_id"));
        task.setTypeId((int)result.get("type_id"));
        task.setTitle((String)result.get("title"));
        task.setDetail((String)result.get("detail"));
        task.setDeadline((LocalDateTime)result.get("deadline"));    

        TaskType type = new TaskType();
        type.setId((int)result.get("type_id"));
        type.setType((String)result.get("type"));
        type.setContent((String)result.get("content"));
        task.setTaskType(type);

        //taskをOptionalでラップする
        Optional<Task> taskOpt = Optional.ofNullable(task);

        return taskOpt;
    }

    @Override
    public void insert(Task task) {
        // TODO 自動生成されたメソッド・スタブ
        
        jdbcTemplate.update("INSERT INTO task(user_id,type_id,title,detail,deadline) VALUES(?,?,?,?,?)",
                task.getUserId(),task.getTypeId(),task.getTitle(),task.getDetail(),task.getDeadline());

    }

    @Override
    public void update(Task task) {
        // TODO 自動生成されたメソッド・スタブ
        jdbcTemplate.update("UPDATE task SET type_id = ?, title = ?, detail = ?,deadline = ? WHERE id = ?",
                task.getTypeId(), task.getTitle(), task.getDetail(), task.getDeadline(), task.getId() );
        
    }

    @Override
    public void deleteById(int id) {
        // TODO 自動生成されたメソッド・スタブ
        jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
        
    }

    @Override
    public List<Task> findByType(int typeId) {
        // TODO 自動生成されたメソッド・スタブ
    	String sql = "SELECT task.id, user_id, type_id, title, detail, deadline, "
				+ "type, content FROM task "
				+ "INNER JOIN task_type ON task.type_id = task_type.id "
				+ "WHERE task.type_id = ?";

		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,typeId);

		List<Task> list = new ArrayList<>();

		for(Map<String, Object> result : resultList) {

			Task task = new Task();
			task.setId((int)result.get("id"));
			task.setUserId((int)result.get("user_id"));
			task.setTypeId((int)result.get("type_id"));
			task.setTitle((String)result.get("title"));
			task.setDetail((String)result.get("detail"));
			task.setDeadline((LocalDateTime)result.get("deadline"));

			TaskType type = new TaskType();
			type.setId((int)result.get("type_id"));
			type.setType((String)result.get("type"));
			type.setContent((String)result.get("content"));
			task.setTaskType(type);

			list.add(task);
		}
		return list;
    }

}
