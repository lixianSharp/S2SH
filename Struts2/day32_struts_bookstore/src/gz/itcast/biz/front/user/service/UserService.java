package gz.itcast.biz.front.user.service;

import gz.itcast.entity.Users;

public interface UserService {
	public Users login(String name);
	public void register(Users user); 
}
