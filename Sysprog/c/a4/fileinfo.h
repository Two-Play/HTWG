//
// Created by Philippe Westenfelder on 23.11.23.
//

#ifndef AUFGABE1_FILEINFO_H
#define AUFGABE1_FILEINFO_H

#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <dirent.h>
#include <errno.h>


typedef struct fileinfo fileinfo;

//fileinfo(char ** fileinfo_create);
enum filetype {
    filetype_directory,
};

struct fileinfo
{
    fileinfo *next;
    enum filetype type;
    union {
        fileinfo * firstElmInDir;
    };


};

void fileinfo_print(fileinfo* fileinfo);
void fileinfo_destroy(fileinfo* head);
fileinfo * fileinfo_create(char* arg);

#endif //AUFGABE1_FILEINFO_H