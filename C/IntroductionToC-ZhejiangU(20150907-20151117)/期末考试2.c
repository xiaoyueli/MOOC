#include <stdio.h>

int main()
{
	int a, b;
	scanf("%d/%d", &a, &b);
	int big, small, mid;
	
	if(a>0&&b>0)
	{
		if(a<b)
		{
			big=b;
			small=a;	
		}else
		{
			big=a;
			small=b; 
		}
		do{
			mid=big%small;
			big=small;
			small=mid;
		}while(mid!=0);
			
			a=a/big;
			b=b/big;
			
		printf("%d/%d", a, b);		
	}


	return 0;
}
