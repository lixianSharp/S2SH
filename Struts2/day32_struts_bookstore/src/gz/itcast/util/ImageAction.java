package gz.itcast.util;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * 生成验证码的action
 * @author APPle
 *
 */
public class ImageAction extends BaseAction {

	@Override
	public String execute() throws Exception {
		//获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//生成一张随机验证码图片，并写出到浏览器
		OutputStream out = response.getOutputStream();
		String sCode = ValidateCodeUtils.genNewCode(out);
		//把sCode共享给用户登录时使用
		sessionMap.put("sCode", sCode);
		return null;
	}
}
