package com.imooc.test.beanannotation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.imooc.beanannotation.a6.jsr_inject_name.JsrServie;
import com.imooc.test.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestA6_Jsr extends UnitTestBase {
	
	public TestA6_Jsr() {
		super("classpath*:spring-beanannotation.xml");
	}
	
	@Test
	public void testSave() {
		JsrServie service = getBean("jsrServie");		//@Resource
		service.save();
	}
	
}
