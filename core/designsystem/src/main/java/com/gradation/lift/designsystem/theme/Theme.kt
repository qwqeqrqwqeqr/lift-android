package com.gradation.lift.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.gradation.lift.designsystem.resource.*

@Composable
fun LiftTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = LiftTypography,
            content = content,
        )
    }
}


internal val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Color.Unspecified,
    onSecondary = Color.Unspecified,
    secondaryContainer = Color.Unspecified,
    onSecondaryContainer = Color.Unspecified,
    tertiary = Color.Unspecified,
    onTertiary = Color.Unspecified,
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,
    error = Red40,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    inversePrimary = Color.Unspecified,
    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
)


//TODO  다크 테마 설정

internal val DarkColorScheme = darkColorScheme(
    primary = Blue40,
    onPrimary = Color.White,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    secondary = Black10,
    onSecondary = Black20,
    secondaryContainer = Color.Unspecified,
    onSecondaryContainer = Color.Unspecified,
    tertiary = Color.Unspecified,
    onTertiary = Color.Unspecified,
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Color.White,
    onBackground = Color.Black,
    surface = CoolGray100,
    onSurface = CoolGray00,
    surfaceVariant = Gray90,
    onSurfaceVariant = CoolGray10,
    outline = CoolGray70,
    inversePrimary = Gray20,
    inverseSurface = CoolGray00,
    inverseOnSurface = CoolGray100,
)

