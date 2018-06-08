package com.imooc.beanannotation.a6.jsr_inject_name;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

/**
 * JSR330标准【需要引入javax.inject包】
 * 
 * @Autowire @Resource @Inject 可以注入对象、属性、set方法、构造器上面注入
 * @Autowire 有@required标签，允许对象为空 
 * @Resource @Inject没有@required标签，强制要求对象不能为空
 * 
 * @Qualifier @Named 作为限定器，都可以使用标签或者bean的id来限定
 * @Named("jsrServie")	// 等效于Component
 * (@Named("jsrDAO") JsrDAO jsrDAO) 等效于@qulifier缩小范围
 */

//@Service
@Named("jsrServie")	// 等效于Component
public class JsrServie {
			
	//@Inject							//注入JsrDAO方式一【成员变量注入】
	//@Named("jsrDAOTest")
	//private JsrDAO jsrDAO;
	
	@Resource(name="jsrDAOTest")		//注入JsrDAO方式二【成员变量注入】
	private JsrDAO jsrDAO;
	
	//private JsrDAO jsrDAO;
	//@Inject//@Resource				//注入JsrDAO方式三【参数注入】
//	public void setJsrDAO(@Named("jsrDAOTest") JsrDAO jsrDAO) {		
//				//比如 当JsrDAO接口有两个实现类，指定用哪个看@Named，此处和qualifier类似
//		this.jsrDAO = jsrDAO;
//	}
	
	@PostConstruct	//IOC初始化
	public void init() {
		System.out.println("a6.@postConstruct IOC init.");
	}
	
	@PreDestroy		//IOC销毁
	public void destroy() {
		System.out.println("a6.@preDestroy IOC destroy.");
	}

	public void save() {
		jsrDAO.save();
	}
	
}
