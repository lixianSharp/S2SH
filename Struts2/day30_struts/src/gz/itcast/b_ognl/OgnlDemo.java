package gz.itcast.b_ognl;

import static org.junit.Assert.*;
import ognl.Ognl;
import ognl.OgnlContext;

import org.junit.Test;

/**
 * 目标：学习了解Ognl表达式核心对象OgnlContext(OGNL的上下文)对象的使用
 * @author 贤元
 *
 */
/**
 * 	结论：
		1）从OgnlContext对象的根对象取出数据，不需要#号
		2）从OgnlContext对象的非根对象取出数据，需要#号	

 * @author 贤元
 *
 */
public class OgnlDemo {
	
	@Test
	public void test1() throws Exception {
		User user = new User();
		user.setName("eric");
		user.setAge(20);
		
		// 1)创建一个OgnlContext对象
		OgnlContext context = new OgnlContext();
		// 2）把user对象存入OgnlContext对象中
		context.put("user", user);
		// 3）从OgnlContext对象取出数据
		User user2 = (User) context.get("user");
		System.out.println(user2.getName()+"\t"+user2.getAge());//eric 20
	}
	
	// 使用Ognl表达式取出OgnlContext的数据，如果是非根对象数据，那么使用#号去取
	@Test
	public void test2() throws Exception {
		User user = new User();
		user.setName("李贤元");
		user.setAge(24);
		
		// 1）创建一个OgnlContext对象
		OgnlContext context = new OgnlContext();
		// 2)把user对象存入OgnlContext对象中
		context.put("use", user);//往OgnlContext的非根对象存数据
		
		//使用Ognl表达式从OgnlContext对象取出数据
		Object ognlObj = Ognl.parseExpression("#use.name");//表达式对象  注意：这里的use是49行中的键"use"
		String name = (String) Ognl.getValue(ognlObj, context, context.getRoot());//获取数据的方法
		System.out.println(name);//李贤元
	}
	
	/**
	 * 使用Ognl表达式取出OgnlContext的数据，
	 * 		如果是根对象数据，不需要使用#号，不需要key名称，直接写存入对象的属性即可
	 */
	@Test
	public void test3() throws Exception {
		User user = new User();
		user.setName("eric");
		user.setAge(20);
		
		// 1）创建一个OgnlContext对象
		OgnlContext context = new OgnlContext();
		// 2）把user对象存入OgnlContext对象 根对象
		context.setRoot(user);//往OgnlContext的根对象 存数据
		
		// 使用Ognl表达式从OgnlContext对象取出数据
		Object ognlObj = Ognl.parseExpression("name");//表达式对象，因为69行的set数据是将数据存入根对象中的，所以直接写"name"即可
		String name = (String) Ognl.getValue(ognlObj, context, context.getRoot());//获取数据方法
		System.out.println(name);//eric
	}
	
	/**
	 * Ognl表达式的特点：不仅可以存取数据，还可以调用方法。
	 * 而EL表达式只能取数据，不能存数据，也不能调用方法
	 * 
	 * 使用Ognl表达式调用Math中的方法
	 */
	/**
	 * Ognl表达式调用静态方法
	 * 支持类静态的方法调用和值访问，表达式的格式:
	 * 		@[类全名（包括包路径）]@[方法名 |  值名]，例如：
             @java.lang.String@format('foo %s', 'bar')
                                或@tutorial.MyConstant@APP_NAME；

	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		//Math.round(3.14);//java.lang.Math类中的四舍五入的方法
		
		//1）创建一个OgnlContext对象
		OgnlContext context = new OgnlContext();
		
		//使用Ognl表达式从OgnlContext对象取出数据
		//Object ognlObj = Ognl.parseExpression("@java.lang.Math@round(10.5)");//表达式对象
		Object ognlObj = Ognl.parseExpression("@Math@round(10.5)");
		Object result = Ognl.getValue(ognlObj, context, context.getRoot());
		System.out.println(result);//11
		
	}
	
}
