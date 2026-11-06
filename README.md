# Sandfall

A falling-sand toy and puzzle game for Android. Pour sand, water, oil, salt and seeds, set things on fire, grow gardens, and breed viruses that follow Conway's Game of Life. Then tilt the whole world to make everything pour the other way.

## Install

Download **[Sandfall.apk](../../releases/latest/download/Sandfall.apk)** from the [latest release](../../releases/latest) and open it on your phone. You may need to allow installs from unknown sources. Every release is signed with the same key, so newer versions install as updates and keep your saved worlds.

Runs on Android 8.0 and newer, and is built for Android 16 (API 36).

## How to play

The whole screen is the play area, held upright (portrait).

- **Elements bubble** (starts bottom left): the elements menu. Pick an element, and choose a brush (draw, line, box, filled box) and its size.
- **Move & zoom** (in the brush section): drag with one finger to move around, pinch to zoom in up to 8×, double-tap to zoom back out. With any other tool, two fingers zoom and pan too (whatever the first finger had just drawn is taken back). The zoom stays when you go back to drawing, so you can draw precisely; the magnifier button in the top bar zooms back out.
- **Options bubble** (starts bottom right): the options menu: sandbox or challenges, gravity, tilt with your phone, screen edges, save/load/clear, how fast virus eats plants, how fast fungus grows, how fast mushrooms eat plants, how long steam takes to turn back into water and how much of it does, and how the virus grows.
- **Top:** pause/play, step one frame, speed (1×, 2×, 4×, ½×) and **undo**. Undo takes the world back to just before your last stroke (or Clear/Load), up to 20 steps; in a challenge it also gives back the elements that stroke used.
- **Floating bubbles:** drag either round button anywhere; it drifts to the nearest side and its menu opens beside it. Positions are remembered.
- **Holding the phone sideways:** the game stays portrait, but the interface turns to stay upright. Menus open in the middle of the screen using the long side, and the play/step/speed buttons move to the edge that is on top.
- **Back button / gesture:** closes an open menu. With no menu open, press back twice to exit. There's also an **Exit** button at the bottom of the options menu, next to the **Guide** button, which explains every element, tool and gesture.
- **Long-press** an element in the menu to see what it does; a tap just selects it.
- **Auto-save:** the sandbox is saved whenever you leave the app (and every 20 seconds) and comes back the next time you open it. Save/Load is a separate slot.

| Element | Behaviour |
|---|---|
| Sand | Falls and piles up |
| Water | Flows. Boils into steam when it touches fire or magma, dissolves salt into brine, erodes soil into sand |
| Brine | Salty water. Sinks under fresh water, withers plants, kills virus. Boils into steam, leaving some salt behind (with a yellow flame) |
| Lava | Molten sand: sand touching magma melts into it, and a sand pile on a vent melts down into a lava pool. A thick, glowing liquid that oozes slowly downhill; stays molten near magma. Sets flammable things alight, boils water and brine, boils acid into fumes. Water quenches it into glass; otherwise it cools into glass after about 10-13 s |
| Glass | Cooled lava. A solid, see-through block that acid and fumes can't eat |
| Fumes | Toxic gas made when acid touches magma (40% of the boiled acid; the rest evaporates). Floats and spreads like steam and eats like acid, then fades away after a few seconds. Never turns back into acid |
| Steam | Floats up and spreads out like a gas, sliding along ceilings, then cools and rains back down as water (or fades away). Plants it touches may grow into it |
| Oil | Floats on water, very flammable |
| Acid | Sinks in water. Dissolves salt into nothing, turns brine back into water, quickly eats plants, seeds, spores and fungus, slowly eats sand, soil and virus (never walls or generators). Gets used up as it eats, and water slowly dilutes it. Boils into toxic fumes on magma |
| Fire | Rises and burns out. Clings to and burns plants, oil, seeds and virus |
| Plant | Drinks neighbouring water and grows into it. Won't drink brine |
| Seed | Falls. Sprouts into a plant when wet |
| Salt | Piles up, sinks slowly through oil, dissolves in water into brine, kills virus, makes flames burn yellow, melts away on magma |
| Soil | Solid ground. Tiny loose bits (pieces of fewer than 10 connected cells) and the soil left by dead fungus fall like sand |
| Virus | Lives by Life rules (B3/S23 by default) in air and water. Eats any plant it touches |
| Spores | Float in the air and slowly sink; fade after 10-20 s. Turn steam they touch into fungus, and may sprout a mushroom when they land on a plant or seed. Killed by fire, magma, water, acid, salt and brine |
| Fungus | Mushrooms: gray stalks with red, white-spotted caps that glow faintly. Grows into steam, eats plants and virus, slowly grows into water (water doesn't kill it). Puffs out spores into open air. Needs food: fungus more than ~30 cells (through the colony) from steam, water, plants or virus dies, leaving nothing (70%), soil (23%) or a seed (7%). Dries out within 3 cells of fire or magma. Burns; acid eats it; salt and brine slowly kill it |
| Wall | Blocks everything |
| Cloud / Magma / Shaker / Spill / Toxin | Generators that make water / fire / salt / oil / virus. Magma also sets fire to anything flammable touching it, boils water and brine, melts salt, boils acid into toxic fumes and melts sand into lava |
| Vat | Generator that drips acid. Acid can't eat it |
| Mycelium | Generator that releases spores into the air. Indestructible: only the Eraser removes it |
| Seeder | Drops nothing. Water that touches it turns into plants, which then keep growing into nearby water |

- **Nature sliders:** *Virus eats plants* goes from never to instantly; *Steam turns to water* goes from about half a second to 30 seconds; *Steam that becomes water* sets how much of it comes back as water (0–100%, default 60%) instead of vanishing. Like screen edges, they only apply in the sandbox; challenges use the defaults.
- **Screen edges:** *Solid* edges act like walls. With *Open* edges, anything that moves off the screen is gone for good. Challenges always use solid edges.
- **Tilt:** ⟲ ⟳ rotate gravity in 45° steps, or turn on **📱 Tilt phone** so gravity follows the way you hold the device.
- **Challenges:** pick a puzzle in the options menu. Each one gives you a limited supply of certain elements to reach a goal. Tap the goal card to hide or show the hint.
  1. **Into the Basin** – steer the rain into the basin with walls.
  2. **Controlled Burn** – clear five groves with two sparks and some oil.
  3. **Quarantine** – wipe out a virus colony with salt.
  4. **Green Thumb** – get rain past a magma vent to the seeds.
  5. **Fresh Water** – boiled brine makes fresh rain; roof it over to the garden.
  6. **Salt Mine** – dissolve most of a salt mountain with Vats and wall off the orchard pit from the acid.
  7. **Fungal Bloom** – grow 450 mushrooms in a steamy cave from 40 spores.

## Building

The app is a thin WebView wrapper (`app/src/main/java`) around the game, which is one self-contained file: `app/src/main/assets/index.html`.

- **Android Studio:** open the folder and run.
- **Command line:** `gradle :app:assembleRelease` (needs JDK 17+ and Gradle 8.13 or newer). Every push builds the APK on GitHub Actions. Every push to `main` also publishes a GitHub release with the APK, signed with the key in the `SIGNING_KEYSTORE_BASE64` and `SIGNING_PASSWORD` repository secrets (alias `sandfall`). Without those secrets the build is signed with a throwaway debug key and no release is published.
- **No Gradle?** `tools/build-apk.sh` builds a debug-signed APK straight from the Android SDK build tools (needs JDK 17+ and the android-36 platform).
