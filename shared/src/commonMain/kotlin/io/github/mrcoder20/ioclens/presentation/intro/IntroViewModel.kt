package io.github.mrcoder20.ioclens.presentation.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mrcoder20.ioclens.domain.repository.SettingsRepository
import io.github.mrcoder20.ioclens.domain.usecase.SetIntroCompletedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IntroViewModel(
    private val setIntroCompletedUseCase: SetIntroCompletedUseCase,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IntroUiState())
    val uiState: StateFlow<IntroUiState> = _uiState.asStateFlow()

    init {
        observeLanguage()
    }

    private fun observeLanguage() {
        viewModelScope.launch {
            settingsRepository.settingsState.collect { settings ->
                _uiState.update {
                    it.copy(
                        currentLanguage = settings.appLanguage,
                        slides = IntroSlideData.getSlides(settings.appLanguage)
                    )
                }
            }
        }
    }

    fun onEvent(event: IntroEvent) {
        when (event) {
            is IntroEvent.PageChanged -> {
                val isLast = event.pageIndex == _uiState.value.slides.size - 1
                _uiState.update {
                    it.copy(
                        currentPageIndex = event.pageIndex,
                        isLastPage = isLast
                    )
                }
            }
            is IntroEvent.LanguageChanged -> {
                settingsRepository.setAppLanguage(event.language)
            }
            IntroEvent.SkipClicked -> {
                completeIntro()
            }
            IntroEvent.CompleteClicked -> {
                completeIntro()
            }
        }
    }

    private fun completeIntro() {
        setIntroCompletedUseCase(completed = true)
        _uiState.update { it.copy(isCompleted = true) }
    }
}
