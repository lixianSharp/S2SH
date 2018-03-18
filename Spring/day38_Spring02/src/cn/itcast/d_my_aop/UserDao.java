package cn.itcast.d_my_aop;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

/**
 * 1、面向过程的分离
 * 2、对象化分离
 * @author 贤元
 *
 */
@Repository   //把对象加入IOC容器   ， 该注解用于持久层的
public class UserDao {
	
	@Resource  //该注解会根据private TransctionAop aop 自动从IOC容器找名字为aop的对象，并为其注入值
	private TransctionAop aop;
	
	public void save(){
		aop.beginTransaction();
		System.out.println("保存。。。");
		aop.commit();
	}
	
	
}
