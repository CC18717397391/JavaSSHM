package com.imooc.beanannotation.a1.scope;

import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * spring3.0开始使用注解进行bean的注入，自动检测并注册bean到上下文ApplicationContext
 * spring自动检测的注解：首先这个注解要注册到类上，这是基于类的注解【使用@service @Repository @Component @Controller】
 * 还有的注解是注册在方法或成员变量上【@Autowired】,如果是注册在类上的注解，那么会作为spring的bean自动注册到applicationContext中
 * @author ClearC
 * 
 */

@Scope("prototype")		//不写默认是singleton一个上下文容器中只存在一份bean的实例  	prototype模式每次请求都会创建新的实例,当请求完成后对象就会被GC回收
@Component				//通用注解	 根据BeanNameGenerator自动生成 默认的bean的id，是类名第一个字母小写
//@Component("bean")	//自定义bean的id,和test中的super.getBean("bean")对应	【@service @Repository @Component @Controller都可以设置】
public class ComponentScope {
	
	public void say(String arg) {
		System.out.println("a1.@Component : " + arg);
	}
	
	public void myHashCode() {
		System.out.println("a1.@Scope : " + this.hashCode());
	}
	
}
