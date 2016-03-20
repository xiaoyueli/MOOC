package captherNine;

public class Item {
	private String title;
	private int playTime;
	private boolean gotIt;
	private String comment;
	
	
	public Item(String title, int playTime, boolean gotIt, String comment) {
		super();
		this.title = title;
		this.playTime = playTime;
		this.gotIt = gotIt;
		this.comment = comment;
	}

	public void print()
	{
		System.out.print(title);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
