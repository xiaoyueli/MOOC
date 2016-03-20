package chaptherOne;

import java.util.Scanner;

public class TemperatureTranslate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int tem = in.nextInt();
		int temC = (int)((tem - 32)*5/9.0);
		System.out.println(temC);
		in.close();
		
	}

}
