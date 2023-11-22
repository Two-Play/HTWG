cmake_minimum_required(VERSION 3.26)
project(a1/aufgabe1 C)
project(a2/bubblesort C)

set(CMAKE_C_STANDARD 11)

add_executable(a1/aufgabe1 a1/aufgabe1.c
        a3/stringsort.c)
add_executable(a2/bubblesort a2/bubblesort.c
        a3/stringsort.c)
