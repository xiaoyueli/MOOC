package homework9;

public class Item {
	
	private int row;
	private int col;
	private int idx;
	
	public Item(int x, int y, int idx){
		row = x;
		col = y;
		this.idx = idx;
	}
	
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}

	public int getIdx(){
		return idx;
	}
	
	public void setIdx(int value){
		idx = value;
	}
}
