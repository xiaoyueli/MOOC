package javaSyntax;

public class Enumeration {
	
	public void print(Grade g){} 	//只能传入A,B,C,D,E
}

enum Grade{
	A("100-90"){
		public String localValue(){ //有抽象方法的枚举类需在对象中实现此抽象方法
			return "优";
		}
	},
	B("89-80"){
		public String localValue(){
			return "良";
		}
	},
	C("79-70"){
		public String localValue(){
			return "中";
		}
	},
	D("69-60"){
		public String localValue(){
			return "差";
		}
	},
	E("59-0"){
		public String localValue(){
			return "不合格";
		}
	};	

	private String value;
	private Grade(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public abstract String localValue(); 	//有抽象方法的枚举类
}
/*
class Grade{				//和enum等价的方法
	private Grade(){};		//private constructor，别人无法新建这个类
	public static final Grade A = new Grade();
	public static final Grade B = new Grade();
	public static final Grade C = new Grade();
	public static final Grade D = new Grade();
	public static final Grade E = new Grade();
	
}
*/