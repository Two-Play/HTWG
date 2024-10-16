#!/bin/sh
c="java -cp /usr/local/lib/antlr-4.13.2-complete.jar:$CLASSPATH org.antlr.v4.gui.TestRig Expr tokens -tokens $1 > ${1%.*}-tokens.txt"
echo $c
eval $c
