package io.github.mrcoder20.ioclens

import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getSystemLanguage(): AppLanguage {
    val lang = NSLocale.currentLocale.languageCode.lowercase()
    return when (lang) {
        "fa" -> AppLanguage.PERSIAN
        "ru" -> AppLanguage.RUSSIAN
        else -> AppLanguage.ENGLISH
    }
}
