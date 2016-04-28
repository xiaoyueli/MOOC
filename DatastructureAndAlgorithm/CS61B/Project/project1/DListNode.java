package project1;

public class DListNode {
	public Run item;
	public DListNode pre;
	public DListNode next;
	
	DListNode(Run item){
		this.item = item;
		pre = null;
		next = null;
	}
	
	DListNode(){
		item = null;
		pre = null;
		next = null;
	}
	
	public String toString(){
		if(item == null){
			return "null";
		}
		return item.toString();
	}

}
