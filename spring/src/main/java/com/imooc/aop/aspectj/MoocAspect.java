package com.imooc.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * @author ClearC
 * 
 */
//@EnableAspectJAutoProxy = <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
@Component
@Aspect	//切面类注解，需要配合使用@Component注解被检测
public class MoocAspect {
	
	//切入点【返回必须是void】
	@Pointcut("execution(* com.imooc.aop.aspectj.biz.*Biz.*(..))")
	public void pointcut() {}
	
	@Pointcut("within(com.imooc.aop.aspectj.biz.*)")
	public void bizPointcut() {}
	
	//before advice
	//@Before("execution(* com.imooc.aop.aspectj.biz.*Biz.*(..))")
	@Before("pointcut()")
	public void before() {
		System.out.println("Before.");
	}
	
	@Before("pointcut() && args(arg)")
	public void beforeWithParam(String arg) {
		System.out.println("BeforeWithParam." + arg);
	}
	
	@Before("pointcut() && @annotation(moocMethod)")
	public void beforeWithAnnotaion(MoocMethod moocMethod) {
		System.out.println("BeforeWithAnnotation." + moocMethod.value());
	}
	
	@AfterReturning(pointcut="bizPointcut()", returning="returnValue")
	public void afterReturning(Object returnValue) {
		System.out.println("AfterReturning : " + returnValue);
	}
	
	//抛异常通知
	@AfterThrowing(pointcut="pointcut()", throwing="e")
	public void afterThrowing(RuntimeException e) {
		System.out.println("AfterThrowing : " + e.getMessage());
	}
	
	@After("pointcut()")
	public void after() {
		System.out.println("After.");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Around 1.");
		Object obj = pjp.proceed();
		System.out.println("Around 2.");
		System.out.println("Around : " + obj);
		return obj;
	}
	
}
