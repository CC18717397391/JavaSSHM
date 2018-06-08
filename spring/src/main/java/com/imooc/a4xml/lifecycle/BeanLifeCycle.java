package com.imooc.a4xml.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class BeanLifeCycle implements InitializingBean, DisposableBean {
	
	//三种初始化和销毁的方法作对比：接口方法快于配置方法，当一个bean使用了接口或配置方法后，默认的全局初始化defautInit和销毁defaultDestroy会覆盖掉
	
	//1.xml全局配置时调用的方法	即使不写以下初始化和销毁的方法实现程序依旧执行，兼容性强
	public void defautInit() {
		System.out.println("Bean defautInit.");
	}
	
	public void defaultDestroy() {
		System.out.println("Bean defaultDestroy.");
	}
	
	//2.实现接口时调用的方法		一旦实现接口必须重写方法，这个没的说
	@Override
	public void destroy() throws Exception {
		System.out.println("Bean destroy.");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Bean afterPropertiesSet.");
	}
	
	//3.xml配置调用的方法	一旦xml文件配置，必须写以下名称对应的初始化和销毁方法，否则加载bean时BUG
	public void start() {
		System.out.println("Bean start .");
	}
	
	public void stop() {
		System.out.println("Bean stop.");
	}
	
}
