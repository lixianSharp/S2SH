package gz.itcast.f_data;

import java.util.Arrays;

public class User {
	private String name;
	private String password;
	private String gender;
	private String[] hobit;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String[] getHobit() {
		return hobit;
	}
	public void setHobit(String[] hobit) {
		this.hobit = hobit;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", gender="
				+ gender + ", hobit=" + Arrays.toString(hobit) + "]";
	}
	
	
}
