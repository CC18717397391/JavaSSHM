package com.imooc.test.beanannotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.beanannotation.a3.required.Mail;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestA3_Required extends UnitTestBase {
	
	public TestA3_Required() {
		super("classpath:spring-beanannotation.xml");
	}
	
	//测试通过注解Required 实现注入初始参数
	@Test
	public void testAutowired() {
		Mail mail = super.getBean("mail");
		System.out.println(mail);;
	}
	
}
