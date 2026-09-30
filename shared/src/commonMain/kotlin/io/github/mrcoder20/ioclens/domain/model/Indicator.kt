package io.github.mrcoder20.ioclens.domain.model

enum class IndicatorType {
    IP_ADDRESS,
    DOMAIN,
    URL,
    FILE_HASH,
    UNKNOWN
}

data class Indicator(
    val rawValue: String,
    val normalizedValue: String,
    val type: IndicatorType,
    val isInternalOrPrivate: Boolean = false
)
