package com.gradation.lift.feature.update_routine.routine.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.routine.data.model.IndexWorkSet
import com.gradation.lift.model.model.work.WorkSet

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    createRoutineCondition: Boolean,
    workSetList: List<IndexWorkSet>,
    appendRoutine: (List<WorkSet>) -> Unit,
    navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            appendRoutine(
                workSetList.map {
                    WorkSet(
                        weight = it.weight.toFloat(),
                        repetition = it.repetition.toInt()
                    )
                }
            )
            navigateRoutineToRoutineSetInUpdateRoutineGraph()
        },
        enabled = createRoutineCondition
    ) {
        Text(
            text = "등록하기",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}
