//
// Created by philippe on 16.11.23.
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

//macht dadurch die Funktion bekannt
void bubblesort(char** a, int n);


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

    //Zufallgenerator
    srand((unsigned int) time(NULL));

    char **a = (char **) malloc(n * sizeof(char*));
    if (a == NULL)
    {
        fprintf(stderr, "%s", "Speicher-Fehler");
        return 1;
    }

    // length of argv[1] + '\0'
    int m = sizeof(argv[1]) + 1;

    printf("Unsortiertes Array:\n");

    // Strings würfeln
    for (int i = 0; i < n; ++i) {
        char* tmp = (char*) malloc(m * sizeof(char));

        int r = rand() % n;

        if (tmp == NULL)
        {
            fprintf(stderr, "%s", "Speicher-Fehler");
            return 1;
        }

        sprintf(tmp, "%d", r);
        a[i] = tmp;
        printf("%s ", a[i]);
    }

    printf("\n");

    // Strings sortieren
    bubblesort(a, n);

    // Strings ausgeben
    printf("Sortiertes Array:\n");
    printf("%s", a[0]);
    for (int i = 1; i < n; ++i) {
        if (strncmp(a[i], a[i - 1], strlen(a[i])) == 0)
        {
            printf("*");
        } else
        {
            printf(" %s", a[i]);
        }
    }

    printf("\n");

    for (int i = 0; i < n; ++i) {
        free(a[i]);
    }

    free(a);

}


void bubblesort(char** a, int n)
{
    for (int i = n; i > 1 ; --i) {
        for (int j = 0; j < i - 1; ++j) {
            if (strncmp(a[j], a[j + 1], strlen(a[j])) > 0)
            {
                char* tmp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = tmp;
            }
        }
    }
}