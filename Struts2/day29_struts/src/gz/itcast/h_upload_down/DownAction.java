package gz.itcast.h_upload_down;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

/**
 * 文件下载
 * @author 贤元
 *
 */
public class DownAction {
	
	private String  serverPath;//注入要下载的文件的地址
	//接收文件名
	private String name;//这次是从jsp中的那个a标签中来的
	
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getServerPath() {
		return serverPath;
	}

	public String getName() {
		return name;
	}

	//http://localhost:8080/day29_struts/upload/down_list.action
	//列出文件
	public String list() throws Exception{
		File file  = new File(serverPath);
		String[] list = file.list();//返回所有String类型的文件名称
		//把文件列表转到jsp页面显示
		ActionContext ac = ActionContext.getContext();
		//得到request域对象
		Map<String,Object> requestMap = ac.getContextMap();
		requestMap.put("list", list);//将数据保存在request域中
		
		System.out.println("list:"+list);
		
		return "list";
	}
	
	//需要提供给struts写出数据的输入流
	public InputStream getInputStream(){
		try {
			FileInputStream fis = new FileInputStream(new File(serverPath+name));
			return fis;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	
	}
	
	//下载方法
	public String down() throws Exception{
		return "down";
	}
	
}
