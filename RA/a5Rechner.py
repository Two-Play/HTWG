
import math


def direct_mapped_cache():
    # Eingabe:
    address = int(input("Adresse: "))
    blockgroesse = int(input("Blockgroesse: "))
    blockanzahl = int(input("Blockanzahl: "))

    # Berechnung:
    blocknummer = math.floor(address / blockgroesse)
    offset = address % blockgroesse
    blockindex = blocknummer % blockanzahl
    tag = math.floor(blocknummer / blockanzahl)

    # Ausgabe:
    print("===========================")
    print("Blocknummer: \t", blocknummer)
    print("Blockindex: \t", blockindex)
    print("Tag: \t\t\t", tag)
    print("Offset: \t\t", offset)


def two_way_associative_cache():
    # Eingabe:
    address = int(input("Adresse: "))
    sets = int(input("Anzahl der Sets: "))
    blockgroesse = int(input("Blockgroesse: "))


    # Berechnung:
    blocknummer = math.floor(address / blockgroesse)
    setindex = blocknummer % sets
    offset = address % blockgroesse
    tag = math.floor(blocknummer / sets)

    # Ausgabe:
    print("===========================")
    print("Blocknummer: \t", blocknummer)
    print("Setindex: \t\t", setindex)
    print("Tag: \t\t\t", tag)
    print("Offset: \t\t", offset)



# input um methode zu waehlen (while schleife)
while True:
    print("1: Direct-Mapped-Cache")
    print("2: 2-Way-Associative-Cache")
    print("3: Exit")
    auswahl = int(input("Auswahl: "))
    if auswahl == 1:
        direct_mapped_cache()
    elif auswahl == 2:
        two_way_associative_cache()
    elif auswahl == 3:
        break
    else:
        print("Ungueltige Eingabe")