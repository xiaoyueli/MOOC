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
	char* sear_fir;
	char* ori = sec;
	int len = 0, loc = 0;
	len = strlen(sec);
	int sign = 1;
	while(sear_fir=strstr(ori, fir))
	{
		loc = len - strlen(sear_fir);
		printf("%d ", loc);	
		sear_fir ++;
		ori = sear_fir;
		sign = 0;
	}
	if(sign)
	printf("-1");
	return 0;
	
}

