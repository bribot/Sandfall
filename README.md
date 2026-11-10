# Sandfall

A falling-sand toy and puzzle game for Android. Pour sand, water, oil, salt and seeds, set things on fire, grow gardens, and breed viruses that follow Conway's Game of Life. Then tilt the whole world to make everything pour the other way.

## Install

Download **[Sandfall.apk](../../releases/latest/download/Sandfall.apk)** from the [latest release](../../releases/latest) and open it on your phone. You may need to allow installs from unknown sources. Every release is signed with the same key, so newer versions install as updates and keep your saved worlds.

Runs on Android 8.0 and newer, and is built for Android 16 (API 36).

## How to play

The whole screen is the play area, held upright (portrait).

- **Elements bubble** (starts bottom left): the elements menu. Pick an element, and choose a brush (draw, line, box, filled box) and its size.
- **Move & zoom** (in the brush section): drag with one finger to move around, pinch to zoom in up to 8×, double-tap to zoom back out. With any other tool, two fingers zoom and pan too (whatever the first finger had just drawn is taken back). The zoom stays when you go back to drawing, so you can draw precisely; the magnifier button in the top bar zooms back out.
- **Options bubble** (starts bottom right): the options menu: sandbox or challenges, gravity, tilt with your phone, screen edges, save/load/clear, how fast virus eats plants, how fast fungus grows, how fast mushrooms eat plants, how long steam takes to turn back into water and how much of it does, and how the virus grows, and how often lightning strikes.
- **Top:** pause/play, step one frame, speed (1×, 2×, 4×, ½×) and **undo**. Undo takes the world back to just before your last stroke (or Clear/Load), up to 20 steps; in a challenge it also gives back the elements that stroke used.
- **Floating bubbles:** drag either round button anywhere; it drifts to the nearest side and its menu opens beside it. Positions are remembered.
- **Holding the phone sideways:** the game stays portrait, but the interface turns to stay upright. Menus open in the middle of the screen using the long side, and the play/step/speed buttons move to the edge that is on top.
- **Back button / gesture:** closes an open menu. With no menu open, press back twice to exit. There's also an **Exit** button at the bottom of the options menu, next to the **Guide** button, which explains every element, tool and gesture.
- **Long-press** an element in the menu to see what it does; a tap just selects it.
- **Auto-save:** the sandbox is saved whenever you leave the app (and every 20 seconds) and comes back the next time you open it. Save/Load is a separate slot.

| Element | Behaviour |
|---|---|
| Sand | Falls and piles up |
| Water | Flows. Boils into steam when it touches fire or magma, dissolves salt into brine, erodes soil into sand, and slowly trickles down through soil and sand |
| Brine | Salty water. Sinks under fresh water, withers plants, kills virus. Boils into steam, leaving some salt behind (with a yellow flame) |
| Lava | Molten sand: sand touching magma melts into it, and a sand pile on a vent melts down into a lava pool. A thick, glowing liquid that oozes slowly downhill; stays molten near magma. Sets flammable things alight and throws off the odd flame while hot, boils water and brine, boils acid into fumes. Water quenches it into glass; otherwise it cools into glass after about 10-13 s |
| Glass | Cooled lava. A solid, see-through block that acid and fumes can't eat. Magma melts it back into lava, even under water |
| Fumes | Toxic gas made when acid touches magma (40% of the boiled acid; the rest evaporates). Floats and spreads like steam and eats like acid, then fades away after a few seconds. Never turns back into acid. **Explodes** when it touches fire, magma or lava: the blast throws loose things outward, shatters glass, crumbles soil, and sets off the rest of the cloud |
| Steam | Floats up and spreads out like a gas, sliding along ceilings, then cools and rains back down as water (or fades away). Plants it touches may grow into it |
| Oil | Floats on water, very flammable; burning oil now and then pops in a small explosion |
| Acid | Sinks in water. Dissolves salt into nothing, turns brine back into water, quickly eats plants, seeds, spores and fungus, slowly eats sand, soil and virus (never walls or generators). Gets used up as it eats, and water slowly dilutes it. Boils into toxic fumes on magma, and an open pool slowly gives off fumes too, shrinking as it does |
| Fire | Rises and burns out. Clings to and burns plants, oil, seeds and virus |
| Plant | Drinks neighbouring water and grows into it. Won't drink brine |
| Seed | Falls. Sprouts into a plant when wet |
| Salt | Piles up, sinks slowly through oil, dissolves in water into brine, kills virus, makes flames burn yellow, melts away on magma |
| Soil | Solid ground. Water slowly trickles down through it (and through sand), swapping places with the grains: a tiny chance at the surface, much more once it is buried in soil or sand, and it drips out underneath. Tiny loose bits (pieces of fewer than 10 connected cells) and the soil left by dead fungus fall like sand |
| Virus | Lives by Life rules (B3/S23 by default) in air and water. Eats any plant it touches |
| Spores | Float in the air and slowly sink; fade after 10-20 s. Turn steam they touch into fungus, and may sprout a mushroom when they land on a plant or seed. Killed by fire, magma, water, acid, salt and brine |
| Fungus | Mushrooms: gray stalks with red, white-spotted caps that glow faintly. Grows into steam, eats plants and virus, slowly grows into water (water doesn't kill it). Puffs out spores into open air. Needs food: fungus more than ~30 cells (through the colony) from steam, water, plants or virus slowly dies (a starving colony fades over a minute or two), leaving nothing (70%), soil (23%) or a seed (7%). Dries out within 3 cells of fire or magma. Burns; acid eats it; salt and brine slowly kill it |
| Wall | Blocks everything |
| Cloud / Magma / Shaker / Spill / Toxin | Generators that make water / fire / salt / oil / virus. Magma also sets fire to anything flammable touching it, boils water and brine, melts salt, boils acid into toxic fumes and melts sand into lava |
| Vat | Generator that drips acid. Acid can't eat it |
| Mycelium | Generator that releases spores into the air. Indestructible: only the Eraser removes it |
| Seeder | Drops nothing. Water that touches it turns into plants, which then keep growing into nearby water |

- **Nature sliders:** *Virus eats plants* goes from never to instantly; *Steam turns to water* goes from about half a second to 30 seconds; *Steam that becomes water* sets how much of it comes back as water (0–100%, default 60%) instead of vanishing. Like screen edges, they only apply in the sandbox; challenges use the defaults.
- **Lightning:** clouds (and big, dense steam clouds) now and then throw a bolt at whatever is below: it fuses sand into a streak of glass, flashes water to steam, sets plants and oil alight and blasts a small crater in soil. Sandbox only; the **⚡ Lightning** slider in the options sets how often it strikes, from off through very rare, rare, normal, often and stormy to constant (each step doubles it).
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

## Globe Wars (prototype)

A real-time strategy prototype, opened from the options menu (mode list → **⚔ Globe Wars**). A map of 4 big globes in a square, linked in a ring (you top-left, Green top-right, Red below you, a neutral globe bottom-right), each a round 112×112 falling-sand world that runs on Sandfall's own element rules and looks like the sandbox (glow, shading, shadows):

- **Volcano** globes melt sand into lava that cools into **🔷 glass**; **gardens** grow **🌿 plants** in the rain from their clouds, and a seeder lining the bowl sprouts the bed again after it is eaten; **fungal caves** grow **🍄 fungus** on the steam from a vent. **Reapers** eat glass, plants and fungus that touch them; the eating spreads through the whole connected body, like fire through a plant bed, and each cell eaten is one resource.
- **Build your factories**: every globe starts with a small factory and spare pieces in stock (sand spouts, a cloud, or a steam vent and reapers). Placing them makes two to three times as much: more spouts over the magma, a cloud over a reaper, another vent with a reaper beside it.
- **Units are a swarm** of glowing bugs living in the globe, in their owner's colour. **Hatch** them next to the core with the army bubble (10 resources each, any mix, up to 250 per globe). **Drag** from one of your globes to a linked one on the map to send half its bugs, or use the army bubble: they fly to that link's gate and through it.
- **Gates**: every link on the map is a pair of doors in the globes' glass, each on the side facing the neighbour. **Open** one of yours (army bubble) and whatever touches it pours through and comes out of the neighbour's gate: sand, water, lava and acid fall **downhill** into globes below you on the map, steam, fire and fumes rise into globes above. Coloured dots run along a link on the map while something flows. Lava, acid and fumes eating at a core wear it down, and when it falls the globe goes to whoever poured them in. You can't close a rival's gate, but you can wall off the inside of yours. The AIs open their gates when lava or acid has pooled by them.
- **Fighting happens in the sand**: bugs coming through a gate head for the core, chewing through sand and plants fast, glass slowly and stone very slowly. Fire, lava and magma burn them, acid and fumes eat them, and water drowns them (bugs at home keep clear of their own fire and water). Bugs of different owners fight when they touch; defenders win more often and hunt invaders. Each invader that reaches the core takes a bite out of its ❤ (20); when it runs out, the globe is captured and the surviving invaders become its garrison. The core heals while no invaders are inside.
- **Strikes**: from the army bubble of a globe next to one of yours, drop 🔥 fire rain (burning oil), 🧪 acid rain, a 🦠 virus outbreak or a ☄️ meteor (a lava blob above the core) into it. A roof keeps rain out. The AIs use them too.
- **Inside a globe**: tap a globe on the map to open it full screen. Like the sandbox, the **elements bubble** holds every element and generator, the brushes (draw, line, box, filled box), the brush size and **Move & zoom**; two fingers zoom and pan with any tool. Both bubbles can be dragged anywhere.
- **Stock**: each globe has its own limited stock of every element, shown on the palette. Drawing uses it up (only into open space), **Dig** picks cells back up into it, and **Buy** adds more for your resources: stone, sand, water, oil, acid, salt, soil, seeds, plants, fire, spores, virus, reapers and generators (magma, sand spouts, rain clouds, steam vents, seeders, mycelium, shakers, spills, vats, toxin). Captured globes keep their stock.
- **Speed**: the game runs at 2× by default; the speed button in the top bar cycles 1×, 2×, 4× and ½×.
- **Upkeep** every 10 s grows with your globes and bugs and is paid from your biggest stock; if you can't pay, bugs desert.
- Two AI rivals, Red and Green, hatch bugs, wall in threatened cores, mass bugs in a staging globe, attack winnable neighbours and use strikes. In the help screen you choose how hard they go after you: **Peaceful** (the default: they never attack you), **Easy** (after 5 minutes, needing a ×2 advantage, at most every 90 s) or **Normal** (after 3 minutes, ×1.4, every 30 s, and they gang up on you once you lead). Every globe runs all the time; the top bar shows how long the simulation takes per frame ("sim … ms").

It lives in `app/src/main/assets/globes.html`, with a copy of the sandbox's element rules and renderer. In globes lava cools faster (and only lava fed by magma is kept hot by its neighbours), steam lasts longer and rains back less, and dead mushrooms rarely leave soil, so the small worlds don't clog up. A match isn't saved when you leave.

## Building

The app is a thin WebView wrapper (`app/src/main/java`) around the game, which is one self-contained file: `app/src/main/assets/index.html`.

- **Android Studio:** open the folder and run.
- **Command line:** `gradle :app:assembleRelease` (needs JDK 17+ and Gradle 8.13 or newer). Every push builds the APK on GitHub Actions. Every push to `main` also publishes a GitHub release with the APK, signed with the key in the `SIGNING_KEYSTORE_BASE64` and `SIGNING_PASSWORD` repository secrets (alias `sandfall`). Without those secrets the build is signed with a throwaway debug key and no release is published.
- **No Gradle?** `tools/build-apk.sh` builds a debug-signed APK straight from the Android SDK build tools (needs JDK 17+ and the android-36 platform).
