package graphalg;

public class EdgeList {
	
	private EdgeNode[] list;;
	private int amount;
 	public EdgeList(int size) {
		// TODO Auto-generated constructor stub
 		list = new EdgeNode[size + 1];
 		amount = 0;
	}
 	
 	public void insertItem(EdgeNode node){
 		if(amount + 1 < list.length ){
 			list[ ++ amount] = node;
 			int idx = amount;
 			int parent_idx = idx / 2;
 			while(parent_idx > 0){
 				if(list[parent_idx].getWeight() > node.getWeight()){
 					list[idx] = list[parent_idx];
 					idx = parent_idx;
 					parent_idx = idx / 2; 
 				}
 				else{
 					break;
 				}
 			}
 			list[idx] = node;
 		}

 		
 	}
 	
 	public EdgeNode deleteMin(int root){
 		if(amount == 0 || root > amount){
 			return null;
 		}
 		EdgeNode edge = list[root];
 		EdgeNode last = list[amount];
 		amount --;
 		int son_idx = root * 2;
 		while(son_idx <= amount){
 			if(son_idx < amount && list[son_idx].getWeight() > list[son_idx + 1].getWeight()){
 				son_idx ++;
 			}
 			if(list[son_idx].getWeight() < last.getWeight()){
 				list[root] = list[son_idx];
 				root = son_idx;
 				son_idx *= 2; 
 			}
 			else{
 				break;
 			}
 		}
 		list[root] = last;
 		return edge;
 	}
 	
 	public boolean isEmpty(){
 		return amount == 0;
 	}
 	
 	public void printOut(){
 		for(int idx = 0; idx < list.length; idx ++){
 			System.out.println(list[idx]);
 		}
 		System.out.println();
 	}
}
