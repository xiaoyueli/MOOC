package lab13;

public class QList{
	
	private Node head;
	private Node rear;
	private int size;
	
	public QList() {
		// TODO Auto-generated constructor stub
		head = new Node();
		size = 0;
	}
	
	public QList(int v){
		this();
		head.next = new Node(v);
		rear = head.next;
		size = 1;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public void add(int v){
		if(size == 0){
			head.next = new Node(v);
			rear = head.next;
			
		}
		else{
			rear.next = new Node(v);
			rear = rear.next;
		}
		size ++;
	}
	
	public int pop(){
		Node node;
		if(size == 0){
			return -1;
		}
		else if(size == 1){
			node = head.next;
			head.next = null;
			rear = null;
		}
		else{
			node = head.next;
			head.next = head.next.next;
		}
		node.next = null;
		size --;
		return node.ver;
		
	}

}
