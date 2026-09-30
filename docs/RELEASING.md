# Releasing

The README sends users to the **Releases page**, where they pick the file for their system.
The **Release** workflow (`.github/workflows/release.yml`) builds each platform and uploads files with stable names:

| Asset | Built on |
|---|---|
| `IOC-Lens-windows-x64.msi` | windows-latest |
| `IOC-Lens-macos-arm64.dmg` | macos-latest |
| `IOC-Lens-linux-amd64.deb` | ubuntu-latest |
| `IOC-Lens-android.apk` | ubuntu-latest |
| `SHA256SUMS.txt` | generated for all of the above |

**iOS** cannot be shipped as a downloadable file. Publish it through **TestFlight / App Store** and paste the link into the release notes.

## Cut a release
```bash
# 1. update CHANGELOG.md, bump version in build files
git commit -am "chore: release v0.1.0"
git tag v0.1.0
git push origin main --tags
```
Tags containing a dash (`v0.1.0-rc1`) are marked pre-release.

## Repository secrets (Settings → Secrets and variables → Actions)
| Secret | Purpose |
|---|---|
| `ANDROID_KEYSTORE_BASE64` | `base64 -w0 release.jks` of your keystore |
| `ANDROID_KEYSTORE_PASSWORD`, `ANDROID_KEY_ALIAS`, `ANDROID_KEY_PASSWORD` | signing config (an unsigned APK cannot be installed) |

Read them in `androidApp/build.gradle.kts` via `System.getenv(...)` inside `signingConfigs { create("release") { ... } }`.

## Code signing (recommended before public launch)
- **Windows**: Authenticode certificate (or SignPath / Azure Trusted Signing).
- **macOS**: Apple Developer ID + notarization.
- **iOS**: Apple Developer account, TestFlight, then App Store.

## Later channels
winget, Homebrew cask, AUR, Flatpak, F-Droid, Google Play.

## Checklist
- [ ] Assets present for Windows, macOS, Linux, Android
- [ ] `SHA256SUMS.txt` present
- [ ] iOS link added to release notes
