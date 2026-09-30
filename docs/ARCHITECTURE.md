# Architecture

```
ioc-lens/
├── shared/
│   ├── core/        # models, IOC parser/classifier, defang/refang, Privacy Guard
│   ├── providers/   # VirusTotal, AbuseIPDB, OTX, URLhaus (Ktor)
│   ├── scoring/     # normalization + explainable aggregation
│   └── storage/     # SQLDelight cache/history + SecureStore abstraction
├── composeApp/      # shared Compose UI (result card, history, settings)
├── desktopApp/      # tray, global hotkey, clipboard (JVM: Windows, macOS, Linux)
├── androidApp/      # share target, in-app scan
└── iosApp/          # share extension, in-app scan (Swift glue)
```

## Pipeline
`input → extract → classify → Privacy Guard → cache → providers (parallel) → normalize → score → UI`

## Provider contract
```kotlin
interface IntelProvider {
    val id: String
    val supports: Set<IocType>
    suspend fun lookup(ioc: Ioc): ProviderResult   // never throws; returns Error state
}
data class ProviderResult(
    val signal: Double,       // 0.0 benign .. 1.0 malicious
    val confidence: Double,   // 0.0 .. 1.0
    val evidence: List<Evidence>,
    val rateLimit: RateLimitInfo?
)
```

## Platform abstractions (expect/actual)
`SecureStore`, `ClipboardSource`, `HotkeyRegistrar`, `OcrEngine`.

## Design principles
1. Privacy first: block before sending, never log indicators remotely.
2. Explainable: every point in the score is traceable.
3. Degrade gracefully: one provider down ≠ no answer.
4. Small: fast cold start, tiny binaries, minimal dependencies.

## Scoring
For each provider that answered: `signal` (0 benign .. 1 malicious) and `confidence` (0..1).

```
score = 100 × Σ(weight × signal × confidence) / Σ(weight × confidence)
```
Bands: 0-19 Clean, 20-44 Low risk, 45-74 Suspicious, 75-100 Malicious, no answers = No data.
**Escalation rule:** a strong signal from a high-confidence provider raises the verdict to at least Suspicious, so it cannot be averaged away.

## Privacy Guard
Runs before cache and network. Default blocklist: RFC 1918, CGNAT, loopback, link-local, IPv6 ULA/link-local, single-label and internal TLDs (`.local .lan .internal .corp .home.arpa`), plus user-defined CIDRs/domains. A blocked IOC never reaches a provider; the UI shows the reason.

## i18n
UI strings live in `shared/core/src/commonMain/resources/i18n/{en,fa,ru}.json`; Compose layout direction follows the locale (RTL for Persian).
