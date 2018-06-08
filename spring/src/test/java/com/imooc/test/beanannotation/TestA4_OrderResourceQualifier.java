package com.imooc.test.beanannotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.beanannotation.a4.order_resource.BeanInvoker;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestA4_OrderResourceQualifier extends UnitTestBase {
	
	public TestA4_OrderResourceQualifier() {
		super("classpath:spring-beanannotation.xml");
	}
	
	//测试@Autowired可以将集合的泛型的两个实现类注解到集合中去
	//测试@Qualifier缩小范围或指定具体的bean
	@Test
	public void testMultiBean() {
		BeanInvoker invoker = super.getBean("beanInvoker");		
		invoker.say();		//Ctrl+左键查看对应的bean，在Automultibean这个包中
	}
	
}
