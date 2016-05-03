package homework4;

public class LockDListNode extends DListNode {
	
	private boolean locked;

	public LockDListNode(Object i, LockDListNode p, LockDListNode n) {
		super(i, p, n);
		// TODO Auto-generated constructor stub
		locked = false;
	}
	
	final void setLocked(){
		locked = true;
	}
	
	protected boolean getStatus(){
		return locked;
	}

}
