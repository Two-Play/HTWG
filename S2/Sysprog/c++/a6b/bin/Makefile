#
# Makefile
#
# Autor: J.Middelberg
# Erstellt am: 10.01.2024
#

#----------------------------------- Kommando-Variablen
CXX = g++
CPPFLAGS = -I../lib
LDFLAGS = -L../lib -l$(LIBNAME)
CXXFLAGS = -Wall -Wextra -Werror -pedantic
RM = rm -f
LIBTYPE = a

#--------------------------------------- Hilfsvariablen
LIBNAME = aufgabe6
LIBNAMEFULL = lib$(LIBNAME).$(LIBTYPE)
LIBPATH = ../lib
TARGET = notenspiegel
SOURCES = $(TARGET).cpp

#----------------------------------------- Musterregeln
%.o: %.cpp
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

#---------------------------------------- Standardziele
.PHONY: all clean

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o depend

depend: $(SOURCES)
	$(CXX) $(CPPFLAGS) -MM $(SOURCES) > $@

#------------------------- Ziele zur Programmerstellung
$(TARGET): $(TARGET).o $(LIBPATH)/$(LIBNAMEFULL)
	$(CXX) $^ $(LDFLAGS) -o $@

#-------------------------------------- Abhaengigkeiten
include depend