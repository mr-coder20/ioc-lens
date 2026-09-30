package io.github.mrcoder20.ioclens.domain.model

enum class VerdictLevel {
    CLEAN,
    LOW_RISK,
    SUSPICIOUS,
    MALICIOUS,
    NO_DATA
}

data class SourceResult(
    val providerName: String,
    val verdict: VerdictLevel,
    val detectionsCount: Int,
    val totalEngineCount: Int,
    val summary: String
)

data class TriageVerdict(
    val indicator: Indicator,
    val overallVerdict: VerdictLevel,
    val confidenceScore: Int, // 0 to 100
    val summaryDescription: String,
    val sourceResults: List<SourceResult>
)
