#include <stdio.h>
#include <string.h>

int main()
{
	char artile[20];
	int num=11;
	int cnt[num];
	int sum=0;
	int i= 0;
	for(i= 0; i< num; i++)
	{
		cnt[i]=0;
	}
	
	while(scanf("%s", &artile)!=EOF)
	{
		int size=0;
		int sign=0;
		int len= strlen(artile);
		char j;
		for(i= 0; i<len; i++)
		{
			j= artile[i];
			if(j>='a'&& j<='z'||j>='A'&&j<='Z'||j=='\''||j=='-'||j>='0'&&j<='9')
			{
				size ++;
				sign=1;
			}else
			{
				if(size!=0)
				sum ++;
				cnt[size] ++;
				sign=0;
				size=0;
			}
		 } 
		if(sign)
		{
			cnt[size] ++;
			sum ++;
		}
	
	}
	
	cnt[0]=sum;
	
	for(int i=0; i< num; i ++)
	{
		
		printf("%d", cnt[i]);
		if(i!=num-1)
		printf(" ");
	}		
	
	return 0;
}
