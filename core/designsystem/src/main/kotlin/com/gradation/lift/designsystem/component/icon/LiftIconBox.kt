package com.gradation.lift.designsystem.component.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


enum class IconBoxSize {
    Size12,
    Size16,
    Size20,
    Size24,
    Size28,
    Size32,
    Size44
}


enum class IconType { Painter, Vector }

@Composable
fun LiftIconBox(
    modifier: Modifier = Modifier,
    icon: Int,
    iconType: IconType,
    iconBoxSize: IconBoxSize,
    padding: Dp = LiftTheme.space.space0,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
) {
    Box(
        modifier
            .size(
                when (iconBoxSize) {
                    IconBoxSize.Size12 -> LiftTheme.space.space12
                    IconBoxSize.Size16 -> LiftTheme.space.space16
                    IconBoxSize.Size20 -> LiftTheme.space.space20
                    IconBoxSize.Size24 -> LiftTheme.space.space24
                    IconBoxSize.Size28 -> LiftTheme.space.space28
                    IconBoxSize.Size32 -> LiftTheme.space.space32
                    IconBoxSize.Size44 -> LiftTheme.space.space44
                }
            )
            .padding(padding)
    ) {
        when (iconType) {
            IconType.Painter ->
                Icon(
                    modifier = modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = icon),
                    contentDescription = contentDescription,
                    tint = tint
                )

            IconType.Vector ->
                Icon(
                    modifier = modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = icon),
                    contentDescription = contentDescription,
                    tint = tint
                )
        }
    }
}

@Composable
@Preview
fun LiftIconBoxPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column {
            LiftIconBox(
                modifier,
                LiftIcon.ChevronDown,
                IconType.Vector,
                IconBoxSize.Size24,
                LiftTheme.space.space0,
                ""
            )
        }
    }
}