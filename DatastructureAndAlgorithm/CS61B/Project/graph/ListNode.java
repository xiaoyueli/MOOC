package graph;

public class ListNode {
	
	Object item;
	ListNode pre;
	ListNode next;
	List list;
	List children;
	ListNode copy1;
	ListNode copy2;
	
	public ListNode() {
		// TODO Auto-generated constructor stub
		item = null;
		pre = null;
		next = null;
		list = null;
		children = null;
		copy1 = null;
		copy2 = null;
	}
	
	public ListNode(Object obj){
		this();
		item = obj;
	}
	
	public ListNode(Object obj, List lst){
		this();
		item = obj;
		list = lst;
	}
	
	public ListNode(ListNode pre, ListNode next, Object obj, List lst){
		this(obj, lst);
		this.pre = pre;
		this.next = next;
	}
	
	public boolean isValid(List lst){
		return list == lst;
	}
	
	public void removed(){

		pre.next = next;
		next.pre = pre;
		list = null;
	}
	
	public void addChild(ListNode node){
		if(children == null){
			children = new List();
		}
		children.addNode(node);
	}
	
	public void removeChild(ListNode node){
		children.deleteNode(node);

	}
	
	public int childNum(){
		if(children != null)
			return children.getSize();
		else{
			return 0;
		}
	}
	
	public void buildCopy(int num){

		copy1 = new ListNode(this);
		copy1.item = item;
		if(num == 2){
			copy2 = new ListNode(this);
			copy2.item = item;
		}

	}
	
	public ListNode getCopy1(){
		return copy1;
	}
	
	public ListNode getCopy2(){
		return copy2;
	}
	
	public void setParent(List lst){
		list = lst;
	}
	
	public List getChildren(){
		return children;
	}
	
	public String toString(){
		if(item == null){
			return "null";
		}
		return item.toString();
	}

}
