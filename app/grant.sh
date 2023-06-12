#!/usr/bin/env bash

if [ "$#" -ne 2 ] || [ ! -x "$1" ]; then
  echo "Usage: $0 ADB_EXECUTABLE PACKAGE_NAME"
  exit 1
fi

ADB_EXECUTABLE="$1"
PACKAGE_NAME="$2"

grant() {
  "$ADB_EXECUTABLE" -s "$device" shell pm grant "$PACKAGE_NAME" "android.permission.$1" >/dev/null 2>&1
}

DEVICES=$("$ADB_EXECUTABLE" devices | grep device | grep -v devices | cut -f1)

for device in ${DEVICES}; do
  grant WRITE_SECURE_SETTINGS
  grant SET_ALWAYS_FINISH
done

exit 0
