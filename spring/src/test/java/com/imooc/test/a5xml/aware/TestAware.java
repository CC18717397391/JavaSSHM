package com.imooc.test.a5xml.aware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAware extends UnitTestBase {
	
	public TestAware() {
		super("classpath:spring-aware.xml");
	}
	
	//此处执行，先初始化IOC容器【xml】中的两个bean信息
	//因为都实现了Spring内置的两个接口并覆盖了方法，所以必须先初始化覆盖的方法。然后再执行test调用的getBean是UnitTestBase中定义的方法
	@Test
	public void testMoocApplicationContext() {
		System.out.println("testMoocApplicationContext : " + super.getBean("moocApplicationContext").hashCode());
		//调用的getBean是UnitTestBase中定义的方法
		//结论：无论是通过Aware接口传入【IOC容器初始化】获得applicationContext，还是通过已经启动的容器context再调用getBean方法都是一样的
	}
	
	@Test
	public void textMoocBeanName() {
		System.out.println("textMoocBeanName : " + super.getBean("moocBeanName").hashCode());
	}
	
}
