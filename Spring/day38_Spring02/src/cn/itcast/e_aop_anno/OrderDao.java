package cn.itcast.e_aop_anno;

import org.springframework.stereotype.Repository;

/**
 * 1、面向过程的分离
 * 2、对象化分离
 * @author 贤元
 *
 */
@Repository  //把对象加入IOC容器，这个注解用于持久层
public class OrderDao {//使用cglib的时候不能实现接口
	public void save(){
		System.out.println("保存订单OrderDao.save()。。。");
	}
}
