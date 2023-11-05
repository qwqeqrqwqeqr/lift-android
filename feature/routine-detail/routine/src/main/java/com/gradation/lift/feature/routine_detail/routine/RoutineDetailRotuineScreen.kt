package com.gradation.lift.feature.routine_detail.routine

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun RoutineDetailRoutineRoute(
    modifier: Modifier = Modifier,
    viewModel: RoutineDetailRoutineViewModel = hiltViewModel(),
) {


    RoutineDetailRoutineScreen(
        modifier,
    )

}


@Composable
fun RoutineDetailRoutineScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필 등록하기",
                onBackClickTopBar = {},
            )
        }
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {

            }
        }
    }
}




