package io.github.mrcoder20.ioclens.domain.usecase

import io.github.mrcoder20.ioclens.domain.repository.SettingsRepository

class SetIntroCompletedUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(completed: Boolean = true) {
        repository.setIntroCompleted(completed)
    }
}
