.PHONEY: .o

APPPATH=./app/build/outputs/apk/release
BUILDAPK=ExperiencePro_release.apk
GRADLE=./gradlew
ZIP=zip
SALT_KEY=.sign_salt
SIGN_FILE=hash256.sign

OS=$(shell uname)
#findstring x,y :如果y中含有x，返回x，否则返回空
#ifneq ：如果不相等
ifneq (,$(findstring MINGW,$(OS)))
GRADLE=graldew.bat
else
GRADLE=./gradlew
endif  #if的结束符

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

#模式字符串替换函数.$(patsubst <pattern>,<replacement>,<text>)
#功能:查找text中的单词是否符合模式 pattern，如果匹配的话，则用 replacement 替换。返回值为替换后的新字符串
OBJ=$(patsubst %.c,%.o,1.c 2.c 3.o)
patsubsttest:
	@echo  "patsubsttest result:"$(OBJ)

#字符串替换函数:$(subst <from>,<to>,<text>)
#函数说明：函数的功能是把字符串中的 form 替换成 to，返回值为替换后的新字符串
SUBSTOBJ=$(subst ee,EE, feet on the street)
subst:
	@echo "subst:"$(SUBSTOBJ)

#去空格函数:$(strip <string>)
STRIPOBJ=$(strip    a       b c)
strip:
	@echo "strip:"$(STRIPOBJ)

#查找字符串函数:$(findstring <find>,<in>) 函数的功能是查找  in 中的 find ,如果我们查找的目标字符串存在。返回值为目标字符串，如果不存在就返回空
FINDSTRINGOBJ=$(findstring b,ab)
findstring:
	@echo "findstring:"$(FINDSTRINGOBJ)

#过滤函数:$(filter <pattern>,<text>)  函数的功能是过滤出 text 中符合模式 pattern 的字符串，可以有多个 pattern 。返回值为过滤后的字符串
FILTEROBJ=$(filter %.c %.o,1.c 2.o 3.o 4.x)
filter:
	@echo "filter:"$(FILTEROBJ)

#反过滤函数:$(filter-out <pattern>,<text>)  函数的功能是功能和 filter 函数正好相反，但是用法相同。去除符合模式  pattern 的字符串，保留符合的字符串。返回值是保留的字符串
FILTEROUTOBJ=$(filter-out %.c %.o,1.c 2.o 3.o 4.x)
filterout:
	@echo "filter-out:"$(FILTEROUTOBJ)

#排序函数:$(sort <list>)  函数的功能是将 <list> 中的单词排序（升序）。返回值为排列后的字符串
SORTOBJ=$(sort  dd  aa aa ddcc)
sort:
	@echo "sort:"$(SORTOBJ)

#$(foreach <var>,<list>,<text>) 功能：把参数<list>中的单词逐一取出放到参数 <var> 所指定的变量中，然后再执行<text>所包含的表达式。
#<text> 的返所返回的每个字符串会以空格分割，<text> 所返回的每个字符串所组成的整个字符串（以空格分隔）将会是 foreach 函数的返回值
name:= a b c d
files:=$(foreach n,$(name),$(n)*c)
foreach:
	@echo "files="$(files)

#$(if <condition>,<then-part>)或(if<condition>,<then-part>,<else-part>)
# condition参数是 if 表达式，如果其返回的是非空的字符串，那么这个表达式就相当于返回真，于是，then-part就会被计算，否则else-part会被计算。
ifobj:=foo1.c
ifobj:=$(if $(ifobj),$(ifobj),foo2.c)
ifcondition:
	@echo "if condition="$(ifobj)

#$(if <condition>,<then-part>)或(if<condition>,<then-part>,<else-part>)
# condition参数是 if 表达式，如果其返回的是非空的字符串，那么这个表达式就相当于返回真，于是，then-part就会被计算，否则else-part会被计算。
callob=$(1)a $(2)b
callobj=$(call callob,a,b)
call:
	@echo "call="$(callobj)

#取目录函数:$(dir <names>)  #取目录函数:$(dir <names>)  从文件名序列 names 中取出目录部分，如果names中没有 "/" ，取出的值为 "./" 。
DIROBJ=$(dir  src/foo.c hacks)
dir:
	@echo "dir:"$(DIROBJ)

#取文件函数:$(notdir <names>)
NOTDIROBJ=$(notdir  src/foo.c hacks)
notdir:
	@echo "notdir:"$(NOTDIROBJ)

#取后缀名函数:$(suffix  <names>)
SUFFIX=$(suffix  src/foo.c hacks)
suffix:
	@echo "suffix:"$(SUFFIX)

#取前缀函数:$(basename   <names>)
BASENAMEOJB=$(basename  src/foo.c hacks)
basename:
	@echo "basename:"$(BASENAMEOJB)

#添加前缀函数:$(addsuffix <suffix>,<names>)
ADDSUFFIXOBJ=$(addsuffix  .c, src/foo.c hacks)
addsuffix:
	@echo "addsuffix:"$(ADDSUFFIXOBJ)

#添加前缀函数:$(addprefix <suffix>,<names>)
ADDPREFIXOBJ=$(addprefix  .c, src/foo.c hacks)
addprefix:
	@echo "addprefix:"$(ADDPREFIXOBJ)

subdir:
	cd foo && $(MAKE) apk

aa:cc
	make -C  foo

cc:
	make -C baz

#链接函数:函数功能是把 list2 中的单词对应的拼接到 list1 的后面。如果 list1 的单词要比 list2的多，
#那么，list1 中多出来的单词将保持原样，如果 list1 中的单词要比 list2 中的单词少，
#那么 list2 中多出来的单词将保持原样。返回值为拼接好的字符串
JOINOBJ=$(join src car,abc zxc qwe)
join:
	@echo "join:"$(JOINOBJ)

#获取匹配模式文件名函数  $(wildcard PATTERN)  可以得到当前函数下所有的 ".c " 和  ".h"  结尾的文件
WILDCARDOBJ=$(wildcard *.cpp *.h)
wildcard:
	@echo "wildcard:"$(WILDCARDOBJ)

#取单词函数:$(word <n>,<text>)  函数的功能是取出函数 <text> 中的第n个单词。返回值为我们取出的第 n 个单词
WORDOBJ=$(word  1,ddfsdfsdfsfdf aa aa ddcc)
word:
	@echo "word:"$(WORDOBJ)

#定义伪目标
.PHONY:subdirs $(SUBDIRS)

SUBDIRS=foo bar baz
MAKE=make

subdirs:$(SUBDIRS)
$(SUBDIRS):
	$(MAKE)  -C $@
foo:baz