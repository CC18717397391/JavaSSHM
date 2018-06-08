package com.imooc.test.beanannotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.beanannotation.a1.scope.ComponentScope;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestA1_ComponentScope extends UnitTestBase {
	
	public TestA1_ComponentScope() {
		super("classpath*:spring-beanannotation.xml");
	}
	
	//该测试根据id获取bean的实例
	@Test
	public void testSay() {
		ComponentScope bean = super.getBean("beanAnnotation");
		//依旧是通过id获取bean的实例，如果未手动自定义id，则会根据BeanNameGenerator自动生成，规则：类名第一个字母小写
		bean.say("This is test @Component.");
		
		//自定义bean的id时调用此方法
		/*bean = super.getBean("bean");
		bean.say("This is test.");*/
	}
	
	//该测试bean的scope
	@Test
	public void testScpoe() {
		ComponentScope bean = super.getBean("beanAnnotation");
		bean.myHashCode();
		
		bean = super.getBean("beanAnnotation");
		bean.myHashCode();
	}
	
}
