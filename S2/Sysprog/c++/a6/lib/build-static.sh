#!/bin/zsh
for file in ./*.cpp
do
    name=${file%.cpp}
    # compile the file
    cmd_c="g++ -c $file -o $name.o -Wall -Wextra -Werror -Weffc++ -Wold-style-cast -std=c++11 -pedantic"
    echo "$cmd_c"
    eval "$cmd_c"
    if [ $? -ne 0 ]
    then
        echo "Failed to compile $file"
        exit 1
    fi
done

lib_cmd="ar rs ./libaufgabe6.a ./*.o"

echo "$lib_cmd"
eval "$lib_cmd"
if [ $? -ne 0 ];
then
    echo "Failed to create static library"
    exit 1
fi

echo build successful