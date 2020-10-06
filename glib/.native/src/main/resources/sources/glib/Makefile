CFLAGS=$(shell pkg-config --cflags glib-2.0)

glib_helpers.o: glib_helpers.c
	$(CC) $(CFLAGS) -c glib_helpers.c $<

clean:
	rm -f glib_helpers.o

.PHONY: clean