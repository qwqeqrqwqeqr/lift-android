package com.gradation.lift.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.gradation.lift.designsystem.resource.*

@Composable
fun LiftMaterialTheme(
    isDarkTheme: Boolean = false,
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        dynamicColor && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        isDarkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    val liftColorScheme = when (isDarkTheme) {
        true -> liftDarkColorScheme()
        false -> liftLightColorScheme()
    }

    CompositionLocalProvider(
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = {
                CompositionLocalProvider(
                    LocalLiftColorScheme provides liftColorScheme,
                    LocalLiftTypography provides LiftTypography()
                ) {
                    Surface(content = content)
                }
            },
        )
    }
}


object LiftTheme {
    val colorScheme: LiftColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalLiftColorScheme.current

    val typography: LiftTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalLiftTypography.current
}
