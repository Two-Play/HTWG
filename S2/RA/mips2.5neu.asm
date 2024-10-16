# int f(int n, int k) {
.text

j Start

.include "Testprozedur G.asm"

F:
addi $sp,$sp, -4
sw $ra, 0($sp)

sub $t0, $a1, $a0 # k-n
addi $t1, $zero, 7
slt $t0, $t1, $t0 # 7<k-n => 1
beq $t0, $zero, else

# return n+k+5
addi $v0, $zero, 5
add $v0, $v0, $a0
add $v0, $v0, $a1
j return
else:
# secure vars
add $sp, $sp, -8
sw $a0, 0($sp)
sw $a1, 4($sp)

# g(k)
move $a0, $a1
jal G

# recover vars
lw $a0, 0($sp)
lw $a1, 4($sp)
add $sp, $sp, 8

# max(8, g(k))
slti $t0, $v0, 8 # g(k) < 8 => 1
beq $t0, $zero, gGreater
addi $a1, $zero, 8
j returnF
gGreater:
move $a1, $v0
returnF: # return f(n-1,max(8,g(k)))
addi $a0, $a0, -1 # n-1
jal F
return:
lw $ra, 0($sp)
addi $sp, $sp, 4
jr $ra

Start:
li $a0, 123
li $a1, 456
jal F