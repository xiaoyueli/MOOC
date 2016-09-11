package GoToSiliconValley;

public class Products {
	private String name;
	private int price;
	private String effectName;
	private int effectAmount;
	Products(String name, String effectName, int effectAmount, int price)
	{
		this.name = name;
		this.price = price;
		this.effectName = effectName;
		this.effectAmount = effectAmount;
	}

	
	public String toString()
	{
		StringBuffer s = new StringBuffer();
		s.append(name);
		s.append("\t");
		s.append(effectName);
		s.append(" + ");
		s.append(effectAmount);
		s.append(":\t");
		s.append(price);
		s.append("$");
		return s.toString();
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getEffect()
	{
		return effectName;
	}
	
	public int getEffAmount()
	{
		return effectAmount;
	}
	
	public int getPrice()
	{
		return price;
	}
	
}
