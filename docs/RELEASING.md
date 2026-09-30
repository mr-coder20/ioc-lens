# Releasing

Download links in the READMEs point to
`https://github.com/mr-coder20/ioc-lens/releases/latest/download/<stable-name>`.
The **Release** workflow (`.github/workflows/release.yml`) builds every platform and uploads files with these stable names:

| Asset | Built on |
|---|---|
| `IOC-Lens-windows-x64.msi` | windows-latest |
| `IOC-Lens-macos-arm64.dmg` | macos-latest (Apple Silicon) |
| `IOC-Lens-linux-amd64.deb` | ubuntu-latest |
| `IOC-Lens-android.apk` | ubuntu-latest |
| `SHA256SUMS.txt` | generated for all of the above |

## Cut a release
```bash
# 1. update CHANGELOG.md, bump version in build files
git commit -am "chore: release v0.1.0"
git tag v0.1.0
git push origin main --tags
```
The workflow publishes a GitHub Release automatically. Tags containing a dash (`v0.1.0-rc1`) are marked pre-release.

## Repository secrets (Settings → Secrets and variables → Actions)
| Secret | Purpose |
|---|---|
| `ANDROID_KEYSTORE_BASE64` | `base64 -w0 release.jks` of your upload keystore |
| `ANDROID_KEYSTORE_PASSWORD`, `ANDROID_KEY_ALIAS`, `ANDROID_KEY_PASSWORD` | signing config (an unsigned APK cannot be installed) |

Read them in `androidApp/build.gradle.kts` via `System.getenv(...)` inside `signingConfigs { create("release") { ... } }`.

## Code signing (recommended before public launch)
- **Windows**: Authenticode certificate (or Azure Trusted Signing / SignPath for OSS) to remove SmartScreen warnings.
- **macOS**: Apple Developer ID + notarization (`notarize` in Compose Desktop `nativeDistributions.macOS`).
- **iOS**: distribute through TestFlight / App Store; build and archive in Xcode or with `xcodebuild` + fastlane.

## Later channels
winget manifest, Homebrew cask, AUR, Flatpak (Flathub), F-Droid, Google Play.

## Checklist
- [ ] README download table matches asset names above
- [ ] `SHA256SUMS.txt` present on the release
- [ ] Release notes mention provider/API changes
