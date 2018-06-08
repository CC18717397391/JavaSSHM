package com.imooc.beanannotation.a5.config_bean_value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/***
 * @Bean 通过该注解可以生成一个IOC容器的bean实例【对象】
 */
@Configuration		//该注解相当于声明该类为一个配置类。类似于配置文件
@ImportResource("classpath:config.xml")		//导入资源配置文件
public class StoreConfig {
	
	@Value("${url}")				//赋值为配置文件中的值
	private String url;
	
	@Value("${jdbc.username}")		//如果是username取值为当前计算机的名称
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Bean	//通过该注解可以生成一个IOC容器的bean实例，类似于xml中的bean的配置
	public MyDriverManager myDriverManager() {
		return new MyDriverManager(url, username, password);
	}
	
	//如果不指定name ，测试方法中传入的就是方法名stringStore 	后面两个初始化和销毁方法在StringStore类中
	@Bean(name = "stringStore", initMethod="init", destroyMethod="destroy")
	public Store stringStore() {
		return new StringStore();
	}
	
	//测试@Scope
	@Bean(name = "stringStoreScope")
	@Scope(value="prototype")
	//@Scope(value="prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
	//不加代理模式正常，加了代理变成单例模式
	public Store stringStoreScope() {
		return new StringStore();
	}
	
	
	//基于泛型的自动装配
	@Autowired
	private Store<String> s1;
	
	@Autowired
	private Store<Integer> s2;
	
	@Bean
	public StringStore stringStore1() {
		return new StringStore();
	}
	
	@Bean
	public IntegerStore integerStore() {
		return new IntegerStore();
	}
	
	@Bean(name = "stringStoreTest")
	public Store stringStoreTest() {
		System.out.println("a5.@bean s1 : " + s1.getClass().getSimpleName());
		System.out.println("a5.@bean s2 : " + s2.getClass().getSimpleName());
		return new StringStore();
	}
	

}
