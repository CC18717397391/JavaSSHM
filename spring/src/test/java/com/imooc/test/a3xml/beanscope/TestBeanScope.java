package com.imooc.test.a3xml.beanscope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.a3xml.beanscope.BeanScope;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanScope extends UnitTestBase {
	
	public TestBeanScope() {
		super("classpath*:spring-beanscope.xml");
	}
	
	//如果执行两个test测试，即便是singleton模式，哈希码也不同，
	//因为junit测试每个单独的测试方法都会调用UnitTestBase的before，after会销毁上下文容器，第二次测试相当于重新加载xml生成IOC容器，所以不同
	
	//测试scope是"singleton"，两次调用是相同的实例
	//如果是prototype，两次调用则是不同的实例
	@Test
	public void testSay() {
		BeanScope beanScope = super.getBean("beanScope");
		beanScope.say();
		
		BeanScope beanScope2 = super.getBean("beanScope");
		beanScope2.say();
	}
	
	
	@Test
	public void testSay2() {
		BeanScope beanScope  = super.getBean("beanScope");
		beanScope.say();
	}

}
