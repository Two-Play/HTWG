/*
 * einstieg.c
 *
 * Das erste C-Programm gibt einen Text aus.
 *
 * Autor: Philippe Westenfelder
 * Erstellt am: 8.10.23
 */

#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    printf("Anzahl bisher geschriebener C-Programme eingeben: ");
    int anzahl;
    scanf("%d", &anzahl);

    printf("Vorname eingeben: ");
    char *vorname = (char*) malloc(8);
    scanf("%s", vorname);

    printf("%ss %d. C-Programm funktioniert!\n", vorname, anzahl + 1);

    return 0;
}
