package io.github.mrcoder20.ioclens.presentation.home

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.AppTheme

sealed interface HomeEvent {
    data class QueryChanged(val newQuery: String) : HomeEvent
    data class ThemeChanged(val theme: AppTheme) : HomeEvent
    data class LanguageChanged(val language: AppLanguage) : HomeEvent
    object AnalyzeClicked : HomeEvent
    object ClearClicked : HomeEvent
    object ReopenIntroClicked : HomeEvent
}
