#include <stdio.h>
#include <string.h>

int main()
{
	int number, signal, read;
	scanf("%d", &number);
	signal=number/10;
	read=number%10;
	if(signal!=0==read>0==read<6)
	{
		char readstr[5][60], sigstr[9][60];
		strcpy(readstr[0], "unreadable");
		strcpy(readstr[1], "barely readable, occasional words distinguishable");
		strcpy(readstr[2], "readable with considerable difficulty");
		strcpy(readstr[3], "readable with practically no difficulty");
		strcpy(readstr[4], "perfectly readable");
		strcpy(sigstr[0], "Faint signals, barely perceptible");
		strcpy(sigstr[1], "Very weak signals");
		strcpy(sigstr[2], "Weak signals");
		strcpy(sigstr[3], "Fair signals");
		strcpy(sigstr[4], "Fairly good signals");
		strcpy(sigstr[5], "Good signals");
		strcpy(sigstr[6], "Moderately strong signals");
		strcpy(sigstr[7], "Strong signals");
		strcpy(sigstr[8], "Extremely strong signals");
		printf("%s, %s.",sigstr[signal-1],readstr[read-1]);
	}
	return 0;
}
