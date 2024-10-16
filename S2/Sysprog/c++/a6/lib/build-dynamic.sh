#!/bin/zsh
# loop over cpp files
for file in *.cpp
do
    # get the name of the file without the extension
    name=${file%.cpp}
    # compile the file
    cmd_c="g++ -fPIC -Wall -Wextra -Werror -Weffc++ -Wold-style-cast -std=c++11 -pedantic -c $file -o $name.o"
    echo "$cmd_c"
    eval "$cmd_c"
    if [ $? -ne 0 ]
    then
        echo "Failed to compile $file"
    fi
done

lib_cmd="g++ -shared *.o -o libaufgabe6.so"
echo $lib_cmd
eval $lib_cmd

if [ $? -ne 0 ];
then
    echo "Failed to create dynamic library"
    exit 1
fi

echo build successful
