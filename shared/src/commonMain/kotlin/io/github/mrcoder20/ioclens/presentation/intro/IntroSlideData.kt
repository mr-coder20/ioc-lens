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
                    subtitle = "Copy indicator. Get instant verdict.",
                    description = "Instantly query suspicious IP addresses, domains, URLs, and file hashes (MD5/SHA256). Receive a consolidated, actionable verdict without tab overload.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "Zero-Data-Leak Guard",
                    title = "Local Threat Intelligence Guard",
                    subtitle = "Private Infrastructure Stays Safe",
                    description = "Automatically intercepts private IP ranges (RFC1918) and internal enterprise domain queries locally before any external HTTPS request is initiated.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Multi-Provider Intel",
                    title = "Corroborated Multi-Source Verdicts",
                    subtitle = "VirusTotal • AbuseIPDB • AlienVault OTX • URLhaus",
                    description = "Consolidates raw detection telemetry from leading global threat feeds into one explainable risk score backed by transparent vendor breakdown.",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "Zero-Telemetry Architecture",
                    title = "Local OS Secure Key Vault",
                    subtitle = "Direct HTTPS Lookups • Zero Middleman",
                    description = "No backend servers, no tracking, and zero telemetry. API keys and configuration reside exclusively in your OS's native secure key storage.",
                    iconEmoji = "🔑"
                )
            )
            AppLanguage.PERSIAN -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "تریاژ هوشمند IOC",
                    title = "تحلیل آنی نشانگرهای آلودگی (IOCs)",
                    subtitle = "کپی در حافظه، تحلیل جامع در یک ثانیه",
                    description = "بررسی فوری آدرس‌های IP، دامنه‌ها، URLها و هش فایل‌ها (MD5/SHA256). دریافت ارزیابی یکپارچه و دقیق بدون سردرگمی میان ده‌ها تب مرورگر.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "امنیتی و حریم‌خصوصی‌محور",
                    title = "سامانه هوشمند Privacy Guard",
                    subtitle = "تضمین نشت نکردن زیرساخت‌های داخلی",
                    description = "شناسایی و مسدودسازی خودکار نشانگرهای خصوصی و درون‌شبکه‌ای (IPهای RFC1918 و دامنه‌های داخلی) پیش از خروج داده به اینترنت.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "تجمع اطلاعات تهدیدات (Threat Intel)",
                    title = "ارزیابی چندمنبعه و قابل استناد",
                    subtitle = "VirusTotal • AbuseIPDB • AlienVault OTX • URLhaus",
                    description = "ادغام هوشمند سیگنال‌های امنیتی معتبرترین دیتابیس‌های جهان و ارائه ارزیابی واحد به همراه جزئیات شفاف (Clean, Low risk, Suspicious, Malicious).",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "معماری غیرمتمرکز و بومی",
                    title = "تضمین ۱۰۰٪ امنیت کلیدها و تله‌متری",
                    subtitle = "بدون سرور واسط، ذخیره‌سازی در Keystore/Keyring",
                    description = "ارتباط مستقیم و امن دستگاه شما با سرویس‌های هدف از طریق HTTPS. ذخیره کلیدهای API در کلیددان امن سیستم‌عامل بدون وجود سرور مرکزی.",
                    iconEmoji = "🔑"
                )
            )
            AppLanguage.RUSSIAN -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "Мгновенный триаж IOC",
                    title = "Анализ индикаторов угроз в реальном времени",
                    subtitle = "Скопируй. Узнай за секунду.",
                    description = "Мгновенный анализ IP-адресов, доменов, URL и хешей файлов (MD5/SHA256). Единый обоснованный вердикт без десятков открытых вкладок.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "Защита от утечек",
                    title = "Локальный модуль Privacy Guard",
                    subtitle = "Защита внутренней инфраструктуры",
                    description = "Автоматический перехват частных IP-диапазонов (RFC1918) и внутренних доменов на устройстве до отправки любых внешних сетевых запросов.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Мультипровайдерный Threat Intel",
                    title = "Обоснованный вердикт из множества источников",
                    subtitle = "VirusTotal • AbuseIPDB • AlienVault OTX • URLhaus",
                    description = "Консолидация данных ведущих мировых фидов угроз в единую оценку риска с прозрачной детализацией по источникам.",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "Безопасная архитектура",
                    title = "Защищённое локальное хранилище",
                    subtitle = "Прямые HTTPS-запросы • Без посредников",
                    description = "Никаких промежуточных серверов и слежки. Ключи API и настройки хранятся исключительно в защищённом хранилище вашей ОС.",
                    iconEmoji = "🔑"
                )
            )
        }
    }
}
