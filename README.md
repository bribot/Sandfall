# Sandfall

A falling-sand toy and puzzle game for Android and the browser. Pour sand, water, oil, salt and seeds, set things on fire, grow gardens, and breed viruses that follow Conway's Game of Life. Then tilt the whole world to make everything pour the other way.

## Play

- **Android:** download `Sandfall.apk` from the latest [Actions build](../../actions) (artifact **Sandfall-apk**) or from a [release](../../releases), then open it on your phone to install. You may need to allow installs from unknown sources.
- **Browser:** open `web/index.html`. It's one self-contained file with no dependencies.

## How to play

| Element | Behaviour |
|---|---|
| Sand | Falls and piles up |
| Water | Flows. Puts out fire, dissolves salt, erodes soil into sand |
| Oil | Floats on water, very flammable |
| Fire | Rises and burns out. Burns plants, oil, seeds and virus |
| Plant | Drinks neighbouring water and grows into it |
| Seed | Falls. Sprouts into a plant when wet |
| Salt | Piles up, sinks slowly through oil, dissolves in water, kills virus |
| Soil | Solid ground |
| Virus | Lives by Life rules (B3/S23 by default) in air and water, and infects plants |
| Wall | Blocks everything |
| Cloud / Magma / Shaker / Spill / Toxin | Generators that make water / fire / salt / oil / virus |

- **Tilt:** ⟲ ⟳ rotate gravity in 45° steps. On a phone, turn on **📱 Tilt phone** and gravity follows the way you hold the device.
- **Challenges:** pick a puzzle from the mode menu. Each one gives you a limited supply of certain elements to reach a goal.
- **Keyboard (desktop):** Space = play/pause, Q/E = tilt, `[` `]` = brush size, 0–9 = pick an element. Right-click erases.

## Building

The Android app is a thin WebView wrapper (`app/`) around the game in `web/`. Gradle packs `web/` into the APK's assets.

- **Android Studio:** open the folder and run.
- **Command line:** `gradle :app:assembleRelease` (needs JDK 17 and Gradle 8.9 or newer). Every push to `main` builds the APK on GitHub Actions. Pushing a `v*` tag also attaches the APK to a GitHub release.
- **No Gradle?** `tools/build-apk.sh` builds a debug-signed APK straight from the Android SDK build tools.
