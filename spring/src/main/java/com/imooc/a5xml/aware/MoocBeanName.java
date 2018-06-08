package com.imooc.a5xml.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * BeanNameAware：会提供一个关于BeanName定义的内容
 * @author ClearC
 *
 */
public class MoocBeanName implements BeanNameAware, ApplicationContextAware {
	
	//同时实现两个接口，并覆盖内置的方法，bean初始化时执行
	
	private String beanName;
	
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
		System.out.println("通过xml初始化IOC，加载MoocBeanName : " + name);
	} 

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println("通过xml初始化IOC，加载setApplicationContext : " + applicationContext.getBean(this.beanName).hashCode());
	}

}
