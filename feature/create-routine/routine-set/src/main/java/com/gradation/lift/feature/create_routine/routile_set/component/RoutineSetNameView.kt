package com.gradation.lift.feature.create_routine.routile_set.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineSetNameView(
    routineSetName: TextFieldValue,
    modifier: Modifier = Modifier
) {
    Column() {

        TextField(
            value = routineSetName,
            onValueChange = {},
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
        routineSetName = TextFieldValue("")
    )


}