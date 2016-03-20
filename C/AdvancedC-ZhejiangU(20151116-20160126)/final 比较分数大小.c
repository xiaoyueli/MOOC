#include <stdio.h>

int main(void)
{
	int a1, a2;
	int b1, b2;
	int a3, b3;
	char op[]= ">=<";
	int i;
	scanf("%d/%d %d/%d", &a1, &a2, &b1, &b2);
	a3 = a1*b2;
	b3 = b1*a2;
	if(a3>b3)
		i=0;
	else if(a3 ==b3)
		i=1;
	else i=2;
	printf("%d/%d %c %d/%d", a1, a2, op[i], b1, b2);

	return 0;
} 
