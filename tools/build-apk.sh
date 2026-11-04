#!/usr/bin/env bash
# Quick APK build that skips Gradle and calls the Android SDK tools directly.
# The normal build is `gradle :app:assembleRelease` (or just open the project in Android Studio).
#
# Needs JDK 17+, the Android 16 platform (android-36) and build-tools 35 or newer:
#   sdkmanager "platforms;android-36" "build-tools;36.0.0"
#   ANDROID_HOME=/path/to/sdk JAVA_HOME=/path/to/jdk17 tools/build-apk.sh
#
# Output: build/Sandfall.apk (debug-signed)
set -euo pipefail
cd "$(dirname "$0")/.."

SDK="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-${LOCALAPPDATA:-$HOME}/Android/Sdk}}"
BT="$SDK/build-tools/${BUILD_TOOLS:-$(ls "$SDK/build-tools" | sort -V | tail -1)}"
API=36
JAR="$SDK/platforms/android-$API/android.jar"
JBIN="${JAVA_HOME:+$JAVA_HOME/bin/}"
[ -f "$JAR" ] || { echo "Missing platform android-$API. Install it with: sdkmanager \"platforms;android-$API\"" >&2; exit 1; }
echo "SDK build-tools: $BT"
echo "Platform:        android-$API"

OUT=build/quick
rm -rf "$OUT"; mkdir -p "$OUT/classes" "$OUT/dex" "$OUT/gen"

# Manifest: Gradle supplies the package name, so add it here.
sed 's|<manifest |<manifest package="com.bribot.sandfall" |' app/src/main/AndroidManifest.xml > "$OUT/AndroidManifest.xml"

"$BT/aapt2" compile --dir app/src/main/res -o "$OUT/res.zip"
"$BT/aapt2" link -o "$OUT/unsigned.apk" -I "$JAR" \
  --manifest "$OUT/AndroidManifest.xml" -R "$OUT/res.zip" -A app/src/main/assets \
  --min-sdk-version 26 --target-sdk-version "$API" \
  --version-code 2 --version-name 2.0 --java "$OUT/gen" --auto-add-overlay

"${JBIN}javac" --release 17 -nowarn -classpath "$JAR" -d "$OUT/classes" \
  $(find app/src/main/java "$OUT/gen" -name '*.java')

"${JBIN}java" -cp "$BT/lib/d8.jar" com.android.tools.r8.D8 --release --min-api 26 --lib "$JAR" --output "$OUT/dex" $(find "$OUT/classes" -name '*.class')
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
