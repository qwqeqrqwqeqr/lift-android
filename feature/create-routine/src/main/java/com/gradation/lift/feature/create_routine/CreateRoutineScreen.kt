package com.gradation.lift.feature.create_routine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.navigation.navigation.navigateCreateRoutineToMain


@Composable
fun CreateRoutineRoute(
    navController: NavController,
    sharedViewModel: CreateRoutineSharedViewModel,
    navigateCreateRoutineRootToFindWorkCategory: () -> Unit,
    navigateCreateRoutineRootToProfile: () -> Unit,
    navigateCreateRoutineToMain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val routineSetUiState = sharedViewModel.routineSetListUiState.collectAsStateWithLifecycle()


    CreateRoutineRoutineSetScreen(
        onBackClickTopBar = { navController.navigateCreateRoutineToMain() },
        modifier = modifier,
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRoutineRoutineSetScreen(
    onBackClickTopBar: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "루틴리스트 만들기",
                    onBackClickTopBar = onBackClickTopBar,
                )
            },
        ) { it ->

            Column(modifier = modifier.padding(it)) {

            }
        }
    }
}

