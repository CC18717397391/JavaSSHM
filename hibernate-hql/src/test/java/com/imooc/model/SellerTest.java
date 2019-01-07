package com.imooc.model;

import com.imooc.util.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 最简化的 单独from子句使用
 * 几种select子句的查询返回类型
 * @author ClearC
 *
 */
public class SellerTest {
	private Session session = null;
	
	//new Seller
	@Test
	public void testSelectClauseSelf(){
		String hql = " select new Seller(s.name,s.tel,s.address) from Seller s ";
		Query query = session.createQuery(hql);
		List<Seller> sellers = query.list();
		
		for(Seller seller : sellers){
			System.out.println("name: "+seller.getName());
			System.out.println("tel:"+seller.getTel());
			System.out.println("address:"+seller.getAddress());
		}
	}
	
	
	//new map
	@Test
	public void BtestSelectClauseMap(){
		String hql = " select new map(s.name,s.tel,s.address) from Seller s ";
		Query query =session.createQuery(hql);
		
		List<Map> maps = query.list();
		for(Map map : maps){
			System.out.println("name:"+map.get("0"));
			System.out.println("tel:"+map.get("1"));
			System.out.println("address:"+map.get("2"));
		}
	}
	
	//new map
	@Test
	public void AtestSelectClauseMap(){
		String hql = " select new map(s.name as name,s.tel as tel,s.address as address) from Seller s ";
		Query query =session.createQuery(hql);
		
		List<Map> maps = query.list();
		for(Map map : maps){
			System.out.println("name:"+map.get("name"));
			System.out.println("tel:"+map.get("tel"));
			System.out.println("address:"+map.get("address"));
		}
	}
	
	//new list(s.name,s.tel,s.address)
	@Test
	public void testSelectClauseList(){
		String hql = " select new list(s.name,s.tel,s.address) from Seller s ";
		
		Query query = session.createQuery(hql);
		List<List> lists = query.list();
		
		for(List list : lists){
			System.out.println("name : "+list.get(0));
			System.out.println("tel:"+list.get(1));
			System.out.println("address:"+list.get(2));
		}
	}
	
	/*
	 * 1.name
	 */
	@Test
	public void BtestSelectClauseObjectArray(){
		String hql = " select s.name from Seller s ";
		
		Query query = session.createQuery(hql);
		
		List<Object> list = query.list(); 
		
		for(Object obj : list){
			System.out.println("name:"+obj);
		}
	}
	
	/*
	 * 1.name 2.tel 3.address 4.star
	 */
	@Test
	public void AtestSelectClauseObjectArray(){
		String hql = " select s.name ,s.tel , s.address , s.star from Seller s ";
		
		Query query = session.createQuery(hql);
		
		List<Object[]> list = query.list(); 
		
		for(Object[] obj : list){
			System.out.println("name:"+obj[0]);
			System.out.println("tel:"+obj[1]);
			System.out.println("address:"+obj[2]);
			System.out.println("star:"+obj[3]);
		}
	}
	
	@Test
	public void testFromClause(){
		String hql = " from Seller  s ";
		
		Query query = session.createQuery(hql);
		
		List<Seller> sellers = query.list();
		for(Seller seller : sellers){
			System.out.println("name:"+seller.getName());
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
