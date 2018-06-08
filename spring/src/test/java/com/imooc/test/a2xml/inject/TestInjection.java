package com.imooc.test.a2xml.inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.a2xml.injection.InjectionService;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {
	
	//加载xml文件 ，自动装载bean进入IOC容器，所以InjectionServiceImpl中的两种注入都必须实现
	public TestInjection() {
		super("classpath:spring-injection.xml");
	}
	
	//设值注入
	@Test
	public void testSetter() {
		InjectionService service = super.getBean("SinjectionService");
		service.save("这是要保存的数据");
	}
	
	//构造注入
	//@Test
	public void testCons() {
		InjectionService service = super.getBean("CONinjectionService");
		service.save("这是要保存的数据");
	}
	
}
