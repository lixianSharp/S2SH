package gz.itcast.biz.front.user.web.action;

import gz.itcast.biz.front.user.service.UserService;
import gz.itcast.biz.front.user.service.UserServiceImpl;
import gz.itcast.entity.Users;
import gz.itcast.util.BaseAction;
import gz.itcast.util.MD5Util;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * 用户模块的action
 * @author APPle
 *
 */
public class UserAction extends BaseAction implements ModelDriven<Users> {
	private Users user = new Users();//接收表单的数据
	
	private UserService service = new UserServiceImpl();
	
	//登录方法
	public String login() throws Exception{
		//获取系统生成的验证码
		//从session域获取系统生成的验证码
		String sCode = (String)sessionMap.get("sCode");
			//对比
		if(!user.getCode().trim().equals(sCode.trim())){
			//抛出错误
			super.addFieldError("user.code.invalid", super.getText("user.code.invalid"));
			return "login_input";
		}
		
		//验证用户名
		Users loginUser = service.login(user.getName());
		
		if(loginUser!=null){
			//验证密码
			//先对用户输入的密码进行md5加密
			String digestPassword = MD5Util.md5(user.getPassword());
			//数据库的密码和用户的密码进行检查
			if(loginUser.getPassword().equals(digestPassword)){
				  //登录成功
				//把用户数据保存到session中去
				sessionMap.put("user", loginUser);
				//清除当前session的验证码
				sessionMap.remove("sCode");
				//转到首页
				return "login";
			}else{
				//抛出错误
				super.addFieldError("user.password.invalid", super.getText("user.password.invalid"));
				return "login_input";
			}
		}else{
			//抛出错误
			super.addFieldError("user.name.invalid", super.getText("user.name.invalid"));
			return "login_input";
		}
	}
	
	//注册方法
	public String register()throws Exception{
		//1)得到系统生成的验证码
		String sCode = (String)sessionMap.get("sCode");
		//2)和用户输入的验证码对比
		if(!user.getCode().trim().equals(sCode)){
			super.addFieldError("user.code.invalid", super.getText("user.code.invalid"));
			return "reg_input";
		}
		//3)调用业务方法保存用户
		service.register(user);
		
		//4)返回主页
		return "reg_succ";
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public Users getModel() {
		return user;
	}
}
