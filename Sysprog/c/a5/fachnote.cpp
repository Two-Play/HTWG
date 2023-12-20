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


fachnote::fachnote(const std::string &f, const benotung &n)
: fach(f), note(n.int_value())
{
    if (f.empty())
    {
        throw std::invalid_argument("unzulaessigr Fachname mit Länge 0");
    }
}
