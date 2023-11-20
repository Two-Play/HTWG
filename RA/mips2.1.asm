# Assemblerprogramm, das ein Array mit n Integer-Werten im Speicher anlegt und die Summe berechnet
.text
.globl main
main:
    # Laden der Variable n in ein Register ($t0)
    lw $t0, n

    # Laden der Adresse des Arrays in ein Register ($t1)
    la $t1, array

    # Initialisierung der Summe im Register $v0 mit 0
    li $v0, 0
  
    # Schleife zur Berechnung der Summe der n Werte im Array
    loop:
        # Überprüfen, ob die Schleife beendet werden soll
        beq $t0, $zero, end_loop   # Wenn n = 0, beende die Schleife

        # Laden des aktuellen Werts aus dem Array und Addieren zur Summe
        lw $t2, 0($t1)      # Lade den Wert aus der aktuellen Adresse des Arrays
        add $v0, $v0, $t2  # Addiere den Wert zur Summe ($v0)

        # Inkrementieren des Array-Zeigers und Dekrementieren von n
        addi $t1, $t1, 4    # Inkrementiere die Adresse des Arrays um 4 (Größe eines Integers in Byte)
        addi $t0, $t0, -1   # Dekrementiere n um 1

        # Schleife erneut durchlaufen
        j loop

    # Ende der Schleife
    end_loop:

.data
.align 0
n: 
    .word 6           # Variable n mit Wert 6

array:               # Array A mit n Integer-Werten
    .word 2, 4, 6, 8, 10, 12
