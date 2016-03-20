#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
	int coe;
	int exp;
	struct Node * next;
}Polynomial;

void attach(int coe, int exp, Polynomial **pp)
{	
	if(coe)
	{
		Polynomial *temp;
		temp = (Polynomial*)malloc(sizeof(struct Node));
		temp->next = NULL; 
		temp->coe = coe;
		temp->exp = exp;
		(*pp)->next = temp;
		*pp = temp;
	}

}

Polynomial* readPoly()
{
	Polynomial *pp, *front;
	pp = (Polynomial*)malloc(sizeof(struct Node));
	pp->next = NULL;
	front = pp;
	int numofitem;
	scanf("%d", &numofitem);
	if(numofitem)
	{
		while(numofitem)
		{	
			int coe, exp;
			scanf("%d", &coe);
			scanf("%d", &exp);
			attach(coe, exp, &pp);
			numofitem --;
		}
		pp = front->next;
		free(front);
	}
	else
	{
		pp->coe = 0;
		pp->exp = 0;	
	}
	return pp;
}

void printPoly(Polynomial *pp)
{
	if(!pp)
	{
		printf("0 0\n");	
	}

	while(pp)
	{
		if(pp->coe)
			printf("%d %d", pp->coe, pp->exp);
		pp = pp->next;
		if(pp)
			printf(" ");
		else
			printf("\n");
	}
}

Polynomial *add(Polynomial *p1, Polynomial *p2)
{
	Polynomial *temp, *front;
	temp = (Polynomial*)malloc(sizeof(struct Node));
	temp->next = NULL; 
	front = temp;
	while(p1&&p2)
	{			

		if(p1->exp == p2->exp)
		{
			attach(p1->coe + p2->coe, p1->exp, &temp);
			p1 = p1->next;
			p2 = p2->next;
		}
		else if (p1->exp > p2->exp)
		{
			attach(p1->coe, p1->exp, &temp);
			p1 = p1->next;
		}
		else
		{
			attach(p2->coe, p2->exp, &temp);
			p2 = p2->next;
		}	
	}
	if(p1 && p1->coe)
	{
		temp->next = p1;
	}
	if(p2 && p2->coe)
	{
		temp->next = p2;
	}
	temp = front->next;
	free(front);
	return temp;	
}


Polynomial *multiply(Polynomial *p1, Polynomial *p2)
{
	Polynomial *temp;
	temp = NULL;
	if(p1&&p2)
	{
		while(p1)
		{
			Polynomial *temp2, *ftemp2, *fp2;
			fp2 = p2;
			temp2 = (Polynomial*)malloc(sizeof(struct Node));
			temp2->next = NULL;
			ftemp2 = temp2;
			while(p2)
			{
				attach(p1->coe * p2->coe, p1->exp + p2->exp, &temp2);
				p2 = p2->next;
			}
			p2 = fp2;
			p1 = p1->next;
			temp2 = ftemp2->next;
			free(ftemp2);
			temp = add(temp, temp2);
		}

	}
	else
	{
		temp->coe = 0;
		temp->exp = 0;
		temp->next = NULL;
	}
	
	return temp;
}

int main()
{
	Polynomial *p1, *p2, *pm, *ps;
	
	p1 = readPoly();
	p2 = readPoly();
	
	pm = multiply(p1, p2);
	ps = add(p1, p2);
	
	printPoly(pm);
	printPoly(ps);
	
	return 0;	
}
