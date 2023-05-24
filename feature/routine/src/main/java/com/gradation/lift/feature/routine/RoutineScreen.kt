package com.gradation.lift.feature.routine

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine.component.RoutineBody
import com.gradation.lift.feature.routine.component.RoutineHeader

@Composable
internal fun RoutineRoute(
    modifier: Modifier = Modifier,
    viewModel: RoutineViewModel = hiltViewModel()
) {
    RoutineScreen(
        modifier = modifier,
    )
}


@Composable
internal fun RoutineScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier.padding(16.dp)
    ) {
        Column {
            RoutineHeader(
                onClick = {},
                modifier = modifier
            )
            Spacer(modifier = modifier.height(16.dp))
            RoutineBody(
                modifier=modifier,
                dateText = "12월 4일")
        }
    }
}

@Preview
@Composable
internal fun RoutineScreenPreview() {
    LiftTheme {
        RoutineScreen()
    }
}

