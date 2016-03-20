package captherThree;

import java.util.Scanner;

public class SumOfPrime {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int m, n, sum = 0;
		m = in.nextInt();
		n = in.nextInt();
		int num = 2;
		int cnt = 0;
		do
		{
			boolean isprime = true;
			for(int i = 2; i <num ; i ++)
			{	
				if(num % i == 0)
					isprime = false;
			}
			if(isprime)
			{
				cnt ++;
				if(cnt >= m && cnt <= n)
					sum += num;
			}
			num ++;
		}while(cnt <= n);

		System.out.println(sum);
		in.close();
	}

}
