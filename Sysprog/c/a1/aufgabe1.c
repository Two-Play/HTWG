#include <stdio.h>
#include <stdbool.h>

int main(void)
{
    int zahlInt = 1;
    short zahlShort = 2;
    long  zahlLong = 3L;
    long long  zahlLL = -4LL;

    unsigned int bytefolge = 0xffffffffU;
    unsigned short byteShort = 0xffffU;
    unsigned long ul = 23;
    unsigned long long ull = 3;

    float pi = 3.14f;
    double piD = 3.14;
    long double ld = 1.3132;

    const char zeichen = 'a';
    const char* s = "Hallo";
    unsigned char uch = 0xff;
    signed char sch = -1;

    bool bo = true;

    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %d\n", (void*) &zahlInt, sizeof(zahlInt), "int", "zahlInt", zahlInt);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %d\n", (void*) &zahlShort, sizeof(zahlShort), "short", "zahlShort", zahlShort);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %ld\n", (void*) &zahlLong, sizeof(zahlLong), "long", "zahlLong", zahlLong);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %lld\n", (void*) &zahlLL, sizeof(zahlLL), "long long", "zahlLL", zahlLL);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %u\n", (void*) &bytefolge, sizeof(bytefolge), "unsigned int", "bytefolge", bytefolge);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %hu\n", (void*) &byteShort, sizeof(byteShort), "unsigned short", "byteShort", byteShort);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %lu\n", (void*) &ul, sizeof(ul), "unsigned long", "ul", ul);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %llu\n", (void*) &ull, sizeof(ull), "unsigned long long", "ull", ull);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %f\n", (void*) &pi, sizeof(pi), "float", "pi", pi);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %lf\n", (void*) &piD, sizeof(piD), "double", "piD", piD);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %Lf\n", (void*) &ld, sizeof(ld), "long double", "ld", ld);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %c\n", (void*) &zeichen, sizeof(zeichen), "char", "zeichen", zeichen);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %s\n", (void*) &s, sizeof(s), "char*", "s", s);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %u\n", (void*) &uch, sizeof(uch), "unsigned char", "uch", uch);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %d\n", (void*) &sch, sizeof(sch), "signed char", "sch", sch);
    printf("Adresse: %p\t Platzbedarf: %zu\t Typ: %s\t Name: %s\t Wert: %d\n", (void*) &bo, sizeof(bo), "bool", "bo", bo);

    printf("\n\n");

    if (sizeof(int) == 4 && sizeof(long) == 4 && sizeof(char * ) == 4) {
        printf("ILP32\n");
    } else if (sizeof(int) == 4 && sizeof(long) == 8 && sizeof(char * ) == 8) {
        printf("LP64 Modell\n");
    } else if (sizeof(int) == 4 && sizeof(long long) == 8 && sizeof(char * ) == 8) {
        printf("LLP64 Modell\n");
    } else {
        printf("Kein Modell gefunden");
    }

    return 0;
}
