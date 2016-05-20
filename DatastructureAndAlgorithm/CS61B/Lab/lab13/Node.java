package lab13;

public class Node {
	
	int ver;
	Node next;

	public Node(int v, Node n) {
		// TODO Auto-generated constructor stub
		ver = v;
		next = n;
	}
	
	public Node(int v){
		ver = v;
		next = null;
	}
	
	public Node(){
		ver = -1;
		next = null;
	}

}
