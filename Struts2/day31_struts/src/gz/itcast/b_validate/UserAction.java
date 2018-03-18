package gz.itcast.b_validate;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private User user;//接收表单参数
	//必须要提供setter方法
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 用代码方式对action的所有方法进行验证
	 * 
	 * Action类覆盖validate方法(验证所有方法) (而且这个对提交上来的所有数据都会进行验证啊)
	 * 
	 * 注意：这个验证方法是对该Action类中的所有方法都进行验证的呀！
	 * 		但是我只想对指定的方法(例如register方法)进行验证，该怎么办呢？
	 * 		这个简单：只需要将验证方法名称改为:validate+需要验证的方法名称   
	 * 		 例如：validateRegister().就 可以验证register()方法。但是不会验证detail方法
	 */
	/*public void validateRegister() {
		if(user == null){
			//把错误信息放入错误信息Map集合
			super.addFieldError("user.error", "至少填写一个数据");
		}else{
			//在这里写表单数据验证的逻辑
			System.out.println("调用了validate方法");
			if(user.getName() == null || user.getName().equals("")){
				//用户名为空
				//把错误信息放入错误信息Map集合
				super.addFieldError("user.error.requried", "用户名不能为空");
				
			}if(user.getPassword() == null || user.getPassword().equals("")){
				//密码为空
				super.addFieldError("password.error.requried", "密码不能为空");
			}
		}
	}*/
	
	/**
	 * 用代码方式进行验证的弊端：
	 * 		代码在程序中写死了！！不太灵活，扩展性不太好!!
	 * 如何解决？
	 * 	xml配置数据验证的规则：
					1）编写一个xml文件，名称：  Action文件名-validation.xml
					2）该xml文件必须放在Action文件的同一目录
	 */
	/**
	 * 如果这个validate方法中出现异常，那么就直接返回错误页面
	 * 如果这个validate方法没有出现异常，那么直接运行业务方法(register)
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		System.out.println("注册成功");
		return SUCCESS;
	}
	
	public String detail() throws Exception{
		System.out.println("查看用户详细信息");
		return SUCCESS;
	}
	
}
