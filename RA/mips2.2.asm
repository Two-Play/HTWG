# Prozedur ISODD, um zu überprüfen, ob eine Zahl ungerade ist
# Argument: $a0 - der Integer-Wert, der überprüft werden soll
# Rückgabewert: $v0: 1, wenn x ungerade ist, 0 wenn x gerade ist

# Hauptprogramm zum Testen der Prozeduren
.text
.globl main
main:
    # Testen von ISODD: Überprüfen, ob die Zahl 7 (ungerade) als Argument ungerade ist
    lw $a0, x            # Lade die Zahl, die überprüft werden soll (hier: 7)
    jal ISODD            # Aufruf der ISODD-Prozedur
    move $s1, $v0        # Speichern des Ergebnisses in $s1

    # Testen von ISEVEN: Überprüfen, ob die Zahl 7 (ungerade) als Argument gerade ist
    lw $a0, x            # Lade die Zahl, die überprüft werden soll (hier: 7)
    
    jal ISEVEN           # Aufruf der ISEVEN-Prozedur

    move $s2, $v0        # Speichern des Ergebnisses in $s2
    
    # Programmende
    li $v0, 10
    syscall
    
    
ISODD:
    andi $v0, $a0, 1     # Überprüfen des LSB, ob die Zahl ungerade ist
    jr $ra               # Rückkehr zur Aufrufadresse

# Prozedur ISEVEN, komplementär zur Prozedur ISODD
# Argument: $a0 - der Integer-Wert, der überprüft werden soll
# Rückgabewert: $v0: 1, wenn x gerade ist, 0 wenn x ungerade ist (invertiert das Ergebnis von ISODD)

ISEVEN:
    sw $ra, 0($sp)	 # Speicher return addr auf Stack
    jal ISODD            # Aufruf der ISODD-Prozedur, um zu überprüfen, ob die Zahl ungerade ist
    seq $v0, $zero, $v0   # Invertieren des Ergebnisses von ISODD (wenn ungerade, setze $v0 auf 0, sonst auf 1)
    lw $ra, 0($sp)	  # Lade return addr von Stack
    jr $ra                # Rückkehr zur Aufrufadresse


.data
.align 0
x:
	.word 7