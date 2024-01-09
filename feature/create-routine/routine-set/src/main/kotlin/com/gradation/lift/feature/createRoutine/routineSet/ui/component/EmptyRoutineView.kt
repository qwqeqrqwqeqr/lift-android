package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.modifier.noRippleClickable


@Composable
fun EmptyRoutineView(
    modifier: Modifier = Modifier,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "운동 목록",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start
            )
        }
        LiftSecondaryContainer(
            modifier = modifier
                .fillMaxWidth(),
            verticalPadding = LiftTheme.space.space36,
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space8,
                    Alignment.CenterVertically
                )
            ) {
                Box(
                    modifier = modifier
                        .background(
                            LiftTheme.colorScheme.no4,
                            shape = CircleShape
                        )
                        .size(LiftTheme.space.space42),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = modifier
                            .noRippleClickable { navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph() }
                            .size(LiftTheme.space.space16),
                        painter = painterResource(id = LiftIcon.Plus),
                        contentDescription = "Add",
                        tint = LiftTheme.colorScheme.no5
                    )
                }
                LiftText(
                    textStyle = LiftTextStyle.No4,
                    text = "+ 버튼을 눌러 루틴을 추가할 수 있어요",
                    color = LiftTheme.colorScheme.no2,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}