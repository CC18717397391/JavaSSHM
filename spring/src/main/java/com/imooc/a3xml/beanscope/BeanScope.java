package com.imooc.a3xml.beanscope;

public class BeanScope {
	
	//随意定义的方法，  方法调用this当前对象的 hashCode，来判断singleton和prototype两者bean作用域的区别
	public void say() {
		System.out.println("BeanScope say : " + this.hashCode());
	}
	
}
