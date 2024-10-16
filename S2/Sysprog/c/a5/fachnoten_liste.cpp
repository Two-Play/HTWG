/*
 * fachnoten_liste.cpp
 *
 * .
 *
 * Autor: J.Middelberg
 * Version: 09.12.23
 */

#include "fachnoten_liste.h"
#include "fachnote.h"


class fachnoten_liste::element final
{
    element *next;
    const fachnote fnote;

    element(element *e, const fachnote &f)
    : next(e), fnote(f.fach, f.note)
    { }

    friend class fachnoten_liste;
    friend class fachnoten_liste::iterator;
};

fachnoten_liste::fachnoten_liste(void (*delete_fachnote)(fachnote *)) {

}

fachnoten_liste::~fachnoten_liste() {

}

fachnoten_liste &fachnoten_liste::insert(fachnote *) {
    return <#initializer#>;
}

fachnoten_liste::iterator fachnoten_liste::begin() {
    return fachnoten_liste::iterator(nullptr);
}

fachnoten_liste::iterator fachnoten_liste::end() {
    return fachnoten_liste::iterator(nullptr);
}

fachnoten_liste::iterator::iterator(fachnoten_liste::element *) {

}

bool fachnoten_liste::iterator::operator!=(const fachnoten_liste::iterator &) const {
    return false;
}

fachnote &fachnoten_liste::iterator::operator*() const {
    return <#initializer#>;
}

fachnoten_liste::iterator &fachnoten_liste::iterator::operator++() {
    return <#initializer#>;
}
