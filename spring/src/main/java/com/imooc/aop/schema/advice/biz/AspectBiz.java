package com.imooc.aop.schema.advice.biz;


/**
 * 业务类，用来测试advice
 * @author ClearC
 *
 */
public class AspectBiz {
	
	public void biz() {
		System.out.println("AspectBiz biz.");
//		throw new RuntimeException();
	}
	//void是一种特殊类型的返回，所以还是会有afterReturning的；当抛出异常后，方法没有办法正常返回，所有抛异常无返回
	
	
	//用来测试环绕通知的参数传递
	public void init(String bizName, int times) {
		System.out.println("AspectBiz init : " + bizName + "   " + times);
	}

}
