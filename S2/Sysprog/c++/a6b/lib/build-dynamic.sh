#!/bin/sh
echo build started

for script in benotung.cpp fachnote.cpp fachnoten_liste.cpp ; do
  compile_command="g++ -fPIC -Wall -Wextra -Werror -pedantic -I. -c $script"
  echo "$compile_command"
  eval "$compile_command"
  if [ $? -ne 0 ] ; then
    echo build failed
    exit 1
  fi
done

lib_command="g++ -shared benotung.o fachnote.o fachnoten_liste.o -o libaufgabe6.so"
echo "$lib_command"
eval "$lib_command"
if [ $? -ne 0 ] ; then
  echo build failed
  exit 1
fi

echo build successful