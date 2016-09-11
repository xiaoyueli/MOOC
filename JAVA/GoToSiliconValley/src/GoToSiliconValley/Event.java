package GoToSiliconValley;

import java.util.ArrayList;


public class Event {
	private boolean canGoSign;
	public Event() {

	}
	
	public Enemy meetEnemy(ArrayList<Enemy> enemyList, ArrayList<Location> locaList, Location loca)
	{
		Enemy newEnemy = new Enemy();
		int caseNum = 0;
		if(locaList.indexOf(loca)<7 && locaList.indexOf(loca) > 0)
			caseNum = 0;
		else if(locaList.indexOf(loca)<14)
			caseNum = 1;
		else if (locaList.indexOf(loca)<21)
			caseNum = 2;
		else if (locaList.indexOf(loca)<28)
			caseNum = 3;
		else if (locaList.indexOf(loca)<35)
			caseNum = 4;
		else if (locaList.indexOf(loca)<42)
			caseNum = 5;
		else if (locaList.indexOf(loca)<50)
			caseNum = 6;
		else caseNum = 7;
		
		if(caseNum <7 && locaList.indexOf(loca) > 0)
			newEnemy = enemyList.get((int)(Math.random()*3+caseNum*3+1));
		else newEnemy = null;
		return newEnemy;
	}
	
	public void battle(Role player, Enemy enemy)
	{	
		while(!player.isDie() && !enemy.isDie())
		{
			int hurtP = (int)(enemy.getStrength() - player.getDefense()*1.0/enemy.getLevel());
			int hurtE = (int)(player.getStrength()-enemy.getDefense()*1.0/player.getLevel());
			if(hurtP < 0)
				hurtP = 1;
			if(hurtE < 0)
				hurtE = 1;
			enemy.loseLife(hurtE);
			System.out.println(enemy.toString()+" got hurt: -"+hurtE+"\t\tlife: "+enemy.getLife()+" left");
			delay(50);
			if(!enemy.isDie())
			{
				player.loseLife(hurtP);
				System.out.println("You got hurt: -"+hurtP+"\t\tlife: "+player.getLife()+" left");
				delay(50);
			}
		}
		if(enemy.isDie())
		{	
			canGoSign = true;
			System.out.println(enemy.toString()+" died, You've got "+enemy.getMoney()+"$.");
			player.addMoney(enemy.getMoney());
			player.levelUp();
			enemy.recover();
			System.out.println();

		}
		else if(player.isDie())
		{
			System.out.println("You died.");
			System.out.println();
			delay(3000);
		}
		
		
	}
		public void resetCanGo()
		{
			canGoSign = false;
		}
		
		public boolean canGo()
		{
			return canGoSign;
		}
	
    public void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }
	
}
