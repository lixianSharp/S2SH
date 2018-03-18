package gz.itcast.util;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * ������֤���action
 * @author APPle
 *
 */
public class ImageAction extends BaseAction {

	@Override
	public String execute() throws Exception {
		//��ȡresponse����
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//����һ�������֤��ͼƬ����д���������
		OutputStream out = response.getOutputStream();
		String sCode = ValidateCodeUtils.genNewCode(out);
		//��sCode������û���¼ʱʹ��
		sessionMap.put("sCode", sCode);
		return null;
	}
}
