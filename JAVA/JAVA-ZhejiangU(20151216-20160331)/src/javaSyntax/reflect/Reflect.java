package javaSyntax.reflect;

public class Reflect {
		//反射技术是用于写框架的
		//反射第一步，加载类，三种方法获得类的字节码
	public static void main(String[] args) throws ClassNotFoundException {
		// method1,
		Class class1 = Class.forName("/Syntax/src/Syntax/Person");
		
		// method2,
		Class class2 = new Person().getClass();
		
		// method3,
		Class class3 =Person.class;
	}

		//class对象提供的常用方法
		// 1, getConstructor()	获得构造函数public
		// 2, getMethod()		获得成员方法public
		// 3, getField()		获得成员变量public
	
		// 1, getDeclaredConstructor()	获得private构造函数
		// 2, getDeclaredConstructor()	获得private成员方法
		// 3, getDeclaredConstructor() 	获得private成员变量
	
}


