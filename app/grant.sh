#!/usr/bin/env bash

PACKAGE_NAME="$1"

grant() {
  adb -s "$device" shell pm grant "$PACKAGE_NAME" "android.permission.$1" >/dev/null 2>&1
}

DEVICES=$(adb devices | grep device | grep -v devices | cut -f1)

for device in ${DEVICES}; do
  grant WRITE_SECURE_SETTINGS
  grant SET_ALWAYS_FINISH
done

exit 0
