package com.clear.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.clear.hibernate.entity.Emp;
import com.clear.hibernate.util.HibernateUtil;
/**
 * -对临时态/游离态的对象执行save,update可将其转变为持久态
 */
public class A7_TestPersistentStatus {
	/**
	 * 验证持久态的数据可以自动同步更新数据库
	 */
	@Test
	public void test1(){
		Emp e=new Emp();
		e.setName("嫦娥");
		Session session=HibernateUtil.getSession();
		Transaction ts=session.beginTransaction();
		try {
			//通过save把临时态变为持久态， 必须有commit操作才会执行
			session.save(e);
			//对持久态数据进行修改，会同步更新到数据库，出现一条updateSQL语句
			e.setName("张浩");
			ts.commit();
		} catch (HibernateException e1) {
			e1.printStackTrace();
			ts.rollback();//多线程执行时，通过rollback回滚事务达到一致性
		}finally{
			session.close();
		}
	}
	
	/**
	 * 验证持久态对象同步更新的时机
	 */
	@Test
	public void test3(){
		Session session=HibernateUtil.getSession();
		Emp emp=(Emp)session.get(Emp.class, 21);
		emp.setName("唐僧");
		//flush方法可以触发同步更新，在控制台能看到更新的SQL语句
		//但并未提交，因此数据库数据没有变化，所以必须commit
		session.flush();
		session.close();
	}
	/**
	 * 验证持久态对象存在于一级缓存中
	 */
	@Test
	public void test4(){
		Emp e=new Emp();
		e.setName("武松");
		Session session=HibernateUtil.getSession();
		Transaction ts=session.beginTransaction();
		Integer id=(Integer) session.save(e);		//save不提交先保存在缓存区中
		
		Emp emp=(Emp) session.get(Emp.class, id);	//可以从缓存区中得到
		System.out.println(emp.getName());
		//ts.commit();
	}
	
	//当调用query.list()方法查询全部数据时，查询到的结果会拆开成一个一个的对象，单独存在缓存区
	//from后面加的是类名
	@Test
	public void test5(){
		String hql="from Emp";
		Session session =HibernateUtil.getSession();
		Query query=session.createQuery(hql);
		List<Emp> emps=query.list();
		System.out.println(emps);
		Emp emp=(Emp)session.get(Emp.class, 1);
		System.out.println(emp.getName());
		session.close();
	}
}
