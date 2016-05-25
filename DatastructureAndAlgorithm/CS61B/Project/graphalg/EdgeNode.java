package graphalg;

public class EdgeNode {

	private Object ver1;
	private Object ver2;
	private int weight;
	public EdgeNode(Object v1, Object v2, int wei) {
		// TODO Auto-generated constructor stub
		ver1 = v1;
		ver2 = v2;
		weight = wei;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public Object ver1(){
		return ver1;
	}
	
	public Object ver2(){
		return ver2;
	}
	
	public String toString(){
		return "[ " + ver1.toString() + "/" + ver2.toString() + " : " + weight + " ]";
	}

}
