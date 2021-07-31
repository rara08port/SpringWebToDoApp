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
		user.setUsername("");
		user.setPassword("dog");
		user.setAdmin_flg(0);
		int count=10;
		
		count = userDao.findRegisterUser(user);
		//System.out.println(count);
		
		//件数チェック
        assertEquals(1,count);

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
