package io.github.mrcoder20.ioclens.presentation.intro

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.domain.model.IntroSlide

object IntroSlideData {
    fun getSlides(language: AppLanguage): List<IntroSlide> {
        return when (language) {
            AppLanguage.ENGLISH -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "Instant IOC Triage",
                    title = "Real-Time Threat Indicator Triage",
                    subtitle = "",
                    description = "Instantly check suspicious IP addresses, domains, URLs, and file hashes. Receive a consolidated, clear verdict from top threat databases at once.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "100% Privacy Protection",
                    title = "Local Privacy Guard",
                    subtitle = "",
                    description = "Automatically blocks private IP ranges and internal domain lookups locally before any external request is sent. Zero data leakage.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Multi-Source Threat Intel",
                    title = "Corroborated Global Intelligence",
                    subtitle = "",
                    description = "Consolidates detection signals from top global security feeds into one clear, explainable score backed by transparent vendor breakdown.",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "Zero Telemetry",
                    title = "Direct HTTPS & OS Secure Storage",
                    subtitle = "",
                    description = "No backend server, no registration, no tracking. API keys are kept safely in your operating system's native secure vault.",
                    iconEmoji = "🔑"
                )
            )
            AppLanguage.PERSIAN -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "تریاژ هوشمند IOC",
                    title = "تحلیل آنی نشانگرهای آلودگی (IOCs)",
                    subtitle = "",
                    description = "بررسی فوری آدرس‌های IP، دامنه‌ها، URLها و هش فایل‌ها. دریافت حکم یکپارچه و دقیق از دیتابیس‌های معتبر بدون سردرگمی در تب‌های مرورگر.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "حریم خصوصی ۱۰۰٪",
                    title = "محافظت هوشمند از داده‌های داخلی",
                    subtitle = "",
                    description = "شناسایی و مسدودسازی خودکار نشانگرهای خصوصی و درون‌شبکه‌ای (IPهای داخلی و دامنه‌های محرمانه) به صورت محلی جهت جلوگیری از نشت داده.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Threat Intel چندمنبعه",
                    title = "ارزیابی همزمان چندین دیتابیس جهانی",
                    subtitle = "",
                    description = "ادغام هوشمند سیگنال‌های امنیتی معتبرترین دیتابیس‌های threat intelligence و ارائه پاسخ واحد با جزئیات کامل و شفاف.",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "معماری غیرمتمرکز",
                    title = "بدون سرور واسط و ردیابی",
                    subtitle = "",
                    description = "بدون نیاز به ساخت حساب، بدون تله‌متری و بدون سرور مرکزی. ارتباط مستقیم و امن HTTPS دستگاه شما با دیتابیس‌های هدف.",
                    iconEmoji = "🔑"
                )
            )
            AppLanguage.RUSSIAN -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "Мгновенный триаж IOC",
                    title = "Экспресс-анализ индикаторов угроз",
                    subtitle = "",
                    description = "Быстрая проверка IP-адресов, доменов, URL и хешей файлов. Единый понятный вердикт из ведущих баз данных угроз.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "100% Защита данных",
                    title = "Модуль Privacy Guard",
                    subtitle = "",
                    description = "Автоматическая локальная блокировка запросов внутренних IP и приватных доменов для исключения утечек информации.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Мультипровайдерный Threat Intel",
                    title = "Единый вердикт из мировых баз",
                    subtitle = "",
                    description = "Объединение сигналов ведущих глобальных сервисов threat intelligence в один понятный вердикт с подробной детализацией.",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "Без телеметрии",
                    title = "Прямое соединение и локальная защита",
                    subtitle = "",
                    description = "Без сторонних серверов, регистрации и слежки. Ключи API хранятся исключительно в локальном защищённом хранилище ОС.",
                    iconEmoji = "🔑"
                )
            )
        }
    }
}
