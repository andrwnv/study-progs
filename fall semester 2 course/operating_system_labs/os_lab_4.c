// COMPILE: gcc -pthread os_lab_4.c -o lab

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

typedef struct Arguments {
    int intervalBegin;
    int intervalEnd;
    int n;
} Args;

double f (double x) { return 2 * x; }

void integral_calc(void* args) {
    Args* _args = args;

    double   a = _args->intervalBegin;
    double   b = _args->intervalEnd;
    unsigned n = _args->n; 

    printf("%f  %f  %d  ", a, b, n);

    double sum = .0;
    double step = (b - a) * (1.0 * n);

    if (0 == n) {
        return;
    }
    
    step = (b - a) / (1.0 * n);

    for (size_t i = 1 ; i < n ; ++i ) {
        sum += f (a + i * step);
    }

    sum += (f(a) + f(b)) / 2;
    sum *= step;

    printf("%0.2f \n", sum);
}

int main(int argc, char* argv[]) 
{ 
    int countOfTids = argc - 1;

    pthread_t* tids = malloc( sizeof(pthread_t) * (countOfTids) );  
    Args*      args = malloc( sizeof(pthread_t) * (countOfTids) );  

    int parsedNum = 0;

    for (int i = 1; i < argc; ++i) {
        FILE* f_input = fopen(argv[i], "r");

        fscanf(f_input, "%d ", &parsedNum);
        args[i - 1].intervalBegin = parsedNum;

        fscanf(f_input, "%d ", &parsedNum);
        args[i - 1].intervalEnd = parsedNum;
        
        fscanf(f_input, "%d ", &parsedNum);
        args[i - 1].n = parsedNum;

        pthread_create(&tids[i - 1], NULL, (void*)integral_calc, (void*)&args[i - 1]);

        fclose(f_input);
    }

    for (int i = 0; i < countOfTids; ++i) {
        pthread_join(tids[i], NULL);
    }

    free(tids);
    free(args);

    return 0; 
} 