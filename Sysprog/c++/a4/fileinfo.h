// fileinfo.h
#ifndef FILEINFO_H
#define FILEINFO_H

#include <stdio.h>
#include "limits.h"

enum filetype {filetype_regular, filetype_directory, filetype_other};

typedef struct fileinfo fileinfo;

struct fileinfo
{
    fileinfo *next;
    enum filetype type;
    char filename[NAME_MAX + 1];

    union
    {
        size_t size;
        fileinfo *list;
    };
};

fileinfo* fileinfo_create(char* filename);

void fileinfo_print(const fileinfo *);

void fileinfo_destroy(fileinfo *);

#endif
