# Prozedur ISODD, um zu überprüfen, ob eine Zahl ungerade ist
# Argument: $a0 - der Integer-Wert, der überprüft werden soll
# Rückgabewert: $v0: 1, wenn x ungerade ist, 0 wenn x gerade ist

.data
prompt1: .asciiz "enter value:"
prompt2: .asciiz "result $s1 is:"
prompt3: .asciiz "result $s2 is:"
lf:      .asciiz "\n"

# Hauptprogramm zum Testen der Prozeduren
.text
.globl main
main:

    li  $v0, 4                  # print string service
    la  $a0, prompt1            # write("enter value:")
    syscall
    li  $v0, 5                  # read integer
    syscall
    
    # Testen von ISODD: Überprüfen, ob die Zahl als Argument ungerade ist
    move $a0, $v0        # Setze die Zahl, die überprüft werden soll
    
    li $v0, 0            #clear $v0
    
    jal ISODD            # Aufruf der ISODD-Prozedur
    move $s1, $v0        # Speichern des Ergebnisses in $s1


    li  $v0, 4                  # print string service
    la  $a0, prompt1            # write("enter value:")
    syscall
    li  $v0, 5                  # read integer
    syscall


    # Testen von ISEVEN: Überprüfen, ob die Zahl als Argument gerade ist
    move $a0, $v0            # Setze die Zahl, die überprüft werden soll 
    
    li $v0, 0            #clear $v0
    
    jal ISEVEN           # Aufruf der ISEVEN-Prozedur

    move $s2, $v0        # Speichern des Ergebnisses in $s2
    
    
    li  $v0, 4                  # print string service
    la  $a0, prompt2            # write("result is:")
    syscall                                
    li   $v0, 1                 # print integer service
    move $a0, $s1               # write ($s1)
    syscall   
    
    li  $v0, 4                  # print string service
    la  $a0, lf                 # write line feed
    syscall
    
    li  $v0, 4                  # print string service
    la  $a0, prompt3            # write("result is:")
    syscall                                
    li   $v0, 1                 # print integer service
    move $a0, $s2               # write ($s1)
    syscall   
    li  $v0, 4                  # print string service
    la  $a0, lf                 # write line feed
    syscall
    
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