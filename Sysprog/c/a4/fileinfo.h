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
#include <limits.h>


// Aliasname fileinfo für struct-Typ
typedef struct fileinfo fileinfo;

//fileinfo(char ** fileinfo_create);
enum filetype {
    filetype_directory,
    filetype_regular,
    filetype_other
};

// Struct-Type
struct fileinfo
{
    fileinfo *next;
    enum filetype type;
    char filename[NAME_MAX + 1];
    union {
        fileinfo * firstElmInDir;
        size_t size;
    };


};

void fileinfo_print(const fileinfo* fileinfo);
void fileinfo_destroy(fileinfo* head);
fileinfo * fileinfo_create(char* arg);

#endif //AUFGABE1_FILEINFO_H