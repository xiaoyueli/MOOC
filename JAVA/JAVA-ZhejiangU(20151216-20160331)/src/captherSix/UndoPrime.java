package captherSix;

import java.util.Scanner;

public class UndoPrime {

	public static boolean isPrime(int num)
	{
		boolean isprime = true;
		if(num != 2)
		{
			for(int i = 2; i<num; i++)
			{
				if(num % i == 0)
					isprime = false;
			}	
		}
		return isprime;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int prime = in.nextInt();
		if(!isPrime(prime))
		{
			System.out.print(prime+"=");
			OUT:
			for(int i = 2; i< prime; i++)
			{
				while(prime % i ==0){
					prime /= i;
					System.out.print(i+"x");
					if(isPrime(prime))
					{
						System.out.print(prime);
						break OUT;
					}
				}
			}
		}
		else
		{
			System.out.println(prime+"="+prime);
		}
			
	}

}
