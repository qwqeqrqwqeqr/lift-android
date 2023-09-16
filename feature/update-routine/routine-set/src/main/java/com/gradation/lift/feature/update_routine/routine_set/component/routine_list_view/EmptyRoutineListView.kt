package com.gradation.lift.feature.update_routine.routine_set.component.routine_list_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

/**
 * [EmptyRoutineListView]
 * 루틴을 하나도 추가하지않은 초기화면 뷰
 * @since 2023-08-21 13:36:54
 */
@Composable
fun EmptyRoutineListView(
    modifier: Modifier = Modifier,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = modifier.align(Alignment.CenterVertically),
            text = "루틴리스트",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no3
        )
    }
    Spacer(modifier = modifier.padding(8.dp))

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
                IconButton(onClick = navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph) {
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
