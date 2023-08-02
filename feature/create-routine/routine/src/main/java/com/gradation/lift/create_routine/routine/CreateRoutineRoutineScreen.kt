package com.gradation.lift.create_routine.routine

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.model.routine.CreateRoutine
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineRoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateCreateRoutineRoutineToFindWorkCategory: () -> Unit,
    navigateCreateRoutineRoutineToRoot: () -> Unit,
    viewModel: CreateRoutineRoutineViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)


    val tempCreateRoutine = sharedViewModel.tempRoutine.collectAsStateWithLifecycle()


    CreateRoutineRoutineScreen(
        modifier = modifier,
        onBackClickTopBar = navigateCreateRoutineRoutineToFindWorkCategory,
        tempCreateRoutine = tempCreateRoutine
    )

    BackHandler(onBack = navigateCreateRoutineRoutineToFindWorkCategory)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    tempCreateRoutine: State<CreateRoutine>
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = tempCreateRoutine.value.workCategory,
                onBackClickTopBar = onBackClickTopBar,
            )
        }, modifier = modifier.fillMaxSize()
    ) { PaddingValues ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(PaddingValues)
                    .padding(16.dp)
            ) {
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun CreateRoutineRoutineScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineRoutineScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            tempCreateRoutine = mutableStateOf(CreateRoutine(workCategory = "턱걸이",workSetList = emptyList()))
        )
    }
}