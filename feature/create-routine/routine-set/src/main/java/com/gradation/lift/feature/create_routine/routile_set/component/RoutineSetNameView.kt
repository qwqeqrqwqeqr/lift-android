package com.gradation.lift.feature.create_routine.routile_set.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineSetNameView(
    routineSetName: String,
    updateText: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {

        Text(
            text = "루틴리스트 이름",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.padding(4.dp))

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
fun RoutineSetNameViewPreview() {

    LiftTheme() {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            RoutineSetNameView(
                routineSetName = "",
                updateText = {}
            )
        }

    }


}