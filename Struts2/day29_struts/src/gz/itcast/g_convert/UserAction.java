package gz.itcast.g_convert;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register() throws Exception {
		// 使用表单的数据
		System.out.println(user);
		return SUCCESS;
	}
}
