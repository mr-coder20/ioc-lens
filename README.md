<div align="center">

**🌐 Language:** **English** · [فارسی](README.fa.md) · [Русский](README.ru.md)

# 🔍 IOC Lens

**Copy it. Point at it. Know in a second.**
Instant, privacy-first indicator triage for SOC analysts on **Windows, macOS, Linux, Android and iOS**.

[![CI](https://github.com/mr-coder20/ioc-lens/actions/workflows/ci.yml/badge.svg)](https://github.com/mr-coder20/ioc-lens/actions)
[![Release](https://img.shields.io/github/v/release/mr-coder20/ioc-lens?include_prereleases&label=release)](https://github.com/mr-coder20/ioc-lens/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/mr-coder20/ioc-lens/total)](https://github.com/mr-coder20/ioc-lens/releases)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-4285F4?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/compose-multiplatform/)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

<!-- HERO DEMO: record a 10-second GIF: copy IP -> popup -> verdict. Save as docs/assets/demo.gif -->
<!-- <img src="docs/assets/demo.gif" alt="IOC Lens demo" width="720"> -->

</div>

> **Project status: pre-alpha.** This README describes the target feature set and the install flow. Items are tagged with the release they ship in (see [Roadmap](docs/ROADMAP.md)). Download links go live as soon as the first release is published.

## Table of contents
- [Why IOC Lens](#why-ioc-lens)
- [Features](#features)
- [Download](#download)
- [Installation guide](#installation-guide) — [Windows](#windows) · [macOS](#macos) · [Linux](#linux) · [Android](#android) · [iOS](#ios)
- [First-time setup (API keys)](#first-time-setup-api-keys)
- [How to use](#how-to-use)
- [Understanding the score](#understanding-the-score)
- [Privacy Guard](#privacy-guard)
- [Privacy & security](#privacy--security)
- [Build from source](#build-from-source)
- [Troubleshooting & FAQ](#troubleshooting--faq)
- [Roadmap](#roadmap) · [Contributing](#contributing) · [License](#license)

---

## Why IOC Lens

Every analyst does this dozens of times per shift: copy an IP / hash / domain → open four browser tabs → paste → compare → decide.

**IOC Lens collapses that into one keystroke (desktop) or one scan/share (mobile).**

| Today | With IOC Lens |
|---|---|
| Open VirusTotal, AbuseIPDB, OTX, URLhaus separately | One query, all sources in parallel |
| Squint at four different scoring systems | One transparent, explainable verdict |
| Paste internal IPs into third-party sites (oops) | **Privacy Guard** blocks internal indicators before they leave your device |
| Screenshot on phone → retype the hash by hand | Camera / image OCR extracts indicators for you |
| Heavy TIP platform for a quick lookup | Tiny native app, instant start |

## Features

| Feature | Win/Mac/Linux | Android | iOS | Target |
|---|:-:|:-:|:-:|:-:|
| **Clipboard lookup** (paste / auto-detect) | ✅ | ✅ | ✅ | v0.1 |
| **Global hotkey + tray popup** | ✅ | – | – | v0.1 |
| **Auto-detection**: IPv4, IPv6, domain, URL, MD5, SHA-1, SHA-256, email, CVE | ✅ | ✅ | ✅ | v0.1 |
| **Defang / refang aware**: `hxxps://evil[.]com`, `1[.]2[.]3[.]4` | ✅ | ✅ | ✅ | v0.1 |
| **Bulk extract** from pasted text (finds every IOC in a blob, log line or email) | ✅ | ✅ | ✅ | v0.1 |
| **Parallel multi-source lookup**: VirusTotal, AbuseIPDB, AlienVault OTX, URLhaus | ✅ | ✅ | ✅ | v0.1 |
| **Explainable aggregate score** with per-source breakdown | ✅ | ✅ | ✅ | v0.1 |
| **Privacy Guard** (RFC 1918 / internal domains / custom rules never leave your device) | ✅ | ✅ | ✅ | v0.1 |
| **Local cache + history** (rate-limit friendly, searchable, works offline for past lookups) | ✅ | ✅ | ✅ | v0.1 |
| **Copy as Markdown / JSON** for tickets and incident notes | ✅ | ✅ | ✅ | v0.1 |
| **Image / screenshot OCR** (drag an image onto the window) | ✅ | ✅ | ✅ | v0.2 |
| **Camera OCR** (point at a screen, printout or slide) | – | ✅ | ✅ | v0.2 / v0.3 |
| **Share target** ("Share → IOC Lens" from any app) | – | ✅ | ✅ | v0.2 / v0.3 |
| **Quick Settings tile** (Android) / **Shortcuts action** (iOS) | – | ✅ | ✅ | v0.2 / v0.3 |
| **Secure key storage** (Windows Credential Manager, macOS Keychain, libsecret, Android Keystore, iOS Keychain) | ✅ | ✅ | ✅ | v0.1 |
| **Dark / light theme, EN / FA / RU UI, RTL support** | ✅ | ✅ | ✅ | v0.2 |
| **Push to MISP / OpenCTI**, SIEM deep links (Wazuh, Splunk, Elastic, Sentinel) | ✅ | ✅ | ✅ | v0.4 |
| **STIX 2.1 export, allow/deny lists, custom provider plugins** | ✅ | ✅ | ✅ | v0.5 |

**No backend. No account. No telemetry.** Your API keys and lookups never touch a server we run, because we do not run one.

## Download

> Direct links use stable file names, so they will keep working for every future release.

| Platform | Download | Notes |
|---|---|---|
| 🪟 **Windows 10/11 (x64)** | [`IOC-Lens-windows-x64.msi`](https://github.com/mr-coder20/ioc-lens/releases/latest/download/IOC-Lens-windows-x64.msi) | MSI installer |
| 🍎 **macOS 12+ (Apple Silicon)** | [`IOC-Lens-macos-arm64.dmg`](https://github.com/mr-coder20/ioc-lens/releases/latest/download/IOC-Lens-macos-arm64.dmg) | Intel Macs: [build from source](#build-from-source) for now |
| 🐧 **Linux (Debian/Ubuntu, x64)** | [`IOC-Lens-linux-amd64.deb`](https://github.com/mr-coder20/ioc-lens/releases/latest/download/IOC-Lens-linux-amd64.deb) | Other distros: build from source (RPM / AppImage planned) |
| 🤖 **Android 8.0+** | [`IOC-Lens-android.apk`](https://github.com/mr-coder20/ioc-lens/releases/latest/download/IOC-Lens-android.apk) | Google Play / F-Droid planned |
| 📱 **iOS 16+** | TestFlight / App Store: *coming soon* | Or [build with Xcode](#ios) |

📦 All releases: <https://github.com/mr-coder20/ioc-lens/releases>
🔐 Checksums: every release includes `SHA256SUMS.txt`.

**Verify your download** (recommended for a security tool):

```powershell
# Windows (PowerShell)
Get-FileHash .\IOC-Lens-windows-x64.msi -Algorithm SHA256
```
```bash
# macOS
shasum -a 256 IOC-Lens-macos-arm64.dmg
# Linux
sha256sum IOC-Lens-linux-amd64.deb
```
Compare the output with the matching line in `SHA256SUMS.txt`.

**Package managers (planned):** `winget`, Homebrew Cask, AUR, Flatpak, F-Droid.

## Installation guide

### Windows
1. Download `IOC-Lens-windows-x64.msi` from [Download](#download).
2. Double-click it and follow the wizard.
3. Early builds are not code-signed, so **SmartScreen** may warn you: click **More info → Run anyway**. Verify the checksum first.
4. Launch **IOC Lens** from the Start menu. It lives in the **system tray** (bottom-right; check the `^` overflow arrow).
5. Default hotkey: **Ctrl + Shift + L**. Change it in **Settings → Hotkeys**.
6. *(Optional)* Enable **Start with Windows** in Settings → General.

Uninstall: *Settings → Apps → Installed apps → IOC Lens → Uninstall*.

### macOS
1. Download `IOC-Lens-macos-arm64.dmg`.
2. Open the DMG and drag **IOC Lens** into **Applications**.
3. First launch: if macOS says the app cannot be verified, **right-click the app → Open → Open**. (Or: *System Settings → Privacy & Security → Open Anyway*.)
4. Grant permissions when asked:
   - **Accessibility / Input Monitoring**: required for the global hotkey.
   - **Screen Recording**: only if you use screen-region OCR.
   - **Camera**: only for camera scanning.
5. The app lives in the **menu bar**. Default hotkey: **⌘ ⇧ L**.
6. *(Optional)* Enable **Launch at login** in Settings → General.

Uninstall: drag the app to the Trash. Stored keys can be removed in **Keychain Access** (search "IOC Lens").

### Linux
**Debian / Ubuntu / Mint / Kali:**
```bash
sudo apt install ./IOC-Lens-linux-amd64.deb
ioc-lens            # or find "IOC Lens" in your app launcher
```
**Other distributions:** build from source (see [Build from source](#build-from-source)); RPM and AppImage are planned.

Notes:
- **Tray icon on GNOME**: install the *AppIndicator and KStatusNotifierItem Support* extension, otherwise the tray icon is hidden.
- **Wayland**: most compositors do not allow apps to register global hotkeys. Bind a custom system shortcut to `ioc-lens --lookup-clipboard` *(planned CLI flag)*, or use the tray icon. X11 sessions support the built-in hotkey.
- **Secret storage** uses `libsecret` (GNOME Keyring / KWallet). Make sure one is running, or keys fall back to an encrypted local file protected by a passphrase.

Uninstall: `sudo apt remove ioc-lens`.

### Android
1. On your phone, download `IOC-Lens-android.apk` (or transfer it from your PC).
2. Verify the checksum if you can (e.g. with a file-hash app).
3. Tap the file. When prompted, allow **Install unknown apps** for the browser / file manager you used (*Settings → Apps → Special access → Install unknown apps*).
4. Open **IOC Lens** and complete [first-time setup](#first-time-setup-api-keys).
5. Three ways to look things up:
   - **Share**: in any app select text → **Share → IOC Lens**.
   - **Scan**: tap the **camera** button and point at a screen or printout.
   - **Quick Settings tile**: add the *IOC Lens* tile; one tap checks your clipboard.

> Android 10+ stops background apps from reading the clipboard. That is why IOC Lens uses Share, the tile and in-app paste instead of silently watching your clipboard. This is a privacy feature, not a bug.

### iOS
**Option A: TestFlight / App Store** *(coming soon)*: open the link from [Download](#download) and install.

**Option B: build it yourself** (free Apple ID works; the app must be re-signed every 7 days, a paid developer account removes this limit):
1. Install **Xcode** on a Mac and clone the repo (see [Build from source](#build-from-source)).
2. Open `iosApp/iosApp.xcodeproj`.
3. Select the **iosApp** target → **Signing & Capabilities** → choose your *Team* and set a unique *Bundle Identifier*.
4. Connect your iPhone, enable **Developer Mode** (*Settings → Privacy & Security → Developer Mode*), select the device and press **Run ▶**.
5. On the iPhone, trust the developer: *Settings → General → VPN & Device Management*.
6. Use it via **Share → IOC Lens**, the in-app **camera scan**, or the **Shortcuts** action.

> iOS shows a "Allow Paste" prompt when an app reads the clipboard. This is system behavior.

## First-time setup (API keys)

IOC Lens queries providers **with your own free API keys**. Open **Settings → Providers**, paste each key, press **Test**.

| Provider | Covers | Get a free key | Free-tier note* |
|---|---|---|---|
| **VirusTotal** | files (hash), URLs, domains, IPs | virustotal.com → sign up → profile menu → **API key** | public API is rate limited (about 4 requests/min, 500/day) |
| **AbuseIPDB** | IPs | abuseipdb.com → account → **API** → Create key | about 1,000 checks/day |
| **AlienVault OTX** | IPs, domains, URLs, hashes, CVEs | otx.alienvault.com → sign up → **Settings → OTX Key** | generous free use |
| **URLhaus (abuse.ch)** | URLs, domains, malware hashes | auth.abuse.ch → sign in → get **Auth-Key** | free, key required |

\* Limits change. Check each provider's terms. IOC Lens tracks rate limits, caches results and will tell you when a source is throttled.

You need **at least one** provider. Enabling more gives a more reliable score.

Keys are stored only in your OS secure storage and are never written to logs or exports.

## How to use

### Desktop (Windows / macOS / Linux)
1. Select or copy any indicator anywhere (browser, SIEM, email, terminal).
2. Press the **global hotkey** (`Ctrl+Shift+L` / `⌘⇧L`).
3. A compact popup appears near the tray with:
   - the **detected type** (IP, domain, SHA-256 …) and the **refanged** form,
   - the **aggregate score** and verdict,
   - a **per-source breakdown** (click a source for evidence and a link to the full report),
   - **Copy as Markdown / JSON** buttons.
4. Paste a whole log line or email body: IOC Lens extracts **every indicator** and shows a list; click one to drill down.
5. Drag an **image or screenshot** onto the window to OCR it *(v0.2)*.
6. `Esc` closes the popup. **History** keeps past lookups (search, filter by verdict, clear anytime).

### Android
- **Share** selected text to IOC Lens, **scan** with the camera, or tap the **Quick Settings tile**.
- Tap a result to see evidence; long-press to copy the verdict as Markdown.

### iOS
- **Share sheet** → IOC Lens, **camera scan** in the app, or add the **Shortcuts** action to automate lookups.

### Example output (Markdown export)
```markdown
### 198.51.100.23 (IPv4): SUSPICIOUS (score 63/100)
| Source | Signal | Confidence | Evidence |
|---|---|---|---|
| AbuseIPDB | 0.80 | 0.9 | 214 reports, abuse confidence 82% |
| VirusTotal | 0.45 | 0.8 | 6/90 engines flagged |
| AlienVault OTX | 0.40 | 0.6 | In 3 pulses |
| URLhaus | n/a | – | No data |
_Checked with IOC Lens v0.1.0 · 2026-01-01 12:00 UTC_
```

## Understanding the score

For every source that answered, IOC Lens normalizes its result to a **signal** (`0` = benign … `1` = malicious) and a **confidence** (`0..1`):

```
score = 100 × Σ(weight × signal × confidence) / Σ(weight × confidence)
```

| Score | Verdict | Meaning |
|---|---|---|
| 0–19 | 🟢 Clean | No meaningful detections |
| 20–44 | 🟡 Low risk | Weak or old signals; review in context |
| 45–74 | 🟠 Suspicious | Notable detections; investigate |
| 75–100 | 🔴 Malicious | Strong, corroborated detections |
| – | ⚪ No data | Not seen by any enabled source (**not** the same as safe) |

**Escalation rule:** one strong signal from a high-confidence source can never be averaged away; the verdict is raised to at least *Suspicious*.
Weights are configurable in **Settings → Scoring**. The score is a triage aid, **not** a verdict. Always apply analyst judgment.

## Privacy Guard

Before anything is sent to a provider, IOC Lens checks the indicator against your rules. **Blocked by default:**

- Private / internal IPv4: `10.0.0.0/8`, `172.16.0.0/12`, `192.168.0.0/16`, `100.64.0.0/10`, `127.0.0.0/8`, `169.254.0.0/16`
- IPv6: `::1`, `fc00::/7`, `fe80::/10`
- Internal names: single-label hosts, `.local`, `.lan`, `.internal`, `.corp`, `.home.arpa`
- **Your own rules**: add internal CIDR ranges, domains and email domains in **Settings → Privacy Guard**
- Optional: strip query string / fragment from URLs before lookup

A blocked indicator is shown in the app with the reason, and **no network request is made for it**. Hash lookups send only the hash, **never a file**.

## Privacy & security

- No backend, no accounts, no analytics, no auto-update pings *(update checks are opt-in)*.
- Only the indicator itself is sent, only to providers you enabled, over HTTPS.
- Keys live in the OS secure store. Exports never include keys.
- History is stored locally; clear it anytime; optional "don't save history" mode.
- Found a vulnerability? See [SECURITY.md](SECURITY.md).

## Build from source

Requirements: **JDK 17+**, **Git**. For Android: Android Studio (SDK 34+). For iOS: macOS + Xcode 15+.

```bash
git clone https://github.com/mr-coder20/ioc-lens.git
cd ioc-lens

./gradlew :desktopApp:run                  # run desktop app
./gradlew :desktopApp:packageDeb           # Linux .deb   (run on Linux)
./gradlew :desktopApp:packageMsi           # Windows .msi (run on Windows)
./gradlew :desktopApp:packageDmg           # macOS .dmg   (run on macOS)
./gradlew :androidApp:installDebug         # install on connected Android device
./gradlew check                            # tests
```
On Windows use `gradlew.bat` instead of `./gradlew`. For iOS open `iosApp/iosApp.xcodeproj` in Xcode (see [iOS](#ios)).

Release process for maintainers: [docs/RELEASING.md](docs/RELEASING.md).

## Troubleshooting & FAQ

**The hotkey does nothing.** Another app may own that shortcut: change it in *Settings → Hotkeys*. On macOS grant Accessibility/Input Monitoring. On Wayland use the tray icon or a system shortcut.

**No tray icon on Linux (GNOME).** Install the *AppIndicator* extension.

**"Rate limited" for a provider.** Free tiers are small. Results are cached, so repeat lookups cost nothing; wait a minute or add another provider.

**Why "No data" instead of "Clean"?** Not being in a database does not make an indicator safe. New infrastructure is often unknown.

**Android does not read my clipboard automatically.** Android 10+ forbids it for background apps. Use Share, the tile, or the in-app paste button.

**Can I use it offline?** Past lookups in history work offline; new lookups need internet.

**Is anything sent to you?** No. There is no server. See [Privacy & security](#privacy--security).

**Can I add my own provider?** Yes, see the `IntelProvider` interface in [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md). A plugin system is planned for v0.5.

## Roadmap
Full details in [docs/ROADMAP.md](docs/ROADMAP.md).

## Contributing
New providers, parsers, translations and platform polish are all welcome. Start with [`good first issue`](https://github.com/mr-coder20/ioc-lens/labels/good%20first%20issue) and read [CONTRIBUTING.md](CONTRIBUTING.md). Translations live in `shared/core/src/commonMain/resources/i18n/`.

## License
[MIT](LICENSE). Threat-intelligence data remains subject to each provider's terms of service.

<div align="center">

⭐ **If IOC Lens saves you time, star the repo. It helps other analysts find it.**

</div>
