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
 * [LiftChangeOrderSmallButton]
 * 순서 변경에 사용되는 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftChangeOrderSmallButton(
    modifier: Modifier = Modifier,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.EqualBlack,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = "순서변경",
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}


/**
 * [LiftAddSmallButton]
 * 추가 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftAddSmallButton(
    modifier: Modifier = Modifier,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Plus,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = "추가",
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}


/**
 * [LiftAddSmallButton]
 * 운동 추가 버튼
 * @since 2024-03-10 12:19:47
 */
@Composable
fun LiftAddWorkSetButton(
    modifier: Modifier = Modifier,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Plus,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size14,
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = "운동추가",
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}

