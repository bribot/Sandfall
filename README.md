# Sandfall

A falling-sand toy and puzzle game for Android. Pour sand, water, oil, salt and seeds, set things on fire, grow gardens, and breed viruses that follow Conway's Game of Life. Then tilt the whole world to make everything pour the other way.

## Install

Download **[Sandfall.apk](../../releases/latest/download/Sandfall.apk)** from the [latest release](../../releases/latest) and open it on your phone. You may need to allow installs from unknown sources. Every release is signed with the same key, so newer versions install as updates and keep your saved worlds.

Runs on Android 8.0 and newer, and is built for Android 16 (API 36).

## How to play

The whole screen is the play area, held upright (portrait).

- **Bottom left:** the elements menu. Pick an element, and choose a brush (draw, line, box, filled box) and its size.
- **Bottom right:** the options menu: sandbox or challenges, gravity, tilt with your phone, screen edges, save/load/clear, how fast virus eats plants, how long steam takes to turn back into water, and how the virus grows.
- **Top:** pause/play, step one frame, and speed (1×, 2×, 4×, ½×).
- **Back button / gesture:** closes an open menu.

| Element | Behaviour |
|---|---|
| Sand | Falls and piles up |
| Water | Flows. Boils into steam when it touches fire or magma, dissolves salt into brine, erodes soil into sand |
| Brine | Salty water. Sinks under fresh water, withers plants, kills virus. Boils away into salt (with a yellow flame) |
| Steam | Floats up, then cools and rains back down as water (or fades away) |
| Oil | Floats on water, very flammable |
| Fire | Rises and burns out. Clings to and burns plants, oil, seeds and virus |
| Plant | Drinks neighbouring water and grows into it. Won't drink brine |
| Seed | Falls. Sprouts into a plant when wet |
| Salt | Piles up, sinks slowly through oil, dissolves in water into brine, kills virus, makes flames burn yellow |
| Soil | Solid ground |
| Virus | Lives by Life rules (B3/S23 by default) in air and water. Eats any plant it touches |
| Wall | Blocks everything |
| Cloud / Magma / Shaker / Spill / Toxin / Seeder | Generators that make water / fire / salt / oil / virus / seeds. Magma also sets fire to anything flammable touching it and boils water and brine |

- **Nature sliders:** *Virus eats plants* goes from never to instantly; *Steam turns to water* goes from about half a second to 30 seconds. Like screen edges, they only apply in the sandbox; challenges use the defaults.
- **Screen edges:** *Solid* edges act like walls. With *Open* edges, anything that moves off the screen is gone for good. Challenges always use solid edges.
- **Tilt:** ⟲ ⟳ rotate gravity in 45° steps, or turn on **📱 Tilt phone** so gravity follows the way you hold the device.
- **Challenges:** pick a puzzle in the options menu. Each one gives you a limited supply of certain elements to reach a goal. Tap the goal card to hide or show the hint.

## Building

The app is a thin WebView wrapper (`app/src/main/java`) around the game, which is one self-contained file: `app/src/main/assets/index.html`.

- **Android Studio:** open the folder and run.
- **Command line:** `gradle :app:assembleRelease` (needs JDK 17+ and Gradle 8.13 or newer). Every push builds the APK on GitHub Actions. Every push to `main` also publishes a GitHub release with the APK, signed with the key in the `SIGNING_KEYSTORE_BASE64` and `SIGNING_PASSWORD` repository secrets (alias `sandfall`). Without those secrets the build is signed with a throwaway debug key and no release is published.
- **No Gradle?** `tools/build-apk.sh` builds a debug-signed APK straight from the Android SDK build tools (needs JDK 17+ and the android-36 platform).
