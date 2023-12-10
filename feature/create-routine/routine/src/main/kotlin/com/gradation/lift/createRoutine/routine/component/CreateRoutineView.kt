package com.gradation.lift.createRoutine.routine.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.createRoutine.routine.data.model.IndexWorkSet
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkSet

@Composable
fun CreateRoutineView(
    modifier: Modifier = Modifier,
    indexWorkSetList: List<IndexWorkSet>,
    addRoutine: (List<WorkSet>) -> Unit,
    createRoutineCondition: Boolean,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            addRoutine(
                indexWorkSetList.map {
                    WorkSet(
                        weight = it.weight.value.toFloat(),
                        repetition = it.repetition.value.toInt()
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
        Spacer(modifier = modifier.padding(2.dp))
        Icon(
            painterResource(id = LiftIcon.ChevronRight),
            contentDescription = "CreateRoutine",
            modifier = modifier
                .fillMaxHeight()
                .width(8.dp)
        )
    }
}
