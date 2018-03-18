package gz.itcast.biz.front.user.web.action;

import gz.itcast.biz.front.user.service.UserService;
import gz.itcast.biz.front.user.service.UserServiceImpl;
import gz.itcast.entity.Users;
import gz.itcast.util.BaseAction;
import gz.itcast.util.MD5Util;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * �û�ģ���action
 * @author APPle
 *
 */
public class UserAction extends BaseAction implements ModelDriven<Users> {
	private Users user = new Users();//���ձ�������
	
	private UserService service = new UserServiceImpl();
	
	//��¼����
	public String login() throws Exception{
		//��ȡϵͳ���ɵ���֤��
		//��session���ȡϵͳ���ɵ���֤��
		String sCode = (String)sessionMap.get("sCode");
			//�Ա�
		if(!user.getCode().trim().equals(sCode.trim())){
			//�׳�����
			super.addFieldError("user.code.invalid", super.getText("user.code.invalid"));
			return "login_input";
		}
		
		//��֤�û���
		Users loginUser = service.login(user.getName());
		
		if(loginUser!=null){
			//��֤����
			//�ȶ��û�������������md5����
			String digestPassword = MD5Util.md5(user.getPassword());
			//���ݿ��������û���������м��
			if(loginUser.getPassword().equals(digestPassword)){
				  //��¼�ɹ�
				//���û����ݱ��浽session��ȥ
				sessionMap.put("user", loginUser);
				//�����ǰsession����֤��
				sessionMap.remove("sCode");
				//ת����ҳ
				return "login";
			}else{
				//�׳�����
				super.addFieldError("user.password.invalid", super.getText("user.password.invalid"));
				return "login_input";
			}
		}else{
			//�׳�����
			super.addFieldError("user.name.invalid", super.getText("user.name.invalid"));
			return "login_input";
		}
	}
	
	//ע�᷽��
	public String register()throws Exception{
		//1)�õ�ϵͳ���ɵ���֤��
		String sCode = (String)sessionMap.get("sCode");
		//2)���û��������֤��Ա�
		if(!user.getCode().trim().equals(sCode)){
			super.addFieldError("user.code.invalid", super.getText("user.code.invalid"));
			return "reg_input";
		}
		//3)����ҵ�񷽷������û�
		service.register(user);
		
		//4)������ҳ
		return "reg_succ";
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public Users getModel() {
		return user;
	}
}
