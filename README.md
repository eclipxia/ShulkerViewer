![Screenshot](https://github.com/xyzbtw/ShulkerViewer/blob/main/gh%20image.png?raw=true)


## Building

One project per supported Minecraft version (1.21.4, 1.21.11, 26.2). Shared code is in `common/`, anything that differs between versions is in `versions/<mc>/src/main/java/org/example/` (`Gfx`, `Compat`).

```
./gradlew buildAll              # all versions, jars end up in build/libs
./gradlew :versions:1.21.11:build
```

## Testing in a dev client

Copy `rusherhack-loader.jar` to `lib/` (gitignored), then:

```
./gradlew :versions:26.2:runPlugin
```

The first launch of a fresh `run/` folder makes RusherHack move the plugin into `rusherhack/legacy/`, so launch twice.

## Adding a version

1. Add `versions/<mc>/` with a `gradle.properties` (`minecraft_version`, `java_version`, `fabric_loader_version`, `obfuscated`) and a `build.gradle` (copy another version's).
2. Add it to `settings.gradle`.
3. Add `Gfx.java` / `Compat.java` for any API that changed and build until it compiles.
