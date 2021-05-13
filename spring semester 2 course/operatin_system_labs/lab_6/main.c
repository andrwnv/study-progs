#include <stdio.h>

extern int asmproc(int*, int);

#define row_size 4

int main()
{
    int index;
    int j;
    int* arr[row_size][row_size] = { {2, 5, 2, 5},
                       {5, 4, 5, 4}, 
                       {6, 5, 6, 5}, 
                       {1, 2, 1, 2}};

    asmproc(arr, row_size);

    for (j = 0; j < row_size; ++j)
    {
        for (index = 0; index < row_size; ++index)
        {
            printf("%d  ", arr[index][j]);
        }

         printf("\n");
    }

    return 0;
}
