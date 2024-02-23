package com.gradation.lift.designsystem.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftInfoContainer(
    modifier: Modifier = Modifier,
    text: String,
) {
    LiftPrimaryContainer(
        modifier = modifier,
        horizontalPadding = LiftTheme.space.space8,
        verticalPadding = LiftTheme.space.space12,
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftIconBox(
                icon = LiftIcon.Warn,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size24,
                tint = LiftTheme.colorScheme.no4
            )
            LiftText(
                text = text,
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start,
                textStyle = LiftTextStyle.No6
            )
        }
    }
}