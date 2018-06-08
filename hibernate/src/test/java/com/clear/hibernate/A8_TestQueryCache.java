package com.clear.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.clear.hibernate.entity.Emp;
import com.clear.hibernate.util.HibernateUtil;
/**
 * 查询缓存:
		--是一个特殊的二级缓存
		--除了能缓存实体对象，还能缓存集合，数组等
		--是基于二级缓存的
		--默认关闭的
	使用步骤：
		--开启二级缓存
		--在主配置文件中hibernate.cfg.xml中，设置开启查询缓存
		--在代码中，查询之前设置其可以缓存的标志。
			setCacheable(true);
 * @author Administrator
 *
 */
public class A8_TestQueryCache {
	
	public static List<Emp> findAll(){
		Session session =HibernateUtil.getSession();
		Query query=session.createQuery("from Emp");
		query.setCacheable(true);
		List<Emp> emps=query.list();
		HibernateUtil.closeSession(session);
		return emps;
	}
	
	public static void main(String[] args) {
		List<Emp> emps=findAll();
		for(Emp e:emps){
			System.out.println(e.getId()+" "+e.getName());
		}
//		HibernateUtil.getFactory().evictQueries();//管理查询缓存的
		System.out.println("......................................");
		emps=findAll();
		for(Emp e:emps){
			System.out.println(e.getId()+" "+e.getName());
		}
	}
}
