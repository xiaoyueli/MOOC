#include <stdio.h>

int prime(int x)
{
	int sign=1, i;
	for(i=2; i<x; i++)
	{
		if(x%i==0)
		{
			sign=0;
			break;
		}
	}
	return sign;
}

void primeout(int x)
{
	int i;
	for(i=2; i<x; i++)
	{
		int sign= 1;
		do{
			if(x%i==0)
			{
				printf("%d", i);
				x= x/i;
				if(prime(x))
				{
					printf("x%d", x);
					sign=0;
					i=x;	
				}	
			else printf("x");
			}else sign= 0;	
		}while(sign);
	}
}

int main()
{
	int number;
	scanf("%d", &number);
	if(prime(number))
	printf("%d=%d", number, number);
	else{
		printf("%d=", number);
		primeout(number);
	}	
	return 0;
}



