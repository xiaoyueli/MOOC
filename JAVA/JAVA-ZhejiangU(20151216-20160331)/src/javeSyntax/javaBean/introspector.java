package javeSyntax.javaBean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;


//使用内省API操作bean属性
public class introspector {
	
	@Test
	public void demo() throws Exception
	{
		//获得bean的所有属性
		BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);	//第二个参数为需排除在外的属性，此处为排除父类Object属性
		PropertyDescriptor[] pds = info.getPropertyDescriptors();
		for(PropertyDescriptor p : pds)
		{
			System.out.println(p.getName());
		}
	}

	public void demo2() throws Exception
	{
		Person p = new Person();
		//操作特定属性
		PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
		
		//操作写方法
		Method m = pd.getWriteMethod();	//public void setAge(int age)
		m.invoke(p, 45);
		
		//操作读方法
		m = pd.getReadMethod(); 	//public int getAge()
		m.invoke(p, null);
		
		//获得属性类型
		System.out.println(pd.getPropertyType());	
	}
}
