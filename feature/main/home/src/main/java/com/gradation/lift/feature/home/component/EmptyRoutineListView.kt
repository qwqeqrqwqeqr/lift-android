package com.gradation.lift.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun EmptyRoutineListView(
    modifier : Modifier = Modifier,
    onClickCreateRoutine : () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(54.dp))
        Text(
            text = "아직 루틴리스트가 없네요.",
            style = LiftTheme.typography.no4,
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.padding(8.dp))
        LiftOutlineButton(
            modifier = modifier.fillMaxWidth(),
            onClick =  onClickCreateRoutine
        ) {
            Text(
                text = "루틴 리스트 만들기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4,
            )
            Icon(
                imageVector = LiftIcon.ChevronRight,
                contentDescription = null,
            )
        }
    }
}