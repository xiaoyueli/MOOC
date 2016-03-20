#include <stdio.h>

int main()
{
	int number, digit=0, numth=0;
	int newnum, twonum=1, count=0;
	scanf("%d", &number);
	digit=number%10;
	numth++;
	if(digit%2==numth%2) newnum=1;
	else newnum=0;
	number=number/10;
	 
	while(number!=0)
	{
		digit=number%10;
		numth++;
		twonum=twonum*2;
		if(digit%2==numth%2) count=1;
		else count=0;
		newnum=newnum+twonum*count;
		number=number/10;
	}
	
	printf("%d", newnum);
	
	
	return 0;
}
