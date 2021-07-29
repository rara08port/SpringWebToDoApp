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
import com.example.demo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<User> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		String sql = "SELECT id, name FROM user ";
        
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
        
        List<User> list = new ArrayList<>();
        
        for(Map<String, Object> result : resultList) {

            User user = new User();
            user.setId((int)result.get("id"));
            user.setUsername((String)result.get("name"));
 
            list.add(user);
        }
        return list;
	}
	
	
	@Override
	public int findRegisterUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		int count=10;
		String sql = "SELECT count(*) FROM user ";
		
		count = jdbcTemplate.queryForObject(sql,Integer.class);
		
		return count;
	}

	@Override
	public User findLoginUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
        String sql = "SELECT id, name, password, admin_flg "
                + " FROM user "
                + "WHERE name = ? AND password = ? ";
        
        
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, user.getUsername(),user.getPassword());

        
        User userResult = new User();
        userResult.setId((int)result.get("id"));
        
        userResult.setUsername((String)result.get("name"));
       
        userResult.setPassword((String)result.get("password"));
        
        userResult.setAdmin_flg((int)result.get("admin_flg"));
       
        //taskをOptionalでラップする
        //Optional<User> userOpt = Optional.ofNullable(userResult);

        return userResult;
	}

	@Override
	public void insert(User user) {
		// TODO 自動生成されたメソッド・スタブ
		
		jdbcTemplate.update("INSERT INTO user(name,password,admin_flg) VALUES(?,?,0)",
                user.getUsername(),user.getPassword());

	}



	

}
