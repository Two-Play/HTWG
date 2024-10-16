//
// Created by Philippe Westenfelder on 23.11.23.
//
#include "fileinfo.h"
#include <string.h>
#include <sys/stat.h>
#include <stdlib.h>


static fileinfo* list_directory(char* dirname)
{
    // Öffnet dir
    DIR *dir = opendir(dirname);
    if (dir == NULL)
    {
        fprintf(stderr, "%s konnte nicht geöffnet werden\n", dirname);
        return NULL;
    }
    errno = 0;

    // cd Arbeitsverzeichnis wegen Pfad
    if (chdir(dirname) != 0)
    {
        fprintf(stderr, "Fehler beim wechseln in das Arbeitsverzeichnis\n");
        closedir(dir);
        return NULL;
    }

    // Zeiger auf Variable für Verzeichniseintrag
    struct dirent *entry;

    fileinfo *head = NULL;
    fileinfo *current = NULL;

    while ((entry = readdir(dir)) != NULL)
    {
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
        {
            continue;
        }

        fileinfo* fileinfo_p = fileinfo_create(entry->d_name);
        if (fileinfo_p == NULL)
        {
            return NULL;
        }

        if (head == NULL)
        {
            head = fileinfo_p;
            current = head;
        }
        else
        {
            current->next = fileinfo_p;
            current = fileinfo_p;
        }
    }

    // Verzeichnis schließen
    closedir(dir);

    // reset Arbeitsverzeichnis
    if (chdir("..") != 0)
    {
        fprintf(stderr, "Fehler beim Zurücksetzen des Arbeitsverzeichnisses\n");
        return NULL;
    }

    return head;
}


static void print_regular(const char *filename, const size_t size)
{
    printf("%s (regular, %zu Byte)\n", filename, size);
}

static void print_other(const char *filename)
{
    printf("%s (other)\n", filename);
}

static void print_directory(const char *path, const char *dirname, const fileinfo *list)
{

    char *new_path = (char*) malloc(strlen(path) + strlen(dirname) + 2);
    if (new_path == NULL)
    {
        fprintf(stderr, "Speicher-Fehler\n");
        return;
    }

    if (strcmp(path, "") != 0)
    {
        sprintf(new_path, "%s/%s", path, dirname);
    }
    else
    {
        sprintf(new_path, "%s", dirname);
    }

    printf("\n%s:\n", new_path);

    const fileinfo *current = list;

    while (current != NULL)
    {
        fileinfo *next = current->next;

        switch (current->type)
        {
            case filetype_regular:
                print_regular(current->filename, current->size);
                break;
            case filetype_directory:
                printf("%s (directory)\n", current->filename);
                break;
            case filetype_other:
                print_other(current->filename);
                break;
        }
        current = next;
    }

    current = list;

    while (current != NULL)
    {
        fileinfo *next = current->next;

        if (current->type == filetype_directory)
        {
            print_directory(new_path, current->filename, current->firstElmInDir);
        }

        current = next;
    }

    free(new_path);
}


void fileinfo_print(const fileinfo* fileinfo)
{

    if (fileinfo->type == filetype_regular)
    {
        print_regular(fileinfo->filename, fileinfo->size);
        return;
    } else if (fileinfo->type == filetype_directory)
    {
        print_directory("", fileinfo->filename, fileinfo->firstElmInDir);
        return;
    } else
    {
        print_other(fileinfo->filename);
    }

}



void fileinfo_destroy(fileinfo* head)
{

    if (head->type == filetype_directory)
    {
        fileinfo* current = head->firstElmInDir;

        while (current != NULL)
        {
            fileinfo *next = current->next;

            if (current->firstElmInDir && current->type == filetype_directory)
            {
                fileinfo_destroy(current->firstElmInDir);
            }

            free(current);
            current = next;
        }
    }
    free(head);
}



fileinfo * fileinfo_create(char* arg)
{

    if (strlen(arg) > NAME_MAX)
    {
        errno = ENAMETOOLONG;
        return NULL;
    }

    struct stat s; // Dateistatus
    // Fehlerbeh.
    if (lstat(arg, &s) == -1)
    {
        return NULL;
    }

    // Erstelle Zeiger auf fileinfo
    fileinfo *info = (fileinfo*) malloc(sizeof(fileinfo));
    if (info == NULL)
    {
        fprintf(stderr, "Speicher-Fehler\n");
        return NULL;
    }

    strcpy(info->filename, arg);
    info->next = NULL;

    if (S_ISREG(s.st_mode))
    {
        info->type = filetype_regular;
        info->size = s.st_size;
        return info;
    }
    else if (S_ISDIR(s.st_mode))
    {
        info->type = filetype_directory;
        info->firstElmInDir = list_directory(arg);
        return info;
    }

    info->type = filetype_other;
    return info;
}