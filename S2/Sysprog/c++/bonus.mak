#
# bonus.mak
#
# Autor: J.Middelberg
# Erstellt am: 10.01.2024
#

#----------------------------------- Kommando-Variablen
TAR = tar
TARFLAGS = -c -z -v -f
SOURCEFILES = bin lib bonus.mak Makefile
RM = rm -f

#--------------------------------------- Hilfsvariablen
TARGET = aufgabe6.tar.gz

#---------------------------------------- Standardziele
.PHONY: all clean

all: $(TARGET)

clean:
	$(RM) $(TARGET)

#------------------------- Ziele zur Programmerstellung
$(TARGET): $(SOURCEFILES)
	$(TAR) $(TARFLAGS) $@ $^
