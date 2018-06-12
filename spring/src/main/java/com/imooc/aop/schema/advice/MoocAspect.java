package com.imooc.aop.schema.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类，用来测试advice
 * @author ClearC
 *
 */
public class MoocAspect {
	
	public void before() {
		System.out.println("MoocAspect before.");
	}
	
	public void afterReturning() {
		System.out.println("MoocAspect afterReturning.");
	}
	
	// 当抛出异常后，方法没有办法正常返回，所以afterReturning失效
	public void afterThrowing() {
		System.out.println("MoocAspect afterThrowing.");
	}
	
	public void after() {
		System.out.println("MoocAspect after.");
	}
	
	//定义方法的第一个参数必须是ProceedingJoinPoint类型
	public Object around(ProceedingJoinPoint pjp) {
		Object obj = null;
		try {
			System.out.println("MoocAspect around 1.");
			obj = pjp.proceed();	//虚拟的业务方法的执行
			System.out.println("MoocAspect around 2.");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;					//虚拟的业务方法的返回值
	}
	
	public Object aroundInit(ProceedingJoinPoint pjp, String bizName, int times) {
		Object obj = null;
		try {
			System.out.println("MoocAspect aroundInit 1	："+bizName + "   " + times);
			obj = pjp.proceed();
			System.out.println("MoocAspect aroundInit 2	："+bizName + "   " + times);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
