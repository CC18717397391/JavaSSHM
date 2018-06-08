package com.imooc.test.a1xml.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imooc.a1xml.ioc.OneInterface;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)

public class TestOneInterface extends UnitTestBase {
	
	//调用父类3：	因为继承与UnitTestBase，父类提供了无参构造传入springXmlpath路径【即xml文件的位置】
	public TestOneInterface() {
		super("classpath*:spring-ioc.xml");
		//通过super调用父类构造传递xml文件
	}
	
	
	//5.
	@Test
	public void testSay() {
		OneInterface oneInterface = super.getBean("oneInterface");
		//调用父类方法getBean从IOC容器中获取对象
		oneInterface.say("This is a test");
	}
	
	/**
	 * 信息: 
	 * 加载xml：Loading XML bean definitions from URL [file:/C:/workspace/8.1.0_Spring/classes/spring-ioc.xml]
	 * 关闭bean容器：Closing org.springframework.context.support.ClassPathXmlApplicationContext@5d22bbb7
	 * 
	 */
}
