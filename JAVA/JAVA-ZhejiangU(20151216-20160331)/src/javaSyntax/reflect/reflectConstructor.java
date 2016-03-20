package javaSyntax.reflect;

import java.lang.reflect.Constructor;


public class reflectConstructor {
		//解析出构造函数创建对象
	public static void main(String[] args) throws Exception {
		Class clazz = Class.forName("/Syntax/src/Syntax/Person");
		Constructor c = clazz.getConstructor(String.class); //需传入要获得的构造函数的参数
//		Constructor c = clazz.getDeclaredConstructor(String.class); 	//获取private 构造函数
//		c.setAccessible(true);		//为private构造函数设置可见后就可以在原类以外生成对象
		Person p = (Person)c.newInstance("name"); //创建Enumeration的对象e
//		Person p = (Person)clazz.newInstance(); 	//直接用clazz生成的对象使用的是无参数的构造函数
	}

}
