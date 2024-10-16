/*
 * fachnote.h
 *
 * Entität zur Repräsentation einer Fachnote.
 *
 * Autor: Philippe Westenfelder
 * Version: 09.12.23
 */

#ifndef FACHNOTE_H
#define FACHNOTE_H

#include <string>
#include "benotung.h"


class fachnote final
{
private:

public:
    fachnote (const std::string&, const benotung&);
    fachnote (const fachnote&) = delete;
    fachnote& operator=(const fachnote&) = delete;
    fachnote (fachnote&&) = delete;
    fachnote& operator=(fachnote&&) = delete;

    const std::string fach;
    const benotung note;
};


#endif //FACHNOTE_H
