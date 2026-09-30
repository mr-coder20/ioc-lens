package io.github.mrcoder20.ioclens.presentation.home

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.AppTheme
import io.github.mrcoder20.ioclens.domain.model.TriageVerdict

data class HomeUiState(
    val queryInput: String = "",
    val isLoading: Boolean = false,
    val activeVerdict: TriageVerdict? = null,
    val errorMessage: String? = null,
    val appTheme: AppTheme = AppTheme.DARK,
    val appLanguage: AppLanguage = AppLanguage.PERSIAN
)
