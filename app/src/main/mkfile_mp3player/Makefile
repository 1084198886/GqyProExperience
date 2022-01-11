lib_codec := lib/codec
lib_db    := lib/db
lib_ui    := lib/ui
libraries := $(lib_codec) $(lib_db) $(lib_ui)
player    := app/player
MAKE=make

.PHONY : all $(player) $(libraries) test lib
all: $(player)

$(player) $(libraries):
	$(info  "echo start=============="$@)
	$(MAKE) -C $@
$(player) : $(libraries)
$(lib_ui) : $(lib_db) $(lib_codec)


a:=a
b:=b
lib:= $(a) $(b)
test:$(lib)
$(lib):
	$(info  "lib start=============="$@)