package com.gradation.lift.feature.updateRoutine.findWorkCategory.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.gradation.lift.ui.extensions.focusClearManager
import com.gradation.lift.ui.extensions.isScrollingUp


@Composable
internal fun FindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    searchFilterText: String,
    workPartFilter: Set<WorkPart>,
    workPartList: List<WorkPart>,
    workCategoryUiState: WorkCategoryUiState,
    filterState: FilterState,
    navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToCreateWorkSetInUpdateRoutineGraph: (Int) -> Unit,
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
                    .focusClearManager(findWorkCategoryScreenState.focusManager)
                    .background(LiftTheme.colorScheme.no17)
            )

            is WorkCategoryUiState.Success -> {
                Column(modifier = modifier.padding(paddingValues)) {
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .padding(vertical = LiftTheme.space.space16),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                    ) {
                        Column(
                            modifier = modifier,
                        ) {
                            AnimatedVisibility(
                                visible = findWorkCategoryScreenState.lazyListState.isScrollingUp(),
                                enter = expandVertically(spring(stiffness = Spring.StiffnessMediumLow)),
                                exit = shrinkVertically(tween(500)),
                            ) {
                                Column {
                                    SearchView(
                                        modifier,
                                        searchFilterText,
                                        filterState,
                                        findWorkCategoryScreenState
                                    )

                                    Spacer(modifier = modifier.height(LiftTheme.space.space16))
                                }
                            }
                            FilterView(modifier, workPartFilter, workPartList, filterState)
                        }
                        FilterCountView(modifier, workCategoryUiState.workCategoryList)
                    }
                    WorkCategoryView(
                        modifier,
                        workCategoryUiState.workCategoryList,
                        navigateFindWorkCategoryToCreateWorkSetInUpdateRoutineGraph,
                        findWorkCategoryScreenState,
                    )
                }
            }
        }
    }
}


