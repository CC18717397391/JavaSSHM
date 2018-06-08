package com.clear.hibernate;

import org.hibernate.Session;
import org.junit.Test;

import com.clear.hibernate.entity.Emp;
import com.clear.hibernate.util.HibernateUtil;
/**
 * 二级缓存(高级特性)(默认关闭)
		--SessionFactory级别的缓存
			由SessionFactory负责管理。
		--二级缓存是默认关闭的
		--二级缓存也是用来缓存实体对象的
			SessionFactory下所有的Session都可以访问该二级缓存区域。
	2)二级缓存的使用步骤：
		--导包ehcache.jar
		--导入缓存配置文件ehcache.xml
		--在主配置文件hibernate.cfg.xml中设置开启二级缓存，并且设置缓存驱动。
		--想缓存哪个实体对象，就在该实体对象的映射文件中(XX.hbm.xml)追加<cache>元素。
 * @author Administrator
 *
 */
public class A9_TestSecondCache {
	/**
	 * 验证二级缓存的存在
	 */
	@Test
	public void test1(){
		Session session1=HibernateUtil.getSession();
		
		Emp e1=(Emp)session1.get(Emp.class, 1);
		System.out.println(e1.getName());
		HibernateUtil.closeSession(session1);
		
		HibernateUtil.getFactory().evict(Emp.class);
			//session.evict(obj):将obj从缓存移除
		Session session2=HibernateUtil.getSession();
		Emp e2=(Emp)session2.get(Emp.class, 1);
		System.out.println(e2.getName());
		HibernateUtil.closeSession(session2);
		
	}
}
