package gz.itcast.b_ognl;

public class Book {
	private String name;
	private User user = new User("rose",20);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String name) {
		super();
		this.name = name;
	}
}
