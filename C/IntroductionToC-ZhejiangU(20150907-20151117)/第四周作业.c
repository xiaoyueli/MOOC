#include <stdio.h>

int main()
{
	int n, m;
	scanf("%d %d", &n, &m);
	int i = 2, num=3;
	int sum = 0, count = 1;
	if ( n == 1) 
	{
		sum = sum + 2;
	}
	
	while(count<m )
	{
		if(num % i == 0)
		{
			num ++;
			i = 2;
		}else i++;
		
		if(num == i)
		{
			count ++;
			if(count>= n)
			{
				sum = sum + num;	
			}
			num ++;
			i = 2;
		}
	}
	printf("%d", sum);
	return 0;
}
