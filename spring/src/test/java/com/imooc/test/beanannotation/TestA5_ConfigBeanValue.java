package com.imooc.test.beanannotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;

import com.imooc.beanannotation.a5.config_bean_value.MyDriverManager;
import com.imooc.beanannotation.a5.config_bean_value.Store;
import com.imooc.beanannotation.a5.config_bean_value.StringStore;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestA5_ConfigBeanValue extends UnitTestBase {
	
	public TestA5_ConfigBeanValue() {
		super("classpath*:spring-beanannotation.xml");
	}
	
	
	//@Test
	public void test() {
		//测试方法@bean传递id的情形
		Store store = super.getBean("stringStore");
		System.out.println("test a5.@bean :" + store.getClass().getSimpleName());
		//测试@ImportResource和@Value
		MyDriverManager manager = super.getBean("myDriverManager");
		System.out.println("test a5.@configuration_@bean_@value :" + manager.getClass().getSimpleName());
	}
	
	//测试Scope
	//@Test
	public void testScope() {
		Store store = super.getBean("stringStoreScope");
		System.out.println("test a5.@scope :" + store.hashCode());
		
		store = super.getBean("stringStoreScope");
		System.out.println("test a5.@scope :" + store.hashCode());
	}
	
	//测试泛型的自动装配，此处不再进行syso输出，因为getbean的时候会IOC自动装配bean
	@Test
	public void testG() {
		StringStore store = super.getBean("stringStoreTest");
	}
	
}
