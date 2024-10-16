/*
 * fachnote.cpp
 *
 * Entität zur Repräsentation einer Fachnote.
 *
 * Autor: J.Middelberg
 * Version: 09.12.23
 */

#include "fachnote.h"
#include <stdexcept>


fachnote::fachnote(const std::string &f, const benotung &n)
: fach(f), note(n)
{
    if (f.empty())
    {
        throw std::invalid_argument("unzulaessiger Fachname mit Länge 0");
    }
}
