package project1;

public class Run {
	Pixel item;
	int size;
	
	Run(){
		item = null;
		size = 0;
	}
	
	Run(Pixel item){
		this.item = item;
		size = 1;
	}
	
	Run(Pixel item, int size){
		this.item = item;
		this.size = size;
	}
	
	void insertItem(Pixel item){
		this.item = item;
		size ++;
	}
	
	void updateItem(Pixel item){
		this.item = item;
	}
	
	void increase(){
		size ++;
	}
	
	void decrease(){
		size --;
	}
	
	void setSize(int size){
		this.size = size;
	}

	public String toString(){
		return item.toString() + ": " + size;
	}
}
