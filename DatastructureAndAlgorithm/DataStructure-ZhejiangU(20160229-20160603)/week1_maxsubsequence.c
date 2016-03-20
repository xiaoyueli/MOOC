#include <stdio.h>

#define TRUE 1
#define FALSE 0
int main()
{
	/*求最大子序列及其和 */
	int size, num, i;
	int maxsum=0, cursum= 0;
	int cnt = 0;
	int maxnum[2] = {}, curnum[2] = {};
	int maxseq = FALSE;
	scanf("%d", &size);
	for(i = 0; i < size; i++)
	{
		scanf("%d", &num);
		cursum += num;
		if(cursum >= 0)
		{
			maxseq = TRUE;
			cnt ++;
			if(cnt == 1)
			{
				curnum[0] = num;
				curnum[1] = num;
			}
			else
			{
				curnum[1] = num;
			}
		}
		else if(cursum < 0)
		{
			cursum = 0;
			cnt = 0;
		}
	
		if(cursum > maxsum)
		{
			maxsum = cursum;
			maxnum[0] = curnum[0];
			maxnum[1] = curnum[1];
		}
		
		if(!maxseq)
		{
			if(i == 0)
			{
				maxnum[0] = num;
			}
			if(i == size - 1)
			{
				maxnum[1] = num;
			}
		}				
	} 
	if(maxsum == 0 && maxseq)
	{
		maxnum[0] = 0;
		maxnum[1] = 0;
	}
	printf("%d %d %d", maxsum, maxnum[0], maxnum[1]);
}
