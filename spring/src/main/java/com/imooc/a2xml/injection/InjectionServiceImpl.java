package com.imooc.a2xml.injection;

/**
 * 当调用任意一个test测试时，请注掉另外一种注入方法，特别包括xml
 * @author ClearC
 *
 */

public class InjectionServiceImpl implements InjectionService {
	
	private InjectionDAO injectionDAO;
	
	//设值注入【标配set方法】		方法名与xml的property-name相关，其他无关
	public void setInjectionDAO(InjectionDAO injectionDAO) {
		this.injectionDAO = injectionDAO;
	}
	
	//构造器注入	参数必须与<constructor-arg name相同【标配】
//	public InjectionServiceImpl(InjectionDAO injectionDAO) {
//		this.injectionDAO = injectionDAO;
//	}
	
	public void save(String arg) {
		//模拟业务操作
		System.out.println("com.imooc.test.a2.ioc.inject：" + arg);
		arg = arg + ":" + this.hashCode();		//此处进行了hashcode重新赋值 ，再调用DAO的
		injectionDAO.save(arg);
	}
	
}
