
#Variablen
CXX = g++
CPPFLAGS = -I../lib
LDFLAGS = -L../lib -l$(LIBNAME)
CXXFLAGS = -Wall -Wextra -Werror -pedantic -std=c++11
LIBTYPE = a
RM = rm -f

# Hilfsvariablen
LIBNAME = aufgabe6
LIBNAMEFULL = lib$(LIBNAME).$(LIBTYPE)
LIBPATH = ../lib
TARGET = notenspiegel
SOURCES = $(TARGET).cpp

# Musterregeln
%.o: %.cpp
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

.PHONY: all clean

all: $(TARGET)

clean:
	$(RM) $(TARGET) $(TARGET).o depend

depend: $(SOURCES)
	$(CXX) $(CPPFLAGS) -MM $(SOURCES) > $@

# Ziele zur Programmerstellung
$(TARGET): $(TARGET).o $(LIBPATH)/$(LIBNAMEFULL)
	$(CXX) $< $(LDFLAGS) -o $@

# Abhaengigkeiten
include depend