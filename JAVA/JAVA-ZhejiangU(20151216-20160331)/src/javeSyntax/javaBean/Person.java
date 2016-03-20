package javeSyntax.javaBean;

public class Person {	//javaBean只每一个封装数据的类，一个Person类就是一个javaBean
	private String name;
	private int age;
	
	//拥有getter and setter方法的字段都是bean的属性
	//由于每一个class都继承于Object类（getClass()），因此有隐藏class属性
	//此处Person 有3个属性值， name age class
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
