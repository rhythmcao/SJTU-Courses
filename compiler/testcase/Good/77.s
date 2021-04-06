.globl main
.text
main:
subu $sp, $sp, 248
L3:
sw $fp, 36($sp)
add $a1, $sp, 244
move $fp, $a1
sw $ra, -212($fp)
sw $s7, -244($fp)
sw $s6, -240($fp)
sw $s5, -236($fp)
sw $s4, -232($fp)
sw $s3, -228($fp)
sw $s2, -224($fp)
sw $s1, -220($fp)
sw $s0, -216($fp)
sw $a0, 0($fp)
li $a1, 50
sw $a1, -8($fp)
li $a1, 49
sw $a1, -12($fp)
li $a1, 48
sw $a1, -16($fp)
li $a1, 47
sw $a1, -20($fp)
li $a1, 46
sw $a1, -24($fp)
li $a1, 45
sw $a1, -28($fp)
li $a1, 44
sw $a1, -32($fp)
li $a1, 43
sw $a1, -36($fp)
li $a1, 42
sw $a1, -40($fp)
li $a1, 41
sw $a1, -44($fp)
li $a1, 40
sw $a1, -48($fp)
li $a1, 39
sw $a1, -52($fp)
li $a1, 38
sw $a1, -56($fp)
li $a1, 37
sw $a1, -60($fp)
li $a1, 36
sw $a1, -64($fp)
li $a1, 35
sw $a1, -68($fp)
li $a1, 34
sw $a1, -72($fp)
li $a1, 33
sw $a1, -76($fp)
li $a1, 32
sw $a1, -80($fp)
li $a1, 31
sw $a1, -84($fp)
li $a1, 30
sw $a1, -88($fp)
li $a1, 29
sw $a1, -92($fp)
li $a1, 28
sw $a1, -96($fp)
li $a1, 27
sw $a1, -100($fp)
li $a1, 26
sw $a1, -104($fp)
li $a1, 25
sw $a1, -108($fp)
li $a1, 24
sw $a1, -112($fp)
li $a1, 23
sw $a1, -116($fp)
li $a1, 22
sw $a1, -120($fp)
li $a1, 21
sw $a1, -124($fp)
li $a1, 20
sw $a1, -128($fp)
li $a1, 19
sw $a1, -132($fp)
li $a1, 18
sw $a1, -136($fp)
li $a1, 17
sw $a1, -140($fp)
li $a1, 16
sw $a1, -144($fp)
li $a1, 15
sw $a1, -148($fp)
li $a1, 14
sw $a1, -152($fp)
li $a1, 13
sw $a1, -156($fp)
li $a1, 12
sw $a1, -160($fp)
li $a1, 11
sw $a1, -164($fp)
li $a1, 10
sw $a1, -168($fp)
li $a1, 9
sw $a1, -172($fp)
li $a1, 8
sw $a1, -176($fp)
li $a1, 7
sw $a1, -180($fp)
li $a1, 6
sw $a1, -184($fp)
li $a1, 5
sw $a1, -188($fp)
li $a1, 4
sw $a1, -192($fp)
li $a1, 3
sw $a1, -196($fp)
li $a1, 2
sw $a1, -200($fp)
li $a1, 1
sw $a1, -204($fp)
move $a0, $fp
lw $a1, -8($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -12($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -16($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -20($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -24($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -28($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -32($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -36($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -40($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -44($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -48($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -52($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -56($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -60($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -64($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -68($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -72($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -76($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -80($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -84($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -88($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -92($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -96($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -100($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -104($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -108($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -112($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -116($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -120($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -124($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -128($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -132($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -136($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -140($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -144($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -148($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -152($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -156($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -160($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -164($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -168($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -172($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -176($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -180($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -184($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -188($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -192($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -196($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -200($fp)
move $a1, $a1
jal printNum
move $a0, $fp
lw $a1, -204($fp)
move $a1, $a1
jal printNum
la $a1, L1
move $a0, $a1
jal print
lw $a1, -216($fp)
move $s0, $a1
lw $a1, -220($fp)
move $s1, $a1
lw $a1, -224($fp)
move $s2, $a1
lw $a1, -228($fp)
move $s3, $a1
lw $a1, -232($fp)
move $s4, $a1
lw $a1, -236($fp)
move $s5, $a1
lw $a1, -240($fp)
move $s6, $a1
lw $a1, -244($fp)
move $s7, $a1
lw $a1, -212($fp)
move $ra, $a1
lw $a1, 36($sp)
move $fp, $a1
j L2
L2:

addu $sp, $sp, 248
jr $ra
.data
L1:
.word 9
.asciiz "Finished!"
.text
printNum:
subu $sp, $sp, 48
L5:
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
lw $a1, -4($fp)
move $a0, $a1
jal printi
la $a1, L0
move $a0, $a1
jal print
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
j L4
L4:

addu $sp, $sp, 48
jr $ra
.data
L0:
.word 1
.asciiz "
"
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
