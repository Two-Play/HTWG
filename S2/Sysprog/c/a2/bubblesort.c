//
// Created by Philippe Westenfelder on 26.10.23.
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, const char *argv[])
{
    //Zufallgenerator
    srand((unsigned int) time(NULL));

    //args überprüen
    if (argc != 2)
    {
        fprintf(stderr, "%s", "Aufruf: java Bubblesort Anzahl");
        return 0;
    }

    //Array Deklaration und init
    int n = atoi(argv[1]);
    int *a = (int*) calloc(n, sizeof (int));
    //fehlerbehandlung
    if (a == NULL)
    {
        fprintf(stderr, "%s", "Speicher-Fehler");
        return 1;
    }

    //Zahlen einlesen
    printf("Bitte %d ganze Zahlen eingeben: ", n);
    int k = 0;
    for (int i = 0; i < n; i++){
        if (scanf("%d", &a[i]) == 1)
        {
            ++k;
        }
    }

    for (int i = k; i < n; ++i) {
        a[i] = rand();
        printf("%d\n", a[i]);
    }



    //Zahlen sortieren
    for (int i = n; i > 1; --i)
    {
        // groessten Wert nach hinten schieben
        for (int j = 0; j < i - 1; ++j)
        {
            if (a[j] > a[j + 1])
            {
                // Werte tauschen
                int tmp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = tmp;
            }
        }
    }

    //Zahlen ausgeben
    printf("Sortierte Zahlen: \n");
    for (int i = 0;i < n; i++)
    {
        printf("%d\n", a[i]);
    }

    //Array Speicher freigeben
    free(a);
}
