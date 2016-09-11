package GoToSiliconValley;


public class Enemy extends Role {
	private String name;
	private int healLife;
	public Enemy() {
		// TODO Auto-generated constructor stub
	}

	public Enemy(int level, String name,int money, int life, int defense, int strength) {
		super.setProperty(level, money, life, defense, strength);
		this.name = name;
		healLife = life;
	}
	
	public String toString()
	{
		return name;
	}

	@Override
	public void getStatus() {
		System.out.println("[Level: "+level+"\tLife: "+life+"\tDefense: "+defense+"\tStrength: "+strength+"\tMoney: "+money+"$]");
	}
	
	public void recover()
	{
		life = healLife;
	}
}
