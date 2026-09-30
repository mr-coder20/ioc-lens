# Architecture

```
ioc-lens/
├── shared/
│   ├── core/        # models, IOC parser/classifier, defang/refang, Privacy Guard
│   ├── providers/   # VirusTotal, AbuseIPDB, OTX, URLhaus (Ktor)
│   ├── scoring/     # normalization + explainable aggregation
│   └── storage/     # SQLDelight cache/history + SecureStore abstraction
├── composeApp/      # shared Compose UI (result card, history, settings)
├── desktopApp/      # tray, global hotkey, clipboard watcher (JVM)
├── androidApp/      # share target, CameraX + ML Kit OCR
└── iosApp/          # share extension, VisionKit OCR (Swift glue)
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
`SecureStore`, `ClipboardSource`, `OcrEngine`, `HotkeyRegistrar`.

## Design principles
1. Privacy first: block before sending, never log indicators remotely.
2. Explainable: every point in the score is traceable.
3. Degrade gracefully: one provider down ≠ no answer.
4. Small: fast cold start, tiny binaries, minimal dependencies.
