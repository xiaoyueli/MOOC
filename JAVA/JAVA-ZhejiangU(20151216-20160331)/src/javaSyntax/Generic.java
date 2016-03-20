package javaSyntax;

//泛型类可以传入任意类型的变量
public class Generic <G> {	//定义在类上的泛型，整个类内有效
	
	public <T> void run(G num, T times){}	//定义在方法上的泛型
	
	public static <G> void jump(G num){}	//定义在类上的泛型对静态方法无效，需单独定义
}
