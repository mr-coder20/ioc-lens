package io.github.mrcoder20.ioclens.core.localization

enum class AppLanguage(
    val code: String,
    val displayName: String,
    val flagEmoji: String,
    val isRtl: Boolean
) {
    ENGLISH("en", "English", "🇬🇧", isRtl = false),
    PERSIAN("fa", "فارسی", "🇮🇷", isRtl = true),
    RUSSIAN("ru", "Русский", "🇷🇺", isRtl = false)
}
