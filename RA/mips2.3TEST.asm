.data
A: .word 3, 4, 6, 8, 11, 13  # Array A mit den gegebenen Elementen
B: .space 24                 # Platz für Array B (n x 4 Bytes)

.text
.globl main
main:
    # Laden der Adresse von Array A in $a0
    la $a0, A

    # Laden der Adresse von Array B in $a1
    la $a1, B

    # Anzahl der Elemente im Array A
    li $t0, 6  # Für das gegebene Beispiel mit 6 Elementen

    # Aufruf der Prozedur evenElem
    jal evenElem

    # Rückgabewert (Anzahl der geraden Elemente) in $s0 speichern
    move $s0, $v0

    # Programmende
    #li $v0, 10   # Syscall-Code für Programmende
    #syscall

# Prozedur evenElem: Filtert gerade Elemente aus Array A und schreibt sie in Array B
# Argumente: $a0 - Adresse von Array A, $a1 - Adresse von Array B, $a2 - Anzahl der Elemente
evenElem:
    # Initialisierung der Variablen i und j
    li $t1, 0   # i = 0 (Array A Index)
    li $t2, 0   # j = 0 (Array B Index)

    # Schleife für die Verarbeitung der Elemente von Array A
    loop:
        bge $t1, $a2, end_loop   # Wenn i >= n, beende die Schleife

        # Laden des aktuellen Elements aus Array A in $t3
        lw $t3, 0($a0)

        # Aufruf der Prozedur isEven, um zu überprüfen, ob das Element gerade ist
        move $a0, $t3  # Übergeben des aktuellen Elements an isEven
        jal isEven     # Aufruf der Prozedur isEven

        # Wenn das Element gerade ist, schreibe es in Array B und erhöhe j
        beq $v0, $zero, not_even   # Wenn isEven false (0), springe zu not_even

        # Element von Array A an Array B schreiben
        sw $t3, 0($a1)   # Schreibe das gerade Element in Array B
        addi $a1, $a1, 4  # Erhöhe die Adresse für Array B um 4 Bytes (Größe eines Elements)
        addi $t2, $t2, 1  # Erhöhe j um 1

        not_even:
        addi $t1, $t1, 1   # Erhöhe i um 1 (Index von Array A)
        addi $a0, $a0, 4   # Erhöhe die Adresse für Array A um 4 Bytes (Größe eines Elements)
        j loop             # Springe zurück zur Schleife

    end_loop:
    move $v0, $t2   # Rückgabe: Anzahl der geraden Elemente in $v0
    jr $ra          # Rückkehr zur Aufrufadresse

# Prozedur isEven: Überprüft, ob ein Element gerade ist (Rückgabewert 1 für gerade, 0 für ungerade)
# Argument: $a0 - das zu überprüfende Element
isEven:
    andi $v0, $a0, 1  # Überprüfen des LSB, ob das Element ungerade ist
    xori $v0, $v0, 1  # Invertieren des Ergebnisses, um zu überprüfen, ob das Element gerade ist
    jr $ra            # Rückkehr zur Aufrufadresse
