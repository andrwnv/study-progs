// COMPILE: gcc -pthread os_lab_4.c -o lab

#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

typedef struct Arguments {
    int interval_begin;
    int interval_end;
    int n;
} Args;

double f (double x) { return 2 * x; }

void integral_calc(void* args) {
    Args* m_args = args;

    double   a = m_args->interval_begin;
    double   b = m_args->interval_end;
    unsigned n = m_args->n; 

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
    int count_of_tids = argc - 1;

    pthread_t* tids = malloc( sizeof(pthread_t) * (count_of_tids) );  
    Args* args      = malloc( sizeof(pthread_t) * (count_of_tids) );  

    int parsed_num = 0;

    for (int i = 1; i < argc; ++i) {
        FILE* f_input = fopen(argv[i], "r");

        fscanf(f_input, "%d ", &parsed_num);
        args[i - 1].interval_begin = parsed_num;

        fscanf(f_input, "%d ", &parsed_num);
        args[i - 1].interval_end = parsed_num;
        
        fscanf(f_input, "%d ", &parsed_num);
        args[i - 1].n = parsed_num;

        pthread_create(&tids[i - 1], NULL, (void*)integral_calc, (void*)&args[i - 1]);

        fclose(f_input);
    }

    for (int i = 0; i < count_of_tids; ++i) {
        pthread_join(tids[i], NULL);
    }

    free(tids);
    free(args);

    return 0; 
} 