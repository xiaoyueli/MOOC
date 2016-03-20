package captherTwo;

import java.util.Scanner;

public class TranslateTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in= new Scanner(System.in);
		int bjt = in.nextInt();
		int minu = bjt % 100;
		int hour = bjt / 100 - 8;
		if(hour < 0)
		{
			hour = hour + 24; 
		}
		int utc = hour*100+minu;
			
		System.out.println(utc);
		in.close();
	}

}
