package captherTwo;

import java.util.Scanner;

public class Signals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int s = num % 10;
		int r = num / 10;
		String str = "";
		switch(s)
		{
		case 1: str = "Faint signals, barely perceptible"; break;
		case 2: str = "Very weak signals"; break;
		case 3: str = "Weak signals"; break;
		case 4: str = "Fair signals"; break;
		case 5: str = "Fairly good signals"; break;
		case 6: str = "Good signals"; break;
		case 7: str = "Moderately strong signals"; break;
		case 8: str = "Strong signals"; break;
		case 9: str = "Extremely strong signals"; break;
		}
		str = str + ", ";
		switch(r)
		{
		case 1: str = str + "unreadable"; break;
		case 2: str = str + "barely readable, occasional words distinguishable"; break;
		case 3: str = str + "readable with considerable difficulty"; break;
		case 4: str = str + "readable with practically no difficulty"; break;
		case 5: str = str + "perfectly readable"; break;
		}
		str = str + ".";
		System.out.println(str);
		in.close();
	}

}
