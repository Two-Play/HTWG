#
# Makefile
#
# Autor: J.Middelberg
# Erstellt am: 10.01.2024
#

#----------------------------------- Kommando-Variablen
CXX = g++
CPPFLAGS = -fPIC -I.
CXXFLAGS = -Wall -Wextra -Werror -pedantic
RM = rm -f
AR = ar
ARFLAGS = rs
LIBTYPE = a

#--------------------------------------- Hilfsvariablen
LIBNAME = aufgabe6
TARGET = lib$(LIBNAME)
OBJECTS = \
    benotung.o \
    fachnote.o \
    fachnoten_liste.o
SOURCES = $(OBJECTS:.o=.cpp)
HEADERS = $(OBJECTS:.o=.h)

#----------------------------------------- Musterregeln
%.o: %.cpp
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

#---------------------------------------- Standardziele
.PHONY: all clean

all: $(TARGET).$(LIBTYPE)

clean:
	$(RM) $(TARGET).a $(TARGET).so $(OBJECTS) depend

depend: $(SOURCES) $(HEADERS)
	$(CXX) $(CPPFLAGS) -MM $(SOURCES) > $@

#-------------------------- Ziele zur Programmerstellung
$(TARGET).a: $(OBJECTS)
	$(AR) $(ARFLAGS) $@ $^

$(TARGET).so: $(OBJECTS)
	$(CXX) -shared $^ -o $@

#-------------------------------------- Abhaengigkeiten
include depend