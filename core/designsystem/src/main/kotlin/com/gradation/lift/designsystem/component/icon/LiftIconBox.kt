package com.gradation.lift.designsystem.component.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


enum class IconBoxSize {
    Size16,
    Size20,
    Size24,
    Size28
}


enum class IconType { Painter, Vector }

@Composable
fun LiftIconBox(
    modifier: Modifier = Modifier,
    icon: Int,
    iconType: IconType,
    iconBoxSize: IconBoxSize,
    contentDescription: String? = null,
) {
    Box(
        modifier.size(
            when (iconBoxSize) {
                IconBoxSize.Size16 -> LiftTheme.space.space16
                IconBoxSize.Size20 -> LiftTheme.space.space20
                IconBoxSize.Size24 -> LiftTheme.space.space24
                IconBoxSize.Size28 -> LiftTheme.space.space28
            }
        )
    ) {
        when (iconType) {
            IconType.Painter ->
                Icon(
                    modifier = modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = icon),
                    contentDescription = contentDescription
                )

            IconType.Vector ->
                Icon(
                    modifier = modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = icon),
                    contentDescription = contentDescription
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
            LiftIconBox(modifier, LiftIcon.ChevronDown, IconType.Vector, IconBoxSize.Size24, "")
        }
    }
}