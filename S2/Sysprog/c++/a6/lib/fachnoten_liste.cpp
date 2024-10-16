#include "fachnoten_liste.h"
#include "fachnote.h"

class fachnoten_liste::element final
{
    element *next;
    fachnote *n;

    element(element *e, fachnote* m)
            : next(e), n(m)
    { }

    friend class fachnoten_liste;
    friend class fachnoten_liste::iterator;
};

fachnoten_liste::iterator::iterator(element *e)
        : current(e)
{ }

//liefert true, wenn die beiden current-Pointer ungleich sind
bool fachnoten_liste::iterator::operator!=(const iterator& i) const
{
    return this->current != i.current;
}

fachnote* fachnoten_liste::iterator::operator*() const
{
    return this->current->n;
}

fachnoten_liste::iterator& fachnoten_liste::iterator::operator++()
{
    this->current = this->current->next;
    return *this;
}

fachnoten_liste::fachnoten_liste(void (*f)(fachnote* n))
        : head(nullptr), function(f)
{ }

fachnoten_liste::~fachnoten_liste()
{
    element *e = this->head;
    while (e != nullptr)
    {
        element *x = e;
        function(e->n);
        e = e->next;
        delete x;
    }
}

fachnoten_liste& fachnoten_liste::insert(fachnote* n)
{
    this->head = new element(this->head, n);
    return *this;
}

fachnoten_liste::iterator fachnoten_liste::begin()
{
    return fachnoten_liste::iterator(this->head);
}

fachnoten_liste::iterator fachnoten_liste::end()
{
    return fachnoten_liste::iterator(nullptr);
}