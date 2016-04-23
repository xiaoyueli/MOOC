
public class Prime {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 100;
		boolean[] primes = new boolean[n+1];
		for(int i = 2; i<= n; i++)
		{
			primes[i] = true;
		}
		
		for(int divisor = 2; divisor * divisor <= n; divisor++)
		{
			for(int i = 2 * divisor; i <=n; i = i + divisor)
			{
				primes[i] = false;
			}
		}
		
		for(int i = 2; i <= n; i++)
		{
			if(primes[i])
			{
				System.out.print(i + " ");
			}
		}
	}

}
