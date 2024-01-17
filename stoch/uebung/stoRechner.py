# input um methode zu waehlen (while schleife)
import math


def bernoulli_verteilung():
    # Eingabe:
    n = int(input("n: "))
    p = float(input("p: "))
    k = int(input("k: "))

    # Berechnung:
    q = 1 - p
    n_fak = math.factorial(n)
    k_fak = math.factorial(k)
    n_k_fak = math.factorial(n - k)
    p_k = p ** k
    q_n_k = q ** (n - k)
    b = (n_fak / (k_fak * n_k_fak)) * p_k * q_n_k

    # Ausgabe:
    print("===========================")
    print("q: \t\t\t", q)
    print("n!: \t\t\t", n_fak)
    print("k!: \t\t\t", k_fak)
    print("(n-k)!: \t\t", n_k_fak)
    print("p^k: \t\t\t", p_k)
    print("q^(n-k): \t\t", q_n_k)
    print("B: \t\t\t", b)


def bino_verteilung():
    # Eingabe:
    n = int(input("n: "))
    p = float(input("p: "))
    k = int(input("k: "))

    # Berechnung:
    q = 1 - p
    n_fak = math.factorial(n)
    k_fak = math.factorial(k)
    n_k_fak = math.factorial(n - k)
    p_k = p ** k
    q_n_k = q ** (n - k)
    b = (n_fak / (k_fak * n_k_fak)) * p_k * q_n_k

    # Ausgabe:
    print("===========================")
    print("q: \t\t\t", q)
    print("n!: \t\t\t", n_fak)
    print("k!: \t\t\t", k_fak)
    print("(n-k)!: \t\t", n_k_fak)
    print("p^k: \t\t\t", p_k)
    print("q^(n-k): \t\t", q_n_k)
    print("B: \t\t\t", b)


def geo_verteilung():
    # Eingabe:
    p = float(input("p: "))
    k = int(input("k: "))

    # Berechnung:
    q = 1 - p
    p_k = p ** k
    q_k_1 = q ** (k - 1)
    g = p_k * q_k_1

    # Ausgabe:
    print("===========================")
    print("q: \t\t\t", q)
    print("p^k: \t\t\t", p_k)
    print("q^(k-1): \t\t", q_k_1)
    print("G: \t\t\t", g)


def poisson_verteilung():
    # Eingabe:
    l = float(input("lambda: "))
    k = int(input("k: "))

    # Berechnung:
    l_k = l ** k
    e_l = math.e ** (-l)
    k_fak = math.factorial(k)
    p = (l_k * e_l) / k_fak

    # Ausgabe:
    print("===========================")
    print("lambda: \t\t", l)
    print("lambda^k: \t\t", l_k)
    print("e^(-lambda): \t\t", e_l)
    print("k!: \t\t\t", k_fak)
    print("P: \t\t\t", p)


while True:
    print("1: Bernoulli-Verteilung")
    print("2: Binomial-Verteilung")
    print("3: Geometrische-Verteilung")
    print("4: Poisson-Verteilung")
    print("5: Exit")
    auswahl = int(input("Auswahl: "))
    if auswahl == 1:
        bernoulli_verteilung()
    elif auswahl == 2:
        bino_verteilung()
    elif auswahl == 3:
        geo_verteilung()
    elif auswahl == 4:
        poisson_verteilung()
    elif auswahl == 5:
        break
    else:
        print("Ungueltige Eingabe")
