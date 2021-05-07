#include <stdio.h>

extern int asmproc(int*, int);

#define size 16
#define row_size 4

int main()
{
    int index;
    int* arr[size] = { 2, 5, 2, 5,
                       5, 4, 5, 4, 
                       6, 5, 6, 5, 
                       1, 2, 1, 2};

    asmproc(arr, size);

    for (index = 1; index <= size; index++)
    {
        printf("%d  ", arr[index - 1]);
        if (index % row_size == 0)
        {
            printf("\n");
        }
    }

    free(arr);

    return 0;
}
