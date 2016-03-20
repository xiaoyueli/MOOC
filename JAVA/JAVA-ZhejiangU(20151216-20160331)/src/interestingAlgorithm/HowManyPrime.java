package interestingAlgorithm;

public class HowManyPrime {
//100以内的素数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = 101;
		boolean[] list = new boolean[total];
		for(int k = 0; k < list.length; k++)
		{
			list[k]=true;
		}
		list[0]=list[1]=false;
		for(int i = 2; i <list.length/2; i ++)
		{
			if(list[i])
			{
				for(int j = 2; i*j <list.length; j ++ )
				{
					list[i*j] = false;
				}
			}
		
		}
		for(int m = 0; m < total; m ++)
		{
			if(list[m])
			{
				System.out.print(m +" ");
			}
		}
	}

}
