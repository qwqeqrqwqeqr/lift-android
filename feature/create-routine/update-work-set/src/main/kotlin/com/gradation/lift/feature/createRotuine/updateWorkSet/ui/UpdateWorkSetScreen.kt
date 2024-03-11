package com.gradation.lift.feature.createRotuine.updateWorkSet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.tab.LiftDoubleTab
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState.Companion.CREATE_ROUTINE
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.RoutineScreenState.Companion.EXERCISE_GUIDE
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkCategoryUiState
import com.gradation.lift.feature.createRotuine.updateWorkSet.data.state.WorkSetState
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.NavigationView
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.WorkSetView
import com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.exerciseGuide.ExerciseGuideView
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.ui.extensions.focusClearManager
import com.gradation.lift.ui.modifier.noRippleClickable


@Composable
internal fun UpdateWorkSetScreen(
    modifier: Modifier = Modifier,
    workCategoryFavoriteFlag: Boolean,
    updateWorkCategoryFavorite: () -> Unit,
    routineIndex: Int?,
    workSetState: WorkSetState,
    workCategoryUiState: WorkCategoryUiState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph: () -> Unit,
    routineScreenState: RoutineScreenState,
) {
    when (workCategoryUiState) {
        is WorkCategoryUiState.Fail -> {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
            )
        }

        WorkCategoryUiState.Loading -> {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
            )
        }

        is WorkCategoryUiState.Success -> {
            Scaffold(
                topBar = {
                    LiftTopBar(
                        title = workCategoryUiState.workCategory.name,
                        backgroundColor = LiftTheme.colorScheme.no5,
                        onClick = navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph,
                        actions = {
                            LiftIconBox(
                                modifier = modifier.noRippleClickable { updateWorkCategoryFavorite() },
                                icon = if (workCategoryFavoriteFlag) LiftIcon.HeartFilled else LiftIcon.HeartEmpty,
                                iconType = IconType.Painter, iconBoxSize = IconBoxSize.Size24
                            )
                        }
                    )
                },
                bottomBar = {
                    NavigationView(
                        modifier,
                        workCategoryUiState.workCategory,
                        routineIndex,
                        workSetState,
                        currentRoutineSetRoutineState,
                        navigateUpdateWorkSetToRoutineSetInCreateRoutineGraph
                    )
                },
                modifier = modifier
            ) { padding ->
                Column(
                    modifier = modifier
                        .focusClearManager(routineScreenState.focusManager)
                        .background(LiftTheme.colorScheme.no5)
                        .padding(padding)
                        .padding(top = LiftTheme.space.space20),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = LiftTheme.space.space20)
                    ) {
                        LiftDoubleTab(
                            valueList = routineScreenState.tabTitleList,
                            selectedIndex = routineScreenState.selectedTabIndex.value,
                            updateSelected = routineScreenState.updateSelectedTabIndex
                        )
                    }
                    when (routineScreenState.selectedTabIndex.value) {
                        CREATE_ROUTINE -> {
                            WorkSetView(
                                modifier,
                                workSetState,
                                routineScreenState
                            )
                        }

                        EXERCISE_GUIDE -> {
                            ExerciseGuideView(
                                modifier,
                                workCategoryUiState.workCategory
                            )
                        }
                    }

                }
            }
        }
    }
}
