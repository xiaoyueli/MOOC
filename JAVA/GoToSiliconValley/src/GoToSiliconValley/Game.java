package GoToSiliconValley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Game {
	private Location currentLoca, lastLocation, birthLocation ;
	private Event currentEvent = new Event(); 
	private HashMap<String, orderList> orders= new HashMap<String, orderList>();
	private HashMap<String, ArrayList<String>> orderLists =new HashMap<String, ArrayList<String>>();
	private HashMap<String, Store> storeList =new HashMap<String, Store>();
	private ArrayList<Location> locationList= new ArrayList<Location>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private HashMap<String, Enemy> currentEnemy = new HashMap<String, Enemy>();
	private Role player = new Role();
	private String guide = "Xiaoyue";
	private boolean enemySign = false; 
	private boolean movingShopSign = false;
	private Scanner in = new Scanner(System.in);
	 

	
	private class orderList
	{
		public void doCmd(String str){}
		public boolean isBye()
		{
			return false;
		}	
		 
	}

	public Game()
	{
		creatMaps();
		creatEnemy();
		creatOrders();	
	}
	
	public void creatOrders()
	{
		orders.put("help", new orderList(){

			@Override
			public void doCmd(String str) {
				System.out.println("Instruction:");
				System.out.println("e.g.\tgo east");
				System.out.println("e.g.\tatk a");
				System.out.println("e.g.\tbuy a");
				System.out.println("e.g.\tback");
				System.out.println("e.g.\tbye");
				System.out.println();
				
			}
			
		});
		orders.put("bye", new orderList(){

			@Override
			public boolean isBye() {
				return true;
			}
			
		});
		orders.put("go", new orderList(){

			@Override
			public void doCmd(String str) {
				goLocation(str);
			}
			
		});
		
		orders.put("back", new orderList(){

			@Override
			public void doCmd(String str) {
				Location mid = currentLoca;
				currentLoca = lastLocation;
				lastLocation = mid;
				enemySign = false;				
			}	
			
		});
		
		orders.put("buy", new orderList(){
			
			public void doCmd(String str) {
				Store currentStore = (Store)currentLoca;
				Products p =currentStore.getProduct(str);
				if(p == null)
				{
					error();
				}
				else
				{
					if(p.getPrice()>player.getMoney())
					{
						System.out.println("=============================");
						System.out.println("You don't have enough money!");
						System.out.println("=============================");
					}
					else
					{
						System.out.println("====================================");
						System.out.println("* "+p+" *");
						System.out.println("====================================");
						switch(p.getEffect())
						{
							case "Life" :{	
									player.loseMoney(p.getPrice());
									player.addLife(p.getEffAmount());
									break;
							}
							case "Defense" :{
								player.loseMoney(p.getPrice());
								player.addDefense(p.getEffAmount()); 					
								break;
							}
							case "Strength" :{
								player.loseMoney(p.getPrice());
								player.addStrength(p.getEffAmount());					
								break;
							}
						}	
					}

				}
				
			}		
		});
		
		orders.put("atk", new orderList(){

			@Override
			public void doCmd(String str) {
				Enemy enemy = currentEnemy.get(str);
				if(enemy == null)
				{
					error();
				}
				else
				{	
					currentEvent.battle(player, enemy);
					if(player.reBorn())
					{
						currentLoca = birthLocation;
						enemySign = false;
								
					}
				}
			}	
			
		});
		
		ArrayList<String> regularOrders=new ArrayList<String>();	
		ArrayList<String> shopOrders= new ArrayList<String>();
		ArrayList<String> fightOrders= new ArrayList<String>();
		ArrayList<String> endOrders= new ArrayList<String>(); 
			
		regularOrders.add("go");
		regularOrders.add("bye");
		regularOrders.add("help");
	
		shopOrders.add("go");
		shopOrders.add("buy");
		shopOrders.add("help");
		
		fightOrders.add("atk");
		fightOrders.add("go");
		fightOrders.add("back");
		fightOrders.add("bye");
		fightOrders.add("help");
		
		endOrders.add("bye");

		orderLists.put("regularOrders",regularOrders);
		orderLists.put("shopOrders",shopOrders);
		orderLists.put("fightOrders", fightOrders);
		orderLists.put("endOrders", endOrders);
	}
	
	public void creatPlayer()
	{
		
		System.out.println("**************************************************************************");
		System.out.println("**************************************************************************");
		System.out.println();
		System.out.println("Let's creat a player first.");
		System.out.print("Name:\t");
		
		player.setName(in.nextLine());
		player.setProperty(1, 10, 20, 1, 4);
		System.out.println();

		
	}
	
	public void creatEnemy()
	{
		enemyList.add(new Enemy(14, "[BOSS] Ghost of laziness", 0, 400, 23, 37));
		enemyList.add(new Enemy(10, "Guard", 29, 330, 15, 29));
		enemyList.add(new Enemy(10, "Warden", 29, 305, 14, 29));
		enemyList.add(new Enemy(9, "DrakeRider", 23, 270, 12, 23));
		enemyList.add(new Enemy(9, "Champion", 23, 255, 11, 23));
		enemyList.add(new Enemy(8, "Warrior", 19, 220, 10, 19));
		enemyList.add(new Enemy(8, "HillFox", 19, 205, 9, 19));
		enemyList.add(new Enemy(7, "Murk-Eye", 17, 185, 9, 17));
		enemyList.add(new Enemy(7, "Thug", 17, 175, 8, 17));
		enemyList.add(new Enemy(6, "Bandit", 13, 150, 7, 13));
		enemyList.add(new Enemy(6, "Watcher", 13, 135, 6, 13));
		enemyList.add(new Enemy(5, "Fleshripper", 11, 110, 6, 11));
		enemyList.add(new Enemy(5, "Coyote", 11, 100, 5, 11));
		enemyList.add(new Enemy(5, "Shaman", 11, 90, 5, 11));
		enemyList.add(new Enemy(4, "Lurker", 9, 75, 4, 7));
		enemyList.add(new Enemy(4, "Goldtooth", 9, 70, 3, 7));
		enemyList.add(new Enemy(4, "Spider", 9, 65, 3, 7));
		enemyList.add(new Enemy(3, "Miner", 7, 50, 3, 5));
		enemyList.add(new Enemy(3, "Cutpurse", 7, 45, 2, 5));
		enemyList.add(new Enemy(2, "Invader", 5, 25, 2, 3));
		enemyList.add(new Enemy(2, "Goblin", 5, 20, 2, 3));
		enemyList.add(new Enemy(1 ,"Spy", 4, 15, 1, 2));
		enemyList.add(new Enemy(1 ,"Thief", 3, 10, 1, 2));
		
	}

	public void creatMaps() {
		
		Store movingStore = new Store("MovingStore");
		Store NYAirportStore = new Store("NYAirportStore");
		Store FloridaPortStore = new Store("FloridaPortStore");
		Store LouisianaPortStore = new Store("LouisianaPortStore");
		Store ArizonaPortStore = new Store("ArizonaPortStore");
		
		Products apple = new Products("Apple", "Life", 10, 3);
		Products pear = new Products("Pear", "Life", 50, 10);
		Products orange = new Products("Orange", "Life", 150, 25);
		Products shirt = new Products("Shirt", "Defense", 1, 10);
		Products jacket = new Products("Jacket", "Defense", 3, 25);
		Products cloak = new Products("Cloak", "Defense", 5, 35);
		Products bun = new Products("Bun", "Strength", 1, 10);
		Products rice = new Products("Rice", "Strength", 3, 25);
		Products noodle = new Products("Noodle", "Strength", 5, 35);
		
		movingStore.addProduct(apple);
		movingStore.addProduct(pear);
		movingStore.addProduct(orange);
		movingStore.addProduct(shirt);
		movingStore.addProduct(jacket);
		movingStore.addProduct(cloak);
		movingStore.addProduct(bun);
		movingStore.addProduct(rice);
		movingStore.addProduct(noodle);
		
		NYAirportStore.addProduct(apple);
		NYAirportStore.addProduct(shirt);
		NYAirportStore.addProduct(bun);

		FloridaPortStore.addProduct(pear);
		FloridaPortStore.addProduct(jacket);
		FloridaPortStore.addProduct(rice);

		LouisianaPortStore.addProduct(pear);
		LouisianaPortStore.addProduct(jacket);
		LouisianaPortStore.addProduct(rice);

		ArizonaPortStore.addProduct(orange);
		ArizonaPortStore.addProduct(cloak);
		ArizonaPortStore.addProduct(noodle);
		
		storeList.put("MovingStore", movingStore);
		storeList.put("NYAirportStore", NYAirportStore);
		storeList.put("FloridaPortStore", FloridaPortStore);
		storeList.put("LouisianaPortStore", LouisianaPortStore);
		storeList.put("ArizonaPortStore", ArizonaPortStore);
		
		Location Washington= new Location("Washington");
		Location Oregon= new Location("Oregon");
		Location California= new Location("California");
		Location Nevada= new Location("Nevada");
		Location Arizona= new Location("Arizona");
		Location Alaska= new Location("Alaska");
		Location Utah= new Location("Utah");
		Location Idaho= new Location("Idaho");
		Location Montana= new Location("Montana");
		Location Wyoming= new Location("Wyoming");
		Location Colorado= new Location("Colorado");
		Location New_Mexico= new Location("New Mexico");
		Location Texas= new Location("Texas");
		Location Oklahoma= new Location("Oklahoma");
		Location Kansas= new Location("Kansas");
		Location Nebraska= new Location("Nebraska");
		Location South_Dakota= new Location("South Dakota");
		Location North_Dakota= new Location("North Dakota");
		Location Minnesota= new Location("Minnesota");
		Location Iowa= new Location("Iowa");
		Location Missouri= new Location("Missouri");
		Location Arkansas= new Location("Arkansas");
		Location Louisiana= new Location("Louisiana");
		Location Hawaii= new Location("Hawaii");
		Location Mississippi= new Location("Mississippi");
		Location Illinois= new Location("Illinois");
		Location Wisconsia= new Location("Wisconsia");
		Location Michigan= new Location("Michigan");
		Location Indiana= new Location("Indiana");
		Location Kentucky= new Location("Kentucky");
		Location Tennessee= new Location("Tennessee");
		Location Alabama= new Location("Alabama");
		Location Georgia= new Location("Georgia");
		Location Florida= new Location("Florida");
		Location South_Carolina= new Location("South Carolina");
		Location North_Carolina= new Location("North Carolina");
		Location Virginia= new Location("Virginia");
		Location West_Virginia= new Location("West Virginia");
		Location Ohio= new Location("Ohio");
		Location Pennsyivania= new Location("Pennsyivania");
		Location Delaware= new Location("Delaware");
		Location Maryland= new Location("Maryland");
		Location New_Jersey= new Location("New Jersey");
		Location New_York= new Location("New York");
		Location Connecticut= new Location("Connecticut");
		Location Rhode_Island= new Location("Rhode Island");
		Location Massachusetts= new Location("Massachusetts");
		Location Vermont= new Location("Vermont");
		Location New_Hampshire= new Location("New Hampshire");
		Location Maine= new Location("Maine");
		Location Arizona_Port= new Location("Arizona Port");
		Location Alaska_Port_North= new Location("Alaska North Port");
		Location Alaska_Port_East= new Location("Alaska East Port");
		Location Louisiana_Port= new Location("Louisiana Port");
		Location Hawaii_Port_West= new Location("Hawaii West Port");
		Location Hawaii_Port_North= new Location("Hawaii North Port");
		Location Hawaii_Port_East= new Location("Hawaii East Port");
		Location NyAirPort= new Location("New York Airport");
		Location Florida_Port = new Location("Florida Port");
		Location destination = new Location("Silicon Valley");

		
		Washington.setExit("east", Idaho);
		Washington.setExit("south", Oregon);
		Oregon.setExit("north", Washington);
		Oregon.setExit("southwest", California);
		California.setExit("south", Arizona);
		California.setExit("north", Oregon);
		California.setExit("siliconValley", destination);
		Nevada.setExit("east", Utah);
		Nevada.setExit("southeast", Arizona);
		Arizona.setExit("east", New_Mexico);
		Arizona.setExit("west", California);
		Arizona.setExit("northwest", Nevada);
		Arizona.setExit("port", Arizona_Port);
		Arizona_Port.setExit("south", Alaska_Port_North);
		Arizona_Port.setExit("out", Arizona);
		Arizona_Port.setExit("store", ArizonaPortStore);
		ArizonaPortStore.setExit("out", Arizona_Port);
		Alaska.setExit("portN", Alaska_Port_North);
		Alaska.setExit("portE", Alaska_Port_East);
		Alaska_Port_North.setExit("north", Arizona_Port);
		Alaska_Port_North.setExit("out", Alaska);
		Alaska_Port_East.setExit("east", Hawaii_Port_West);
		Alaska_Port_East.setExit("out", Alaska);
		Utah.setExit("northeast", Wyoming);
		Utah.setExit("west", Nevada);
		Idaho.setExit("northwest", Washington);
		Idaho.setExit("east", Wyoming);;
		Montana.setExit("east", North_Dakota);
		Montana.setExit("south", Wyoming);
		Wyoming.setExit("west", Idaho);
		Wyoming.setExit("north", Montana);
		Wyoming.setExit("southwest", Utah);
		Colorado.setExit("east", Kansas);
		Colorado.setExit("south", New_Mexico);
		New_Mexico.setExit("west", Arizona);
		New_Mexico.setExit("north", Colorado);
		Texas.setExit("north", Oklahoma);
		Texas.setExit("east", Louisiana);
		Oklahoma.setExit("north", Kansas);
		Oklahoma.setExit("south", Texas);
		Kansas.setExit("west", Colorado);
		Kansas.setExit("south", Oklahoma);
		Nebraska.setExit("north", South_Dakota);
		Nebraska.setExit("east", Iowa);
		South_Dakota.setExit("north", North_Dakota);
		South_Dakota.setExit("south", Nebraska);
		North_Dakota.setExit("west", Montana);
		North_Dakota.setExit("south", South_Dakota);
		Minnesota.setExit("east", Michigan);
		Minnesota.setExit("south", Iowa);
		Iowa.setExit("north", Minnesota);
		Iowa.setExit("northeast", Wisconsia);
		Iowa.setExit("southwest", Nebraska);
		Missouri.setExit("west", Kansas);
		Missouri.setExit("east", Illinois);
		Arkansas.setExit("southeast", Mississippi);
		Arkansas.setExit("south", Louisiana);
		Louisiana.setExit("west", Texas);
		Louisiana.setExit("north", Arkansas);
		Louisiana.setExit("port", Louisiana_Port);
		Louisiana_Port.setExit("south", Hawaii_Port_North);
		Louisiana_Port.setExit("out", Louisiana);
		Louisiana_Port.setExit("store", LouisianaPortStore);
		LouisianaPortStore.setExit("out", Louisiana_Port);
		Hawaii.setExit("portW", Hawaii_Port_West);
		Hawaii.setExit("portN", Hawaii_Port_North);
		Hawaii.setExit("portE", Hawaii_Port_East);
		Hawaii_Port_West.setExit("out", Hawaii);
		Hawaii_Port_West.setExit("west", Alaska_Port_East);
		Hawaii_Port_North.setExit("north", Louisiana_Port);
		Hawaii_Port_North.setExit("out", Hawaii);
		Hawaii_Port_East.setExit("east", Florida_Port);
		Hawaii_Port_East.setExit("out", Hawaii);
		Mississippi.setExit("northwest", Arkansas);
		Mississippi.setExit("east", Alabama);
		Illinois.setExit("north", Wisconsia);
		Illinois.setExit("east", Indiana);
		Illinois.setExit("southwest", Missouri);
		Wisconsia.setExit("south", Illinois);
		Wisconsia.setExit("southwest", Iowa);
		Michigan.setExit("west", Minnesota);
		Michigan.setExit("southeast", Ohio);
		Indiana.setExit("west", Illinois);
		Indiana.setExit("east", Ohio);
		Kentucky.setExit("east", West_Virginia);
		Kentucky.setExit("south", Tennessee);
		Tennessee.setExit("north", Kentucky);
		Tennessee.setExit("south", Alabama);
		Alabama.setExit("west", Mississippi);
		Alabama.setExit("north", Tennessee);
		Florida.setExit("north", Georgia);
		Florida.setExit("port", Florida_Port);
		Florida_Port.setExit("out", Florida);
		Florida_Port.setExit("store", FloridaPortStore);
		Florida_Port.setExit("west", Hawaii_Port_East);
		FloridaPortStore.setExit("out", Florida_Port );
		Georgia.setExit("north", South_Carolina);
		Georgia.setExit("south", Florida);
		South_Carolina.setExit("north", North_Carolina);
		South_Carolina.setExit("southwest", Georgia);
		North_Carolina.setExit("north", Virginia);
		North_Carolina.setExit("south", South_Carolina);
		Virginia.setExit("northwest", West_Virginia);
		Virginia.setExit("northeast", Delaware);
		Virginia.setExit("south", North_Carolina);
		West_Virginia.setExit("west", Kentucky);
		West_Virginia.setExit("north", Pennsyivania);
		West_Virginia.setExit("east", Virginia);
		Ohio.setExit("west", Indiana);
		Ohio.setExit("northwest", Michigan);
		Ohio.setExit("northeast", Pennsyivania);
		Pennsyivania.setExit("west", Ohio);
		Pennsyivania.setExit("east", New_Jersey);
		Pennsyivania.setExit("south", West_Virginia);
		Delaware.setExit("east", Maryland);
		Delaware.setExit("southwest", Virginia);
		Maryland.setExit("west", Delaware);
		Maryland.setExit("northeast", New_Jersey);
		New_Jersey.setExit("west", Pennsyivania);
		New_Jersey.setExit("northeast", Connecticut);
		New_Jersey.setExit("south", Maryland);
		Connecticut.setExit("east", Rhode_Island);
		Connecticut.setExit("south", New_Jersey);
		Rhode_Island.setExit("west", Connecticut);
		Rhode_Island.setExit("north", Massachusetts);
		Massachusetts.setExit("north", New_Hampshire);
		Massachusetts.setExit("south", Rhode_Island);
		New_York.setExit("northeast", Vermont);
		New_York.setExit("airport", NyAirPort);
		NyAirPort.setExit("out", New_York);
		NyAirPort.setExit("store", NYAirportStore);
		NYAirportStore.setExit("out", NyAirPort);
		Vermont.setExit("west", New_York);
		Vermont.setExit("northeast", Maine);
		New_Hampshire.setExit("northeast", Maine);
		New_Hampshire.setExit("south", Massachusetts);
		Maine.setExit("southwest", Vermont);
		Maine.setExit("south", New_Hampshire);
		storeList.get("MovingStore").setExit("out", currentLoca);
		
		
		locationList.add(California);
		locationList.add(Oregon);
		locationList.add(Arizona);
		locationList.add(Nevada);
		locationList.add(Washington);
		locationList.add(Alaska);
		locationList.add(Idaho);
		locationList.add(Utah);
		locationList.add(Montana);
		locationList.add(Wyoming);
		locationList.add(New_Mexico);
		locationList.add(Colorado);
		locationList.add(Nebraska);
		locationList.add(Kansas);
		locationList.add(Oklahoma);
		locationList.add(Texas);
		locationList.add(South_Dakota);
		locationList.add(North_Dakota);
		locationList.add(Minnesota);
		locationList.add(Iowa);
		locationList.add(Texas);
		locationList.add(Louisiana);
		locationList.add(Missouri);
		locationList.add(Arkansas);
		locationList.add(Mississippi);
		locationList.add(Illinois);
		locationList.add(Hawaii);
		locationList.add(Wisconsia);
		locationList.add(Michigan);
		locationList.add(Indiana);
		locationList.add(Alabama);
		locationList.add(Tennessee);
		locationList.add(Kentucky);
		locationList.add(Ohio);
		locationList.add(Florida);
		locationList.add(Georgia);
		locationList.add(South_Carolina);
		locationList.add(North_Carolina);
		locationList.add(Virginia);
		locationList.add(West_Virginia);
		locationList.add(Pennsyivania);
		locationList.add(Delaware);
		locationList.add(Maryland);
		locationList.add(New_Jersey);
		locationList.add(Connecticut);
		locationList.add(Rhode_Island);
		locationList.add(Massachusetts);
		locationList.add(Vermont);
		locationList.add(New_Hampshire);
		locationList.add(Maine);
		locationList.add(New_York);

	
		birthLocation = NyAirPort;
		currentLoca = birthLocation;

	}
	
	public void description()
	{
		System.out.println("Welcome to \"Go to Silicon Valley!\"");
		System.out.println("Game Version: 1.0");
		delay(2000);
		System.out.println();
		System.out.println("This is an RPG game with word command.");
		delay(1000);
		System.out.println("You will creat a player who has properties of Level, Life, Defense, Strength and Money.");
		delay(1000);
		System.out.println("You can improve them by visiting stores, and you can get money from killing enemies.");
		delay(1000);
		System.out.println("All of these properties influence your ability to win.");
		delay(1000);
		System.out.println("I hope you will like it. :D");
		delay(1000);
		System.out.println();
	}	
	
	public void gameGuide()
	{
		
		System.out.println(guide+": Nice to meet you!!  "+player.getName());
		delay(2000);
		System.out.println(guide+": I am "+guide+". Thank you for helping me go to Silicon Valley!");
		delay(1000);
		System.out.println(guide+": It is the heaven of programmers, and I'd like to have a visit before I make determination to change my career.");
		delay(1000);
		System.out.println(guide+": We will set off from New York Airport and go through the whole country to California.");
		delay(1000);
		System.out.println(guide+": There are several ways with different enemies, and we must eliminate the all we meet. ");
		delay(1000);
		System.out.println(guide+": You will get money and experience after killing them. ");
		delay(1000);
		System.out.println(guide+": If you died, we will go back here. ");
		delay(1000);
		System.out.println(guide+": You can buy stuff in stores to enhance your power and heal your life,");
		delay(1000);
		System.out.println(guide+": but please note, only few ports always has a store, so make sure all get ready before we start.");
		delay(1000);
		System.out.println(guide+": We will also meet the ** Moving Store ** on the road, but it is a probability event.");
		delay(1000);
		System.out.println(guide+": The command you will use:");
		System.out.println();
		delay(1000);
		orders.get("help").doCmd("help");
		System.out.println();
		delay(5000);
		System.out.println(guide+": I have prepared a gift for you, you can choose one from the list:");
		System.out.println();
		delay(1000);
		System.out.println("**********************************");
		System.out.println("* a:   a bottle of magical water *");
		System.out.println("* b:   a delicate coat           *");
		System.out.println("* c:   a strong stick            *");
		System.out.println("**********************************");
		System.out.println();
		System.out.print("Choose:\t");
		String choice = in.nextLine();
		System.out.println();
		switch(choice)
		{
			case "a" : {
				player.addDefense(50);
				System.out.println("you have got 50 life.");
				break;
			}	
			case "b" : {
				player.addDefense(3);
				System.out.println("you have got 3 defense.");
				break;
			}
			case "c" : {
				player.addStrength(3);
				System.out.println("you have got 3 strength.");
				break ;
			}
			default :{
				System.out.println(guide+": I am sorry, the gift has been stolen...:( ");
			}		 
		}
		player.getStatus();
		System.out.println();
		delay(2000);
		System.out.println(guide+": Good Luck!");
		System.out.println();

	}
	
	public void showEndWords()
	{
		System.out.println("** Welcome to Sillicon Valley **");
		System.out.println();
		delay(2000);
		System.out.println("*******************************************************************");
		System.out.println(guide+": Thank you so much, "+player.getName()+"!");
		delay(1000);
		System.out.println(guide+": We finally arrived Sillicon Valley!!");
		delay(1000);
		System.out.println(guide+": This place is as amazing as I think.");
		delay(1000);
		System.out.println(guide+": I believe equiping myself with technical skills can expand oppertunities of my career and life.");
		delay(1000);
		System.out.println(guide+": It is time for me to apply for a Master's program in Computer Science.");
		delay(1000);
		System.out.println(guide+": I want to learn more.");
		delay(1000);
		System.out.println(guide+": Thanks again, "+player.getName()+", bye!");
		System.out.println("*******************************************************************");
		delay(2000);
		System.out.println();
	}
	
	public void showEnemy(Enemy enemy)
	{	
		currentEvent.resetCanGo();
		currentEnemy.put("a", enemy);
		System.out.println("***********************************************************************************");
		System.out.print("In front of you:\t"+"a : "+enemy+"\t");
		enemy.getStatus();
		enemySign = true;
		System.out.println("***********************************************************************************");
	}
	
	public boolean isStore(String name)
	{
		boolean isstore =true;
		if(storeList.get(name)==null)
			isstore = false;
		return isstore;
	}
	
	
	public void locationInfo()
	{
		movingShopSign = false;
		String curLoca = currentLoca.toString();
		if(isStore(curLoca))
		{
			if(curLoca.equals("MovingStore"))
			{
				storeList.get("MovingStore").prepareProducts();
			}
		}
		else if(curLoca.equals("Silicon Valley"))
		{
			showEndWords();
		}
		else
		{
			
			Enemy enemy = new Enemy();
			enemy = currentEvent.meetEnemy(enemyList, locationList, currentLoca);
			if(curLoca.equals("California"))
			{
				enemy = enemyList.get(0);
				showEnemy(enemy);
			}
			else if(enemy != null && Math.random()<0.9)
			{
				showEnemy(enemy);
			}else enemySign = false;
			if(Math.random()<0.3)
			{
				System.out.println("=======================");
				System.out.println("* MovingStore pops up *");
				System.out.println("=======================");
				movingShopSign = true;
			}
						
		}
	}
	
	public void showLocation()
	{
		if(isStore(currentLoca.toString()))
		{
			Store store = (Store)currentLoca;
			store.showProducts();
		}
		System.out.println("You are here:\t"+currentLoca);
		System.out.print("You can go:\t");
		if(movingShopSign)
		System.out.println(currentLoca.getExits()+" and [ms]");
		else System.out.println(currentLoca.getExits());
	}
	
	public void goLocation(String dir)
	{
		String curLoca = currentLoca.toString();
		if(curLoca.equals("MovingStore") && dir.equals("out"))
		{
			currentLoca = lastLocation;
			locationInfo();
		}
		else if(dir.equals("ms"))
		{
			lastLocation = currentLoca;
			currentLoca = storeList.get("MovingStore");
			locationInfo();
		}
		else if(currentLoca.getLocation(dir)!= null)
		{
			
			lastLocation = currentLoca;
			currentLoca=currentLoca.getLocation(dir); 
			locationInfo();
		}
		else
		{
			error();
		}
		
	}
	
	public void play()
	{
		
		delay(3000);
		System.out.println("**************************************************************************");
		System.out.println("******************************Game begins*********************************");
		System.out.println("**************************************************************************");
		System.out.println();	
		Scanner in = new Scanner(System.in);
		ArrayList<String> rightOrders = new ArrayList<String>();
		while(true)
		{
			String curLoca = currentLoca.toString();
			if(isStore(curLoca))
			{
				rightOrders = orderLists.get("shopOrders");
			}
			else if(curLoca.equals("Silicon Valley"))
			{
				rightOrders = orderLists.get("endOrders");
			}
			else if (enemySign && !currentEvent.canGo())
			{
				rightOrders = orderLists.get("fightOrders");
			}
			else 
			{
				rightOrders = orderLists.get("regularOrders");
			}
			showLocation();
			System.out.println("You can do:\t"+rightOrders);
			player.getStatus();
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.print("* What do you want to do *: \t");
			String line= in.nextLine();
			System.out.println();
			System.out.println();
			System.out.println();
			String[] word= line.split(" ");	
			if(!rightOrders.contains(word[0]))
			{
				error();
			}
			else
			{
				String doWhat= "";
				if(word.length>1)
					doWhat= word[1];
				orderList newOrder=orders.get(word[0]);
				if(!word[0].equals("go")|| doWhat.equals("ms")||rightOrders != orderLists.get("fightOrders"))
				{
					newOrder.doCmd(doWhat);
					if(newOrder.isBye())
					{
						System.out.println("See you next time!");
						break;
					}
				}
				else
				{
				
					System.out.println("===========================================");
					System.out.println("You must defeat enemy before you call \"go\"" );
					System.out.println("===========================================");
					
				}
			}
		}
		in.close();
	}
	
	public void error()
	{
		System.out.println("********************");
		System.out.println("* Invalid command! *");
		System.out.println("********************");
		System.out.println();
	}
	
    public void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }
   
	public static void main(String[] args) {
		Game game= new Game();
		game.description();
		game.creatPlayer();
		game.gameGuide();
		game.play();	
	}
	
}
