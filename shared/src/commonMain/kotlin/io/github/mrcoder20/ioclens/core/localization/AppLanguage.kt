package io.github.mrcoder20.ioclens.core.localization

enum class AppLanguage(
    val code: String,
    val displayName: String,
    val flagEmoji: String,
    val badgeCode: String,
    val isRtl: Boolean
) {
    ENGLISH("en", "English", "🇬🇧", "EN", isRtl = false),
    PERSIAN("fa", "فارسی", "🇮🇷", "FA", isRtl = true),
    RUSSIAN("ru", "Русский", "🇷🇺", "RU", isRtl = false)
}
