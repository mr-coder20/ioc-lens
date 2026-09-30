<div align="center">

<!-- Replace with your logo: docs/assets/logo.png -->
# 🔍 IOC Lens

**Copy it. Point at it. Know in a second.**
Instant, privacy-first indicator triage for SOC analysts — on desktop *and* in your pocket.

[![CI](https://github.com/mr-coder20/ioc-lens/actions/workflows/ci.yml/badge.svg)](https://github.com/mr-coder20/ioc-lens/actions)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Compose-Multiplatform-4285F4?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/compose-multiplatform/)
[![Platforms](https://img.shields.io/badge/platforms-Windows%20|%20macOS%20|%20Linux%20|%20Android%20|%20iOS-success)](#-platforms)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

<!-- HERO DEMO: record a 10-second GIF: copy IP -> tray popup -> verdict -->
<img src="docs/assets/demo.gif" alt="IOC Lens demo" width="720">

</div>

---

## Why IOC Lens?

Every analyst does this 50 times a shift: copy an IP/hash/domain → open 4 browser tabs → paste → compare → decide.

**IOC Lens collapses that into one keystroke.**

| Today | With IOC Lens |
|---|---|
| Open VirusTotal, AbuseIPDB, OTX, URLhaus separately | One query, all sources in parallel |
| Squint at 4 different scoring systems | One transparent, explainable verdict |
| Paste internal IPs into third-party sites (oops) | **Privacy Guard** blocks private/internal indicators |
| Screenshot on phone → retype hash by hand | Camera/OCR extracts indicators for you |
| Heavy TIP platform for a quick lookup | Tiny native app, starts instantly |

## ✨ Features

- ⚡ **Clipboard & hotkey lookup**: global shortcut, tray popup, result in seconds
- 📷 **Camera / screenshot OCR**: extract IOCs from screens, printouts, slides
- 🧠 **Auto-detection**: IPv4/IPv6, domains, URLs, MD5/SHA1/SHA256, emails, CVEs
- 🧼 **Refang / defang aware**: `hxxps://evil[.]com` just works
- 🛡️ **Privacy Guard**: RFC1918, loopback, link-local and your custom internal ranges/domains are *never* sent out
- 📊 **Explainable aggregate score**: see exactly which source contributed what and why
- 🔌 **Pluggable providers**: VirusTotal, AbuseIPDB, AlienVault OTX, URLhaus (more welcome)
- 🗄️ **Local-first cache & history**: rate-limit friendly for free API tiers, works offline on past lookups
- 📤 **Export**: copy as Markdown / JSON for tickets and incident notes
- 🔐 **Your keys stay yours**: stored in OS Keychain / Keystore, no backend, **no telemetry**

## 🖥️ Platforms

| Platform | Status | Form factor |
|---|---|---|
| Windows / macOS / Linux | 🚧 Planned (v0.1) | Tray app + global hotkey |
| Android | 🚧 Planned (v0.2) | App + Share target + camera |
| iOS | 🚧 Planned (v0.3) | App + Share extension + camera |

> Built with **Kotlin Multiplatform + Compose Multiplatform**: one shared core, native feel everywhere.

## 🚀 Quick start

```bash
git clone https://github.com/mr-coder20/ioc-lens.git
cd ioc-lens
./gradlew :desktopApp:run          # desktop
./gradlew :androidApp:installDebug # android
```

Add your free API keys in **Settings → Providers**. No key? URLhaus and OTX work with limited/no key.

## 🧩 How scoring works

```
score = Σ (provider_weight × normalized_signal × confidence)
```

Each provider maps its raw response to a normalized signal `0..1` plus a confidence.
The UI always shows the breakdown, never a black-box number. See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md).

## 🔒 Privacy & security

- No backend, no accounts, no analytics.
- Only the indicator itself is sent, only to providers you enabled.
- Internal indicators are blocked by default (Privacy Guard).
- Found a vulnerability? See [SECURITY.md](SECURITY.md).

## 🗺️ Roadmap

See [docs/ROADMAP.md](docs/ROADMAP.md). Highlights: MISP / OpenCTI push, Wazuh/Splunk/Elastic deep links, STIX 2.1 export, bulk mode, custom provider plugins.

## 🤝 Contributing

New providers, parsers, translations, and platform polish are all welcome.
Look for [`good first issue`](https://github.com/mr-coder20/ioc-lens/labels/good%20first%20issue) and read [CONTRIBUTING.md](CONTRIBUTING.md).

## 📄 License

[MIT](LICENSE). Threat-intel data remains subject to each provider's terms of service.

<div align="center">

⭐ **If IOC Lens saves you time, star the repo. It helps other analysts find it.**

</div>
