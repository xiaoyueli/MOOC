package graph;

public class List {
	
	ListNode head;
	private int size;
	public List() {
		// TODO Auto-generated constructor stub
		head = new ListNode();
		size = 0;
		
	}
	
	public ListNode front(){
		if(size == 0){
			return null;
		}
		return head.next;
	}
	
	public int getSize(){
		return size;
	}
	
	public void additem(Object obj){
		if(size == 0){
			head.next = new ListNode(head, head, obj, this);
			head.pre = head.next;
		}
		else{
			head.next = new ListNode(head, head.next, obj, this);	
			head.next.next.pre = head.next;
		}
		size ++;
	}
	
	public void addNode(ListNode node){
		
		if(size == 0){
			head.next = node;
			head.pre = node;
			node.next = head;
			node.pre = head;
		}
		else{
			node.pre = head;
			node.next = head.next;
			node.next.pre = node;
			node.pre.next = node;
		}
		node.list = this;
		size ++;
	}
	
	
	public void deleteNode(ListNode node){
		if(node.isValid(this)){
			node.removed();
			size --;
		}

	}
	
	public String toString(){
		ListNode node = head.next;
		StringBuffer sb = new StringBuffer("[ ");
		while(node.isValid(this)){
			sb.append(node.item + " ");
			node = node.next;
		}
		sb.append("]");
		
		return sb.toString();
	}

}
