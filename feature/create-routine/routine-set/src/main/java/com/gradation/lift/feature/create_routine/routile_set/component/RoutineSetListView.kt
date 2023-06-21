package com.gradation.lift.feature.create_routine.routile_set.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RoutineSetListView(modifier: Modifier = Modifier){

    Column(
        modifier= modifier.fillMaxWidth()
    ) {
        Text(
            text = "요일선택",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.padding(4.dp))
        Text(
            text = "+버튼을 눌러 리스트를 만들어요",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
        )
    }
}


@Preview
@Composable
fun RoutineSetListViewPreview() {

    LiftTheme() {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            RoutineSetListView(

            )
        }

    }


}