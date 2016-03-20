package captherFour;

import java.util.Scanner;

public class BinomialExpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int col = 101;
		int row = 2;
		int[][] list = new int[row][col];
		int coe, pow;
		int cnt = 0;
		int len =0;
		do{
			int lensign = -1;
			do{
	 			pow = in.nextInt();
	 			coe = in.nextInt();
	 			list[cnt][pow] = coe;
	 			if(lensign == -1 && pow > len)
	 			{
	 				len = pow;
	 				lensign = 1;
	 			}
	 		}while(pow != 0);
			cnt ++;		
		}while(cnt < row);
		boolean sign = false;
		for(int i = len; i >=0; i-- )
		{
			coe = list[0][i]+list[1][i];
			if(coe != 0)
			{
			
				if(sign && coe > 0)
				{
					System.out.print("+");
				}
				if(coe != 1 && coe !=-1)
				{
					System.out.print(coe);
					sign = true;
				}
				else if(coe == -1 )
				{
					if(i == 0)
					{
						System.out.print(coe);
						sign = true;
					}
					else System.out.print("-");
						
				}
				else
				{
					if(i ==0)
					{
						System.out.print(coe);
						sign = true;
					}
				}
				
				if(i > 1)
				{
					
					System.out.print("x"+i);
					sign = true;
				}
				else if(i == 1)
				{
					System.out.print("x");
					sign = true;
				}	
				
			}
			if(!sign && i == 0)
				System.out.print("0");
			
		}
		in.close();
	}

}
