package com.gradation.lift.create_routine.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun NavigationView(
    modifier: Modifier,
    selectedPicture: String,
    updateRoutineSetPicture: (String) -> Unit,
    navigateProfileToRoutineSetInCreateRoutineGraph: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LiftButton(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                updateRoutineSetPicture(selectedPicture)
                navigateProfileToRoutineSetInCreateRoutineGraph()
            },
        ) {
            Text(
                text = "등록하기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }

}