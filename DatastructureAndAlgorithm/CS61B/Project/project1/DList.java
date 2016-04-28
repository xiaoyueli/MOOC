package project1;

public class DList {
	protected DListNode head;
	protected int size;
	
	DList(){
		head = new DListNode();
		head.next = head;
		head.pre = head;
		size = 0;
	}
	
	DList(Run node){
		this();
		head.next = new DListNode(node);
		head.next.next = head;
		head.next.pre = head;
		head.pre = head.next;
		size ++;
	}
	
	DList(Run front, Run rear){
		this(front);
		head.pre = new DListNode(rear);
		head.pre.pre = head.next;
		head.pre.next = head;
		head.next.next = head.pre;
		size ++;
	}
	
	void insertRear(Run item){
		DListNode temp = new DListNode(item);
		temp.pre = head.pre;
		temp.next = head;
		head.pre.next = temp;
		head.pre = temp;
		size ++;
	}
	
	void decrease(){
		size --;
	}
	
	void increase(){
		size ++;
	}
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("size: " + size + "\n");
		DListNode temp = head;
		int cnt = size;
		while(cnt != 0){
			str.append(temp.next.toString() + "\n");
			temp = temp.next;
			cnt --;
		}
		return str.toString();
	}
	
	public static void main(String[] args){

	}
	

}
