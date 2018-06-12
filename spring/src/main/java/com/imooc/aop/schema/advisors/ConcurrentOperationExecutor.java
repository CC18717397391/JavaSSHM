package com.imooc.aop.schema.advisors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

/**
 * 切面bean,用来测试advicsors
 * @author ClearC
 */

public class ConcurrentOperationExecutor implements Ordered {

	private static final int DEFAULT_MAX_RETRIES = 2;

	private int maxRetries = DEFAULT_MAX_RETRIES;
	
	private int order = 1;

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	//通常这种方式用在环绕通知中，统计切面方法的调用次数或频率，并对其进行控制
	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
		int numAttempts = 0;	//定义整形变量，对应的次数
		PessimisticLockingFailureException lockFailureException;
		do {
			numAttempts++;		//每次+1
			System.out.println("Try times : " + numAttempts);
			try {
				return pjp.proceed();	//执行虚拟方法，环绕通知的参数传递，默认就是这么写
			} catch (PessimisticLockingFailureException ex) {
				lockFailureException = ex;	//如果抛异常，异常赋值给上面定义的方法变量
			}
		} while (numAttempts <= this.maxRetries);	//判断次数
		System.out.println("Try error : " + numAttempts);
		throw lockFailureException;		//执行完毕，抛异常给上面走一遍catch
	}
}
