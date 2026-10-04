package io.github.mrcoder20.ioclens.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import io.github.mrcoder20.ioclens.core.localization.AppLanguage
import io.github.mrcoder20.ioclens.core.localization.AppTheme
import io.github.mrcoder20.ioclens.getSystemLanguage

private val SignatureDarkColorScheme = darkColorScheme(
    primary = PrimaryCyan,
    onPrimary = DarkBackground,
    primaryContainer = PrimaryCyanVariant,
    onPrimaryContainer = TextPrimaryDark,
    secondary = SecondaryTeal,
    onSecondary = DarkBackground,
    background = DarkBackground,
    onBackground = TextPrimaryDark,
    surface = DarkSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondaryDark,
    outline = TextMutedDark
)

@Composable
fun IOCLensTheme(
    appTheme: AppTheme = AppTheme.DARK,
    appLanguage: AppLanguage = getSystemLanguage(),
    content: @Composable () -> Unit
) {
    val layoutDirection = if (appLanguage.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection
    ) {
        MaterialTheme(
            colorScheme = SignatureDarkColorScheme,
            typography = AppTypography,
            content = content
        )
    }
}
