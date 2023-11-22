//
// Created by philippe on 16.11.23.
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "%s", "Aufruf: java Bubblesort Anzahl");
        return 0;
    }

    int n = atoi(argv[1]);
    if (n < 1) {
        fprintf(stderr, "%s", "Anzahl muss midestens 1 sein");
        return 0;
    }

    int *a = (int*) calloc(n, sizeof (int));
    if (a == NULL)
    {
        fprintf(stderr, "%s", "Speicher-Fehler");
        return 1;
    }

    printf("Unsortiertes Array:");

    //Zufallgenerator
    srand((unsigned int) time(NULL));
}
