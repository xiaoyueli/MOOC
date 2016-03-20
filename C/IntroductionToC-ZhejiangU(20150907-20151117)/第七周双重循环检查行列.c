#include <stdio.h>

int main()
{
	const int size=3;
	int board[size][size];
	int rowof1, rowof0, colof1, colof0, diaof1, diaof0;
	int i, j, n, m;
	int result=-1;
	
	for(i=0; i<size&&result==-1; i++){
		rowof1=rowof0=0;
		colof1=colof0=0;
		diaof1=diaof0=0;
		for(j=0; j<size&&result==-1; j++)
		{
			scanf("%d", &board[i][j]);
			if(board[i][j])rowof1 ++;
			else rowof0 ++;
			if(i==size-1)
			{			
				for(n=0; n<size; n++)
				{
					if(board[n][j]) colof1 ++;
					else colof0 ++;
				}
				if(j==0)
					for(n=0, m=size-1; n<size; n++)
						if(board[m--][n]) diaof1 ++;
						else diaof0 ++;				
				else if(j==size-1)
				for(n=0; n<size; n++)
					if(board[n][n]) diaof1 ++;
					else diaof0 ++;			
				if(colof1==size||diaof1==size) result=1;
				else if(colof0==size||diaof0==size) result=0;
				colof1=colof0=diaof1=diaof0=0;			
			}
			if(i==0)
			{
				for(n=0; n<=j; n++)
				printf("%d ", board[i][n]);
				printf("\n");
			}else
			{
				for(n=0; n<i;n++){
					
					for(m=0; m<size; m++)
					printf("%d ", board[n][m]);
					printf("\n");
				}
				for(n=0; n<=j; n++)
				printf("%d ", board[i][n]);
				printf("\n");				
			}				
		}
		if(rowof1==size) result=1;
		else if(rowof0==size) result=0;	
	}	
	switch(result)
		{
			case 0: printf("0 win!");
					break;
			case 1: printf("1 win!");
					break;
			case -1: printf("no one win!");
					break;
		}
	return 0;	
}
