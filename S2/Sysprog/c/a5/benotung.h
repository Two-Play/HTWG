/*
 * benotung.h
 *
 * Wertklasse zur Repräsentation einer Note.
 *
 * Autor: J.Middelberg
 * Version: 09.12.23
 */

#ifndef BENOTUNG_H
#define BENOTUNG_H


class benotung final
{
private:
    int note;
public:
    benotung(int);

    static benotung beste;
    static benotung schlechteste;

    int int_value() const;
    bool ist_bestanden() const;
    friend bool operator==(const benotung &, const benotung &);
};

#endif //BENOTUNG_H
