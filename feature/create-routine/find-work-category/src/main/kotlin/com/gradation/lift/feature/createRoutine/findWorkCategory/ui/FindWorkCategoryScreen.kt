package com.gradation.lift.feature.createRoutine.findWorkCategory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.state.WorkCategoryUiState
import com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component.FilterCountView
import com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component.FilterView
import com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component.SearchView
import com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component.WorkCategoryView
import com.gradation.lift.model.model.work.WorkPart


@Composable
internal fun FindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    searchFilterText: String,
    workPartFilter: Set<WorkPart>,
    workPartList: List<WorkPart>,
    workCategoryUiState: WorkCategoryUiState,
    filterState: FilterState,
    navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToRoutineInCreateRoutineGraph: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동부위 선택",
                onBackClickTopBar = navigateFindWorkCategoryToRoutineSetInCreateRoutineGraph,
            )
        }, modifier = modifier
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {

            when (workCategoryUiState) {
                is WorkCategoryUiState.Fail -> {}
                WorkCategoryUiState.Loading -> {}
                is WorkCategoryUiState.Success -> {
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .padding(LiftTheme.space.paddingSpace),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                    ) {
                        SearchView(modifier, searchFilterText, filterState)
                        FilterView(modifier, workPartFilter, workPartList, filterState)
                        FilterCountView(modifier, workCategoryUiState.workCategoryList)
                    }
                    WorkCategoryView(
                        modifier.fillMaxSize(),
                        workCategoryUiState.workCategoryList,
                        navigateFindWorkCategoryToRoutineInCreateRoutineGraph
                    )
                }
            }
        }
    }
}

