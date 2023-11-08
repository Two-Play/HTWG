#init
addi $s0, $s0, 5
addi $s1, $s1, -3

#Start prog
sub $t0, $s0, $s1
slt $t1, $t0, $zero
bne $t1, $zero, ELSE
or $s2, $zero, $zero #reset $s2
add $s2, $s2, $t0 #c=a-b
j exit

ELSE:
nor $t2, $t0, $zero
addi $t3, $t2, 1
or $s2, $zero, $zero #reset $s2
add $s2, $s2, $t3 #c=a-b
exit: