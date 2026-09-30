package io.github.mrcoder20.ioclens.data.repository

import io.github.mrcoder20.ioclens.domain.model.Indicator
import io.github.mrcoder20.ioclens.domain.model.IndicatorType
import io.github.mrcoder20.ioclens.domain.model.SourceResult
import io.github.mrcoder20.ioclens.domain.model.TriageVerdict
import io.github.mrcoder20.ioclens.domain.model.VerdictLevel
import io.github.mrcoder20.ioclens.domain.repository.ThreatIntelRepository
import kotlinx.coroutines.delay

class ThreatIntelRepositoryImpl : ThreatIntelRepository {

    override suspend fun analyzeIndicator(indicator: Indicator): TriageVerdict {
        // Simulate real multi-source lookup delay
        delay(600)

        if (indicator.isInternalOrPrivate) {
            return TriageVerdict(
                indicator = indicator,
                overallVerdict = VerdictLevel.CLEAN,
                confidenceScore = 100,
                summaryDescription = "🛡️ Privacy Guard Activated: Private/Internal indicator detected. Lookup blocked to protect internal infrastructure.",
                sourceResults = listOf(
                    SourceResult("Privacy Guard", VerdictLevel.CLEAN, 0, 1, "Internal indicator safely handled locally")
                )
            )
        }

        if (indicator.type == IndicatorType.UNKNOWN) {
            return TriageVerdict(
                indicator = indicator,
                overallVerdict = VerdictLevel.NO_DATA,
                confidenceScore = 0,
                summaryDescription = "⚪ Unrecognized indicator format. Please provide a valid IP, Domain, URL, or File Hash.",
                sourceResults = emptyList()
            )
        }

        val sources = listOf(
            SourceResult("VirusTotal", VerdictLevel.SUSPICIOUS, 4, 90, "4 security vendors flagged this as suspicious"),
            SourceResult("AbuseIPDB", VerdictLevel.LOW_RISK, 12, 100, "Reported 12 times in recent 30 days"),
            SourceResult("AlienVault OTX", VerdictLevel.CLEAN, 0, 50, "In 0 pulses in OTX threat feeds"),
            SourceResult("URLhaus / abuse.ch", VerdictLevel.CLEAN, 0, 10, "No active malware payloads recorded")
        )

        return TriageVerdict(
            indicator = indicator,
            overallVerdict = VerdictLevel.SUSPICIOUS,
            confidenceScore = 78,
            summaryDescription = "🟠 Suspicious Indicator: Corroborated signals from VirusTotal and AbuseIPDB.",
            sourceResults = sources
        )
    }
}
