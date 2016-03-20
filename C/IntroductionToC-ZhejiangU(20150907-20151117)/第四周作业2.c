#include <stdio.h>

int main()
{
	int number, mid;
	scanf("%d", &number);
	int count=1;
	int type,sign=0;
	mid= number;
	if(mid<0)
	{
	   mid=-mid;
	   printf("fu ");
	   number=-number;	
	} 

	while(mid>9)
	{
		mid= mid/10;
    	count=count*10;
	}
    sign= count;
	do{
		type= number/count;
		number=number%count;
		if(sign!=count) printf(" ");
		count=count/10;
	
	switch(type)
	{
		case 0: printf("ling"); continue;
		case 1: printf("yi"); continue;
		case 2: printf("er"); continue;
		case 3: printf("san"); continue;
		case 4: printf("si"); continue;
		case 5: printf("wu"); continue;
		case 6: printf("liu"); continue;
		case 7: printf("qi"); continue;
		case 8: printf("ba"); continue;
		case 9: printf("jiu"); continue;
	}

    }while(count>0);


	return 0;
}
