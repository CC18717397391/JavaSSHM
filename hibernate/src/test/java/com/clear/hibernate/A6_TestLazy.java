package com.clear.hibernate;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.clear.hibernate.entity.Emp;
import com.clear.hibernate.util.HibernateUtil;


public class A6_TestLazy {
	/**
	 * 验证session.load方法采用的延迟加载
	 */
	@Test
	public void test1(){
		Session session =HibernateUtil.getSession();
		Emp emp=(Emp) session.load(Emp.class, 1);
		//System.out.println(emp);
		System.out.println(emp.getClass().getName());		//代理对象
		session.close();
	}
	/**
	 * 验证query.iterate()采用延迟加载
	 */
	@Test
	public void test2(){
		String hql="from Emp";
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery(hql);
		Iterator<Emp> it=query.iterate();
		while(it.hasNext()){
			Emp emp=it.next();
			System.out.println(emp.getId());
		}
		session.close();
	}
	
}
