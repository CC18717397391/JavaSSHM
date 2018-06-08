package com.imooc.a5xml.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring提供了aware接口，通过该接口可以对相应的资源进行操作，其中会包括IOC容器核心的内容
 * @author ClearC
 * ApplicationContextAware:实现ApplicationContext应用上下文的，会向实现了这个接口的bean提供ApplicationContext【也就是IOC容器】的上下文信息
 * 当然实现了这个接口的bean必须配置到xml文件中并加载
 */

public class MoocApplicationContext implements ApplicationContextAware  {
	
	private ApplicationContext applicationContext;	//此处声明是表示实际开发中可以这样调用applicationContext，因为下面有this.赋值
		
	//通过实现接口并覆盖setApplicationContext该方法，可以在方法参数中得到applicationContext，传入的是加载了这个bean【在xml文件中配置】的IOC容器的上下文信息
	//在初始化IOC容器【加载xml】的时候，会调用setApplicationContext方法【本来是隐式调用，因为这里实现了接口并覆盖了】
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
		System.out.println("通过xml初始化IOC，加载MoocApplicationContext : " + applicationContext.getBean("moocApplicationContext").hashCode());
		//此处的getBean方法不同于UnitTestBase中定义的getBean方法，
	}
	
}
