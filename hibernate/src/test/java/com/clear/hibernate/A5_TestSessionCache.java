package com.clear.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.clear.hibernate.entity.Emp;
import com.clear.hibernate.util.HibernateUtil;

/**
 * get方法在调用之后会先去缓存区查询，如果不存在会直接去数据库发出SQL语句进行查询，返回持久化对象
 * @author ClearC
 *
 */
public class A5_TestSessionCache {
	
		/*访问数据库时，Hibernate会优先向缓存区去查找要访问的数据，如果缓存区存在数据，
		则直接返回，如果缓存区不存在，会向数据库查询，返回数据，同时把该数据放到缓存区。
		使用同一个Session,查询同一条数据多次，只输出一次SQL语句，
		说明只进入数据库查询一次，验证了缓存的存在。*/
		@Test
		public void test1(){
			Session session=HibernateUtil.getSession();
			Emp e1=(Emp) session.get(Emp.class,1);
			System.out.println(e1.getName());
			Emp e2=(Emp) session.get(Emp.class,1);
			System.out.println(e2.getName());
			session.close();
		}
	
		//-一个Session不能去访问其他Session的缓存区，即一级缓存不能交叉访问。所以执行两次SQL
		@Test
		public void test2(){
			Session session1 =HibernateUtil.getSession();
			Session session2 =HibernateUtil.getSession();
			Emp e1=(Emp) session1.get(Emp.class,1);
			System.out.println(e1.getName());
			Emp e2=(Emp) session2.get(Emp.class,1);
			System.out.println(e2.getName());
			session1.close();
			session2.close();
		}
		
		
		@Test
		public void test3(){
			Session session=HibernateUtil.getSession();
			Emp e1=(Emp) session.get(Emp.class,1);
			System.out.println(e1.getName());
			Emp e2=(Emp) session.get(Emp.class,21);
			System.out.println(e2.getName());
			session.close();
		}
		
		/*--session.evict(obj):将obj从缓存移除
		--session.clear():移除缓存中所有对象
		--session.close():释放缓存区*/
		@Test
		public void test4(){
			Session session=HibernateUtil.getSession();
			Emp e1=(Emp) session.get(Emp.class,1);
			System.out.println(e1.getName());
			//session.evict(e1);//将e1对象从缓存中移除
			session.clear();
			Emp e2=(Emp) session.get(Emp.class,1);
			System.out.println(e2.getName());
			session.close();
		}
		
		/*
		 * 更新数据时同步到缓存区，所以在update更新之后，第二个get没有执行查询SQL
		 */
		@Test
		public void test5(){
			Session session=HibernateUtil.getSession();
			session.clear();
			
			Emp e1=(Emp) session.get(Emp.class,1);
			System.out.println(e1.getName());
			e1.setName("zhangxiaomei");
			Transaction ts=session.beginTransaction();
			session.update(e1);
			ts.commit();		//update操作需要执行commit
			Emp e2=(Emp) session.get(Emp.class,1);
			System.out.println(e2.getName());
			session.close();
		}
}
