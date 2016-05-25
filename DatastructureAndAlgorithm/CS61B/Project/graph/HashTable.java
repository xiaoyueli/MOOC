package graph;

public class HashTable {
	
	private List[] table;
	private int coe1, coe2;
	private int prime;
	private int buckets;
	private int amount;
	final double LOAD_FACTOR = 0.75;

	public HashTable() {
		// TODO Auto-generated constructor stub
		table = new List[3];
		buckets = table.length;
		iniCoefficient(buckets);
	}
	
	public HashTable(int s){
		buckets = nextPrime(s);
		table = new List[buckets];
		iniCoefficient(buckets);
	}
	
	public boolean isPrime(int num){
		if(num < 2){
			return false;
		}
		for(int divisor = 2; divisor < num; divisor ++){
			if(num % divisor == 0){
				return false;
			}
		}
		return true;
	}
	
	public int nextPrime(int num){
		num ++;
		while(!isPrime(num)){
			num ++;
		}
		return num;
	}
	
	public void iniCoefficient(int s){
		prime = s;
		for(int cnt = 0; cnt < 3; cnt ++){
			prime = nextPrime(prime);
		}
		do{
			coe1 = (int)(Math.random() * prime);
		}while(coe1 == 0);
		
		coe2 = (int)(Math.random() * prime);
		amount = 0;
	}
	
	public int getTCode(int hashcode){
		return Math.abs((coe1 * hashcode + coe2) % prime % buckets);
	}
	
	public int getAmount(){
		return amount;
	}
	
	public boolean isExist(Object obj){
		int tableCode = getTCode(obj.hashCode());
		List list = table[tableCode];
		if(list == null){
			return false;
		}
		ListNode node = list.front();
		while(node.isValid(list)){
			if(((ListNode)(node.item)).item.equals(obj)){
				return true;
			}
			node = node.next;
		}
		return false;
		
	}
	
	public void addItem(Object obj){
		if(!isExist(obj)){
			int tableCode = getTCode(obj.hashCode());
			List list = table[tableCode];
			if(list == null){
				list = new List();
				table[tableCode] = list;
			}
			ListNode node = new ListNode(obj);
			list.additem(node);
			amount ++;
		}
	}
	
	public void deleteItem(Object obj){
		int tCode = getTCode(obj.hashCode());
		List list = table[tCode];
		if(list != null){
			ListNode node = list.front();
			while(node.isValid(list)){
				if(((ListNode)(node.item)).item.equals(obj)){
					node.removed();
					amount --;
					break;
				}
				node = node.next;
			}

		}
	}
	
	public ListNode getNode(Object obj){
		if(isExist(obj)){
			int tableCode = getTCode(obj.hashCode());
			List list = table[tableCode];
			ListNode node = list.front();
			while(node.isValid(list)){
				if(((ListNode)(node.item)).item.equals(obj)){
					return (ListNode)(node.item);
				}
				node = node.next;
			}
		}
		
		return null;
	}
	
	public List reHashTable(List objs){
		
		if(1.0 * objs.getSize() / buckets > LOAD_FACTOR + 0.05 || 1.0 * objs.getSize() / buckets < LOAD_FACTOR - 0.05){
			int size = (int)(objs.getSize() / LOAD_FACTOR);
			buckets = nextPrime(size);
			iniCoefficient(buckets);
			table = new List[buckets];
			List temp = new List();
			ListNode node = objs.front();
			while(node.isValid(objs)){
				Object item = node.item;
				addItem(item);
				ListNode newNode = getNode(item);
				newNode.children = node.children;
				temp.addNode(newNode);
				node = node.next;
			}
			
			return temp;
		}
		return objs;
		
	}

}
