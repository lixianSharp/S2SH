package cn.itcast.e_component;
/**
 * 组建映射：
 * 	对象之间的关系：
 * 		组合关系：一个类中包含对另外一个类的引用
 * 		继承关系：一个类继承另外一个类
 * 	映射：
 * 		组合关系的映射就是"组件映射"
 * 		继承关系的映射就是"继承映射"
 * ·
 * ··	组件类和包含的组件类同时映射到一个表
 * @author 贤元
 *
 */
//需求：汽车与车轮
//汽车
public class Car {
	private int id;
	private String type;//汽车的类型，也就是汽车的品牌
	
	//车轮	组合关系：一个类中包含对另外一个类的引用
	//汽车与车轮的关系：组合关系
	private Wheel wheel;
	public Wheel getWheel() {
		return wheel;
	}
	
	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
