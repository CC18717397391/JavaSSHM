package com.imooc.model;


import com.imooc.util.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CommodityTest {
	private Session session = null;
	
	//order by
	@Test
	public void testOrderby(){
		String hql = " from Commodity order by seller.id asc,price desc,name asc  ";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		
		for(Commodity c : commodities){
			System.out.println("name:"+c.getName());
			System.out.println("price:"+c.getPrice());
		}
	}
	
	
	//	+-*/
	@Test
	public void testWhere4(){
		String hql = " from Commodity c where c.price*5>3000 ";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		
		for(Commodity c : commodities){	
			System.out.println("name:"+c.getName());
			System.out.println("price:"+c.getPrice()*5);
		}
	}
	
	
	//or
	@Test
	public void testWhere3(){
		String hql = " from Commodity c where c.price<200 or c.price>3000 ";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		
		for(Commodity c : commodities){
			System.out.println("name:"+c.getName());
			System.out.println("price:"+c.getPrice());
		}
	}
	
	//and
	@Test
	public void testWhere2(){
		String hql  = " from Commodity c where c.price between 1000 and 5000 and c.category like '%电脑%' or name like '%电脑%' ";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		
		for(Commodity c : commodities){
			System.out.println("name:"+c.getName());
			System.out.println("category:"+c.getCategory());
			System.out.println("price:"+c.getPrice());
		}
	}
	
	//where
	@Test
	public void testWhere1(){
		String hql = " from Commodity c where c.price<=200 ";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		
		for(Commodity c : commodities){
			System.out.println("name:"+c.getName());
			System.out.println("price:"+c.getPrice());
		}
	}
	
	//from
	@Test
	public void testFromClause(){
		String hql = " from Commodity ";
		
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		
		for(Commodity c : commodities){
			System.out.println("name:"+c.getName());
			
			//hibernate默认是不查询外键信息所对应的数据，只有在调用的时候才会进行临时的查询，这样是为了提高查询的效率和性能
			//System.out.println("seller's name :"+c.getSeller().getName());
		}
	}

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

}
