![Screenshot](https://github.com/xyzbtw/ShulkerViewer/blob/main/gh%20image.png?raw=true)

# ShulkerViewer

A [RusherHack](https://rusherhack.org) plugin that shows the contents of shulker boxes in a panel beside any container screen. Click a panel to pick up that shulker.

Supported Minecraft versions: **1.21.4**, **1.21.11**, **26.2**.

## Settings

| Setting | Description |
| --- | --- |
| DynamicColor | Tint each panel with the shulker's dye color |
| DefaultColor | Panel color when DynamicColor is off (or the shulker is undyed) |
| Compact | Merge identical items and hide empty slots |
| Scale | Panel size, 0.5 to 1.5 |

Scroll while a container is open to move the panels if they don't fit on screen.

## Installing

Build the jar for your version (see below) and put it in `.minecraft/rusherhack/plugins/`. Use the jar that matches your Minecraft version.

## Building

Requires JDK 25 installed. Gradle (9.8) is pinned to run on Java 25 through `gradle/gradle-daemon-jvm.properties`, so your shell's default `java` can be older.

```
./gradlew buildAll          # every version, jars end up in build/libs
./gradlew build-1.21.11     # one version
```

Jars are named `shulker-viewer-<plugin version>+mc<minecraft version>.jar`.

## Project layout

One Gradle project per Minecraft version, with the shared code in `common/`:

```
common/src/main/              shared module code and rusherhack-plugin.json
versions/<mc>/
  gradle.properties           minecraft_version, java_version, fabric_loader_version, obfuscated
  build.gradle                picks the Loom plugin (remapping for 1.21.x, non-remapping for 26.x)
  src/main/java/org/example/  Gfx.java and Compat.java, the only version specific code
gradle/version.gradle         build logic shared by every version project
```

`Gfx` wraps the gui graphics class (`GuiGraphics` before 26, `GuiGraphicsExtractor` after) and `Compat` holds the other calls that differ: current screen, container contents, GUI-scaled mouse position and dye color.

## Testing in a dev client

Copy `rusherhack-loader.jar` to `lib/` (gitignored), then:

```
./gradlew run-1.21.4
./gradlew run-1.21.11
./gradlew run-26.2
```

This builds the plugin, copies it into `versions/<mc>/run/rusherhack/plugins/` and starts a RusherHack dev client. Each version has its own `run/` folder.

- The first launch of a fresh `run/` folder makes RusherHack move the plugin into `rusherhack/legacy/`, so launch twice.
- The 26.2 client can report a "client shutdown watchdog" crash when it exits. It is just a slow shutdown, not a plugin problem.

## gradleTui

The root tasks (`build-<mc>`, `run-<mc>`, `buildAll`, `cleanAll`) are in the `Plugin` group, so they show up together in gradleTui and `./gradlew tasks`.

## Adding a version

1. Add `versions/<mc>/` with a `gradle.properties` and a `build.gradle` (copy another version's). Set `obfuscated = false` for 26.x and newer.
2. Add it to the `include` line in `settings.gradle`.
3. Add `Gfx.java` and `Compat.java` for any API that changed, and build until it compiles.
4. Check that a RusherHack API snapshot exists for it at https://maven.rusherhack.org/snapshots.
