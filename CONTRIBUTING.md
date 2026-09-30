# Contributing to IOC Lens

Thanks for helping! This project is small on purpose: **fast, private, single-purpose**. Contributions that keep it that way are the easiest to merge.

## Ways to contribute
- 🔌 **Add a provider**: implement `IntelProvider` (see `docs/ARCHITECTURE.md`)
- 🧪 **Improve parsing**: edge cases in defang/refang, IPv6, punycode, URLs
- 🌍 **Translate** the UI
- 🐛 **Fix bugs** / polish a platform
- 📖 **Docs**: examples, screenshots, tutorials

## Workflow
1. Open an issue first for anything larger than a small fix.
2. Fork → branch (`feat/...`, `fix/...`) → PR against `main`.
3. Keep PRs focused; add tests for parsing/scoring logic.
4. Use [Conventional Commits](https://www.conventionalcommits.org) (`feat:`, `fix:`, `docs:` ...).

## Ground rules
- **No telemetry, no analytics, no hidden network calls.** PRs adding any will be closed.
- Secrets must go through the platform secure store abstraction.
- A provider must never receive an indicator that Privacy Guard blocks.
- Shared logic goes in `shared/`; platform code only for platform APIs.

## Dev setup
JDK 17+, Android Studio (or IntelliJ) with the Kotlin Multiplatform plugin, Xcode for iOS.
Run `./gradlew check` before pushing.
