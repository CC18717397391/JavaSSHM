package com.clear.hibernate;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;



public class A1_TestSession {

	//openSession()方法获取session对象
	@Test
	public void testOpenSession(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		Session session1 = sessionFactory.openSession();
		if(session1!=null){
			System.out.println("session1创建成功");
		}else{
			System.out.println("session1创建失败");
		}
		Session session2 = sessionFactory.getCurrentSession();
		if(session2!=null){
			System.out.println("session2创建成功");
		}else{
			System.out.println("session2创建失败");
		}
	}
	
	/*
	 * openSession【手动关闭】如果不关闭，每次创建新的session对象，多次之后导致连接池溢出，所以需要手动关闭
	 */
	@Test
	public void testSaveStudentsWithOpenSession(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		Session session1 = sessionFactory.openSession();
		//开启事务
		Transaction transaction =  session1.beginTransaction();
		session1.doWork(new Work() {			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashCode1:"+connection.hashCode());
			}
		});
		session1.close();		//手动关闭之后，两次使用相同的connection
		transaction.commit();	//提交事务
		
		Session session2 = sessionFactory.openSession();
		transaction =  session2.beginTransaction();
		session2.doWork(new Work() {			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashCode2:"+connection.hashCode());
			}
		});
		transaction.commit();	//提交事务	
	}
	
	
	
	/*
	 * getCurrentSession【无需手动关闭】在事务提交或回滚之后会自动关闭回收，第二次可以继续使用
	 * 执行此段测试：开启本地事务 <property name="hibernate.current_session_context_class">thread</property>	
	 */
	@Test
	public void testSaveStudentsWithGetCurrentSession(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		Session session1 = sessionFactory.getCurrentSession();
		//开启事务
		Transaction transaction =  session1.beginTransaction();
		//生成一个学生对象		
		session1.doWork(new Work() {			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashCode1:"+connection.hashCode());
			}
		});
		transaction.commit();	//提交事务,自动关闭session
		
		Session session2 = sessionFactory.getCurrentSession();
		transaction =  session2.beginTransaction();
		session2.doWork(new Work() {			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashCode2:"+connection.hashCode());
			}
		});
		transaction.commit();	//提交事务	
	}
}
