package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    createWorkHistory: () -> Unit,
) {
    Column(modifier.padding(start = 16.dp,end=16.dp, bottom = 16.dp )) {

        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = createWorkHistory,
        ) {
            Text(
                text = "완료",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }

}