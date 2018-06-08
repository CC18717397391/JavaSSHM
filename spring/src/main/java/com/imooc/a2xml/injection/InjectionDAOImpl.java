package com.imooc.a2xml.injection;

public class InjectionDAOImpl implements InjectionDAO {
	
	public void save(String a) {
		//模拟数据库保存操作
		System.out.println("DAO保存数据：" + a);
	}

}
