package com.imooc.a6xml.autowiring;


/**
 * 自动注入实现bean属性的注入 ，通过在xml中的顶部配置
 * 测试一种注入的时候，记得注释掉另外一种注入方法，看xml配置
 *
 */
public class AutoWiringService {
	
	
	private AutoWiringDAO autoWiringDAO;
	
	//构造注入		default-autowire="constructor"
//	public AutoWiringService(AutoWiringDAO autoWiringDAO) {
//		System.out.println("AutoWiringService");
//		this.autoWiringDAO = autoWiringDAO;
//	}
	
	//设值注入的set方法		default-autowire="byName"
	public void setAutoWiringDAO(AutoWiringDAO autoWiringDAO) {
		System.out.println("setAutoWiringDAO");
		this.autoWiringDAO = autoWiringDAO;
	}
	
	//自定义say方法，方法内部还是调用了DAO的say方法
	public void say(String word) {
		this.autoWiringDAO.say(word);
	}

}
