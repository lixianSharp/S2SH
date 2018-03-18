package gz.itcast.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 所有业务请求的标准
 * @author 贤元
 *
 */
public interface Action {
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
