package captherSix;

import java.util.Scanner;

public class IntactNumber {

	public static boolean isIntact(int num)
	{
		boolean isintact = false;
		int sum = 0;
		for(int i =1; i< num; i++)
		{
			if(num % i ==0)
				sum +=i;
		}
		if(sum == num)
			isintact = true;
		return isintact;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int lowerB = in.nextInt();
		int upperB = in.nextInt();
		boolean isExist = false;
		for(int i =lowerB; i<=upperB; i++)
		{
			if(isIntact(i) && isExist)
			{
				System.out.print(" "+i);
				isExist = true;
			}
			else if(isIntact(i))
			{
				System.out.print(i);
				isExist = true;
			}
		}
		if(!isExist)
		{
			System.out.print("NIL");
		}
		in.close();
	}

}
