package com.gradation.lift.feature.work.routine_selection.component.routine_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun EmptyRoutineListView(
    modifier:Modifier=Modifier
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.open_box),
                contentDescription = "",
                modifier = modifier
                    .size(96.dp)
            )
            Spacer(modifier = modifier.padding(4.dp))
            Text(
                text = "루틴이 존재하지 않네요...",
                style = LiftTheme.typography.no4,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.padding(8.dp))
        }
    }
}