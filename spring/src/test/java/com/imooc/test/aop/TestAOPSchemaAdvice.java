package com.imooc.test.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.aop.schema.advice.Fit;
import com.imooc.aop.schema.advice.biz.AspectBiz;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPSchemaAdvice extends UnitTestBase {
	
	public TestAOPSchemaAdvice() {
		super("classpath:spring-aop-schema-advice.xml");
	}
	
	//@Test
	public void testBiz() {
		AspectBiz biz = super.getBean("aspectBiz");
		biz.biz();
	}
	
	//测试环绕通知的带参切入
	//@Test
	public void testInit() {
		AspectBiz biz = super.getBean("aspectBiz");
		biz.init("moocService", 3);
	}
	
	//测试Introductions，属于简介的一种advice
	@Test
	public void testFit() {
		Fit fit = (Fit)super.getBean("aspectBiz");
		fit.filter();
	}
	
}
