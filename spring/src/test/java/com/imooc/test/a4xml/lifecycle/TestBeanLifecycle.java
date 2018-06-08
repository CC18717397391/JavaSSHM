package com.imooc.test.a4xml.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanLifecycle extends UnitTestBase {
	
	public TestBeanLifecycle() {
		super("classpath:spring-lifecycle.xml");
	}
	
	//因为测试方法会创建容器，执行完毕关闭容器【即UnitTestBase中的before/after】，所以直接执行了全部生命周期各环节所调用的方法
	@Test
	public void test1() {
		super.getBean("beanLifeCycle");
	}
	
}
