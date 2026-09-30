package io.github.mrcoder20.ioclens.presentation.intro

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.domain.model.IntroSlide

object IntroSlideData {
    fun getSlides(language: AppLanguage): List<IntroSlide> {
        return when (language) {
            AppLanguage.ENGLISH -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "Instant Triage",
                    title = "Instant IOC Threat Analysis",
                    subtitle = "Copy it. Know in a second.",
                    description = "Copy an IP address, domain, URL or file hash and get one clear, explainable verdict from several threat intelligence sources at once.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "Privacy Protection",
                    title = "Smart Privacy Guard",
                    subtitle = "Internal data stays on your machine",
                    description = "Blocks private and internal indicators (internal IP addresses and domains) before anything is sent out. No data leak to public tools.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Multi-Source Intel",
                    title = "Explainable Verdicts",
                    subtitle = "VirusTotal, AbuseIPDB, AlienVault OTX",
                    description = "Combines signals from top threat intelligence services into one clear, explainable score (Clean, Low risk, Suspicious, Malicious).",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "User Control",
                    title = "Zero Backend & No Tracking",
                    subtitle = "Your keys, your control",
                    description = "No backend server, no account, no telemetry. API keys stay kept in your operating system's local secure storage.",
                    iconEmoji = "🔑"
                )
            )
            AppLanguage.PERSIAN -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "تریاژ سریع و هوشمند",
                    title = "تحلیل فوری نشانگرهای آلودگی",
                    subtitle = "کپی کن، در یک ثانیه بفهم!",
                    description = "آدرس IP، دامنه، URL یا هش فایل را کپی کنید و بدون باز کردن ده‌ها تب مرورگر، یک حکم شفاف از چند سرویس معتبر جهانی دریافت کنید.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "محافظت از حریم خصوصی",
                    title = "قابلیت هوشمند Privacy Guard",
                    subtitle = "اطلاعات داخلی شبکه لو نمی‌روند",
                    description = "مسدودسازی هوشمند IPها و دامنه‌های داخلی سازمان قبل از هرگونه ارسال اینترنتی. داده‌های محرمانه سازمان روی سیستم شما می‌مانند.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "تجمع چند منبعی",
                    title = "حکم شفاف و قابل توضیح",
                    subtitle = "VirusTotal, AbuseIPDB, AlienVault OTX",
                    description = "ترکیب هوشمند نتایج سرویس‌های Threat Intelligence و ارائه یک پاسخ واحد (Clean, Low risk, Suspicious, Malicious) همراه با جزییات کامل.",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "کنترل کامل کاربر",
                    title = "بدون سرور، بدون ردیابی",
                    subtitle = "کلیدها دست خودتان است",
                    description = "بدون حساب کاربری، بدون تله‌متری و بدون سرور واسط. تمام کلیدهای API به صورت امن فقط روی دستگاه شما ذخیره می‌شوند.",
                    iconEmoji = "🔑"
                )
            )
            AppLanguage.RUSSIAN -> listOf(
                IntroSlide(
                    id = 1,
                    badgeText = "Мгновенный триаж",
                    title = "Мгновенный анализ индикаторов",
                    subtitle = "Скопируй. Узнай за секунду.",
                    description = "Скопируйте IP-адрес, домен, URL или хеш файла и получите один понятный вердикт сразу от нескольких источников threat intelligence.",
                    iconEmoji = "🔍"
                ),
                IntroSlide(
                    id = 2,
                    badgeText = "Защита приватности",
                    title = "Умный Privacy Guard",
                    subtitle = "Внутренние данные остаются на ПК",
                    description = "Блокирует приватные и внутренние индикаторы (внутренние IP и домены) до отправки наружу. Никаких утечек внутренних данных.",
                    iconEmoji = "🛡️"
                ),
                IntroSlide(
                    id = 3,
                    badgeText = "Много источников",
                    title = "Объяснимый вердикт",
                    subtitle = "VirusTotal, AbuseIPDB, AlienVault OTX",
                    description = "Объединяет сигналы нескольких сервисов threat intelligence в один понятный вердикт (Clean, Low risk, Suspicious, Malicious).",
                    iconEmoji = "🌐"
                ),
                IntroSlide(
                    id = 4,
                    badgeText = "Полный контроль",
                    title = "Без бэкенда и слежки",
                    subtitle = "Ваши ключи под вашим контролем",
                    description = "Без аккаунтов, без телеметрии и без промежуточных серверов. API-ключи хранятся только локально на вашем устройстве.",
                    iconEmoji = "🔑"
                )
            )
        }
    }
}
