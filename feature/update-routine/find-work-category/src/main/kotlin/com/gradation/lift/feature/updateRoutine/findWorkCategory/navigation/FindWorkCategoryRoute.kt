package com.gradation.lift.feature.updateRoutine.findWorkCategory.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FindWorkCategoryScreenState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.rememberFindWorkCategoryScreenState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.viewmodel.FindWorkCategoryViewModel
import com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.FindWorkCategoryScreen
import com.gradation.lift.model.model.work.WorkPart

@Composable
internal fun FindWorkCategoryRoute(
    modifier: Modifier = Modifier,
    navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: (Int) -> Unit,
    viewModel: FindWorkCategoryViewModel = hiltViewModel(),
    findWorkCategoryScreenState: FindWorkCategoryScreenState = rememberFindWorkCategoryScreenState(),
) {


    val searchFilterText: String by viewModel.filterState.searchFilterText.collectAsStateWithLifecycle()
    val workPartFilter: Set<WorkPart> by viewModel.filterState.workPartFilter.collectAsStateWithLifecycle()
    val workPartList: List<WorkPart> by viewModel.workPartList.collectAsStateWithLifecycle()
    val workCategoryUiState: WorkCategoryUiState by viewModel.workCategoryUiState.collectAsStateWithLifecycle()
    val filterState: FilterState = viewModel.filterState



    FindWorkCategoryScreen(
        modifier,
        searchFilterText,
        workPartFilter,
        workPartList,
        workCategoryUiState,
        filterState,
        navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph,
        navigateFindWorkCategoryToRoutineInUpdateRoutineGraph,
        findWorkCategoryScreenState
    )

    BackHandler(onBack = navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph)
}