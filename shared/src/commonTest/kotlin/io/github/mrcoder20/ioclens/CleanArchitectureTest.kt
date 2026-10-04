package io.github.mrcoder20.ioclens

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.AppTheme
import io.github.mrcoder20.ioclens.core.storage.InMemoryKeyValueStorage
import io.github.mrcoder20.ioclens.data.repository.SettingsRepositoryImpl
import io.github.mrcoder20.ioclens.data.repository.ThreatIntelRepositoryImpl
import io.github.mrcoder20.ioclens.domain.model.IndicatorType
import io.github.mrcoder20.ioclens.domain.model.VerdictLevel
import io.github.mrcoder20.ioclens.domain.usecase.AnalyzeIndicatorUseCase
import io.github.mrcoder20.ioclens.domain.usecase.GetAppSettingsUseCase
import io.github.mrcoder20.ioclens.domain.usecase.SetIntroCompletedUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CleanArchitectureTest {

    @Test
    fun testSettingsRepositoryAndSystemDefaults() {
        val storage = InMemoryKeyValueStorage()
        val settingsRepo = SettingsRepositoryImpl(storage)
        val getIntroUseCase = GetAppSettingsUseCase(settingsRepo)
        val setIntroUseCase = SetIntroCompletedUseCase(settingsRepo)

        // Initial default theme should be DARK
        assertEquals(AppTheme.DARK, settingsRepo.getSettings().appTheme)

        // Complete intro
        setIntroUseCase(completed = true)
        assertTrue(getIntroUseCase().isIntroCompleted)

        // Switch Theme
        settingsRepo.setAppTheme(AppTheme.SYSTEM)
        assertEquals(AppTheme.SYSTEM, settingsRepo.getSettings().appTheme)

        // Switch Language to Russian
        settingsRepo.setAppLanguage(AppLanguage.RUSSIAN)
        assertEquals(AppLanguage.RUSSIAN, settingsRepo.getSettings().appLanguage)
        assertFalse(AppLanguage.RUSSIAN.isRtl)

        // Switch Language to Persian
        settingsRepo.setAppLanguage(AppLanguage.PERSIAN)
        assertEquals(AppLanguage.PERSIAN, settingsRepo.getSettings().appLanguage)
        assertTrue(AppLanguage.PERSIAN.isRtl)
    }

    @Test
    fun testPrivacyGuardAndIndicatorParsing() {
        runBlocking {
            val threatRepo = ThreatIntelRepositoryImpl()
            val analyzeUseCase = AnalyzeIndicatorUseCase(threatRepo)

            // Test internal IP
            val internalIp = analyzeUseCase.parseIndicator("192.168.1.100")
            assertEquals(IndicatorType.IP_ADDRESS, internalIp.type)
            assertTrue(internalIp.isInternalOrPrivate)

            val internalVerdict = analyzeUseCase(internalIp)
            assertEquals(VerdictLevel.CLEAN, internalVerdict.overallVerdict)
            assertTrue(internalVerdict.summaryDescription.contains("Privacy Guard"))

            // Test external domain
            val domain = analyzeUseCase.parseIndicator("malicious-site.com")
            assertEquals(IndicatorType.DOMAIN, domain.type)
            assertFalse(domain.isInternalOrPrivate)
        }
    }

    @Test
    fun testSystemLanguageDetection() {
        val systemLanguage = getSystemLanguage()
        assertTrue(systemLanguage in AppLanguage.entries)
    }
}
