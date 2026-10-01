package io.github.mrcoder20.ioclens.data.repository

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.AppTheme
import io.github.mrcoder20.ioclens.core.storage.KeyValueStorage
import io.github.mrcoder20.ioclens.domain.model.AppSettings
import io.github.mrcoder20.ioclens.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsRepositoryImpl(
    private val storage: KeyValueStorage
) : SettingsRepository {

    private val KEY_INTRO_COMPLETED = "key_intro_completed"
    private val KEY_PRIVACY_GUARD = "key_privacy_guard"
    private val KEY_AUTO_SCAN = "key_auto_scan"
    private val KEY_THEME = "key_theme"
    private val KEY_LANGUAGE = "key_language"
    private val KEY_VT_API = "key_vt_api"
    private val KEY_ABUSEIPDB_API = "key_abuseipdb_api"
    private val KEY_ALIENVAULT_API = "key_alienvault_api"

    private val _settingsState = MutableStateFlow(loadSettingsFromStorage())
    override val settingsState: StateFlow<AppSettings> = _settingsState.asStateFlow()

    override fun getSettings(): AppSettings {
        return _settingsState.value
    }

    override fun setIntroCompleted(completed: Boolean) {
        storage.setBoolean(KEY_INTRO_COMPLETED, completed)
        _settingsState.update { it.copy(isIntroCompleted = completed) }
    }

    override fun setPrivacyGuardEnabled(enabled: Boolean) {
        storage.setBoolean(KEY_PRIVACY_GUARD, enabled)
        _settingsState.update { it.copy(privacyGuardEnabled = enabled) }
    }

    override fun setAppTheme(theme: AppTheme) {
        storage.setString(KEY_THEME, theme.name)
        _settingsState.update { it.copy(appTheme = theme) }
    }

    override fun setAppLanguage(language: AppLanguage) {
        storage.setString(KEY_LANGUAGE, language.name)
        _settingsState.update { it.copy(appLanguage = language) }
    }

    override fun updateApiKey(provider: String, key: String) {
        when (provider.lowercase()) {
            "virustotal" -> storage.setString(KEY_VT_API, key)
            "abuseipdb" -> storage.setString(KEY_ABUSEIPDB_API, key)
            "alienvault" -> storage.setString(KEY_ALIENVAULT_API, key)
        }
        _settingsState.value = loadSettingsFromStorage()
    }

    private fun loadSettingsFromStorage(): AppSettings {
        val themeStr = storage.getString(KEY_THEME, AppTheme.DARK.name)
        val langStr = storage.getString(KEY_LANGUAGE, AppLanguage.ENGLISH.name)

        val theme = runCatching { AppTheme.valueOf(themeStr) }.getOrDefault(AppTheme.DARK)
        val language = runCatching { AppLanguage.valueOf(langStr) }.getOrDefault(AppLanguage.ENGLISH)

        return AppSettings(
            isIntroCompleted = storage.getBoolean(KEY_INTRO_COMPLETED, false),
            privacyGuardEnabled = storage.getBoolean(KEY_PRIVACY_GUARD, true),
            autoScanClipboard = storage.getBoolean(KEY_AUTO_SCAN, true),
            appTheme = theme,
            appLanguage = language,
            virusTotalApiKey = storage.getString(KEY_VT_API, ""),
            abuseIpDbApiKey = storage.getString(KEY_ABUSEIPDB_API, ""),
            alienVaultApiKey = storage.getString(KEY_ALIENVAULT_API, "")
        )
    }
}
