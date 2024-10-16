.data
#G: .word 0   # Speicherplatz für die Funktion G(n)

.text
.globl G

# Prozedur zur Implementierung der Funktion f(n, k)
# $a0 = n, $a1 = k
G:
    # Prolog: Register sichern gemäß MIPS-Konventionen
    addi $sp, $sp, -8     # Platz für $ra und $s0 im Stack reservieren
    sw $ra, 0($sp)        # Sichern von $ra
    sw $s0, 4($sp)        # Sichern von $s0

    bge $a1, $a0, greater_than_7   # Vergleich: k - n > 7
    addi $v0, $zero, 8             # Setze Rückgabewert auf 8 (Basenfall)
    j recursive_call               # Springe zur Rekursion

greater_than_7:
    add $v0, $a0, $a1   # Berechnung: n + k
    addi $v0, $v0, 5    # n + k + 5

recursive_call:
    addi $a0, $a0, -1       # n - 1 für den rekursiven Aufruf
    jal G                   # Rekursiver Aufruf von G mit Argument n - 1

    move $s0, $v0           # Speichern des Rückgabewerts von G(n-1) in $s0

    jal max                 # Aufruf der Funktion max(g(k))

    move $a1, $v0           # Übergabe des Rückgabewerts von max(g(k)) an $a1 für den rekursiven Aufruf

    jal G                   # Rekursiver Aufruf von G mit aktualisierten Argumenten n-1 und max(g(k))

    # Epilog: Wiederherstellen der gesicherten Register und Rückkehr zur Aufrufadresse
    lw $ra, 0($sp)         # Wiederherstellen von $ra
    lw $s0, 4($sp)         # Wiederherstellen von $s0
    addi $sp, $sp, 8       # Stack-Bereinigung für den Rückgabesprung
    jr $ra                 # Rückkehr zur Aufrufadresse

# Funktion max(a, b) zum Finden des Maximums von zwei Zahlen
max:
    sub $v0, $a0, $a1    # $v0 = a - b
    bgez $v0, max_end    # Wenn a - b >= 0, springe zu max_end

    move $v0, $a1        # Wenn a - b < 0, setze $v0 = b (b ist größer als a)

max_end:
    jr $ra               # Rückkehr zur Aufrufadresse
