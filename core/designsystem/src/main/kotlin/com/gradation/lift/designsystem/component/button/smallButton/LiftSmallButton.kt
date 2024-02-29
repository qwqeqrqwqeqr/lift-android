package com.gradation.lift.designsystem.component.button.smallButton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


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
            Icon(
                modifier = modifier.size(LiftTheme.space.space12),
                painter = painterResource(id = LiftIcon.EqualBlack),
                contentDescription = "EqualBlack",
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
            Icon(
                modifier = modifier.size(LiftTheme.space.space12),
                painter = painterResource(id = LiftIcon.Plus),
                contentDescription = "Plus",
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
            Icon(
                modifier = modifier.size(LiftTheme.space.space12),
                painter = painterResource(id = LiftIcon.Plus),
                contentDescription = "Plus",
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

