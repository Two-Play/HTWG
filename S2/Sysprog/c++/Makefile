#
# Makefile
#
# Autor: J.Middelberg
# Erstellt am: 10.01.2024
#

#--------------------------------------- Hilfsvariablen
TARGETDIRS = lib bin

#---------------------------------------- Standardziele
.PHONY: all clean

all clean:
	for p in $(TARGETDIRS) ; do \
		(cd $$p && $(MAKE) $@) ; \
	done
