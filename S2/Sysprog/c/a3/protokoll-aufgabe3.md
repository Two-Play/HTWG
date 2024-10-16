# Test

## Java
### Normal
```commandline
➜  a3 git:(main) ✗ java Stringsort 15
Unsortiertes Array:
5 6 6 10 8 12 12 5 10 13 2 8 7 13 11 
Sortiertes Array:
10* 11 12* 13* 2 5* 6* 7 8*
```
### Fehler
#### Falsches Argument
```commandline
➜  a3 git:(main) ✗ java Stringsort -1
Anzahl muss midestens 1 sein
```
#### Kein Argument
```commandline
➜  a3 git:(main) ✗ java Stringsort   
Aufruf: java Stringsort Anzahl
```
#### Sehr große Zahl
```commandline
➜  a3 git:(main) ✗ java Stringsort 100000000000000000000000000  
Exception in thread "main" java.lang.NumberFormatException: For input string: "100000000000000000000000000"
        at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        at java.base/java.lang.Integer.parseInt(Integer.java:661)
        at java.base/java.lang.Integer.parseInt(Integer.java:777)
        at Stringsort.main(Stringsort.java:24)
```

## C
### Normal
```commandline
➜  a3 git:(main) ✗ ./stringsort 15                                     
Unsortiertes Array:
14 12 0 5 4 14 6 3 10 2 10 10 14 3 0 
Sortiertes Array:
0* 10** 12 14** 2 3* 4 5 6
```
### Fehler
#### Falsches Argument
```commandline
➜  a3 git:(main) ✗ ./stringsort -1                         
Anzahl muss midestens 1 sein% 
```
#### Kein Argument
```commandline
➜  a3 git:(main) ✗ ./stringsort                               
Aufruf: java Bubblesort Anzahl% 
```
#### Sehr große Zahl
```commandline
➜  a3 git:(main) ✗ ./stringsort 100000000000000000000000000  
Anzahl muss midestens 1 sein%  
```


## C - Optimiert
### Normal
```commandline
➜  a3 git:(main) ✗ ./stringsort-optimiert 15 
Unsortiertes Array:
7 8 9 13 12 9 5 13 8 10 4 2 6 8 9 
Sortiertes Array:
10 12 13* 2 4 5 6 7 8** 9**
```
### Fehler
#### Falsches Argument
```commandline
➜  a3 git:(main) ✗ ./stringsort-optimiert -1 
Anzahl muss midestens 1 sein%  
```
#### Kein Argument
```commandline
➜  a3 git:(main) ✗ ./stringsort-optimiert   
Aufruf: java Bubblesort Anzahl%   
```
#### Sehr große Zahl
```commandline
➜  a3 git:(main) ✗ ./stringsort-optimiert 100000000000000000000000000  
Anzahl muss midestens 1 sein%     
```


```commandline
                         
```

# CPP Check
### C
```commandline
➜  a3 git:(main) ✗ make cppcheck                   
cppcheck --enable=warning,style --std=c11 stringsort.c
Checking stringsort.c ...
Active checkers: 106/565
```
### C - Optimiert
```commandline
➜  a3 git:(main) ✗ make TARGET=stringsort-optimiert cppcheck  
cppcheck --enable=warning,style --std=c11 stringsort-optimiert.c
Checking stringsort-optimiert.c ...
Active checkers: 106/565

```

# Laufzeit
## Java
```commandline
➜  a3 git:(main) ✗ time java Stringsort 20000 > /dev/null          
java Stringsort 20000 > /dev/null  2,55s user 0,03s system 111% cpu 2,316 total

```

## C
```commandline
➜  a3 git:(main) ✗ time ./stringsort 20000 > /dev/null 
./stringsort 20000 > /dev/null  2,45s user 0,01s system 99% cpu 2,457 total
```
### gcc 02
```commandline
➜  a3 git:(main) ✗ time ./stringsort 20000 > /dev/null
./stringsort 20000 > /dev/null  2,07s user 0,01s system 99% cpu 2,080 total
```

## C- Optimiert
```commandline
➜  a3 git:(main) ✗ time ./stringsort-optimiert 20000 > /dev/null
./stringsort-optimiert 20000 > /dev/null  2,03s user 0,00s system 99% cpu 2,033 total
```
### gcc 02
```commandline
➜  a3 git:(main) ✗ time  ./stringsort-optimiert 20000 > /dev/null    
./stringsort-optimiert 20000 > /dev/null  1,84s user 0,00s system 99% cpu 1,839 total
```

# Valgrind / leaks

## C
```commandline
➜  a3 git:(main) ✗ valgrind ./stringsort 200
==51241== Memcheck, a memory error detector
==51241== Copyright (C) 2002-2022, and GNU GPL'd, by Julian Seward et al.
==51241== Using Valgrind-3.21.0 and LibVEX; rerun with -h for copyright info
==51241== Command: ./stringsort 200
==51241== 
Unsortiertes Array:
135 171 42 1 33 4 144 147 131 77 4 2 138 64 7 4 68 26 45 31 31 132 104 137 101 45 59 72 44 156 29 179 79 71 132 112 27 28 12 158 105 168 112 196 184 71 152 52 97 149 83 80 33 140 18 86 185 29 158 29 138 187 8 17 10 92 82 189 120 46 99 178 14 11 174 150 82 78 3 180 27 38 60 12 178 30 98 115 12 56 144 102 44 104 119 6 196 153 196 69 151 95 47 117 107 173 68 141 51 71 73 30 109 134 42 88 116 140 155 80 196 100 182 192 156 54 151 105 7 147 174 159 194 21 76 53 194 96 195 45 167 68 75 29 154 69 69 23 9 24 103 157 76 38 150 33 92 101 138 51 0 64 162 146 85 191 0 79 87 147 76 7 15 103 36 122 172 57 97 133 81 152 90 158 190 192 143 82 45 33 86 45 97 48 192 182 39 144 13 127 
Sortiertes Array:
0* 1 10 100 101* 102 103* 104* 105* 107 109 11 112* 115 116 117 119 12** 120 122 127 13 131 132* 133 134 135 137 138** 14 140* 141 143 144** 146 147** 149 15 150* 151* 152* 153 154 155 156* 157 158** 159 162 167 168 17 171 172 173 174* 178* 179 18 180 182* 184 185 187 189 190 191 192** 194* 195 196*** 2 21 23 24 26 27* 28 29*** 3 30* 31* 33*** 36 38* 39 4** 42* 44* 45**** 46 47 48 51* 52 53 54 56 57 59 6 60 64* 68** 69** 7** 71** 72 73 75 76** 77 78 79* 8 80* 81 82** 83 85 86* 87 88 9 90 92* 95 96 97** 98 99
==51241== 
==51241== HEAP SUMMARY:
==51241==     in use at exit: 0 bytes in 0 blocks
==51241==   total heap usage: 203 allocs, 203 frees, 6,032 bytes allocated
==51241== 
==51241== All heap blocks were freed -- no leaks are possible
==51241== 
==51241== For lists of detected and suppressed errors, rerun with: -s
==51241== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)

```

## C - Optimiert
```commandline
➜  a3 git:(main) ✗ valgrind ./stringsort-optimiert 200
==51275== Memcheck, a memory error detector
==51275== Copyright (C) 2002-2022, and GNU GPL'd, by Julian Seward et al.
==51275== Using Valgrind-3.21.0 and LibVEX; rerun with -h for copyright info
==51275== Command: ./stringsort-optimiert 200
==51275== 
Unsortiertes Array:
31 37 189 42 9 112 12 179 129 117 59 127 27 57 49 133 54 55 150 121 155 144 119 88 101 104 123 64 158 34 43 141 71 32 183 32 97 147 164 26 65 175 154 44 185 155 177 39 162 80 112 118 24 31 158 77 136 81 93 46 67 137 187 139 169 171 123 66 70 87 45 135 63 151 132 48 106 61 39 68 93 152 138 69 135 96 146 71 130 40 69 197 177 57 88 98 180 12 117 50 51 162 138 66 113 70 114 19 83 106 87 177 58 178 198 145 74 145 169 156 137 38 106 66 95 146 164 75 110 81 126 162 43 64 28 108 86 95 127 121 1 167 50 11 145 49 156 171 194 125 80 131 164 138 197 11 84 113 87 195 195 165 109 190 181 89 51 19 184 178 140 137 97 191 148 194 40 57 118 186 134 198 69 50 136 18 14 172 131 53 167 78 18 28 21 151 118 72 170 102 
Sortiertes Array:
1 101 102 104 106** 108 109 11* 110 112* 113* 114 117* 118** 119 12* 121* 123* 125 126 127* 129 130 131* 132 133 134 135* 136* 137** 138** 139 14 140 141 144 145** 146* 147 148 150 151* 152 154 155* 156* 158* 162** 164** 165 167* 169* 170 171* 172 175 177** 178* 179 18* 180 181 183 184 185 186 187 189 19* 190 191 194* 195* 197* 198* 21 24 26 27 28* 31* 32* 34 37 38 39* 40* 42 43* 44 45 46 48 49* 50** 51* 53 54 55 57** 58 59 61 63 64* 65 66** 67 68 69** 70* 71* 72 74 75 77 78 80* 81* 83 84 86 87** 88* 89 9 93* 95* 96 97* 98
==51275== 
==51275== HEAP SUMMARY:
==51275==     in use at exit: 0 bytes in 0 blocks
==51275==   total heap usage: 3 allocs, 3 frees, 2,833 bytes allocated
==51275== 
==51275== All heap blocks were freed -- no leaks are possible
==51275== 
==51275== For lists of detected and suppressed errors, rerun with: -s
==51275== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)

```