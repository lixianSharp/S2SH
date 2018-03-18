package cn.itcast.d_my_aop;

import org.springframework.stereotype.Component;

//重复代码
@Component("aop")  //把对象加入IOC容器，
public class TransctionAop {
	public void beginTransaction(){
		System.out.println("开启事务");
	}
	
	public void commit(){
		System.out.println("提交事务");
	}
}
