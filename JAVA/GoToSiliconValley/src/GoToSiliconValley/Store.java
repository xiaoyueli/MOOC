package GoToSiliconValley;


import java.util.ArrayList;
import java.util.HashMap;

public class Store extends Location {

	private ArrayList<Products> list = new ArrayList<Products>();
	private ArrayList<Products> temList;
	private ArrayList<Products> newList = new ArrayList<Products>();
	private HashMap<String, Integer> transfer = new HashMap<String, Integer>();
	public Store(String storeName) {
		super(storeName);
	}

	public void addProduct(Products name)
	{
		list.add(name);
	}
	
	public void showProducts()
	{
		System.out.println("Welcome to "+super.toString()+"!");
		if(list.size()<9)
		{
			newList = list;
			System.out.println("We have:");
		}
		else
		{
			newList = temList;
			System.out.println("We provide abundant products, and they are different every day.");
			System.out.println("Today, we have:");
		}
		System.out.println("************************************");
		char cnt ='a';
		String index;
		for(int i = 0; i < newList.size(); i ++)
		{
			System.out.println("* "+cnt+": "+newList.get(i)+" *");
			index = String.valueOf(cnt);
			transfer.put(index, i);
			cnt++;
		}
		System.out.println("************************************");
	}
	
	public void prepareProducts()
	{
		temList = new ArrayList<Products>();
		for(int i = 0; i < list.size(); i ++)
		{
			if(Math.random() < 0.5)
			{
				temList.add(list.get(i));
			}
		}
	}
	
	public Products getProduct(String index)
	{
		Products p = null;
		if(transfer.get(index)!=null)
		{
			p = newList.get(transfer.get(index));
		}
		return p;
	}
}
