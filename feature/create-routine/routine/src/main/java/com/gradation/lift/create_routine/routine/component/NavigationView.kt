package com.gradation.lift.create_routine.routine.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.create_routine.routine.data.model.IndexWorkSet
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkSet

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    createRoutineCondition: Boolean,
    workSetList: List<IndexWorkSet>,
    addRoutine: (List<WorkSet>) -> Unit,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            addRoutine(
                workSetList.map {
                    WorkSet(
                        weight = it.weight.toFloat(),
                        repetition = it.repetition.toInt()
                    )
                }
            )
            navigateRoutineToRoutineSetInCreateRoutineGraph()
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
