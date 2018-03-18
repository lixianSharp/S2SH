package gz.itcast.h_upload_down;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * 文件下载
 * @author 贤元
 *
 */
public class DownAction {
	
	//注入服务器目录地址
	private String  serverPath;//注入要下载的文件的地址
	//接收文件名
	private String name;//这次是从jsp中的那个a标签中来的
	
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	
	public String getServerPath() {
		return serverPath;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	//http://localhost:8080/day29_struts/upload/down_list.action
	//列出文件
	public String list() throws Exception{
		System.err.println("serverPath服务器目录地址：="+serverPath);
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
			//转码：解决中文乱码问题
			//先用ISO8859-1编码 再使用UTF-8解码
			String filename = new String(name.getBytes("ISO8859-1"),"UTF-8");//中名名称.后缀名
			System.out.println(filename);
			FileInputStream fis = new FileInputStream(new File(serverPath+filename));//服务器目录地址+文件名
			return fis;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	
	}
	
	//下载方法
	public String down() throws Exception{
		System.out.println(this.name);
		return "down";
	}
	
}
