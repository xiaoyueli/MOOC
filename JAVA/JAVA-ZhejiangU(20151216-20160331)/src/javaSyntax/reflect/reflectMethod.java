package javaSyntax.reflect;

import java.lang.reflect.Method;

public class reflectMethod {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class clazz = Class.forName("/Syntax/src/Syntax/reflect/Person");
		Person p = (Person) clazz.newInstance();
		
		//public void test1(String name)
		Method m = clazz.getMethod("test1", String.class);
		m.invoke(p, "kitty");
		
		//private int test2()
		Method m2 = clazz.getDeclaredMethod("test2", null);		//无参数传入null
		m2.setAccessible(true);									//使私有方法可见
		m2.invoke(p, null);
		
		//public static void test3()
		Method m3 = clazz.getMethod("test3", null);
		m3.invoke(null, null);			//静态方法可传可不传入对象，
		
		//public static void main(String[] args)
		Method m4 = clazz.getMethod("main", String[].class);
//		m4.invoke(null, new Object[]{new String[]{"11","22"}});		//参数是数组会被自动分解，如果需要数组参数需包装在另一层数组内，数组是object所以可以包装在Object数组内
		m4.invoke(null, (Object)new String[]{"11","22"});
	}

}
