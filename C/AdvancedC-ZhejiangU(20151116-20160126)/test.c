#include <stdio.h>
#include <stdlib.h>


int main(void)
{    
	int n;
	scanf("%d", &n);
	int **points;
    int i, j;
    int max_x=-1000, max_y=-1000;
    int min_x=1000, min_y=1000;
    points = (int**)malloc(sizeof(int*)*n);
    for (i = 0; i < n; i++)
	{
        points[i] = (int*)malloc(sizeof(int)*2);
    }
   
    for (i = 0; i < n; ++i){
        for (j = 0; j < 2; ++j){
           scanf("%d", &points[i][j]);
           if(j==0)
           {
           	if(points[i][j]<min_x)
           	min_x = points[i][j];
           	if(points[i][j]>max_x)
           	max_x = points[i][j];
		   }
			if(j==1)
           {
           	if(points[i][j]<min_y)
           	min_y = points[i][j];
           	if(points[i][j]>max_y)
           	max_y = points[i][j];
		   }
        }
    }
  
  	printf("%d %d %d %d\n", min_x, min_y, max_x, max_y);
    for (i = 0; i < n; i++){
        free(points[i]);
    }
    free(points);
    return 0;
} 




