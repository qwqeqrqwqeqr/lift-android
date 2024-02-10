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
fun LiftLabelFilterSmallButton(
    modifier: Modifier = Modifier,
    labelType: Set<Int>,
    selectedAll: Boolean,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = LiftIcon.Label),
                contentDescription = "Calendar",
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "라벨",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            if (selectedAll) {
                LiftText(
                    textStyle = LiftTextStyle.No5,
                    text = "전체",
                    color = LiftTheme.colorScheme.no4,
                    textAlign = TextAlign.Start
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    labelType.forEach {
                        RoutineLabel(modifier = modifier.size(LiftTheme.space.space12), id = it)
                    }
                }
            }
        }
    }
}

@Composable
fun LiftWeekdayFilterSmallButton(
    modifier: Modifier = Modifier,
    weekdayType: String,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = LiftIcon.Calendar),
                contentDescription = "Calendar",
                tint = LiftTheme.colorScheme.no2
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "요일",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = weekdayType,
                color = LiftTheme.colorScheme.no4,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun LiftSortFilterSmallButton(
    modifier: Modifier = Modifier,
    sortType: String,
) {
    LiftBaseSmallButton(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = LiftIcon.Sort),
                contentDescription = "Sort",
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

