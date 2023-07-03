package com.gradation.lift.feature.create_routine.routile_set.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.canvas.PlusCircle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

@Composable
fun RoutineSetListView(
    modifier: Modifier = Modifier,
    haveRoutineSet: Boolean,
    onClickPlusCircle: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),

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
        Spacer(modifier = modifier.padding(4.dp))

        if (haveRoutineSet) {
            RoutineSetListDetailView(modifier)
        } else {
            RoutineSetListEmptyView(modifier,onClickPlusCircle)
        }

    }
}


@Composable
fun RoutineSetListEmptyView(
    modifier: Modifier = Modifier,
    onClickPlusCircle: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(16.dp))
        PlusCircle(modifier.clickable(onClick =onClickPlusCircle))
        Spacer(modifier = modifier.padding(16.dp))

    }
}

@Composable
fun RoutineSetListDetailView(
    modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "안빔")

    }
}


@Preview
@Composable
fun RoutineSetListViewPreview() {

    LiftMaterialTheme() {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            RoutineSetListView(haveRoutineSet = false, onClickPlusCircle = {})
        }

    }


}