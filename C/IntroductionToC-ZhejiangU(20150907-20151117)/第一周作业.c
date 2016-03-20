#include <stdio.h>

int main()
{
	int number;
	int a, b, c;
	scanf("%d", &number);
	a=number/100;
	b=number%100/10;
	c=number%100%10;
	int newnum=c*100+b*10+a;
	printf("%d", newnum); 
	return 0;
}
