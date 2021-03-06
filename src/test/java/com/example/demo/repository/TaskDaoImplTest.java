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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskDaoImplTest {
	
	@Autowired
	private TaskDaoImpl taskDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/SpringTodoApp";
	private static final String USER = "root";
	private static final String PASSWORD = "";

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
    @DisplayName("DBUnit????????????")
	//@DatabaseSetup("/src/test/resources/data/init.xml")
    void testdbunit() {
		System.out.println("TaskDaoImplTest DBunit Start");

    }
    
    @Test
    @DisplayName("DBUnit??????????????????????????????")
    @Order(1) //
    public void testTableCompTset() throws Exception {    
    	System.out.println("TaskDaoImplTest testTableCompTest Start");

    	IDataSet databaseDataSet = dbTester.getConnection().createDataSet();
    	ITable actualTable = databaseDataSet.getTable("task");
    	IDataSet expectedDataSet =new FlatXmlDataSetBuilder().build(new File("src/test/resources/data/expected.xml"));
    	ITable expectedTable = expectedDataSet.getTable("task");

    	Assertion.assertEquals(actualTable,expectedTable);

    }
	
	@Test
    @DisplayName("findAll????????????")
	@Order(2) //
    void testfindAll() {
		System.out.println("TaskDaoImplTest findAll Start");
		List<Task> list = taskDao.findAll();
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //?????????????????????
        assertEquals(2,list.size());
        
        //?????????????????????????????????????????????????????????
        assertEquals(1,list.get(0).getId());
        assertEquals(1,list.get(0).getUserId());
        assertEquals(1,list.get(0).getTypeId());
        assertEquals("JUnit?????????",list.get(0).getTitle());
        assertEquals("?????????????????????????????????",list.get(0).getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
        assertEquals("??????",list.get(0).getTaskType().getType());
        assertEquals("??????????????????????????????????????????",list.get(0).getTaskType().getContent());

    }
	
	
	@Test
    @DisplayName("findById????????????")
	@Order(3) //
    void testfindById() {
		System.out.println("TaskDaoImplTest findById Start");
		Optional<Task> taskOpt = taskDao.findById(1);
		Task task = new Task();
		if(taskOpt.isPresent()) {
		    task = taskOpt.get();
		    System.out.println("isPresent OK");
		}
        
        //?????????????????????????????????????????????????????????
        assertEquals(1,task.getId());
        assertEquals(1,task.getUserId());
        assertEquals(1,task.getTypeId());
        assertEquals("JUnit?????????",task.getTitle());
        assertEquals("?????????????????????????????????",task.getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),task.getDeadline());
        assertEquals("??????",task.getTaskType().getType());
        assertEquals("??????????????????????????????????????????",task.getTaskType().getContent());

    }
	
	@Test
    @DisplayName("findById????????????(???????????????????????????????????????)")
	@Order(4) //
    void testfindById2() {
        // ????????????????????????????????????????????????????????????
		//taskDao.findById(10);
        assertThrows(EmptyResultDataAccessException.class, () -> taskDao.findById(10));
    }

	
	@Test
    @DisplayName("insert????????????")
	@Order(5) //
    void testInsert() {
		
		System.out.println("TaskDaoImplTest testInsert Start");
		Task task = new Task();
		task.setUserId(1);
		task.setTypeId(1);
		task.setTitle("InsertTest");
		task.setDetail("Insert??????????????????");
		String date = "2021-07-07 10:12:34";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		task.setDeadline(LocalDateTime.parse(date, dtFt));
		
		taskDao.insert(task);
		
		List<Task> list = taskDao.findAll();
		
		//??????????????????
		assertEquals(3,list.size());
		

    }
	
	@Test
    @DisplayName("update????????????")
	@Order(6) //
    void testUpdate() {
		
		System.out.println("TaskDaoImplTest testUpdate Start");
		Task task = new Task();
		task.setId(3);
		task.setUserId(1);
		task.setTypeId(3);
		task.setTitle("Title Update");
		task.setDetail("Detail Update");
		String date = "2021-07-17 12:34:56";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		task.setDeadline(LocalDateTime.parse(date, dtFt));
		
		taskDao.update(task);
		
		List<Task> list = taskDao.findAll();
		
		//??????????????????
		//assertEquals(2,list.size());
		assertEquals(3,list.size());
		
		//Task taskx = list.get(0);
		Optional<Task> taskx = taskDao.findById(3);
				
		//assertEquals(1,taskx.getId());
		
        assertEquals(1,taskx.get().getUserId());
        assertEquals(3,taskx.get().getTypeId());
        assertEquals("Title Update",taskx.get().getTitle());
        assertEquals("Detail Update",taskx.get().getDetail());
        assertEquals(LocalDateTime.parse(date, dtFt),taskx.get().getDeadline());
        

    }
	
	@Test
    @DisplayName("delete????????????")
	@Order(7) //
    void testDeleteById() {
		
		System.out.println("TaskDaoImplTest testDelete Start");
		
		//taskDao.deleteById(2);
		taskDao.deleteById(3);
		
		List<Task> list = taskDao.findAll();
		
		//??????????????????
		//assertEquals(1,list.size());
		assertEquals(2,list.size());
		
    }
	
	@Test
    @DisplayName("findByType????????????")
	@Order(8) //
    void testfindByType() {
		System.out.println("TaskDaoImplTest findByType Start");
		List<Task> list = taskDao.findByType(1);
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //?????????????????????
        assertEquals(1,list.size());
        
        //?????????????????????????????????????????????????????????
        //assertEquals(1,list.get(0).getId());
        assertEquals(1,list.get(0).getUserId());
        assertEquals(1,list.get(0).getTypeId());
        assertEquals("JUnit?????????",list.get(0).getTitle());
        assertEquals("?????????????????????????????????",list.get(0).getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
        assertEquals("??????",list.get(0).getTaskType().getType());
        assertEquals("??????????????????????????????????????????",list.get(0).getTaskType().getContent());

    }
	
	
	@Test
    @DisplayName("findLisBytUserId????????????")
	@Order(9) //
    void testfindListByUserId() {
		System.out.println("TaskDaoImplTest findListByUserId Start");
		int userId = 1;
		List<Task> list = taskDao.findListByUserId(userId);
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //?????????????????????
        
        assertEquals(2,list.size());
        
        //?????????????????????????????????????????????????????????
        
        //assertEquals(1,list.get(0).getId());
        assertEquals(1,list.get(0).getUserId());
        assertEquals(1,list.get(0).getTypeId());
        assertEquals("JUnit?????????",list.get(0).getTitle());
        assertEquals("?????????????????????????????????",list.get(0).getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
        assertEquals("??????",list.get(0).getTaskType().getType());
        assertEquals("??????????????????????????????????????????",list.get(0).getTaskType().getContent());
        

    }
	
	
	@Test
    @DisplayName("findByTypeUserId????????????")
	@Order(10) //
    void testfindByTypeUserId() {
		System.out.println("TaskDaoImplTest findByTypeUserId Start");
		List<Task> list = taskDao.findByTypeUserId(1,1);
        System.out.println(list.get(0).getTitle());     
        System.out.println(list.size());
        System.out.println(list.get(0).getTaskType().getType());
        
        //?????????????????????
        assertEquals(1,list.size());
        
        //?????????????????????????????????????????????????????????
        //assertEquals(1,list.get(0).getId());
        assertEquals(1,list.get(0).getUserId());
        assertEquals(1,list.get(0).getTypeId());
        assertEquals("JUnit?????????",list.get(0).getTitle());
        assertEquals("?????????????????????????????????",list.get(0).getDetail());
        String date = "2020-07-07 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.parse(date, dtFt),list.get(0).getDeadline());
        assertEquals("??????",list.get(0).getTaskType().getType());
        assertEquals("??????????????????????????????????????????",list.get(0).getTaskType().getContent());

    }
	
	
	
	
	

}
