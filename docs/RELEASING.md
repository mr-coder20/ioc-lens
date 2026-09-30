# Releasing

The READMEs send users to the **Releases page**, where they pick the file for their OS.
The **Release** workflow (`.github/workflows/release.yml`) builds each desktop platform and uploads files with stable names:

| Asset | Built on |
|---|---|
| `IOC-Lens-windows-x64.msi` | windows-latest |
| `IOC-Lens-macos-arm64.dmg` | macos-latest |
| `IOC-Lens-linux-amd64.deb` | ubuntu-latest |
| `SHA256SUMS.txt` | generated for all of the above |

## Cut a release
```bash
# 1. update CHANGELOG.md, bump version in build files
git commit -am "chore: release v0.1.0"
git tag v0.1.0
git push origin main --tags
```
The workflow publishes a GitHub Release automatically. Tags containing a dash (`v0.1.0-rc1`) are marked pre-release.

## Code signing (recommended before public launch)
- **Windows**: Authenticode certificate (or SignPath / Azure Trusted Signing) to remove SmartScreen warnings.
- **macOS**: Apple Developer ID + notarization.

## Later channels
winget, Homebrew cask, AUR, Flatpak.

## Checklist
- [ ] Assets present for Windows, macOS, Linux
- [ ] `SHA256SUMS.txt` present
- [ ] Release notes written
