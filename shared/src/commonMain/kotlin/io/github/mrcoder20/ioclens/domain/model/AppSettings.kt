package io.github.mrcoder20.ioclens.domain.model

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.AppTheme

data class AppSettings(
    val isIntroCompleted: Boolean = false,
    val privacyGuardEnabled: Boolean = true,
    val autoScanClipboard: Boolean = true,
    val appTheme: AppTheme = AppTheme.DARK,
    val appLanguage: AppLanguage = AppLanguage.ENGLISH,
    val virusTotalApiKey: String = "",
    val abuseIpDbApiKey: String = "",
    val alienVaultApiKey: String = ""
)
