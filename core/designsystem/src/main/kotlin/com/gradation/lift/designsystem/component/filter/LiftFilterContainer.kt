package com.gradation.lift.designsystem.component.filter

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
fun LiftLabelFilterContainer(
    modifier: Modifier = Modifier,
    labelType: Set<Int>,
    selectedAll: Boolean,
) {
    LiftBaseFilterContainer(modifier = modifier) {
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
            if(selectedAll){
                LiftText(
                    textStyle = LiftTextStyle.No5,
                    text = "전체",
                    color = LiftTheme.colorScheme.no4,
                    textAlign = TextAlign.Start
                )
            }else {
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
fun LiftWeekdayFilterContainer(
    modifier: Modifier = Modifier,
    weekdayType: String,
) {
    LiftBaseFilterContainer(modifier = modifier) {
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
fun LiftSortFilterContainer(
    modifier: Modifier = Modifier,
    sortType: String,
) {
    LiftBaseFilterContainer(modifier = modifier) {
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


@Composable
fun LiftChangeOrderContainer(
    modifier: Modifier = Modifier,
) {
    LiftBaseFilterContainer(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier=modifier.size(LiftTheme.space.space12),
                painter = painterResource(id = LiftIcon.Order),
                contentDescription = "Order",
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
fun LiftAddContainer(
    modifier: Modifier = Modifier,
) {
    LiftBaseFilterContainer(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier=modifier.size(LiftTheme.space.space12),
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
fun LiftAddWorkSetContainer(
    modifier: Modifier = Modifier,
) {
    LiftBaseFilterContainer(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier=modifier.size(LiftTheme.space.space12),
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

@Composable
fun LiftBaseFilterContainer(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .height(LiftTheme.space.space28)
            .background(
                LiftTheme.colorScheme.no1,
                RoundedCornerShape(LiftTheme.space.space6)
            )
            .padding(horizontal = LiftTheme.space.space6),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        content = content
    )
}