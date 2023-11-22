# Rückgabewert: $v0: 1, wenn x ungerade ist, 0 wenn x gerade ist
.data

B:
    .space 24  # Integer hat 4 byte X 6 platzhalter = 24 bytes

A:
    .word 3, 4, 6, 8, 11, 13

# Hauptprogramm zum Testen der Prozeduren
.text
.globl main
main:
    # Laden der Adresse von Array A in $a0
    la $a0, A
    # Laden der Adresse von Array B in $a1
    la $a1, B
    # Anzahl der Elemente im Array A
    li $t0, 6
        addi $sp,$sp, -4
    sw $s7, 0($sp)	 # Speicher return addr auf Stack !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    # Aufruf der Prozedur EVENELEM
    jal EVENELEM
    
    # Speichern des Ergebnisses in $s0
    move $s0, $v0
    
    # Programmende
    li $v0, 10
    syscall
    
    
EVENELEM:
    # Initialisierung der Variablen i und j
    li $t1, 0   # i = 0 (Array A Index)
    li $t2, 0   # j = 0 (Array B Index)
    
	while:
    	    bge $t1, $t0, endWhile   # Wenn i >= n, beende die Schleife
    
    	    # Laden des aktuellen Elements aus Array A in $t3
            lw $t3, 4($a0)
            # Nächste zahl vom Array zeigen
            #addi $a0, $a0, 4
    	    
    	    # Übergeben des aktuellen Elements an isEven
	    move $a3, $t3

            # Aufruf der Prozedur isEven, um zu überprüfen, ob das Element gerade ist
            jal ISEVEN
    
   	    # Wenn isEven false (0), springe zu not_even
            beq $v0, $zero, notEven   

       	    # Element von Array A an Array B schreiben
            sw $t3, 0($a1)   # Schreibe das gerade Element in Array B
            addi $a1, $a1, 4  # Erhöhe die Adresse für Array B um 4 Bytes (Größe eines Elements)
            addi $t2, $t2, 1  # Erhöhe j um 1

            notEven:
        	addi $t1, $t1, 1   # Erhöhe i um 1 (Index von Array A)
         	addi $a0, $a0, 4   # Erhöhe die Adresse für Array A um 4 Bytes (Größe eines Elements)
    
    	    j while
    
endWhile:
    move $v0, $t2   # Rückgabe: Anzahl der geraden Elemente in $v0
    lw $ra, ($s7)	  # Lade return addr von Stack !!!!!!!!!!!!!!!!!!!!!!!!!!
        addi $sp,$sp, 4
    jr $ra          # Rückkehr zur Aufrufadresse
    
ISODD:
    andi $v0, $a3, 1     # Überprüfen des LSB, ob die Zahl ungerade ist
    jr $ra               # Rückkehr zur Aufrufadresse

# Rückgabewert: $v0: 1, wenn x gerade ist, 0 wenn x ungerade ist (invertiert das Ergebnis von ISODD)

ISEVEN:
    addi $sp,$sp, -4
    sw $ra, 0($sp)	 # Speicher return addr auf Stack
    jal ISODD            # Aufruf der ISODD-Prozedur, um zu überprüfen, ob die Zahl ungerade ist
    seq $v0, $zero, $v0   # Invertieren des Ergebnisses von ISODD (wenn ungerade, setze $v0 auf 0, sonst auf 1)
    lw $ra, 0($sp)	  # Lade return addr von Stack
    addi $sp,$sp, 4
    jr $ra                # Rückkehr zur Aufrufadresse
