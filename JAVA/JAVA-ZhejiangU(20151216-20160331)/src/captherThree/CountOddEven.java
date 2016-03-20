package captherThree;

import java.util.Scanner;

public class CountOddEven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		int odd = 0, even = 0;
		int num;
		do{
			num= in.nextInt();
			if(num != -1)
			{
				if(num % 2 == 0)
					even ++;
				else odd ++;
			}
		}while(num != -1);
		System.out.println(odd+" "+even);
		
		in.close();
		
	}

}
