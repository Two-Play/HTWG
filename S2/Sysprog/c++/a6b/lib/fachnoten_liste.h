/*
 * fachnoten_liste.h
 *
 * Verkettete Liste zur Speicherung von Fachnoten
 *
 * Autor: J.Middelberg
 * Version: 09.12.23
 */

#ifndef FACHNOTEN_LISTE_H
#define FACHNOTEN_LISTE_H


#include "fachnote.h"

typedef void (*delete_fachnote_fp)(fachnote *);

class fachnoten_liste final
{
private:
    delete_fachnote_fp delete_fachnote;
    class element;
    element *head;

public:
    explicit fachnoten_liste (void (*delete_fachnote)(fachnote *));
    ~fachnoten_liste();
    fachnoten_liste(const fachnoten_liste&) = delete;
    fachnoten_liste& operator=(const fachnoten_liste&) = delete;
    fachnoten_liste (fachnoten_liste&&) = delete;
    fachnoten_liste& operator=(fachnoten_liste&&) = delete;

    fachnoten_liste& insert(fachnote *);

    class iterator final
    {
    private:
        element *current;
        explicit iterator(element*);
    public:
        bool operator!=(const iterator&) const;
        fachnote* operator*() const;
        iterator& operator++();

        friend class fachnoten_liste;
    };

    iterator begin();
    iterator end();
};


#endif //FACHNOTEN_LISTE_H
