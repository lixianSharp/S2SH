package cn.itcast.d_my_aop2;

import org.springframework.stereotype.Component;


@Component("aop") //把对象加入IOC容器,这个注解用于组件类
public class TransctionAop {
	public void beginTransaction(){
		System.out.println("开启事务");
	}
	
	public void commit(){
		System.out.println("提交事务");
	}
}
