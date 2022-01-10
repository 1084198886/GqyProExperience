.PHONEY: .o

APPPATH=./app/build/outputs/apk/release
BUILDAPK=ExperiencePro_release.apk
GRADLE=./gradlew
ZIP=zip
SALT_KEY=.sign_salt
SIGN_FILE=hash256.sign

OS=$(shell uname)

ifneq (,$(findstring MINGW,$(OS)))
GRADLE=graldew.bat
else
GRADLE=./gradlew
endif

#Makefile使用#注释
VERSION=$(shell git describe   --dirty --always --tags)

# 伪目标实现多文件编辑，执行make all时，会顺序执行 apk  和clean下的命令
all: apk clean

apk:
	@echo "foo GRADLE="$(GRADLE)",OS="$(OS)+",version="$(VERSION)
#	cat $(APPPATH)/$(BUILDAPK) $(SALT_KEY) | sha256sum - | cut -d' ' -f 1 | tr -d '\n' > $(APPPATH)/$(SIGN_FILE)
	#cd $(APPPATH) && $(ZIP) ExperiencePro-$(VERSION).zip $(SIGN_FILE) $(BUILDAPK)

clean:
	@echo "remove build cache apk!"
	cd $(APPPATH) && rm -rf *.apk *.json *.sign
	@echo "remove build cache file success"


#定义伪目标
.PHONY:subdirs $(SUBDIRS)

SUBDIRS=foo bar baz
MAKE=make

subdirs:$(SUBDIRS)
$(SUBDIRS):
	$(MAKE)  -C $@
foo:baz