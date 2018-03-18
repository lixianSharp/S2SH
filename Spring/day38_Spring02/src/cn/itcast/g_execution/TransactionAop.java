package cn.itcast.g_execution;

import org.aspectj.lang.ProceedingJoinPoint;

//切面类
public class TransactionAop {
	
	// 【前置通知】
	// 在执行业务方法之前执行
	public void beginTransaction() {
		System.out.println("【前置通知】开启事务..");
	}

	// 【后置通知】
	// 在执行业务方法之后执行
	public void commit() {
		System.out.println("【后置通知】提交事务..");
	}

	// 【返回后通知】 在执行目标方法结束后执行,出现异常不会执行
	public void afterReturing() {
		System.out.println("[返回后通知]");
	}

	// 【异常通知】 在执行目标方法的时候出现异常执行
	public void afterThrowing() {
		System.out.println("[异常通知]");
	}

	// 【环绕通知】 会环绕目标方法执行
	public void arroud(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("【环绕前：】");
		pjp.proceed();// 执行目标方法
		System.out.println("【环绕后：】");
	}

}
