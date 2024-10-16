# Hilfsvariablen
TARGETDIRS = lib bin

.PHONY: all clean

all clean:
	for p in $(TARGETDIRS) ; do \
		(cd $$p && $(MAKE) $@) ; \
	done
