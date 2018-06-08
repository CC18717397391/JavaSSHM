package com.imooc.beanannotation.a2.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Autowired用于构造器或成员变量
 * @Autowired(required=false)；通过该方式避免抛出空异常；
 * @Autowired也可以使用来注解官方定义的接口，比如BeanFactory,ApplicationContext,ResourceLoader,MessageSource
 * 	@Autowired
 *	private ApplicationContext context;	接下来就可以使用context进行操作，知识点说明，与demo无关
 */

@Service	//针对service层的注解
public class InjectionServiceImpl implements InjectionService {
	
	@Autowired(required=false)	//设值注入，最方便常用
	private InjectionDAO injectionDAO;
	
//	@Autowired		//构造注入
//	public InjectionServiceImpl(InjectionDAO injectionDAO) {
//		this.injectionDAO = injectionDAO;
//	}

	
	public void save(String arg) {
		//模拟业务操作
		System.out.println("a2.@Autowired Service：");
		arg = arg + ":" + this.hashCode();
		injectionDAO.save(arg);
	}
	
}
