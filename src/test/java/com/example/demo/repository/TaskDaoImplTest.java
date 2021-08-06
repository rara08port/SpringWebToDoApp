package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

import org.junit.jupiter.api.BeforeAll;
import org.dbunit.IDatabaseTester;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;






import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


import com.example.demo.entity.Task;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class TaskDaoImplTest {
	
	@Autowired
	private TaskDaoImpl taskDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	//private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    //private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/sample?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	//private static final String CONNECTION_URL = "jdbc:mysql://localhost/SpringTodoApp";
    //private static final String USER = "root";
    ///private static final String PASSWORD = "pass";
    //private static final String SCHEMA = "sample";
	
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/SpringTodoApp";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	//private static final String SCHEMA = "";

    private static IDatabaseTester dbTester;
    
    
	@BeforeAll
    public static void before() throws Exception {
		System.out.println("before");
		
		
		dbTester = new JdbcDatabaseTester(
                DRIVER_NAME, CONNECTION_URL, USER, PASSWORD) {
            @Override
            public IDatabaseConnection getConnection() throws Exception {
                IDatabaseConnection con = super.getConnection();
                con.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
                return con;
            }
        };
        
        IDataSet dataSet =
                new FlatXmlDataSetBuilder().build(new File("src/test/resources/data/init.xml"));

        dbTester.setDataSet(dataSet);
        //dbTester.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        //dbTester.setSetUpOperation(DatabaseOperation.INSERT);
 
        //dbTester.setSetUpOperation(DatabaseOperation.REFRESH);
        dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);

        dbTester.onSetup();
		
		
		
    }

    @AfterAll
    public static void after() throws Exception {
    	System.out.println("after");
    	dbTester.setTearDownOperation(DatabaseOperation.NONE);
        dbTester.onTearDown();
    }
    
    @Test
    @DisplayName("DBUnitのテスト")
	//@DatabaseSetup("/src/test/resources/data/init.xml")
    void testdbunit() {
		System.out.println("TaskDaoImplTest DBunit Start");

    }
    
    @Test
    @DisplayName("DBUnitファイル比較のテスト")
    public void testTableCompTset() throws Exception {    
    	System.out.println("TaskDaoImplTest testTableCompTest Start");

    	IDataSet databaseDataSet = dbTester.getConnection().createDataSet();
    	ITable actualTable = databaseDataSet.getTable("task");
    	IDataSet expectedDataSet =new FlatXmlDataSetBuilder().build(new File("src/test/resources/data/expected.xml"));
    	ITable expectedTable = expectedDataSet.getTable("task");

    	Assertion.assertEquals(actualTable,expectedTable);

    }
	
	@Test
    @DisplayName("findAllのテスト")
	//@DatabaseSetup("src/test/resources/data/init.xml")
    void testfindAll() {
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
	
	
	@Test
    @DisplayName("findByIdのテスト")
    void testfindById() {
		System.out.println("TaskDaoImplTest findById Start");
		Optional<Task> taskOpt = taskDao.findById(1);
		Task task = new Task();
		if(taskOpt.isPresent()) {
		    task = taskOpt.get();
		    System.out.println("isPresent OK");
		}
        
        //各カラムの値が正しくセットされているか
        assertEquals(1,task.getId());
        assertEquals(1,task.getUserId());
        assertEquals(1,task.getTypeId());
        assertEquals("JUnitを学習",task.getTitle());
        assertEquals("テストの仕方を学習する",task.getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),task.getDeadline());
        assertEquals("緊急",task.getTaskType().getType());
        assertEquals("最優先で取り掛かるべきタスク",task.getTaskType().getContent());

    }
	
	@Test
    @DisplayName("findByIdのテスト(レコードが取得できない場合)")
    void testfindById2() {
        // レコードが取得できず例外がスローされるか
		//taskDao.findById(10);
        assertThrows(EmptyResultDataAccessException.class, () -> taskDao.findById(10));
    }

	
	@Test
    @DisplayName("insertのテスト")
    void testInsert() {
		
		System.out.println("TaskDaoImplTest testInsert Start");
		Task task = new Task();
		task.setUserId(1);
		task.setTypeId(1);
		task.setTitle("InsertTest");
		task.setDetail("Insertテストの詳細");
		String date = "2021-07-07 10:12:34";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		task.setDeadline(LocalDateTime.parse(date, dtFt));
		
		taskDao.insert(task);
		
		List<Task> list = taskDao.findAll();
		
		//件数チェック
		assertEquals(3,list.size());
		
		
		//Task taskx = list.get(2);
		
		//System.out.println(taskx.getDetail());
		
//		Task task1 = list.get(0);
//		Task task2 = list.get(1);
//		Task task3 = list.get(2);
//		System.out.println(task1.getDetail());
//		System.out.println(task2.getDetail());
//		System.out.println(task3.getDetail());
		
		/*
		assertEquals(3,taskx.getId());
        assertEquals(1,taskx.getUserId());
        assertEquals(1,taskx.getTypeId());
        assertEquals("InsertTest",taskx.getTitle());
        assertEquals("Insertテストの詳細",taskx.getDetail());
        assertEquals(LocalDateTime.parse(date, dtFt),taskx.getDeadline());
        */
		

    }
	
	@Test
    @DisplayName("updateのテスト")
    void testUpdate() {
		
		System.out.println("TaskDaoImplTest testUpdate Start");
		Task task = new Task();
		task.setId(1);
		task.setUserId(1);
		task.setTypeId(3);
		task.setTitle("Title Update");
		task.setDetail("Detail Update");
		String date = "2021-07-17 12:34:56";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		task.setDeadline(LocalDateTime.parse(date, dtFt));
		
		taskDao.update(task);
		
		List<Task> list = taskDao.findAll();
		
		//件数チェック
		assertEquals(2,list.size());
		
		Task taskx = list.get(0);
				
		assertEquals(1,taskx.getId());
        assertEquals(1,taskx.getUserId());
        assertEquals(3,taskx.getTypeId());
        assertEquals("Title Update",taskx.getTitle());
        assertEquals("Detail Update",taskx.getDetail());
        assertEquals(LocalDateTime.parse(date, dtFt),taskx.getDeadline());
        

    }
	
	@Test
    @DisplayName("deleteのテスト")
    void testDeleteById() {
		
		System.out.println("TaskDaoImplTest testDelete Start");
		
		taskDao.deleteById(2);
		
		List<Task> list = taskDao.findAll();
		
		//件数チェック
		assertEquals(1,list.size());
		
    }
	
	@Test
    @DisplayName("findByTypeのテスト")
    void testfindByType() {
		System.out.println("TaskDaoImplTest findByType Start");
		List<Task> list = taskDao.findByType(1);
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //件数のチェック
        assertEquals(1,list.size());
        
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
	
	
	@Test
    @DisplayName("findLisBytUserIdのテスト")
    void testfindListByUserId() {
		System.out.println("TaskDaoImplTest findListByUserId Start");
		int userId = 3;
		List<Task> list = taskDao.findListByUserId(userId);
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //件数のチェック
        
        assertEquals(2,list.size());
        
        //各カラムの値が正しくセットされているか
        
        //assertEquals(1,list.get(0).getId());
        assertEquals(3,list.get(0).getUserId());
        assertEquals(1,list.get(0).getTypeId());
        assertEquals("JUnitを学習",list.get(0).getTitle());
        assertEquals("テストの仕方を学習する",list.get(0).getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
        assertEquals("緊急",list.get(0).getTaskType().getType());
        assertEquals("最優先で取り掛かるべきタスク",list.get(0).getTaskType().getContent());
        

    }
	
	
	@Test
    @DisplayName("findByTypeUserIdのテスト")
    void testfindByTypeUserId() {
		System.out.println("TaskDaoImplTest findByTypeUserId Start");
		List<Task> list = taskDao.findByTypeUserId(1,3);
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //件数のチェック
        assertEquals(1,list.size());
        
        //各カラムの値が正しくセットされているか
        //assertEquals(1,list.get(0).getId());
        assertEquals(3,list.get(0).getUserId());
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
