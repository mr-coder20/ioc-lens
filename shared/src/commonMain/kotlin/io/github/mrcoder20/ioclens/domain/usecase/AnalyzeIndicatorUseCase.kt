package io.github.mrcoder20.ioclens.domain.usecase

import io.github.mrcoder20.ioclens.domain.model.Indicator
import io.github.mrcoder20.ioclens.domain.model.IndicatorType
import io.github.mrcoder20.ioclens.domain.model.TriageVerdict
import io.github.mrcoder20.ioclens.domain.repository.ThreatIntelRepository

class AnalyzeIndicatorUseCase(
    private val repository: ThreatIntelRepository
) {
    fun parseIndicator(rawInput: String): Indicator {
        val trimmed = rawInput.trim()
        val isInternal = isInternalIpOrDomain(trimmed)
        val type = when {
            trimmed.matches(Regex("""^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$""")) -> IndicatorType.IP_ADDRESS
            trimmed.matches(Regex("""^(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$""")) -> IndicatorType.DOMAIN
            trimmed.startsWith("http://") || trimmed.startsWith("https://") -> IndicatorType.URL
            trimmed.matches(Regex("""^[a-fA-F0-9]{32}$|^[a-fA-F0-9]{40}$|^[a-fA-F0-9]{64}$""")) -> IndicatorType.FILE_HASH
            else -> IndicatorType.UNKNOWN
        }
        return Indicator(
            rawValue = rawInput,
            normalizedValue = trimmed.lowercase(),
            type = type,
            isInternalOrPrivate = isInternal
        )
    }

    suspend operator fun invoke(indicator: Indicator): TriageVerdict {
        return repository.analyzeIndicator(indicator)
    }

    private fun isInternalIpOrDomain(input: String): Boolean {
        if (input.startsWith("127.") || input.startsWith("10.") || input.startsWith("192.168.")) return true
        if (input.startsWith("172.") && isPrivate172(input)) return true
        if (input.lowercase().endsWith(".local") || input.lowercase().endsWith(".internal")) return true
        return false
    }

    private fun isPrivate172(ip: String): Boolean {
        val parts = ip.split(".")
        if (parts.size >= 2) {
            val secondOctet = parts[1].toIntOrNull() ?: 0
            return secondOctet in 16..31
        }
        return false
    }
}
