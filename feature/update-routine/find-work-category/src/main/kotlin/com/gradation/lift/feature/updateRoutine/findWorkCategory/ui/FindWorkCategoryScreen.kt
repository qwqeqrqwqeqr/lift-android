package com.gradation.lift.feature.updateRoutine.findWorkCategory.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FilterState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.FindWorkCategoryScreenState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.state.WorkCategoryUiState
import com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component.FilterCountView
import com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component.FilterView
import com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component.SearchView
import com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component.WorkCategoryView
import com.gradation.lift.model.model.work.WorkPart


@Composable
internal fun FindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    searchFilterText: String,
    workPartFilter: Set<WorkPart>,
    workPartList: List<WorkPart>,
    workCategoryUiState: WorkCategoryUiState,
    filterState: FilterState,
    navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: (Int) -> Unit,
    findWorkCategoryScreenState: FindWorkCategoryScreenState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "운동부위 선택",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph
            )
        }, modifier = modifier
    ) { paddingValues ->
        when (workCategoryUiState) {
            is WorkCategoryUiState.Fail -> Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
            )

            WorkCategoryUiState.Loading -> Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
            )

            is WorkCategoryUiState.Success -> {
                Column(modifier = modifier.padding(paddingValues)) {
                    AnimatedVisibility(findWorkCategoryScreenState.searchSortFilterView) {
                        Column(
                            modifier = modifier
                                .background(LiftTheme.colorScheme.no5)
                                .padding(vertical = LiftTheme.space.space16,),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                        ) {
                            Column(
                                modifier = modifier,
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                            ) {
                                SearchView(modifier, searchFilterText, filterState,findWorkCategoryScreenState)
                                FilterView(modifier, workPartFilter, workPartList, filterState)
                            }
                            FilterCountView(modifier, workCategoryUiState.workCategoryList)
                        }
                    }
                    WorkCategoryView(
                        modifier,
                        workCategoryUiState.workCategoryList,
                        navigateFindWorkCategoryToRoutineInUpdateRoutineGraph,
                        findWorkCategoryScreenState,
                    )
                }
            }
        }
    }
}


