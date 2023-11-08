#init vars
addi $s3, $s3, 10 #n = 10
addi $s0, $s0, 0 #a = 0
addi $s1, $s1, 1 #b = 1

While:
addi $s3, $s3, -1 #n = n - 1
slt $t0, $s3, $zero
bne $t0, $zero, Exit

add $s2, $s0, $s1 # c = a + b
add $s0, $s1, $zero # a = b
add $s1, $s2, $zero # B = c

j While

Exit: