#include <stdio.h>

int intact(int n)
{
	int sign= 0;
	int i, sum=0;
	for(i=1; i<n; i++)
	{
		if(n%i== 0)
		sum=sum+i;
	} 
	if(sum==n) sign=1;
	return sign;
}
int main()
{
	int n, m;
	scanf("%d %d", &n, &m);
	int i, j=0, cnt=0;
	for(i= n; i<= m; i++)
	{
		if(intact(i))
		cnt= cnt+ 1;	
	}
	if(cnt)
	{
		for(i= n; i<= m; i++)
		{
			if(intact(i))
			{
				printf("%d", i);
				j=j+ 1;
				if(j!=cnt)
				printf(" ");
			}
		}
	}else printf("NIL\n");
	
	return 0;
}
