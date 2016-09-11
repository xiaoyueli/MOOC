package GoToSiliconValley;


public class Role {
	protected String name;
	protected int money;
	protected int life;
	protected int strength;
	protected int defense;
	protected int level;
	private int exp = 0;
	public Role() {
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setProperty(int level, int money, int life, int defense, int strength)
	{
		this.money = money;
		this.life = life;
		this.defense = defense;
		this.strength = strength;
		this.level = level;
	}
	
	public void addMoney(int amount)
	{
		money = money + amount;
	}
	
	public void loseMoney(int amount)
	{
		money = money - amount;
	}
	public int getMoney()
	{
		return money;
	}
	
	
	public void addLife(int amount)
	{
		life = life + amount;
	}
	
	public void loseLife(int amount)
	{
		life = life - amount;
	}
	
	public int getLife()
	{
		return life;
	}
	
	
	public void addStrength(int amount)
	{
		strength = strength + amount;
	}
	
	
	public int getStrength()
	{
		return strength;
	}
	

	public void addDefense(int amount)
	{
		defense = defense + amount;
	}
	
	
	public int getDefense()
	{
		return defense;
	}
	
	public void addLevel()
	{
		
		level = level + 1;
		System.out.println("You are up to:\tLevel "+level);
	
	}
	
	public void levelUp()
	{
		boolean sign = false;
	
		if(level >=10)
		{
			level = 10;
			exp= 0;
		}else
		{
			if(exp == 10)
			{
				sign = true;
				exp = 0;
			}
			else exp ++;
			if(sign)
			addLevel();
		}
	}
	public int getLevel()
	{
		return level;
	}
	
	public void getStatus()
	{
		System.out.println("====================================");
		System.out.println("Your status:");
		System.out.println(name+"\tLevel: "+level+" Exp: "+exp*10+"/100 $: "+money);
		System.out.println("Life: "+life+" Strength: "+strength+" Defense: "+defense);
		System.out.println("====================================");
	}
	
	public boolean isDie()
	{
		boolean isdie = false;
		if(life <= 0)
		{
			isdie = true;
		}
		return isdie;
	}
	
	public boolean reBorn()
	{
		boolean reborn = false;
		if(this.isDie())
		{
			life = 20;
			reborn = true;
		}
		return reborn;
		
		
	}
}
