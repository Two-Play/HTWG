/*
 * benotung.cpp
 *
 * Wertklasse zur Repräsentation einer Note.
 *
 * Autor: Philippe Westenfelder
 * Version: 09.12.23
 */

#include "benotung.h"
#include <stdexcept>
#include <string>
#include <iostream>

// Initialisierung der statischen Attribute
benotung benotung::beste = benotung(10);
benotung benotung::schlechteste = benotung(40);

benotung::benotung(int n)
// Initialisierungsliste
: note(n)
{
    // Prüfe, ob Note im zulässigen Bereich liegt
    switch(n)
    {
        case 10:
        case 13:
        case 17:
        case 20:
        case 23:
        case 27:
        case 30:
        case 33:
        case 37:
        case 40:
        case 50:
            break;
        default:
            throw std::invalid_argument("unzulässige Note " + std::to_string(n));
            //std::cerr << "unzulässige Note " << n << '\n';
    }
}

int benotung::int_value() const {
    return this->note;
}

bool benotung::ist_bestanden() const {
    if (this->note <= benotung::schlechteste.int_value())
    {
        return true;
    }
    return false;
}

bool operator==(const benotung &n1, const benotung &n2) {
    return n1.note == n2.note;
}
