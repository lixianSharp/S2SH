package gz.itcast.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 所有业务请求标准的接口
 * @author APPle
 *
 */
public interface Action {
	public String execute(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException;
}
