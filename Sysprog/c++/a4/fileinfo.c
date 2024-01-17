/*
 * fileinfo.c
 *
 * .
 *
 * Author J.Middelberg
 * Erstellt am: 23.11.2023
 */
#include "fileinfo.h"

#include <stdio.h>
#include <errno.h>
#include "limits.h"
#include <string.h>
#include <stdlib.h>
#include <sys/stat.h> // lstat
#include <dirent.h>
#include <unistd.h>

static fileinfo* list_directory(char* dirname)
{
    // Öffne Verzeichnis
    DIR *dir = opendir(dirname);
    if (dir == NULL)
    {
        fprintf(stderr, "%s konnte nicht geöffnet werden\n", dirname);
        return NULL;
    }
    errno = 0;

    // Wechsle das Arbeitsverzeichnis um keinen Pfad mitführen zu müssen
    if (chdir(dirname) != 0)
    {
        fprintf(stderr, "Fehler beim Wechseln des Arbeitsverzeichnisses\n");
        closedir(dir);
        return NULL;
    }

    // Zeiger auf Variable für Verzeichniseintrag
    struct dirent *entry;

    // Zeiger für Verzeichniseintragsliste
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

    // Zurücksetzen des Arbeitsverzeichnisses
    if (chdir("..") != 0)
    {
        fprintf(stderr, "Fehler beim Zurücksetzen des Arbeitsverzeichnisses\n");
        return NULL;
    }

    return head;
}

fileinfo* fileinfo_create(char* filename)
{
    // Überprüfe Länge des Dateinamens
    if (strlen(filename) > NAME_MAX)
    {
        errno = ENAMETOOLONG;
        return NULL;
    }

    // Erhalte Dateiinformationen
    struct stat fileStat;
    if (lstat(filename, &fileStat) == -1)
    {
        return NULL;
    }

    // Erstelle neuen Zeiger auf struct fileinfo
    fileinfo *info = (fileinfo*) malloc(sizeof(fileinfo));
    if (info == NULL)
    {
        fprintf(stderr, "Speicherreservierung fehlgeschlagen\n");
        return NULL;
    }

    // Überprüfe Dateityp und schreibe Informationen in fileinfo
    strcpy(info->filename, filename);
    info->next = NULL;

    if (S_ISREG(fileStat.st_mode))
    {
        info->type = filetype_regular;
        info->size = fileStat.st_size;
    }
    else if (S_ISDIR(fileStat.st_mode))
    {
        info->type = filetype_directory;
        info->list = list_directory(filename);
    }
    else
    {
        info->type = filetype_other;
    }

    return info;
}

void fileinfo_destroy(fileinfo *info)
{
    if (info->type == filetype_directory)
    {
        fileinfo* current = info->list;

        while (current != NULL)
        {
            fileinfo *next = current->next;

            if (current->type == filetype_directory && current->list)
            {
                fileinfo_destroy(current->list);
            }

            free(current);
            current = next;
        }
    }

    free(info);
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
        fprintf(stderr, "Speicherreservierung fehlgeschlagen\n");
        return;
    }

    // Wenn path nicht leer dann neuen Ordner mit "/" anhängen, sonst nur Ordner anhängen
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
            print_directory(new_path, current->filename, current->list);
        }

        current = next;
    }

    free(new_path);
}

void fileinfo_print(const fileinfo *info)
{
    switch (info->type)
    {
    case filetype_regular:
        print_regular(info->filename, info->size);
        break;
    case filetype_directory:
        print_directory("", info->filename, info->list);
        break;
    case filetype_other:
        print_other(info->filename);
        break;
    }
}