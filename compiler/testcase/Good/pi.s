.globl main
.text
main:
subu $sp, $sp, 76
L25:
sw $fp, 36($sp)
add $a1, $sp, 72
move $fp, $a1
sw $ra, -40($fp)
sw $s7, -72($fp)
sw $s6, -68($fp)
sw $s5, -64($fp)
sw $s4, -60($fp)
sw $s3, -56($fp)
sw $s2, -52($fp)
sw $s1, -48($fp)
sw $s0, -44($fp)
sw $a0, 0($fp)
li $a1, 10000
sw $a1, -8($fp)
li $a1, 0
sw $a1, -12($fp)
li $a1, 2800
sw $a1, -16($fp)
li $a1, 0
sw $a1, -20($fp)
li $a1, 0
sw $a1, -24($fp)
add $a1, $fp, -28
move $s2, $a1
li $a0, 2801
li $a1, 0
jal initArray
move $a1, $v0
sw $a1, ($s2)
li $a1, 0
sw $a1, -32($fp)
L16:
lw $a2, -12($fp)
lw $a1, -16($fp)
sub $a2, $a2, $a1
li $a1, 0
bne $a2, $a1, L17
L15:
L22:
move $a0, $fp
jal test
move $a1, $v0
li $a2, 0
bne $a1, $a2, L23
L18:
lw $a1, -44($fp)
move $s0, $a1
lw $a1, -48($fp)
move $s1, $a1
lw $a1, -52($fp)
move $s2, $a1
lw $a1, -56($fp)
move $s3, $a1
lw $a1, -60($fp)
move $s4, $a1
lw $a1, -64($fp)
move $s5, $a1
lw $a1, -68($fp)
move $s6, $a1
lw $a1, -72($fp)
move $s7, $a1
lw $a1, -40($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L24
L17:
lw $a1, -8($fp)
div $a0, $a1, 5
lw $a2, -28($fp)
lw $a1, -12($fp)
mul $a1, $a1, 4
add $a1, $a2, $a1
sw $a0, ($a1)
lw $a1, -12($fp)
add $a1, $a1, 1
sw $a1, -12($fp)
j L16
L23:
lw $a1, -16($fp)
sw $a1, -12($fp)
L20:
move $a0, $fp
jal test2
move $a1, $v0
li $a2, 0
bne $a1, $a2, L21
L19:
lw $a1, -16($fp)
sub $a1, $a1, 14
sw $a1, -16($fp)
move $a0, $fp
lw $a0, -24($fp)
lw $a1, -20($fp)
lw $a2, -8($fp)
div $a1, $a1, $a2
add $a1, $a0, $a1
move $a1, $a1
jal printf
lw $a0, -20($fp)
lw $a1, -20($fp)
lw $a2, -8($fp)
div $a1, $a1, $a2
lw $a2, -8($fp)
mul $a1, $a1, $a2
sub $a1, $a0, $a1
sw $a1, -24($fp)
j L22
L21:
lw $a2, -20($fp)
lw $a1, -12($fp)
mul $a1, $a2, $a1
sw $a1, -20($fp)
j L20
L24:

addu $sp, $sp, 76
jr $ra
.text
printf:
subu $sp, $sp, 48
L27:
sw $fp, 36($sp)
add $a2, $sp, 44
move $fp, $a2
sw $ra, -12($fp)
sw $s7, -44($fp)
sw $s6, -40($fp)
sw $s5, -36($fp)
sw $s4, -32($fp)
sw $s3, -28($fp)
sw $s2, -24($fp)
sw $s1, -20($fp)
sw $s0, -16($fp)
sw $a1, -4($fp)
sw $a0, 0($fp)
li $a0, 1
lw $a1, -4($fp)
li $a2, 1000
blt $a1, $a2, L0
L1:
li $a0, 0
L0:
li $a1, 0
bne $a0, $a1, L3
L4:
L2:
li $a0, 1
lw $a2, -4($fp)
li $a1, 100
blt $a2, $a1, L5
L6:
li $a0, 0
L5:
li $a1, 0
bne $a0, $a1, L8
L9:
L7:
li $a0, 1
lw $a1, -4($fp)
li $a2, 10
blt $a1, $a2, L10
L11:
li $a0, 0
L10:
li $a1, 0
bne $a0, $a1, L13
L14:
L12:
lw $a1, -4($fp)
move $a0, $a1
jal printi
lw $a1, -16($fp)
move $s0, $a1
lw $a1, -20($fp)
move $s1, $a1
lw $a1, -24($fp)
move $s2, $a1
lw $a1, -28($fp)
move $s3, $a1
lw $a1, -32($fp)
move $s4, $a1
lw $a1, -36($fp)
move $s5, $a1
lw $a1, -40($fp)
move $s6, $a1
lw $a1, -44($fp)
move $s7, $a1
lw $a1, -12($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L26
L3:
li $a0, 0
jal printi
j L2
L8:
li $a0, 0
jal printi
j L7
L13:
li $a0, 0
jal printi
j L12
L26:

addu $sp, $sp, 48
jr $ra
.text
test2:
subu $sp, $sp, 44
L29:
sw $fp, 36($sp)
add $a1, $sp, 40
move $fp, $a1
sw $ra, -8($fp)
sw $s7, -40($fp)
sw $s6, -36($fp)
sw $s5, -32($fp)
sw $s4, -28($fp)
sw $s3, -24($fp)
sw $s2, -20($fp)
sw $s1, -16($fp)
sw $s0, -12($fp)
sw $a0, 0($fp)
lw $a1, 0($fp)
lw $a0, -20($a1)
lw $a1, 0($fp)
lw $a2, -28($a1)
lw $a1, 0($fp)
lw $a1, -12($a1)
mul $a1, $a1, 4
add $a1, $a2, $a1
lw $a2, ($a1)
lw $a1, 0($fp)
lw $a1, -8($a1)
mul $a1, $a2, $a1
add $a1, $a0, $a1
lw $a2, 0($fp)
sw $a1, -20($a2)
lw $a1, 0($fp)
lw $a1, -32($a1)
sub $a1, $a1, 1
lw $a2, 0($fp)
sw $a1, -32($a2)
lw $a1, 0($fp)
lw $a0, -20($a1)
lw $a1, 0($fp)
lw $a2, -20($a1)
lw $a1, 0($fp)
lw $a1, -32($a1)
div $a2, $a2, $a1
lw $a1, 0($fp)
lw $a1, -32($a1)
mul $a1, $a2, $a1
sub $a0, $a0, $a1
lw $a1, 0($fp)
lw $a2, -28($a1)
lw $a1, 0($fp)
lw $a1, -12($a1)
mul $a1, $a1, 4
add $a1, $a2, $a1
sw $a0, ($a1)
lw $a1, 0($fp)
lw $a2, -20($a1)
lw $a1, 0($fp)
lw $a1, -32($a1)
div $a2, $a2, $a1
lw $a1, 0($fp)
sw $a2, -20($a1)
lw $a1, 0($fp)
lw $a1, -32($a1)
sub $a1, $a1, 1
lw $a2, 0($fp)
sw $a1, -32($a2)
lw $a1, 0($fp)
lw $a1, -12($a1)
sub $a2, $a1, 1
lw $a1, 0($fp)
sw $a2, -12($a1)
lw $a1, 0($fp)
lw $a1, -12($a1)
move $v0, $a1
lw $a1, -12($fp)
move $s0, $a1
lw $a1, -16($fp)
move $s1, $a1
lw $a1, -20($fp)
move $s2, $a1
lw $a1, -24($fp)
move $s3, $a1
lw $a1, -28($fp)
move $s4, $a1
lw $a1, -32($fp)
move $s5, $a1
lw $a1, -36($fp)
move $s6, $a1
lw $a1, -40($fp)
move $s7, $a1
lw $a1, -8($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L28
L28:

addu $sp, $sp, 44
jr $ra
.text
test:
subu $sp, $sp, 44
L31:
sw $fp, 36($sp)
add $a1, $sp, 40
move $fp, $a1
sw $ra, -8($fp)
sw $s7, -40($fp)
sw $s6, -36($fp)
sw $s5, -32($fp)
sw $s4, -28($fp)
sw $s3, -24($fp)
sw $s2, -20($fp)
sw $s1, -16($fp)
sw $s0, -12($fp)
sw $a0, 0($fp)
li $a1, 0
lw $a2, 0($fp)
sw $a1, -20($a2)
lw $a1, 0($fp)
lw $a1, -16($a1)
mul $a2, $a1, 2
lw $a1, 0($fp)
sw $a2, -32($a1)
lw $a1, 0($fp)
lw $a1, -32($a1)
move $v0, $a1
lw $a1, -12($fp)
move $s0, $a1
lw $a1, -16($fp)
move $s1, $a1
lw $a1, -20($fp)
move $s2, $a1
lw $a1, -24($fp)
move $s3, $a1
lw $a1, -28($fp)
move $s4, $a1
lw $a1, -32($fp)
move $s5, $a1
lw $a1, -36($fp)
move $s6, $a1
lw $a1, -40($fp)
move $s7, $a1
lw $a1, -8($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L30
L30:

addu $sp, $sp, 44
jr $ra
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
