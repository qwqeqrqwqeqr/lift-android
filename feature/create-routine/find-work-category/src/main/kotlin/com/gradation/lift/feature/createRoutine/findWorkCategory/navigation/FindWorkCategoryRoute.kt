package com.gradation.lift.feature.createRoutine.findWorkCategory.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.state.WorkCategoryUiState
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.viewmodel.FindWorkCategoryViewModel
import com.gradation.lift.feature.createRoutine.findWorkCategory.ui.FindWorkCategoryScreen
import com.gradation.lift.model.model.work.WorkPart

@Composable
internal fun FindWorkCategoryRoute(
    modifier: Modifier = Modifier,
    navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToRoutineInCreateRoutineGraph: (Int) -> Unit,
    viewModel: FindWorkCategoryViewModel = hiltViewModel(),
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
        navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph,
        navigateFindWorkCategoryToRoutineInCreateRoutineGraph
    )

    BackHandler(onBack = navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph)
}