# Test
### Java
```commandline
$ java Bubblesort 10
Bitte 10 ganze Zahlen eingeben: 1 2 3 4 5 6 7 8 9 10
Sortierte Zahlenfolge:
1
2
3
4
5
6
7
8
9
10
```
#### Random

```commandline
$ java Bubblesort 10            
Bitte 10 ganze Zahlen eingeben: -121216370
-984687765
947660578
-325013917
1507823547
522353147
1807949604
-1627431340
1810607229
-2099622575
Sortierte Zahlenfolge: 
-2099622575
-1627431340
-984687765
-325013917
-121216370
522353147
947660578
1507823547
1807949604
1810607229
```

#### Mix
```commandline
java Bubblesort 10 
Bitte 10 ganze Zahlen eingeben: 1 2 3 4 5
1943866803
1582663071
1457092502
17653344
-1161077308
Sortierte Zahlenfolge: 
-1161077308
1
2
3
4
5
17653344
1457092502
1582663071
1943866803
```

#### Falscheingabe
```commandline
$ java Bubblesort 100000000000000000000000000   
Exception in thread "main" java.lang.NumberFormatException: For input string: "100000000000000000000000000"
        at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        at java.base/java.lang.Integer.parseInt(Integer.java:668)
        at java.base/java.lang.Integer.parseInt(Integer.java:784)
        at Bubblesort.main(Bubblesort.java:28)
```
```commandline
$ java Bubblesort -1                         
Exception in thread "main" java.lang.NegativeArraySizeException: -1
        at Bubblesort.main(Bubblesort.java:29)

```
### c

```commandline
$ ./bubblesort 10
Bitte 10 ganze Zahlen eingeben: 1 2 3 4 5 6 7 8 9 10
Sortierte Zahlen: 
1
2
3
4
5
6
7
8
9
10
```
#### Random
```commandline
$ ./bubblesort 10
Bitte 10 ganze Zahlen eingeben: 1039117034
1102973034
588941534
588232915
1563375264
1185641003
567576908
142732782
170633375
944464880
Sortierte Zahlen: 
142732782
170633375
567576908
588232915
588941534
944464880
1039117034
1102973034
1185641003
1563375264

```
#### Mix
```commandline
$ ./bubblesort 10
Bitte 10 ganze Zahlen eingeben: 1 2 3 4 5
1040058226
1889201449
1263032448
2057986588
1208965934
Sortierte Zahlen: 
1
2
3
4
5
1040058226
1208965934
1263032448
1889201449
2057986588

```
#### Falscheingabe
```commandline
$ ./bubblesort 10000000000000000000
Speicher-Fehler%   
```
```commandline
$ ./bubblesort -1                  
Speicher-Fehler% 
```

### CPP Check
```commandline
$ make cppcheck
cppcheck --enable=warning,style --std=c11 bubblesort.c
Checking bubblesort.c ...
Active checkers: 106/565

```

### Valgrind / leaks
```commandline
leaks --atExit -- ./bubblesort 10

bubblesort(18913) MallocStackLogging: could not tag MSL-related memory as no_footprint, so those pages will be included in process footprint - (null)
bubblesort(18913) MallocStackLogging: recording malloc and VM allocation stacks using lite mode
Bitte 10 ganze Zahlen eingeben: a
1044881835
1355219326
959652000
1268975030
1003230853
1406833774
860286148
1969377832
167771213
84748380
Sortierte Zahlen: 
84748380
167771213
860286148
959652000
1003230853
1044881835
1268975030
1355219326
1406833774
1969377832
Process 18913 is not debuggable. Due to security restrictions, leaks can only show or save contents of readonly memory of restricted processes.

Process:         bubblesort [18913]
Path:            /Users/USER/Documents/*/bubblesort
Load Address:    0x104034000
Identifier:      bubblesort
Version:         0
Code Type:       ARM64
Platform:        macOS
Parent Process:  leaks [18912]

Date/Time:       2023-11-02 15:10:22.721 +0100
Launch Time:     2023-11-02 15:10:21.613 +0100
OS Version:      macOS 14.0 (23A344)
Report Version:  7
Analysis Tool:   /usr/bin/leaks

Physical footprint:         3441K
Physical footprint (peak):  3441K
Idle exit:                  untracked
----

leaks Report Version: 4.0, multi-line stacks
Process 18913: 187 nodes malloced for 19 KB
Process 18913: 0 leaks for 0 total leaked bytes.

```

### Auto Test
```commandline
$ ./bubblesort 1000 < /dev/null | tail -1000 > out.txt
(nichts)
```
```commandline
$ sort -n out.txt | diff - out.txt
(nichts)
```

***Was gibt die obige Befehlsfolge im Terminal aus, wenn Ihr bubblesort richtig sortiert hat?***  
nichts

### Laufzeit
#### Java
```commandline
$ time java Bubblesort 1000 < /dev/null > /dev/null

java Bubblesort 1000 < /dev/null > /dev/null  0.06s user 0.02s system 99% cpu 0.074 total


$ time java Bubblesort 10000 < /dev/null > /dev/null
java Bubblesort 10000 < /dev/null > /dev/null  0.16s user 0.03s system 125% cpu 0.146 total

$ time java Bubblesort 100000 < /dev/null > /dev/null
java Bubblesort 100000 < /dev/null > /dev/null  13.75s user 0.20s system 100% cpu 13.888 total

```
#### C
 ```commandline
$ time ./bubblesort 1000 < /dev/null > /dev/null

./bubblesort 1000 < /dev/null > /dev/null  0.00s user 0.00s system 72% cpu 0.007 total

$ time ./bubblesort 10000 < /dev/null > /dev/null
./bubblesort 10000 < /dev/null > /dev/null  0.16s user 0.00s system 98% cpu 0.170 total

$ time ./bubblesort 100000 < /dev/null > /dev/null
./bubblesort 100000 < /dev/null > /dev/null  18.99s user 0.18s system 99% cpu 19.210 total

```

### C optimiert

```commandline
$ time ./bubblesort 1000 < /dev/null > /dev/null

./bubblesort 1000 < /dev/null > /dev/null  0.00s user 0.00s system 1% cpu 0.361 total

$ time ./bubblesort 10000 < /dev/null > /dev/null 
./bubblesort 10000 < /dev/null > /dev/null  0.04s user 0.00s system 97% cpu 0.039 total

$ time ./bubblesort 100000 < /dev/null > /dev/null
./bubblesort 100000 < /dev/null > /dev/null  10.12s user 0.09s system 99% cpu 10.248 total

```


***Wächst die Ausführungszeit tatsächlich quadratisch mit der Array-Größe?***  
ja  
***Ist das Java-Programm oder das C-Programm schneller? Können Sie sich den Unterschied erklären?***  
C ist schneller (unoptimiert aber nur bei kleineren Arrays, vermutlich wegen memory management), da es näher an der Hardware ist und nicht in einer VM läuft   
***Ist das optimierte Programm erkennbar schneller?***  
ja  