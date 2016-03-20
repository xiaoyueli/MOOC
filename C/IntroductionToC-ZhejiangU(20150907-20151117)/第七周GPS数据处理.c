#include <stdio.h>
#include <string.h>
#define STATUS 18
#define mod 65536

int main()
{
	int sign=1, check=0;
	char string[100];
	char *p;
	int cnt, n;
	int gprmc, checknum, cmp;
	int hour, min, second;
	
	
	do{
		scanf("%s,", &string);
		p=string;
		gprmc=strncmp(string, "$GPRMC", 6);
		

		if(strcmp(string, "END")==0)
		sign=0;
		else if(gprmc==0&&string[STATUS]=='A')
		{
		
				
				cmp=p[1];
				for(n=2; p[n]!='*'; n++)
				{
					cmp=cmp^p[n];
			
				}
				cmp=cmp%mod;
				
				checknum=0;
				for(n; p[n]!='\0'; n++)
				{
					if(p[n]>='0'&&p[n]<='9')
					{
						checknum=checknum*16+p[n]-'0';
					}else if(p[n]>='A'&&p[n]<='F')
						checknum=checknum*16+p[n]-'A'+10;
				}
				
			
				if(cmp==checknum)
				
				{
					hour=(p[7]-'0')*10+p[8]-'0';
					min=(p[9]-'0')*10+p[10]-'0';
					second=(p[11]-'0')*10+p[12]-'0';
					
					if(hour+8>=24)
					hour=hour-16;
					else hour=hour+8;
						
					check=1;
				}			
			
		}
		



	}while(sign);
	

	if(check)
	printf("%02d:%02d:%02d", hour, min, second);
	
	return 0;
}
