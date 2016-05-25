package graphalg;

import graph.List;

public class HashTable {

	private Object[] table;
	private int coe1, coe2;
	private int prime;
	private int buckets;
	final double LOAD_FACTOR = 0.75;
	
	public HashTable(int size){
		buckets = (int)(size / LOAD_FACTOR);
		buckets = nextPrime(buckets);
		table = new Object[buckets];
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
	}
	
	public int getTCode(int hashcode){
		return Math.abs((coe1 * hashcode + coe2) % prime % buckets);
	}
	
	public void addItem(Object obj){
		int tCode = getTCode(obj.hashCode());
		if(table[tCode] == null){
			table[tCode] = obj;
		}
		else{
			while(table[tCode] != null){
				tCode ++;
				if(table[tCode] == null){
					table[tCode] = obj;
					break;
				}
			}
		}
	}
	
	public int returnTCode(Object obj){
		int tCode = getTCode(obj.hashCode());
		if(table[tCode].equals(obj)){
			return tCode;
		}
		while(!table[tCode].equals(obj)){
			tCode ++;
			if(tCode >= buckets){
				tCode -= buckets;
			}
		}
		return tCode;
	}
	
	public int getBuckets(){
		return buckets;
	}

}
