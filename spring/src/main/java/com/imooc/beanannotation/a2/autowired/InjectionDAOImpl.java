package com.imooc.beanannotation.a2.autowired;

import org.springframework.stereotype.Repository;

@Repository			//针对DAO层的注解
public class InjectionDAOImpl implements InjectionDAO {
	
	public void save(String arg) {
		//模拟数据库保存操作
		System.out.println("a2.@Autowired Dao：" + arg);
	}

}
