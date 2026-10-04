package io.github.mrcoder20.ioclens.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mrcoder20.ioclens.core.localization.LocalizationProvider
import io.github.mrcoder20.ioclens.domain.repository.SettingsRepository
import io.github.mrcoder20.ioclens.domain.usecase.AnalyzeIndicatorUseCase
import io.github.mrcoder20.ioclens.domain.usecase.SetIntroCompletedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val analyzeIndicatorUseCase: AnalyzeIndicatorUseCase,
    private val setIntroCompletedUseCase: SetIntroCompletedUseCase,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val initialSettings = settingsRepository.getSettings()

    private val _uiState = MutableStateFlow(
        HomeUiState(
            appTheme = initialSettings.appTheme,
            appLanguage = initialSettings.appLanguage
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            settingsRepository.settingsState.collect { settings ->
                _uiState.update {
                    it.copy(
                        appTheme = settings.appTheme,
                        appLanguage = settings.appLanguage
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.QueryChanged -> {
                _uiState.update { it.copy(queryInput = event.newQuery, errorMessage = null) }
            }
            is HomeEvent.ThemeChanged -> {
                settingsRepository.setAppTheme(event.theme)
            }
            is HomeEvent.LanguageChanged -> {
                settingsRepository.setAppLanguage(event.language)
            }
            HomeEvent.AnalyzeClicked -> {
                analyzeQuery()
            }
            HomeEvent.ClearClicked -> {
                _uiState.update { it.copy(queryInput = "", activeVerdict = null, errorMessage = null) }
            }
            HomeEvent.ReopenIntroClicked -> {
                setIntroCompletedUseCase(completed = false)
            }
        }
    }

    private fun analyzeQuery() {
        val input = _uiState.value.queryInput
        val strings = LocalizationProvider.getStrings(_uiState.value.appLanguage)

        if (input.isBlank()) {
            _uiState.update { it.copy(errorMessage = strings.enterIndicatorError) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val indicator = analyzeIndicatorUseCase.parseIndicator(input)
                val verdict = analyzeIndicatorUseCase(indicator)
                _uiState.update { it.copy(isLoading = false, activeVerdict = verdict) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error") }
            }
        }
    }
}
