package com.clear.hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.clear.hibernate.entity.Address;
import com.clear.hibernate.entity.User;

//测试类
public class A2_TestCRUD {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction; 
	
	@Before
	public void init(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		session = sessionFactory.openSession();
		//开启事务		不开启只执行DDL语句，不执行DML语句
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();	//提交事务
		session.close();		//关闭会话
		sessionFactory.close();	//关闭会话工厂
	}
	
	
	@Test
	public void testSaveStudents1(){
		//生成学生对象
		User s = new User(1,"张三丰","男",Date.valueOf("1997-08-08"), new Address("710068","6666666","西安"));
		session.save(s);	//保存对象进入数据库	，不开启事务就不能将对象保存到数据库，hibernate默认不自动提交事务
	}
	
	
	/*如果在init中不开启事务，并且在destroy中不提交事务
	 * 程序只执行DDL语句，不执行DML语句，就是只建表，不插入数据
	 * 如果想让hibernate像jdbc一样自动提交事务，必须调用并重写session的doWork()方法，开启自动提交,session.flush();
	 * 通常不建议这样做 
	 * 该测试doWork()无需提交commit
	 **/ 
	//@Test
	public void testSaveStudents2(){
		//生成学生对象
		User s = new User(0,"张三丰2","男",Date.valueOf("1997-08-08"),new Address("710068","6666666","西安"));
		session.doWork(new Work() {			
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.setAutoCommit(true);	//设置自动提交
			}
		});
		session.save(s);	
		session.flush();
	}
	
	
	//将图片音频等数据以二进制数据类型Blob存入数据库
	//@Test
	public void testWriteBlob() throws IOException{
		User s = new User(0,"张三丰","男",Date.valueOf("1997-08-08"),new Address("710068","6666666","西安"));
		//获取照片文件
		File f = new File("c:"+File.separator+"images"+File.separator+"1.jpg");
		//获取输入流
		InputStream input = new FileInputStream(f);
		//创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
		//对象设置照片属性
		s.setPicture(image);
		//保存对象进入数据库
		session.save(s);
	}
	

	
	//取出数据库中对象的相应数据
	//@Test
	public void testReadBlob() throws Exception{
		
		User s = (User) session.get(User.class, 2);
		//Students s = (Students) session.load(Students.class, 2);
		//如果下方没有调用对象s的语句，load方法不会发送sql执行语句，只有用的时候才会调用
		//获取Blob对象
		Blob image = s.getPicture();
		//获得照片输入流
		InputStream input = image.getBinaryStream();
		//创建输出流
		File f = new File("c:"+File.separator+"images"+File.separator+"dest.jpg");
		//获得输出流
		OutputStream output = new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	}
	
	//get方法在调用之后立刻发送sql语句，返回持久化对象
	//@Test
	public void testGetStudents(){
		User s = (User) session.get(User.class, 1);
		//通过反射获取对象所属的类的名字，是Students
		System.out.println(s.getClass().getName());
		System.out.println(s);
	}
	
	//@Test
	public void testLoadStudents(){
		User s = (User) session.load(User.class, 1);
		//如果下方没有调用对象s的语句，load方法不会发送sql执行语句，只有用的时候才会调用
		//通过反射获取对象所属的类的名字,返回的是一个代理对象
		System.out.println(s.getClass().getName());	//获取代理对象的名称
		//System.out.println(s);
	}
	
	//@Test
	public void testUpdateStudents(){
		User s = (User) session.get(User.class, 1);
		s.setGender("女");
		session.update(s);
		//更新数据时会同步到缓存区，所以在update更新之后，后续get没有执行查询SQL
	}
	
	//@Test
	public void testDeleteStudents(){
		User s = (User) session.get(User.class, 1);
//		Students s = new Students();
//		s.setSid(1);
		session.delete(s);
	}
	
	@Test
	public void queryfindAll(){
		String hql="from User";
		Query query=session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list=query.list();
		for(User e:list){
			System.out.println(e);
		}
	}
	
}
