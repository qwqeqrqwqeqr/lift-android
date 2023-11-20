package com.gradation.lift.designsystem.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.gradation.lift.designsystem.resource.*

@Composable
fun LiftMaterialTheme(
    isDarkTheme: Boolean = false,
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
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
                    LocalLiftTypography provides LiftTypography(),
                    LocalLiftSpace provides LiftSpace()
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


    val space: LiftSpace
        @Composable
        @ReadOnlyComposable
        get() = LocalLiftSpace.current
}
