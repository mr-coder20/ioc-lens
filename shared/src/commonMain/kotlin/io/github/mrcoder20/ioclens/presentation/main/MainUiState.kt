package io.github.mrcoder20.ioclens.presentation.main

import io.github.mrcoder20.ioclens.domain.model.AppSettings

data class MainUiState(
    val appSettings: AppSettings = AppSettings(),
    val isLoading: Boolean = true
)
