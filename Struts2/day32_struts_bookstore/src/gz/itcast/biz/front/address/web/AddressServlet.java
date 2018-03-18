package gz.itcast.biz.front.address.web;



import gz.itcast.biz.front.address.service.AddressService;
import gz.itcast.biz.front.address.service.AddressServiceImpl;
import gz.itcast.entity.Address;
import gz.itcast.entity.Users;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 注意：这里的所有方法都必须在登录之后才能执行！！！
 * @author APPle
 *
 */
public class AddressServlet extends BaseServlet {
	
	AddressService service = new AddressServiceImpl();
	
	/**
	 * 添加收货地址
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *  /address?action=addAddress
	 */
	public void addAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)接收数据
		Address addr = WebUtil.copyRequestToBean(request, Address.class);
		
		//设置当前用户id
		//得到当前登录用户
		Users loginUser = (Users)request.getSession().getAttribute("user");
		addr.setUser(loginUser);
		
		//2)调用方法保存
		service.save(addr);

		//3)返回到addback.jsp页面
		response.sendRedirect(request.getContextPath()+"/jsp/front/address/addback.jsp");
	}
	
	/**
	 * 根据用户id查询收货地址
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *  /address?action=queryAddress
	 */
	public void queryAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			//从sesssion中取出当前登录用户数据
			Users loginUser = (Users)request.getSession().getAttribute("user");
			
			//查询对应的地址
			List<Address> addressList = service.queryAddress(loginUser.getId());
			
			//保存数据，然后跳转到列表页面
			request.setAttribute("addressList", addressList);
			request.getRequestDispatcher("/jsp/front/address/list.jsp").forward(request, response);
	}
}
