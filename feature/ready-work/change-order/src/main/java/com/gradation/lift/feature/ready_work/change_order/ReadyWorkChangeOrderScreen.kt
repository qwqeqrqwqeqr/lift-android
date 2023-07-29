package com.gradation.lift.feature.ready_work.change_order

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.change_order.component.routine_list.LoadingRoutineListView
import com.gradation.lift.feature.ready_work.change_order.component.routine_list.RoutineListView
import com.gradation.lift.feature.ready_work.change_order.data.ReadyWorkChangeOrderViewModel
import com.gradation.lift.feature.ready_work.change_order.data.RoutineSetRoutineUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadyWorkChangeOrderRoute(
    navController: NavController,
    navigateToReadyWorkSelection: () -> Unit,
    navigateReadyWorkToWorkGraph: () -> Unit,
    selectedRoutineSetIdList: Set<Int>?,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkChangeOrderViewModel = hiltViewModel(),
) {
    val routineSetRoutine by viewModel.routineSetRoutine.collectAsStateWithLifecycle()

    //TODO Implement DraggableState
//    val dragAndDropState= remember { mutableStateOf(DraggableState()) }
    ReadyWorkChangeOrderScreen(
        modifier = modifier,
        onBackClickTopBar = navigateToReadyWorkSelection,
        onClickStartWork = {
            viewModel.createWork()
            navigateReadyWorkToWorkGraph()
        },
        routineSetRoutine = routineSetRoutine,
        onDeleteRoutineSetRoutineList = viewModel.deleteRoutineSetIdList()
    )

    LaunchedEffect(key1 = true) {
        viewModel.updateSelectedRoutineSetIdList(
            selectedRoutineSetIdList ?: emptySet<Int>()
        )
    }

}

@ExperimentalMaterial3Api
@Composable
fun ReadyWorkChangeOrderScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickStartWork: () -> Unit,
    onDeleteRoutineSetRoutineList: (Int) -> Unit,
    routineSetRoutine: RoutineSetRoutineUiState,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 선택",
                onBackClickTopBar = onBackClickTopBar
            )
        },
        floatingActionButton = {
            LiftButton(
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    ),
            ) {
                Text(
                    text = "운동시작하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                    modifier = modifier
                        .align(CenterVertically)
                        .fillMaxHeight()
                        .width(8.dp)
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "운동순서",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = modifier.padding(8.dp))
            when (routineSetRoutine) {
                RoutineSetRoutineUiState.Empty -> {

                }
                is RoutineSetRoutineUiState.Fail -> {

                }
                RoutineSetRoutineUiState.Loading -> {
                    LoadingRoutineListView()
                }
                is RoutineSetRoutineUiState.Success -> {
                    RoutineListView(
                        modifier = modifier,
                        routineSetRoutine = routineSetRoutine.routineSetRoutine,
                        onDeleteRoutineSetRoutineList = onDeleteRoutineSetRoutineList
                    )

                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ReadyWorkChangeOrderScreenPreview() {
    LiftMaterialTheme {
        ReadyWorkChangeOrderScreen(
            modifier = Modifier,
            onBackClickTopBar = { true },
            onClickStartWork = {},
            onDeleteRoutineSetRoutineList = {},
            routineSetRoutine =
//            RoutineSetRoutineUiState.Success(
//                routineSetRoutine = routineSetRoutineModelList
//            ),
            RoutineSetRoutineUiState.Loading
        )
    }
}