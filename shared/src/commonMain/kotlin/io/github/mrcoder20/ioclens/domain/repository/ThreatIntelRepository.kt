package io.github.mrcoder20.ioclens.domain.repository

import io.github.mrcoder20.ioclens.domain.model.Indicator
import io.github.mrcoder20.ioclens.domain.model.TriageVerdict

interface ThreatIntelRepository {
    suspend fun analyzeIndicator(indicator: Indicator): TriageVerdict
}
