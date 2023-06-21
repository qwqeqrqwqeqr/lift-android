package com.gradation.lift.feature.create_routine.routile_set.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.LiftTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineSetNameView(
    routineSetName: String,
    updateText : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column() {

        LiftTextField(
            value = routineSetName,
            onValueChange = updateText,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("루틴이름을 정해주세요.") },
            singleLine = true,
        )
    }


}


@Preview
@Composable
fun RoutineSetNameViewPreview(){

    RoutineSetNameView(
        routineSetName = "",
        updateText= {}
    )


}