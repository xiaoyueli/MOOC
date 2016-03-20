#include <stdio.h>
#include <string.h>


void strnew(char *str, int a);

int main()
{
	int size = 10000;
	char fir[10000]={""};
	char sec[10000]={""};
	gets(fir);
	gets(sec);
	int len= strlen(fir);
	int len2= strlen(sec);
	int i=0, j=0, sign= 0;
	int cnt= len2;
	for(i; i< len2; i++)
	{

		if(strncmp(fir, sec, len)== 0)
		{
			printf("%d ", j);
			sign = 1;
		
		}
		j ++;
		cnt --;
		strnew(sec, cnt); 
	}
	if(!sign)
	printf("-1");
	return 0;
}


void strnew(char *str, int a)
{
	int i = 0;
	for(i; i< a+1; i++)
	{
		str[i]=str[i+1];
	}
}
