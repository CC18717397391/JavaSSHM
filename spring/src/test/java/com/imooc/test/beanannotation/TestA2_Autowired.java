package com.imooc.test.beanannotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.beanannotation.a2.autowired.InjectionService;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestA2_Autowired extends UnitTestBase {
	
	public TestA2_Autowired() {
		super("classpath:spring-beanannotation.xml");
	}
	
	//测试通过注解autowire 实现注入
	@Test
	public void testAutowired() {
		InjectionService service = super.getBean("injectionServiceImpl");
		service.save("This is @Autowired");
	}
	
}
