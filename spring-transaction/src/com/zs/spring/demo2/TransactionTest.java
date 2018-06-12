package com.zs.spring.demo2;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class TransactionTest {

	/**
	 * 一定要注入代理类:因为代理类进行增强的操作
	 */
	//@Resource(name="accountService")	  //未添加事务管理：注入xml的bean<bean id="accountService"
	@Resource(name="accountServiceProxy")	//添加事务管理：注入增强代理类
	private AccountService accountService;
	
	@Test
	public void demo1(){
		accountService.transfer("aaa", "bbb", 200d);
	}
}
