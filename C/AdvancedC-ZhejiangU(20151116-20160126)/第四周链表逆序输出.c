#include <stdio.h>
#include <stdlib.h>

typedef struct _node{
	int number;
	struct _node* next;
}Node;

typedef struct _list{
	Node* head;
	Node* tail;
}List;

Node* getList();
void print(Node* p);

int main()
{
	print(getList());
}

Node* getList()
{
	int value;
	List list;
	list.head = NULL;
	list.tail = NULL;
	scanf("%d", &value);
	while(value != -1)
	{
		Node* p = (Node*)malloc(sizeof(Node));
		p->number = value;
		p->next = NULL;
		if(list.head)
		{
			list.tail->next = p;
			list.tail = p;
		}
		else
		{
			list.head = p;
			list.tail = list.head;
		}
	
		scanf("%d", &value);
	}
	return list.head;
}

void print(Node* p)
{
	if(p == NULL)
	{
	}
	else if(p->next == NULL)
	{
		printf("%d ", p->number);
	}
	else
	{
		print(p->next);
		printf("%d ", p->number);
	}
}
