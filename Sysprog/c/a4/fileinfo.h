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

fileinfo(char ** fileinfo_create);
enum fileinfo {
    filetype_directory,

};
#endif //AUFGABE1_FILEINFO_H