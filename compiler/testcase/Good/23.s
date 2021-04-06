.globl main
.text
main:
subu $sp, $sp, 68
L33:
sw $fp, 36($sp)
add $a1, $sp, 64
move $fp, $a1
sw $ra, -32($fp)
sw $s7, -64($fp)
sw $s6, -60($fp)
sw $s5, -56($fp)
sw $s4, -52($fp)
sw $s3, -48($fp)
sw $s2, -44($fp)
sw $s1, -40($fp)
sw $s0, -36($fp)
sw $a0, 0($fp)
li $a1, 8
sw $a1, -8($fp)
add $a1, $fp, -12
move $s2, $a1
lw $a1, -8($fp)
move $a0, $a1
li $a1, 0
jal initArray
move $a1, $v0
sw $a1, ($s2)
add $a1, $fp, -16
move $s2, $a1
lw $a1, -8($fp)
move $a0, $a1
li $a1, 0
jal initArray
move $a1, $v0
sw $a1, ($s2)
add $a1, $fp, -20
move $s2, $a1
lw $a2, -8($fp)
lw $a1, -8($fp)
add $a1, $a2, $a1
sub $a1, $a1, 1
move $a0, $a1
li $a1, 0
jal initArray
move $a1, $v0
sw $a1, ($s2)
add $a1, $fp, -24
move $s2, $a1
lw $a1, -8($fp)
lw $a2, -8($fp)
add $a1, $a1, $a2
sub $a1, $a1, 1
move $a0, $a1
li $a1, 0
jal initArray
move $a1, $v0
sw $a1, ($s2)
move $a0, $fp
li $a1, 0
jal try
lw $a1, -36($fp)
move $s0, $a1
lw $a1, -40($fp)
move $s1, $a1
lw $a1, -44($fp)
move $s2, $a1
lw $a1, -48($fp)
move $s3, $a1
lw $a1, -52($fp)
move $s4, $a1
lw $a1, -56($fp)
move $s5, $a1
lw $a1, -60($fp)
move $s6, $a1
lw $a1, -64($fp)
move $s7, $a1
lw $a1, -32($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L32
L32:

addu $sp, $sp, 68
jr $ra
.text
try:
subu $sp, $sp, 56
L35:
sw $fp, 36($sp)
add $a2, $sp, 52
move $fp, $a2
sw $ra, -20($fp)
sw $s7, -52($fp)
sw $s6, -48($fp)
sw $s5, -44($fp)
sw $s4, -40($fp)
sw $s3, -36($fp)
sw $s2, -32($fp)
sw $s1, -28($fp)
sw $s0, -24($fp)
sw $a1, -4($fp)
sw $a0, 0($fp)
lw $a2, -4($fp)
lw $a1, 0($fp)
lw $a1, -8($a1)
beq $a2, $a1, L15
L16:
li $a1, 0
sw $a1, -8($fp)
lw $a1, 0($fp)
lw $a1, -8($a1)
sub $a1, $a1, 1
sw $a1, -12($fp)
lw $a2, -8($fp)
lw $a1, -12($fp)
ble $a2, $a1, L17
L13:
L14:
lw $a1, -24($fp)
move $s0, $a1
lw $a1, -28($fp)
move $s1, $a1
lw $a1, -32($fp)
move $s2, $a1
lw $a1, -36($fp)
move $s3, $a1
lw $a1, -40($fp)
move $s4, $a1
lw $a1, -44($fp)
move $s5, $a1
lw $a1, -48($fp)
move $s6, $a1
lw $a1, -52($fp)
move $s7, $a1
lw $a1, -20($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L34
L15:
lw $a1, 0($fp)
move $a0, $a1
jal printboard
j L14
L17:
lw $a1, 0($fp)
lw $a2, -12($a1)
lw $a1, -8($fp)
mul $a1, $a1, 4
add $a1, $a2, $a1
lw $a1, ($a1)
li $a2, 0
beq $a1, $a2, L26
L27:
li $a2, 0
L25:
li $a1, 0
bne $a2, $a1, L23
L24:
li $a1, 0
L22:
li $a2, 0
bne $a1, $a2, L20
L21:
L19:
lw $a2, -8($fp)
lw $a1, -12($fp)
bge $a2, $a1, L13
L18:
lw $a1, -8($fp)
add $a1, $a1, 1
sw $a1, -8($fp)
j L17
L26:
li $s2, 1
lw $a1, 0($fp)
lw $a0, -20($a1)
lw $a1, -8($fp)
lw $a2, -4($fp)
add $a1, $a1, $a2
mul $a1, $a1, 4
add $a1, $a0, $a1
lw $a1, ($a1)
li $a2, 0
beq $a1, $a2, L28
L29:
li $s2, 0
L28:
move $a2, $s2
j L25
L23:
li $s2, 1
lw $a1, 0($fp)
lw $a0, -24($a1)
lw $a1, -8($fp)
add $a2, $a1, 7
lw $a1, -4($fp)
sub $a1, $a2, $a1
mul $a1, $a1, 4
add $a1, $a0, $a1
lw $a2, ($a1)
li $a1, 0
beq $a2, $a1, L30
L31:
li $s2, 0
L30:
move $a1, $s2
j L22
L20:
li $a0, 1
lw $a1, 0($fp)
lw $a2, -12($a1)
lw $a1, -8($fp)
mul $a1, $a1, 4
add $a1, $a2, $a1
sw $a0, ($a1)
li $s2, 1
lw $a1, 0($fp)
lw $a0, -20($a1)
lw $a2, -8($fp)
lw $a1, -4($fp)
add $a1, $a2, $a1
mul $a1, $a1, 4
add $a1, $a0, $a1
sw $s2, ($a1)
li $s2, 1
lw $a1, 0($fp)
lw $a0, -24($a1)
lw $a1, -8($fp)
add $a1, $a1, 7
lw $a2, -4($fp)
sub $a1, $a1, $a2
mul $a1, $a1, 4
add $a1, $a0, $a1
sw $s2, ($a1)
lw $a0, -8($fp)
lw $a1, 0($fp)
lw $a2, -16($a1)
lw $a1, -4($fp)
mul $a1, $a1, 4
add $a1, $a2, $a1
sw $a0, ($a1)
lw $a2, 0($fp)
move $a0, $a2
lw $a1, -4($fp)
add $a1, $a1, 1
move $a1, $a1
jal try
li $a0, 0
lw $a1, 0($fp)
lw $a2, -12($a1)
lw $a1, -8($fp)
mul $a1, $a1, 4
add $a1, $a2, $a1
sw $a0, ($a1)
li $s2, 0
lw $a1, 0($fp)
lw $a0, -20($a1)
lw $a1, -8($fp)
lw $a2, -4($fp)
add $a1, $a1, $a2
mul $a1, $a1, 4
add $a1, $a0, $a1
sw $s2, ($a1)
li $s2, 0
lw $a1, 0($fp)
lw $a0, -24($a1)
lw $a1, -8($fp)
add $a2, $a1, 7
lw $a1, -4($fp)
sub $a1, $a2, $a1
mul $a1, $a1, 4
add $a1, $a0, $a1
sw $s2, ($a1)
j L19
L34:

addu $sp, $sp, 56
jr $ra
.text
printboard:
subu $sp, $sp, 60
L37:
sw $fp, 36($sp)
add $a1, $sp, 56
move $fp, $a1
sw $ra, -24($fp)
sw $s7, -56($fp)
sw $s6, -52($fp)
sw $s5, -48($fp)
sw $s4, -44($fp)
sw $s3, -40($fp)
sw $s2, -36($fp)
sw $s1, -32($fp)
sw $s0, -28($fp)
sw $a0, 0($fp)
li $a1, 0
sw $a1, -4($fp)
lw $a1, 0($fp)
lw $a1, -8($a1)
sub $a1, $a1, 1
sw $a1, -16($fp)
lw $a1, -4($fp)
lw $a2, -16($fp)
ble $a1, $a2, L10
L0:
la $a1, L12
move $a0, $a1
jal print
lw $a1, -28($fp)
move $s0, $a1
lw $a1, -32($fp)
move $s1, $a1
lw $a1, -36($fp)
move $s2, $a1
lw $a1, -40($fp)
move $s3, $a1
lw $a1, -44($fp)
move $s4, $a1
lw $a1, -48($fp)
move $s5, $a1
lw $a1, -52($fp)
move $s6, $a1
lw $a1, -56($fp)
move $s7, $a1
lw $a1, -24($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L36
L10:
li $a1, 0
sw $a1, -8($fp)
lw $a1, 0($fp)
lw $a1, -8($a1)
sub $a1, $a1, 1
sw $a1, -12($fp)
lw $a1, -8($fp)
lw $a2, -12($fp)
ble $a1, $a2, L7
L1:
la $a1, L9
move $a0, $a1
jal print
lw $a2, -4($fp)
lw $a1, -16($fp)
bge $a2, $a1, L0
L11:
lw $a1, -4($fp)
add $a1, $a1, 1
sw $a1, -4($fp)
j L10
L7:
lw $a1, 0($fp)
lw $a2, -16($a1)
lw $a1, -4($fp)
mul $a1, $a1, 4
add $a1, $a2, $a1
lw $a2, ($a1)
lw $a1, -8($fp)
beq $a2, $a1, L5
L6:
la $a1, L3
move $a1, $a1
L4:
move $a0, $a1
jal print
lw $a2, -8($fp)
lw $a1, -12($fp)
bge $a2, $a1, L1
L8:
lw $a1, -8($fp)
add $a1, $a1, 1
sw $a1, -8($fp)
j L7
L5:
la $a1, L2
move $a1, $a1
j L4
L36:

addu $sp, $sp, 60
jr $ra
.data
L12:
.word 1
.asciiz "
"
.data
L9:
.word 1
.asciiz "
"
.data
L3:
.word 2
.asciiz " ."
.data
L2:
.word 2
.asciiz " O"
# int *initArray(int size, int init)
# {int i;
#  int *a = (int *)malloc(size*sizeof(int));
#  for(i=0;i<size;i++) a[i]=init;
#  return a;
# }

.text
initArray:
sll $a0,$a0,2
li $v0,9
syscall
move $a2,$v0
b Lrunt2
Lrunt1:
sw $a1,($a2)
sub $a0,$a0,4
add $a2,$a2,4
Lrunt2:
bgtz $a0, Lrunt1
j $ra

# 
# int *allocRecord(int size)
# {int i;
#  int *p, *a;
#  p = a = (int *)malloc(size);
#  for(i=0;i<size;i+=sizeof(int)) *p++ = 0;
#  return a;
# }

allocRecord:
li $v0,9
syscall
move $a2,$v0
b Lrunt4
Lrunt3:
sw $0,($a2)
sub $a0,$a0,4
add $a2,$a2,4
Lrunt4:
bgtz $a0, Lrunt3
j $ra

# struct string {int length; unsigned char chars[1];};
# 
# int stringEqual(struct string *s, struct string *t)
# {int i;
#  if (s==t) return 1;
#  if (s->length!=t->length) return 0;
#  for(i=0;i<s->length;i++) if (s->chars[i]!=t->chars[i]) return 0;
#  return 1;
# }

stringEqual:
beq $a0,$a1,Lrunt10
lw  $a2,($a0)
lw  $a3,($a1)
addiu $a0,$a0,4
addiu $a1,$a1,4
beq $a2,$a3,Lrunt11
Lrunt13:
li  $v0,0
j $ra
Lrunt12:
lbu  $t0,($a0)
lbu  $t1,($a1)
bne  $t0,$t1,Lrunt13
addiu $a0,$a0,1
addiu $a1,$a1,1
addiu $a2,$a2,-1
Lrunt11:
bgez $a2,Lrunt12
Lrunt10:
li $v0,1
j $ra

# 
# void print(struct string *s)
# {int i; unsigned char *p=s->chars;
#  for(i=0;i<s->length;i++,p++) putchar(*p);
# }

print:
lw $a1,0($a0)
add $a0,$a0,4
add $a2,$a0,$a1
lb $a3,($a2)
sb $0,($a2)
li $v0,4
syscall
sb $a3,($a2)
j $ra

# void flush()
# {
#  fflush(stdout);
# }

flush:
j $ra

# int t_main()
# {int i;
#  for(i=0;i<256;i++)
#    {consts[i].length=1;
#     consts[i].chars[0]=i;
#    }
#  return t_main(0 /* static link */);
# }

.data
Runtconsts: 
.space 2048
Runtempty: .word 0

.text

t_main: 
li $a0,0
la $a1,Runtconsts
li $a2,1
Lrunt20:
sw $a2,($a1)
sb $a0,4($a1)
addiu $a1,$a1,8
slt $a3,$a0,256
bnez $a3,Lrunt20
li $a0,0
j t_main

# int ord(struct string *s)
# {
#  if (s->length==0) return -1;
#  else return s->chars[0];
# }

ord:
lw $a1,($a0)
li $v0,-1
beqz $a1,Lrunt5
lbu $v0,4($a0)
Lrunt5:
j $ra



# struct string empty={0,""};

# struct string *chr(int i)
# {
#  if (i<0 || i>=256) 
#    {printf("chr(%d) out of range\n",i); exit(1);}
#  return consts+i;
# }

.data
Lrunt30: .asciiz "character out of range\n"
.text

chr:
andi $a1,$a0,255
bnez $a1,Lrunt31
sll  $a0,$a0,3
la   $v0,Runtconsts($a0)
j $ra
Lrunt31:
li   $v0,4
la   $a0,Lrunt30
syscall
li   $v0,10
syscall

# int size(struct string *s)
# { 
#  return s->length;
# }
size:
lw $v0,($a0)
j $ra

# struct string *substring(struct string *s, int first, int n)
# {
#  if (first<0 || first+n>s->length)
#    {printf("substring([%d],%d,%d) out of range\n",s->length,first,n);
#     exit(1);}
#  if (n==1) return consts+s->chars[first];
#  {struct string *t = (struct string *)malloc(sizeof(int)+n);
#   int i;
#   t->length=n;
#   for(i=0;i<n;i++) t->chars[i]=s->chars[first+i];
#   return t;
#  }
# }
# 
substring:
    add $a1, $a0, $a1
    move $a3, $a1
    li $v0, 9
    add $a2, $a2, 1
    move $a0, $a2
    add $a0, $a0, 1
    syscall
    # got a new string in $v0
    add $a2,$a2,$a3
    add $a2,$a2,-1
    move $a0, $v0
substringcopy:
    beq $a1 $a2 substringexit
    lb $a3 ($a1)
    sb $a3 ($a0)
    add $a1, $a1, 1
    add $a0, $a0, 1
    j substringcopy
substringexit:
    sb $zero, ($a0)
    jr $ra


# struct string *concat(struct string *a, struct string *b)
# {
#  if (a->length==0) return b;
#  else if (b->length==0) return a;
#  else {int i, n=a->length+b->length;
#        struct string *t = (struct string *)malloc(sizeof(int)+n);
#        t->length=n;
#        for (i=0;i<a->length;i++)
# 	 t->chars[i]=a->chars[i];
#        for(i=0;i<b->length;i++)
# 	 t->chars[i+a->length]=b->chars[i];
#        return t;
#      }
# }

concat:
lw $t0,($a0)
lw $t1,($a1)
beqz $t0,Lrunt50
beqz $t1,Lrunt51
addiu  $t2,$a0,4
addiu  $t3,$a1,4
add  $t4,$t0,$t1
addiu $a0,$t4,4
li $v0,9
syscall
addiu $t5,$v0,4
sw $t4,($v0)
Lrunt52:
lbu $a0,($t2)
sb  $a0,($t5)
addiu $t2,1
addiu $t5,1
addiu $t0,-1
bgtz $t0,Lrunt52
Lrunt53:
lbu $a0,($t4)
sb  $a0,($t5)
addiu $t4,1
addiu $t5,1
addiu $t2,-1
bgtz $t2,Lrunt52
j $ra
Lrunt50:
move $v0,$a1
j $ra
Lrunt51:
move $v0,$a0
j $ra

# int not(int i)
# { return !i;
# }
# 

_not:
seq $v0,$a0,0
j $ra


# #undef getchar
# 
# struct string *getchar()
# {int i=getc(stdin);
#  if (i==EOF) return &empty;
#  else return consts+i;
# }

.data
getchbuf: .space 200
getchptr: .word getchbuf

.text
getchar:
lw  $a0,getchptr
lbu $v0,($a0)
add $a0,$a0,1
bnez $v0,Lrunt6
li $v0,8
la $a0,getchbuf
li $a1,200
syscall
la $a0,getchbuf
lbu $v0,($a0)
bnez $v0,Lrunt6
li $v0,-1
Lrunt6:
sw $a0,getchptr
j $ra


printi:
li $v0, 1
syscall
jr $ra

stringCompare:
strcmptest:
lb $a2 ($a0)
lb $a3 ($a1)
beq $a2, $zero, strcmpend
beq $a3, $zero, strcmpend
bgt $a2, $a3  strcmpgreat
blt $a2, $a3  strcmpless
add $a0, $a0, 1
add $a1, $a1, 1
j strcmptest
strcmpgreat:
li $v0, 1
jr $ra
strcmpless:
li $v0, -1
jr $ra
strcmpend:
bne $a2 $zero strcmpgreat
bne $a3 $zero strcmpless
li $v0, 0
jr $ra
