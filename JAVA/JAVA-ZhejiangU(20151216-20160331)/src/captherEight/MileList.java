package captherEight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MileList {
	
	private ArrayList<String> cities = new ArrayList<String>();
	private HashMap<Integer, ArrayList<Integer>> miles = new HashMap<Integer, ArrayList<Integer>>();
	private int total = 0;
	private Scanner in = new Scanner(System.in);
	public void setCities()
	{
		
		String name = in.next();
		while(!name.equals("###")){
			cities.add(name);
			total ++;
			name = in.next();
		}
	}
	
	public void setMiles()
	{
		for(int i = 0; i < total; i ++)
		{
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < total; j++)
			{
				list.add(in.nextInt());
			}
			miles.put(i, list);
		}
	}
	
	public void printDistance()
	{
		
		int indexO = cities.indexOf(in.next());
		int indexT = cities.indexOf(in.next());
		System.out.println(miles.get(indexO).get(indexT));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MileList list = new MileList();
		list.setCities();
		list.setMiles();
		list.printDistance();
		
	}

}
