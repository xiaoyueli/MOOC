#include <stdio.h>
#include <string.h>

int main()
{
	char string[20]="";
	int len=0, sign=1;
	int cnt=0;
	do{
		scanf("%s", &string);
		len=strlen(string);
		
		if(string[len-1]=='.')
		{
			if(len-1!=0||cnt!=0)
			printf("%d", len-1);
			sign=0;
		}else 
		{
			printf("%d", len);
			cnt++;
		}
		
		if(sign) printf(" ");
	}while(sign); 
	
	
	
	return 0;
}
