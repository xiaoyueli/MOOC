#include <stdio.h>

int main()
{
	int size;
	scanf("%d", &size);
	
	int array[size][size];
	int i, j, x;
	
	for(i=0; i<size; i++)
	{
		for(j=0; j<size; j++)
		{
			scanf("%d", &array[i][j]);		
		}
	}
	
	int sign=0; 
	int r_m, r_n;
	int m, n;
	for(i=0; i<size; i++)
	{
		int max=0, min=100;
		for(j=0; j<size; j++)
		{
			if(array[i][j]>max)
			{
				max=array[i][j];
				r_m=i; 
				r_n=j;
			}
				
		}
//		printf("%d row max=%d at %d column ", r_m, max, r_n);
		for(m=0; m<size; m++)
    	{
    		if(array[m][r_n]<min)
			min=array[m][r_n];
		}
//		printf("%d column min=%d\n", r_n, min);
		if(max==min)
		{
		 sign=1;
		 break;	
		}		
	}
	
	if(sign)
	printf("%d %d", r_m, r_n);
	else printf("NO");

	return 0;
}
