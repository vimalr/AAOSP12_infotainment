# AAOSP 12 Automotive UI — Horsewaves prototype

Native Java Android application prototype for Android 12 / AAOSP 12. It provides a touch-friendly landscape UI with Home, Navigation, Media, Apps, Phone, Vehicle Information, Climate, EV Smart Cluster, and Settings screens.

## Important prototype limits
- Vehicle speed, RPM, fuel, battery, range and temperature are simulated demo values.
- Navigation is a visual route preview only; no maps provider or GPS route engine is configured.
- Media controls are UI-only; no playback service is configured.
- Phone UI does not place calls; Bluetooth/HFP integration is not configured.
- Climate controls do not command HVAC; VHAL properties and vehicle permissions must be integrated for a real vehicle.
- This is a native Android app prototype, not an Android Auto phone-projection app and not a production certified instrument cluster.

## Build APK on Ubuntu
Install JDK 17 and Android SDK platform 32 + build-tools (or use Android Studio), then from this directory:

```bash
gradle assembleDebug
```

APK output:
`app/build/outputs/apk/debug/app-debug.apk`

If Gradle is not installed, Android Studio can open this project and sync/build it. The GitHub Actions workflow `.github/workflows/build-apk.yml` builds the debug APK and uploads it as an artifact on each push or manual workflow dispatch.

## Install on AAOSP 12 device
After building and connecting ADB:

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n in.horsewaves.automotive/.MainActivity
```

## GitHub Actions build
1. Create a GitHub repository.
2. Extract and push this project to the repository root.
3. Open **Actions → Build AAOSP 12 Automotive APK → Run workflow** (or push to `main`).
4. Download the `AAOSP12-Automotive-debug-apk` artifact from the workflow run.

## PNG concept assets
The `design-assets/` directory contains nine UI reference PNGs. These are design references and are not embedded as screenshots in the app; the application screens are drawn with native Android widgets.

## Alternative visual themes (PNG references)

The `design-assets/themes/` directory contains nine theme concept PNGs:

1. Modern Dark Blue
2. Modern Light White
3. Minimal Gray
4. Sporty Red
5. Nature Green
6. Premium Purple
7. Classic Brown/Gold
8. Ocean Teal
9. Sunset Orange

These PNGs are design references, not runtime-selectable themes. The current Java UI must be updated to implement theme switching and recreate the chosen design with native Android views.
# AAOSP12_infotainment
# AAOSP12_infotainment
# AAOSP12_infotainment
