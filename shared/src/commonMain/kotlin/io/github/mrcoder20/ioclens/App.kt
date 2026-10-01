package io.github.mrcoder20.ioclens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import io.github.mrcoder20.ioclens.core.storage.createPersistentKeyValueStorage
import io.github.mrcoder20.ioclens.data.repository.SettingsRepositoryImpl
import io.github.mrcoder20.ioclens.data.repository.ThreatIntelRepositoryImpl
import io.github.mrcoder20.ioclens.domain.usecase.AnalyzeIndicatorUseCase
import io.github.mrcoder20.ioclens.domain.usecase.GetAppSettingsUseCase
import io.github.mrcoder20.ioclens.domain.usecase.SetIntroCompletedUseCase
import io.github.mrcoder20.ioclens.presentation.home.HomeViewModel
import io.github.mrcoder20.ioclens.presentation.intro.IntroViewModel
import io.github.mrcoder20.ioclens.presentation.main.MainScreen
import io.github.mrcoder20.ioclens.presentation.main.MainViewModel

@Composable
@Preview
fun App() {
    // Composition Root / Dependency Injection (Persistent Settings Storage Across App Restarts)
    val keyValueStorage = remember { createPersistentKeyValueStorage() }
    val settingsRepository = remember { SettingsRepositoryImpl(keyValueStorage) }
    val threatIntelRepository = remember { ThreatIntelRepositoryImpl() }

    val getAppSettingsUseCase = remember { GetAppSettingsUseCase(settingsRepository) }
    val setIntroCompletedUseCase = remember { SetIntroCompletedUseCase(settingsRepository) }
    val analyzeIndicatorUseCase = remember { AnalyzeIndicatorUseCase(threatIntelRepository) }

    val mainViewModel = remember { MainViewModel(getAppSettingsUseCase) }
    val introViewModel = remember { IntroViewModel(setIntroCompletedUseCase, settingsRepository) }
    val homeViewModel = remember { HomeViewModel(analyzeIndicatorUseCase, setIntroCompletedUseCase, settingsRepository) }

    MainScreen(
        mainViewModel = mainViewModel,
        introViewModel = introViewModel,
        homeViewModel = homeViewModel,
        settingsRepository = settingsRepository
    )
}
