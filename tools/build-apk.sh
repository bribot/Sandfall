#!/usr/bin/env bash
# Quick APK build that skips Gradle and calls the Android SDK tools directly.
# Handy on machines with an older SDK/JDK. The normal build is `gradle :app:assembleRelease`
# (or just open the project in Android Studio).
#
#   ANDROID_HOME=/path/to/sdk JAVA_HOME=/path/to/jdk tools/build-apk.sh
#
# Output: build/Sandfall.apk (debug-signed)
set -euo pipefail
cd "$(dirname "$0")/.."

SDK="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-${LOCALAPPDATA:-$HOME}/Android/Sdk}}"
# BUILD_TOOLS picks a version (e.g. 29.0.3 if your JDK is 8 — newer apksigner needs JDK 9+).
BT="$SDK/build-tools/${BUILD_TOOLS:-$(ls "$SDK/build-tools" | sort -V | tail -1)}"
API="$(ls "$SDK/platforms" | sed 's/android-//' | sort -n | tail -1)"
JAR="$SDK/platforms/android-$API/android.jar"
JBIN="${JAVA_HOME:+$JAVA_HOME/bin/}"
echo "SDK build-tools: $BT"
echo "Platform:        android-$API"

OUT=build/quick
rm -rf "$OUT"; mkdir -p "$OUT/classes" "$OUT/dex" "$OUT/gen"

# Manifest: Gradle supplies the package name, so add it here.
sed 's|<manifest |<manifest package="com.bribot.sandfall" |' app/src/main/AndroidManifest.xml > "$OUT/AndroidManifest.xml"

"$BT/aapt2" compile --dir app/src/main/res -o "$OUT/res.zip"
"$BT/aapt2" link -o "$OUT/unsigned.apk" -I "$JAR" \
  --manifest "$OUT/AndroidManifest.xml" -R "$OUT/res.zip" -A web \
  --min-sdk-version 24 --target-sdk-version "$API" \
  --version-code 1 --version-name 1.0 --java "$OUT/gen" --auto-add-overlay

"${JBIN}javac" -source 8 -target 8 -nowarn -bootclasspath "$JAR" -d "$OUT/classes" \
  $(find app/src/main/java "$OUT/gen" -name '*.java')

"${JBIN}java" -cp "$BT/lib/d8.jar" com.android.tools.r8.D8 --release --min-api 24 --lib "$JAR" --output "$OUT/dex" $(find "$OUT/classes" -name '*.class')
(cd "$OUT/dex" && "$BT/aapt" add ../unsigned.apk classes.dex >/dev/null)

"$BT/zipalign" -f 4 "$OUT/unsigned.apk" "$OUT/aligned.apk"

KS=build/debug.keystore
if [ ! -f "$KS" ]; then
  "${JBIN}keytool" -genkeypair -keystore "$KS" -storepass android -keypass android \
    -alias androiddebugkey -dname "CN=Android Debug,O=Android,C=US" -keyalg RSA -keysize 2048 -validity 10000
fi
"${JBIN}java" -jar "$BT/lib/apksigner.jar" sign --ks "$KS" --ks-pass pass:android --key-pass pass:android \
  --out build/Sandfall.apk "$OUT/aligned.apk"
echo "Built build/Sandfall.apk"
