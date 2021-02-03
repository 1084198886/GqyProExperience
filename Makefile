.PHONEY: .o

APPPATH=./app/build/outputs/apk/release
BUILDAPK=ExperiencePro_release.apk
GRADLE=./gradlew
ZIP=zip
SALT_KEY=.sign_salt
SIGN_FILE=hash256.sign

OS=$(shell uname)

ifneq (,$(findstring MINGW,$(OS)))
GRADLE=gradlew.bat
else
GRADLE=./gradlew
endif

VERSION=$(shell git describe --abbrev=4 --dirty --always --tags)
PUBLISH=jgypublish

apk:
	@echo "build app apk!"
	$(GRADLE) app:assembleRelease
	@echo "build upgrade app zip"
	cat $(APPPATH)/$(BUILDAPK) $(SALT_KEY) | sha256sum - | cut -d' ' -f 1 | tr -d '\n' > $(APPPATH)/$(SIGN_FILE)
	cd $(APPPATH) && $(ZIP) ExperiencePro-$(VERSION).zip $(SIGN_FILE) $(BUILDAPK)

clean:
	@echo "remove build cache apk!"
	cd $(APPPATH) && rm -rf *.apk *.json *.sign
	cd $(LCPATH) && rm -rf *.apk *.json *.sign
	@echo "remove build cache file success"
