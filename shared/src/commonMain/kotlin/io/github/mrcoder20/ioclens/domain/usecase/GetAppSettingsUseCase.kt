package io.github.mrcoder20.ioclens.domain.usecase

import io.github.mrcoder20.ioclens.domain.model.AppSettings
import io.github.mrcoder20.ioclens.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.StateFlow

class GetAppSettingsUseCase(
    private val repository: SettingsRepository
) {
    val settingsState: StateFlow<AppSettings> = repository.settingsState

    operator fun invoke(): AppSettings {
        return repository.getSettings()
    }
}
