package interestingAlgorithm;

public class PrimeList {
//构造前50的素数表
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = 50;
		int[] list = new int[total];
		list[0] = 2;
		int cnt = 1;
		OUT:
		for(int i =3; cnt < total; i ++ )
		{
			for(int j = 2; j < i ; j++)
			{
				if(i % j == 0)
					continue OUT;
			}
			list[cnt] = i;
			cnt ++;
		}
		
		for(int k: list)
		{
			System.out.print(k+" ");
		}
	}

}
