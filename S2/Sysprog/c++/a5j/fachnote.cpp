/*
 * fachnote.cpp
 *
 * Entität zur Repräsentation einer Fachnote.
 *
 * Autor: Philippe Westenfelder
 * Version: 09.12.23
 */

#include "fachnote.h"
#include <stdexcept>
#include <iostream>


fachnote::fachnote(const std::string &f, const benotung &n)
: fach(f), note(n)
{
    if (f.size() == 0)
    {
        throw std::invalid_argument("unzulässiger Fachname mit Länge 0");
    }
}
