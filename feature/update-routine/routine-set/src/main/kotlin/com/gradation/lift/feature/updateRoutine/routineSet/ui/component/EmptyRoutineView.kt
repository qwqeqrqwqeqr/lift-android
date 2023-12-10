package com.gradation.lift.feature.updateRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun EmptyRoutineView(
    modifier: Modifier = Modifier,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
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
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = LiftTheme.colorScheme.no1,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = modifier
                        .background(
                            LiftTheme.colorScheme.no4,
                            shape = RoundedCornerShape(size = 64.dp)
                        )
                        .size(42.dp), contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph) {
                        Icon(
                            modifier = modifier.size(16.dp),
                            painter = painterResource(id = LiftIcon.Plus),
                            contentDescription = "",
                            tint = LiftTheme.colorScheme.no5
                        )
                    }
                }
                Spacer(modifier = modifier.padding(5.dp))
                Text(
                    text = "+ 버튼을 눌러 루틴을 추가해요",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no2
                )

            }
        }
    }
}