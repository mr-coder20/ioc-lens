package io.github.mrcoder20.ioclens.presentation.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.mrcoder20.ioclens.core.theme.IOCLensTheme
import io.github.mrcoder20.ioclens.domain.repository.SettingsRepository
import io.github.mrcoder20.ioclens.presentation.home.HomeScreen
import io.github.mrcoder20.ioclens.presentation.home.HomeViewModel
import io.github.mrcoder20.ioclens.presentation.intro.IntroScreen
import io.github.mrcoder20.ioclens.presentation.intro.IntroViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    introViewModel: IntroViewModel,
    homeViewModel: HomeViewModel,
    settingsRepository: SettingsRepository,
    modifier: Modifier = Modifier
) {
    val uiState by mainViewModel.uiState.collectAsState()

    IOCLensTheme(
        appTheme = uiState.appSettings.appTheme,
        appLanguage = uiState.appSettings.appLanguage
    ) {
        if (uiState.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            AnimatedContent(
                targetState = uiState.appSettings.isIntroCompleted,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                modifier = modifier
            ) { isCompleted ->
                if (isCompleted) {
                    HomeScreen(
                        viewModel = homeViewModel,
                        settingsRepository = settingsRepository
                    )
                } else {
                    IntroScreen(
                        viewModel = introViewModel,
                        onIntroFinished = {
                            // Handled inside IntroViewModel
                        }
                    )
                }
            }
        }
    }
}
