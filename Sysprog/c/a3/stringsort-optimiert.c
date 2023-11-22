//
// Created by philippe on 16.11.23.
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>



// Bubblesort-Funktion, die denselben Funktionsprototyp wie qsort verwendet
int bubblesort(void *base, size_t num, size_t width, int (*compar)(const void *, const void *)) {
    char* tmp = malloc(width);
    if (tmp == NULL) {
        printf("Fehler bei malloc (tmp)\n");
        return 1;
    }

    for (int i = num; i > 1; --i) {
        for (int j = 0; j < i - 1; ++j) {
            char* a = (char*) base + j * width;
            char* b = (char*) base + (j + 1) * width;
            if (compar(a, b) > 0) {
                memcpy(tmp, a, width);
                memcpy(a, b, width);
                memcpy(b, tmp, width);
            }
        }
    }

    free(tmp);

    return 0;
}



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

    // length of argv[1] + '\0'
    const int m = sizeof(argv[1]) + 1;

    char *a = malloc(n * m);
    if (a == NULL)
    {
        fprintf(stderr, "%s", "Speicher-Fehler");
        return 1;
    }

    printf("Unsortiertes Array:\n");

    // Strings würfeln
    for (int i = 0; i < n; ++i) {
        //unsgned wegen overflow error
        const unsigned int r = rand() % n;

        sprintf((char*) a + i * m, "%u", r);
        printf("%s ", (char*) a + i * m);

    }

    printf("\n");

    // Strings sortieren
    bubblesort(a, n, m, (int (*)(const void *, const void *)) strcmp);

    printf("Sortiertes Array:\n");
    //printf("%s", a[0]);
    //String append
    printf("%s", (char*) a);

    for (int i = 1; i < n; ++i) {
        const char *curr = (char*) a + i * m;
        const char *prev = (char*) a + (i - 1) * m;

        if (strncmp(curr, prev, m) == 0) {
            printf("*");
        } else {
            printf(" %s", curr);
        }
    }

    printf("\n");

    free(a);

}
