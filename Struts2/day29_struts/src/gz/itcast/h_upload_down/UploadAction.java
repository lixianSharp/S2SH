package gz.itcast.h_upload_down;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * 文件上传的三个条件：
 * 		1、表单有file
 * 		2、提交方式为post
 * 		3、enctype="multipart/form-date"
 * 
 * @author 贤元
 *
 */
//文件上传
public class UploadAction{
	/**
	 * 1）接收上传的内容
	 */
	//接收上传的文件，名字来自于表单的file的name属性名称
	private File attach;//
	//2）接收文件类型
	private String attachContentType;
	//3)接收文件名称
	private String attachFileName;
	//4）接收描述
	private String info;//

	/**
	 * 注入服务器目录地址
	 */
	private String savePath;
	/**
	 * 注意：一定要给setter方法，这些方法就是给上传文件内容进行赋值的
	 */
	public void setAttach(File attach) {
		this.attach = attach;
	}
	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
	public void setInfo(String info){
		this.info = info;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
	public String execute() throws Exception {
		System.out.println(attach);//  D:\ qwert\ upload_c03f093_15adf60d06f__8000_00000000.tmp
		System.out.println(attachContentType);// image/jpeg   上传的文件类型
		System.out.println(attachFileName);// 1.jpg  上传的文件的名字
		System.out.println(info);//这是一张图片  也就是文件的描述信息

		/**
		 * 把文件保存到服务器硬盘
		 */
		FileUtils.copyFile(attach, new File(savePath+attachFileName));
		return "success";
	}

}
