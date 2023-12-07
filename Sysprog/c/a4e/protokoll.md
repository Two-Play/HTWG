```commandline
➜  a4 git:(main) ✗ make cppcheck
cppcheck --enable=warning,style --std=c11 listfiles.c fileinfo.c
Checking fileinfo.c ...
1/2 files checked 81% done
Checking listfiles.c ...
2/2 files checked 100% done
Active checkers: 106/565
➜  a4 git:(main) ✗ make         
gcc -g -Wall -Wextra -Werror -std=c11 -pedantic -D_POSIX_C_SOURCE=200112L  -c -o listfiles.o listfiles.c
gcc -g -Wall -Wextra -Werror -std=c11 -pedantic -D_POSIX_C_SOURCE=200112L  -c -o fileinfo.o fileinfo.c
gcc -g  listfiles.o fileinfo.o -o listfiles


➜  a4 git:(main) ✗ ./listfiles hhallo
hhallo: No such file or directory (errno 2)
➜  a4 git:(main) ✗ valgrind ./listfiles *             
==9198== Memcheck, a memory error detector
==9198== Copyright (C) 2002-2022, and GNU GPL'd, by Julian Seward et al.
==9198== Using Valgrind-3.22.0 and LibVEX; rerun with -h for copyright info
==9198== Command: ./listfiles aufgabe4.tar.gz fileinfo.c fileinfo.h fileinfo.o listfiles listfiles.c listfiles-example-dir listfiles.o listfiles-out.txt Makefile
==9198== 
aufgabe4.tar.gz (regular, 1121 Byte)
fileinfo.c (regular, 5257 Byte)
fileinfo.h (regular, 793 Byte)
fileinfo.o (regular, 13688 Byte)
listfiles (regular, 24248 Byte)
listfiles.c (regular, 1221 Byte)
listfiles.o (regular, 7304 Byte)
listfiles-out.txt (regular, 212 Byte)
Makefile (regular, 557 Byte)

listfiles-example-dir:
example-subdir (directory)

listfiles-example-dir/example-subdir:
example.txt (regular, 18 Byte)
==9198== 
==9198== HEAP SUMMARY:
==9198==     in use at exit: 0 bytes in 0 blocks
==9198==   total heap usage: 17 allocs, 17 frees, 70,076 bytes allocated
==9198== 
==9198== All heap blocks were freed -- no leaks are possible
==9198== 
==9198== For lists of detected and suppressed errors, rerun with: -s
==9198== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
➜  a4 git:(main) ✗   ./listfiles xxx listfiles.c listfiles-example-dir /dev/tty > out.txt 2>&1
  diff -Z listfiles-out.txt out.txt

➜  a4 git:(main) ✗ 

```