package com.gradation.lift.feature.create_routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.feature.create_routine.component.RoutineSetListView
import com.gradation.lift.feature.create_routine.component.RoutineSetNameView
import com.gradation.lift.feature.create_routine.component.RoutineSetWeekDateView
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineFindWorkpart
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineRoutineDetail


@Composable
fun CreateRoutineRoute(
    navController: NavController,
    sharedViewModel: CreateRoutineSharedViewModel,
    modifier: Modifier = Modifier,
) {


    val routineSetUiState=  sharedViewModel.routineSetListUiState.collectAsStateWithLifecycle()

    val routineSetName = sharedViewModel.routineSetName
    val updateRoutineSetName = sharedViewModel::updateRoutineSetName
    val haveRoutineSet = routineSetUiState.value.isNotEmpty()
    val onClickPlusCircle = {navController.navigateToCreateRoutineFindWorkpart()}
    val onClickCreateRoutine ={navController.navigateToCreateRoutineRoutineDetail() }
    CreateRoutineRoutineSetScreen(
        onBackClickTopBar = { navController.popBackStack() },
        modifier = modifier,
        routineSetName = routineSetName,
        updateRoutineSetName = updateRoutineSetName,
        haveRoutineSet = haveRoutineSet,
        onClickPlusCircle = onClickPlusCircle,
        onClickCreateRoutine=onClickCreateRoutine
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRoutineRoutineSetScreen(
    onBackClickTopBar: () -> Unit,
    modifier: Modifier = Modifier,
    routineSetName: String,
    updateRoutineSetName: (updateText: String) -> Unit,
    haveRoutineSet: Boolean,
    onClickPlusCircle: () -> Unit,
    onClickCreateRoutine: () -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "루틴리스트 만들기",
                    onBackClickTopBar = onBackClickTopBar,
                )
            },
        ) { padding ->
            Box(
                modifier = modifier
                    .padding(padding)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
                    )
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Column {
                    RoutineSetNameView(routineSetName,updateRoutineSetName)
                    RoutineSetListView(haveRoutineSet = haveRoutineSet,onClickPlusCircle=onClickPlusCircle)
                    RoutineSetWeekDateView()

                    LiftButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onClickCreateRoutine,
                    ) {
                        Text(
                            text = "생성하기",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }

                }
            }

        }
    }

}

