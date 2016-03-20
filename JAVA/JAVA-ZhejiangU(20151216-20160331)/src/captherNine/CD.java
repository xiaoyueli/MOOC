package captherNine;

public class CD extends Item {
	private String artist;
	private int numofTracks;
	public CD(String title, int playTime, boolean gotIt, String comment, String artist, int numofTracks) {
		super(title, playTime, gotIt, comment);
		this.artist = artist;
		this.numofTracks = numofTracks;
	}
	
	public void print()
	{
		super.print();
		System.out.print(" artist: "+artist+"numofTracks: "+ numofTracks);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
