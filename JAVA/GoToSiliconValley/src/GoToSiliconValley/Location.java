package GoToSiliconValley;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	private HashMap<String, Location> exits= new HashMap<String, Location>();
	private String name;
	public Location(String name)
	{
		this.name= name;
	}
	
	public void setExit(String dir, Location name)
	{
		exits.put(dir, name);
	}
	
	public ArrayList<String> getExits()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(String s: exits.keySet())
		{
			list.add(s);	
		}
		return list;
	}
	
	public Location getLocation(String dir)
	{
		return exits.get(dir);
	}	
	
	public String toString()
	{
		return name;
	}
}
