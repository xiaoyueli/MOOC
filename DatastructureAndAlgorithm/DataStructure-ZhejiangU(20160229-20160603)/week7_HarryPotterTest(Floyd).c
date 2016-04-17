#include <stdio.h>
#include <stdlib.h>

int ** build_graph(int len)
{
	int **graph;
	int i, row, col;
	graph = (int**)malloc(sizeof(int*)*len);
	for(i = 0; i < len; i ++)
	{
		graph[i] = (int*)malloc(sizeof(int)*len);
	}
	for(row = 0; row < len; row++)
	{
		for(col = 0; col < len; col++)
		{
			if(row == col)
			{
				graph[row][col] = 0;
			}
			else
			{
				graph[row][col] = 99999;
			}
		}
	}
	return graph;
}

void read_connections(int ** graph, int num)
{
	int i;
	int animal, parter, len;
	for(i = 0; i < num; i++)
	{
		scanf("%d %d %d", &animal, &parter, &len);
		graph[animal - 1][parter - 1] = len;
		graph[parter - 1][animal - 1] = len;
	}

}

void floyd_optimal_connection(int ** graph, int len)
{

	int animal, parter, in_ani;
	
	for(in_ani = 0; in_ani < len; in_ani++)
	{
		for(animal = 0; animal < len; animal++)
		{
			for(parter = 0; parter< len; parter++)
			{
				if(graph[animal][parter] > graph[animal][in_ani] + graph[in_ani][parter])
					graph[animal][parter] = graph[animal][in_ani] + graph[in_ani][parter];
			}
		}
	}

}

void compute_optimal_animal(int**graph, int num)
{
	
	int easier_ani = 99999;
	int most_dif = 0;
	int animal, parter;
	int best_ani = 0, max_words;
	int is_ok;
	for(animal = 0; animal < num; animal ++)
	{
		is_ok = 1;
		most_dif = 0;
		for(parter = 0; parter < num; parter ++)
		{
			if(graph[animal][parter] == 99999)
			{
				is_ok = 0;
				break;
			}
			if(graph[animal][parter] > most_dif)
			{
				most_dif = graph[animal][parter];
			}
		}
		if(is_ok && most_dif < easier_ani)
		{
			easier_ani = most_dif;
			best_ani = animal + 1;
		}

	}
	
	if(best_ani)
		printf("%d %d", best_ani, easier_ani);
	else
		printf("%d", best_ani);
}

void printout(int ** graph, int len) //For check the graph
{
	int i, j;
	for(i = 0; i < len; i++)
	{
		for(j = 0; j < len; j ++)
		{
			printf("%d ", graph[i][j]);
		}
		printf("\n");
	}
	printf("\n");
}

int main()
{
	int total, connections;
	int ** g_animal;
	scanf("%d %d", &total, &connections);
	
	g_animal = build_graph(total);	
	read_connections(g_animal, connections);
	floyd_optimal_connection(g_animal, total);
	compute_optimal_animal(g_animal, total);
	
	return 0;
}
