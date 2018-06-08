package com.clear.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 工具类：通过该工具类可以初始化hibernate，获得会话工厂/会话的获取和关闭
 * 不同的hibernate版本会话的获取方式也不一样，hibernate3和4的区别比较大
 * 本例hibernate4
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;	//会话工厂
	private static Session session;
	
	//静态代码块初始化
	static {
		// 创建Configuration对象，读取hibernate.cfg.xml文件，完成初始化
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		StandardServiceRegistry ssr=ssrb.build();
		sessionFactory=config.buildSessionFactory(ssr);
	}
	
	//获取SessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//获取Session
	public static Session getSession(){
		session=sessionFactory.openSession();
		return session;
	}
	
	//关闭Session
	public static void closeSession(Session session){
		if(session!=null){
			session.close();
		}
	}
	
	public static SessionFactory getFactory(){
		return sessionFactory;	
	}
}
