package gz.itcast.entity;
/**
 * Õº È∑÷¿‡
 * @author APPle
 *
 */
public class Types {
	private String id;
	private String name;
	private String descr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public String toString() {
		return "Types [descr=" + descr + ", id=" + id + ", name=" + name + "]";
	}
	
}
