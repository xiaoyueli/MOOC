#include <stdio.h>

int input(int x[]) //������ʽ���鸳ֵ 
{
	int power, coeff;
	int len_x, n;
	scanf("%d %d",&power, &coeff);
	len_x= power+1;
 	for(n=0; n<len_x; n++)
	x[n]=0;
	x[power]=coeff;
	while(power!=0) {
	scanf("%d %d",&power, &coeff);
	x[power]=coeff;
	}
	
	return len_x;
}

int main()
{
	int length=101;
	int a[length], b[length];
	int n;
	int len_a, len_b, len_max;
  
  
 	len_a=input(a);
 	len_b=input(b);
 	
 	if(len_a>len_b){               //����������ʽ�������Ϊһ���� 
 		for(n=len_b; n<len_a; n++)
		 b[n]=0;
		 len_max=len_a;	
	 } 
 	else if(len_b>len_a)
	 {
	 	for(n=len_a; n<len_b; n++)
		 a[n]=0;
		 len_max=len_b;
	 }else len_max=len_a;
	 
	int c[len_max];
	int sign=0;
	for(n=0; n<len_max; n++) //����������ӵ�ϵ����ֵ���µ�����C 
	{
		c[n]=a[n]+b[n];
		if(c[n]!=0) sign=-1;  //����������Ƿ�ÿһ�Ϊ0 
	}	
	
	if(sign==0)				//A,B�������Ϊ��ʱ����� 
	{
		printf("0");
	}
	
	int cnt=0;
	for(n=len_max-1; n>-1; n--)	//��ȥ�������е�ֵΪ���ĩβ���� 
	if(c[n]==0) cnt ++;
	else break;
	
	len_max=len_max-cnt;
	
	for(n=len_max-1; n>-1; n--)
	{
		if(c[n]!=0)
		{
			if(c[n]>1)
			{
				if(n==len_max-1)
				printf("%d", c[n]);
				else printf("+%d", c[n]);
				
				if(n>1)
				printf("x%d", n);
				else if(n==1) printf("x");
				
			}else if(c[n]==1)
			{
				if(n==len_max-1);
				else printf("+");
				
				if(n>1)
				printf("x%d", n);
				else if(n==1) printf("x");
				else if(n==0) printf("1");
			}else if(c[n]==-1)
			{
				if(n>1)
				printf("-x%d", n);
				else if(n==1) printf("-x");
				else if(n==0) printf("-1");
			}else if(c[n]<-1)
			{
				printf("%d", c[n]);
				if(n>1)
				printf("x%d", n);
				else if(n==1) printf("x");
			}
		}
	}
	return 0;
}

