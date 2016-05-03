package homework4;

public class LockDList extends DList {

	public LockDList() {
		// TODO Auto-generated constructor stub
		lockNode(head);
	}
	
	public void lockNode(DListNode node){
		((LockDListNode)node).setLocked();
	}

	@Override
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		// TODO Auto-generated method stub
		return new LockDListNode(item, (LockDListNode)prev, (LockDListNode)next);
	}

	@Override
	public void remove(DListNode node) {
		// TODO Auto-generated method stub
		if(((LockDListNode)node).getStatus() != true){
			super.remove(node);
		}
	}
	
}
