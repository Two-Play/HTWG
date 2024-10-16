//
// Created by philippe on 16.11.23.
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

//macht dadurch die Funktion bekannt
void bubblesort(const char** a, int n);

int main(int argc, const char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "%s", "Aufruf: java Bubblesort Anzahl");
        return 0;
    }

    const int n = atoi(argv[1]);

    if (n < 1) {
        fprintf(stderr, "%s", "Anzahl muss midestens 1 sein");
        return 0;
    }

    //Zufallgenerator
    srand(( int) time(NULL));

    //** = String Array
    char **a = (char **) malloc(n * sizeof(char*));
    if (a == NULL)
    {
        fprintf(stderr, "%s", "Speicher-Fehler");
        return 1;
    }

    // length of argv[1] + '\0'
    const size_t m = strlen(argv[1]) + 1;

    printf("Unsortiertes Array:\n");

    // Strings würfeln
    for (int i = 0; i < n; ++i) {

        //String
        char* tmp = (char*) malloc(m * sizeof(char));
        if (tmp == NULL)
        {
            fprintf(stderr, "%s", "Speicher-Fehler");
            return 1;
        }

        //unsgned wegen overflow error
        const unsigned int r = rand() % n;

        sprintf(tmp, "%u", r);
        a[i] = tmp;
        printf("%s ", a[i]);

    }

    printf("\n");

    // Strings sortieren
    bubblesort((const char **) a, n);

    printf("Sortiertes Array:\n");
    //printf("%s", a[0]);
    //String append
    char* stringbuilder = (char*) malloc(n * m);
    if (stringbuilder == NULL)
    {
        fprintf(stderr, "%s", "Speicher-Fehler");
        return 1;
    }
    //stringbuilder[0] = (char) a[0];
    sprintf(stringbuilder, "%s", a[0]);
    //strcat(stringbuilder, a[0]);
    for (int i = 1; i < n; ++i) {
        if (strncmp(a[i], a[i - 1], strlen(a[i])) == 0)
        {
            //printf("*");
            strcat(stringbuilder, "*");
        } else
        {
            //printf(" %s", a[i]);
            strcat(stringbuilder, " ");
            strcat(stringbuilder, a[i]);
        }
    }

    // String ausgeben
    printf("%s\n", stringbuilder);

    for (int i = 0; i < n; ++i) {
        free(a[i]);
    }

    free(stringbuilder);
    free(a);

}

void bubblesort(const char** a, int n)
{
    for (int i = n; i > 1 ; --i) {
        for (int j = 0; j < i - 1; ++j) {
            if (strncmp(a[j], a[j + 1], strlen(a[j])) > 0)
            {
                const char* tmp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = tmp;
            }
        }
    }
}
