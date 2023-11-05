package com.gradation.lift.feature.routine_detail.routine_list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun RoutineDetailRoutineListRoute(
    modifier: Modifier = Modifier,
    viewModel: RoutineDetailRoutineListViewModel = hiltViewModel(),
) {


    RoutineDetailRoutineListScreen(
        modifier,
    )

}


@Composable
fun RoutineDetailRoutineListScreen(
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




