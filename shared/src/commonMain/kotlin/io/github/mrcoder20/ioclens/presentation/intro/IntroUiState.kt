package io.github.mrcoder20.ioclens.presentation.intro

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.domain.model.IntroSlide
import io.github.mrcoder20.ioclens.getSystemLanguage

data class IntroUiState(
    val slides: List<IntroSlide> = IntroSlideData.getSlides(getSystemLanguage()),
    val currentPageIndex: Int = 0,
    val isLastPage: Boolean = false,
    val isCompleted: Boolean = false,
    val currentLanguage: AppLanguage = getSystemLanguage()
)
