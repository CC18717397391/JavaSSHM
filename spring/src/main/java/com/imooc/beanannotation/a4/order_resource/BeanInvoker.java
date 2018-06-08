package com.imooc.beanannotation.a4.order_resource;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * list输出顺序，便是放入顺序，hashset和hashmap的输出顺序是无序的，treemap和treeset的输出顺序是自动顺序，但也可自己实现排序
 */
@Component
public class BeanInvoker {
	
	@Autowired		//自动导入泛型类型对应的bean，泛型的两个实现类会被@Autowired注解进来两个实例，顺序看实现类的@order
	private List<BeanInterface> list;
	
	@Autowired		//自动导入泛型类型对应的bean，key是所有bean的id，value就是bean的对象
	private Map<String, BeanInterface> map;
	
	@Autowired
	@Qualifier("beanImplOne")		//第一种方式：测试@Autowired+@Qualifier缩小范围或指定具体的bean，通过该方式可以，但不建议
	private BeanInterface beanInterfaceA;
	
	@Resource(name="beanImplTwo")	//第二种方式：JSR-250@Resource指定具体的bean
	private BeanInterface beanInterfaceB;
	
	public void say() {
		//测试list的泛型注入
		if (null != list && 0 != list.size()) {
			System.out.print("a4.@Autowired@Order@Resource@Qualifier.list...    ");
			for (BeanInterface bean : list) {
				System.out.print(bean.getClass().getSimpleName() + "  ");
			}
		} else {
			System.out.println("List<BeanInterface> list is null !!!!!!!!!!");
		}
		
		System.out.println();
		
		//测试map的泛型注入
		if (null != map && 0 != map.size()) {
			System.out.print("a4.@Autowired@Order@Resource@Qualifier.map...    ");
			for (Map.Entry<String, BeanInterface> entry : map.entrySet()) {
				System.out.print(entry.getKey() + ":" + entry.getValue().getClass().getSimpleName() + "  ");
			}
		} else {
			System.out.println("Map<String, BeanInterface> map is null !!!!!!!!!!");
		}
		
		System.out.println();
		
		//测试@Autowired+@Qualifier缩小范围或指定具体的bean
		if (null != beanInterfaceA) {
			System.out.println("a4.@Autowired@Qualifier...    " + beanInterfaceA.getClass().getSimpleName());
		} else {
			System.out.println("a4.@Autowired@Qualifier.beanInterface is null...");
		}
		
		//测试@@Resource指定具体的bean
		if (null != beanInterfaceB) {
			System.out.println("a4.@Resource...    " + beanInterfaceB.getClass().getSimpleName());
		} else {
			System.out.println("a4.@Resource.beanInterface is null...");
		}
		
		
	}

}
