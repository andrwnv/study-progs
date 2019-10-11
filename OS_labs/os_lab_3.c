// COMPILE: gcc os_lab_3.c -o lab3

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define file_path "/tmp/saved_pid.txt"

int main() {
    int pid = 0;

    FILE* file = fopen(file_path, "r");

    if (!file) {
        pid = fork(); // get child PID

        if (pid == 0) {
            while(1) {
                sleep(1);
            }

        } else {
            printf("Child process creadted! \n");
            file = fopen(file_path, "w"); // save PID to tmp
            fprintf(file, "%d", pid);
            fclose(file);
        }

    } else {
        fscanf(file, "%d", &pid);
        fclose(file);
        printf("Child process killed! \n");
        kill(pid, 15); // Use SIGTERM for kill
        remove(file_path);
    }
}
