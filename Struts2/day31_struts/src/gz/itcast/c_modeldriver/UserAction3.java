package gz.itcast.c_modeldriver;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction3 extends ActionSupport{
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public UserAction3() {
		// TODO Auto-generated constructor stub
	}
	
	public String resiter() throws Exception{
		System.out.println(user);
		return "ui";//执行该方法后跳转的视图标记
	}
	
	
}
