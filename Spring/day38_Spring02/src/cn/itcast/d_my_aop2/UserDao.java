package cn.itcast.d_my_aop2;

import org.springframework.stereotype.Repository;

/**
 * 1、面向过程的分离
 * 2、对象化分离
 * @author 贤元
 *
 */
@Repository //把对象加入IOC容器 ，默认是类名且第一个字母小写userDao  ，该注解用于持久层
public class UserDao implements IUserDao{
	public void save(){
		System.out.println("保存");
	}
}
