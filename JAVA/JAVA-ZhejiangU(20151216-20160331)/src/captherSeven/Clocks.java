package captherSeven;

class Display
{
	private int value = 0;
	private int limit = 0;
	
	Display(int limit)
	{
		this.limit = limit;
	}
	
	public boolean increase()
	{
		boolean turn = false;
		value ++;
		if(value == limit)
		{
			value = 0;
			turn = true;
			
		}
		return turn;
			
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value =value;
	}
}

class Clock{
	private Display hours = new Display(24);
	private Display minutes = new Display(60);
	private Display seconds = new Display(60);

	
	Clock(int hour, int minute, int second){
		hours.setValue(hour);
		minutes.setValue(minute);
		seconds.setValue(second);
	}
	
	public void tick()
	{
		
		if(seconds.increase())
		if(minutes.increase())
		{
			hours.increase();
		}

	}
	
	public String toString()
	{
		String time = String.format("%02d:%02d:%02d", hours.getValue(), minutes.getValue(), seconds.getValue());
		return time;
	}
}



public class Clocks {
	public static void main(String[] args) {
		java.util.Scanner in = new java.util.Scanner(System.in);
		Clock clock = new Clock(in.nextInt(), in.nextInt(), in.nextInt());
		clock.tick();
		System.out.println(clock);
		in.close();
	}

}
