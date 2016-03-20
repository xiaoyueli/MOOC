#include <stdio.h>
  
int main(void)
{
    int size;
    scanf("%d", &size);
  
    int a[size][size];
  
    int i, j;
    for (i = 0; i < size; i++) {
        for (j = 0; j < size; j++) {
            scanf("%d", &a[i][j]);
        }
    }
  
    for (i = 0; i < size; i++) {
        int max = 0;
        for (j = 0; j < size; j++) {
            if (a[i][j] > a[i][max]) {
                max = j;
            }
        }
  
        int min = 0;
        for (j = 0; j < size; j++) {
            if (a[j][max] < a[min][max]) {
                min = j;
            }
        }
  
        if (min == i) {
            printf("%d %d\n", min, max);
            goto end;
        }
    }
    printf("NO\n");
  
    end:;
    return 0;
}
