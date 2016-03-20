package captherThree;

import java.util.Scanner;

public class BinaryNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int cnt = 1;
		int dig;
		int sum = 0;
		do
		{
			dig = num % 10;
			if(dig % 2 == cnt % 2)
			{
				sum = sum +(int)Math.pow(2, (cnt-1));
			}
			num = num/10;
			cnt ++;
		}while(num != 0);
		
		System.out.println(sum);
		in.close();
	}

}
