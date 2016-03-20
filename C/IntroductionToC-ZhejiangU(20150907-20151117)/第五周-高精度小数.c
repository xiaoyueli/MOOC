#include <stdio.h>

int main()
{
	int small, big;
	scanf("%d/%d", &small, &big);
	int count= 0, quo= 0;
	printf("0."); 
	do{
		small= small*10;
		quo= small/big;
		small= small%big;
		printf("%d", quo);
		count ++;
	}while(!(small==0||count==200)); 
	printf("\n");
	return 0;
}
