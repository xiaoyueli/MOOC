#include <stdio.h>
#include <stdlib.h>

typedef struct{
	int* array;
	int size;
}Array;

Array creat_arr(int n)
{
	Array arr;
	arr.size = n;
	arr.array=(int*)malloc(sizeof(int)*arr.size);
	return arr;
}

void free_arr(Array *a)
{
	free(a->array);
	a->array = NULL;
	a->size = 0;
}

int main()
{
	int total, i;
	scanf("%d", &total);
	Array list = creat_arr(total);
	for(i = 0; i< total; i++)
	{
		scanf("%d", &list.array[i]);
	}
	int j;
	int min, index;
	for(i = 0; i<list.size; i++)
	{
		min = list.array[i];
		index = i;
		for(j = i; j < list.size; j++)
		{
			if(min>list.array[j])
			{
				min = list.array[j];
				index = j;
			}
		}
		if(i!=index)
		{
			list.array[index] = list.array[i];
			list.array[i] = min;
		}
	}
	
	for(i = 0; i< list.size; i++)
	{
		printf("%d ", list.array[i]);
	}
	free_arr(&list);
	return 0;
	
}
