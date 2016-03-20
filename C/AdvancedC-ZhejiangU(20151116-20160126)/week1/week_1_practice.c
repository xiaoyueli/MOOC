#include <stdio.h>
#include <stdlib.h>

int main()
{
	
	int x=10;
	int y= 20;
	int i=0;
	int num=10;
	for(i;i<= num; i ++)
	{
		x=(int)(rand()%400);
		y=(int)(rand()%600);
		printf("x= %d, y=%d\n", x, y);
	}
	return 0;
}
