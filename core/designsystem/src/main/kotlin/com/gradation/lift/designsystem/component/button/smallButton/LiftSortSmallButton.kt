package com.gradation.lift.designsystem.component.button.smallButton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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

/**
 * [LiftSortSmallButton]
 * 정렬 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftSortSmallButton(
    modifier: Modifier = Modifier,
    sortType: String,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Sort,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "정렬",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = sortType,
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}