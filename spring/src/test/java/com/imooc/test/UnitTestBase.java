package com.imooc.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 该类提供了加载xml文件并初始化IOC容器的一系列操作，被所有测试案例继承并调用，是所有案例的第一步
 * BeanFactory【beans.jar内】提供配置结构和基本功能，加载并初始化bean
 * ApplicationContext保存了bean对象并在spring中使用
 * ApplicationContext三种加载方式：本地文件/Classpath【本次使用】/Web应用中依赖servlet或Listener
 * 一个bean容器指的是：UnitTestBase基类中通过ClassPathXmlApplicationContext加载xml文件获得context并启动之后就是一个上下文容器
 */
public class UnitTestBase {
	
	private ClassPathXmlApplicationContext context;
	
	private String springXmlpath;
	
	public UnitTestBase(String springXmlpath) {	//2.通过构造器传入springXmlpath
		this.springXmlpath = springXmlpath;
	}
	
	//4.
	@Before
	public void before() {
		if (StringUtils.isEmpty(springXmlpath)) {
			springXmlpath = "classpath*:spring-*.xml";
		}
		try {
			context = new ClassPathXmlApplicationContext(springXmlpath.split("[,\\s]+"));
			//加载xml文件【有三种方式：文件/Classpath/Web应用】，创建上下文context【即SpringBean容器】，当容器启动之后会查找xml配置信息并将Bean信息装载到上下文中
			context.start();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
	//6.
	@After
	public void after() {
		context.destroy();
	}
	
	
	//通过beanId从xml文件中获得对应的类的对象
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getBean(String beanId) {
		try {
			return (T)context.getBean(beanId);
			//通过context.getBean()方法获取相应的对象
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected <T extends Object> T getBean(Class<T> clazz) {
		try {
			return context.getBean(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}

}
