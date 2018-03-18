package gz.itcast.e_scope;

import java.util.ArrayList;
import java.util.List;

public class BookAction extends BaseAction {

	public String list() throws Exception {
		// 1)从数据库中得到数据
		List<String> list = new ArrayList<String>();
		list.add("eric");
		list.add("jacky");
		list.add("rose");

		// 往request域中存放数据
		requestMap.put("request_list", list);
		// 往session域中存放数据
		sessionMap.put("session_list", list);
		// 往context域中存放数据
		contextMap.put("context_list", list);
		return "success";
	}
}
