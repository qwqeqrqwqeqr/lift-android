package com.gradation.lift.feature.workReady.routineSelection.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.smallButton.LiftLabelFilterSmallButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftSortFilterSmallButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftWeekdayFilterSmallButton
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftSearchInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.model.RecentUsedRoutineSetRoutine
import com.gradation.lift.feature.workReady.routineSelection.data.model.SortType
import com.gradation.lift.feature.workReady.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.workReady.routineSelection.data.state.SortFilterState
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.ui.extensions.isScrollingUp
import com.gradation.lift.ui.modifier.noRippleClickable

/**
 * 검색 및 정렬 화면 화면
 * @since 2023-12-03 22:48:19
 */
@Composable
internal fun SearchSortFilterView(
    modifier: Modifier = Modifier,
    sortFilterState: SortFilterState,
    searchFilterText: String,
    routineSetRoutineList: List<RecentUsedRoutineSetRoutine>,
    weekdayFilterType: WeekdayFilterType,
    labelFilterType: LabelFilterType,
    sortType: SortType,
    routineListScreenState: RoutineListScreenState,
) {
    Column(
        modifier = modifier.padding(
            horizontal = LiftTheme.space.space20,
            vertical = LiftTheme.space.space16
        ),
    ) {
        AnimatedVisibility(
            routineListScreenState.lazyListState.isScrollingUp(),
            enter = expandVertically(spring(stiffness = Spring.StiffnessMediumLow)),
            exit = shrinkVertically(tween(500)),
        ) {
            Column {
                SearchView(modifier, sortFilterState, searchFilterText, routineListScreenState)
                Spacer(modifier = modifier.height(LiftTheme.space.space16))
            }
        }
        SortFilterView(
            modifier,
            routineSetRoutineList,
            weekdayFilterType,
            labelFilterType,
            sortType,
            routineListScreenState
        )
    }
}


@Composable
internal fun SearchView(
    modifier: Modifier = Modifier,
    sortFilterState: SortFilterState,
    searchFilterText: String,
    routineListScreenState: RoutineListScreenState,
) {
    LiftSearchInputTextField(
        modifier = modifier,
        value = searchFilterText,
        placeHolderValue = "찾으시는 운동을 검색해주세요",
        onValueChange = sortFilterState.updateSearchFilterText,
        onValueClear = sortFilterState.clearSearchFilterText,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { routineListScreenState.focusManager.clearFocus() }
        )
    )
}


@Composable
internal fun SortFilterView(
    modifier: Modifier = Modifier,
    routineSetRoutineList: List<RecentUsedRoutineSetRoutine>,
    weekdayFilterType: WeekdayFilterType,
    labelFilterType: LabelFilterType,
    sortType: SortType,
    routineListScreenState: RoutineListScreenState,
) {
    Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
        LiftMultiStyleText(
            defaultColor = LiftTheme.colorScheme.no9,
            defaultTextStyle = LiftTextStyle.No6,
            textAlign = TextAlign.Start,
            textWithStyleList = listOf(
                TextWithStyle(text = "총 "),
                TextWithStyle(text = "${routineSetRoutineList.size}", style = LiftTextStyle.No5),
                TextWithStyle(text = "개의 루틴")
            )
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
            item {
                LiftSortFilterSmallButton(
                    modifier = modifier.noRippleClickable {
                        routineListScreenState.updateSortTypeBottomSheetView(
                            true
                        )
                    },
                    sortType = sortType.getName()
                )
            }
            item {
                LiftWeekdayFilterSmallButton(
                    modifier = modifier.noRippleClickable {
                        routineListScreenState.updateWeekdayFilterTypeBottomSheetView(
                            true
                        )
                    },
                    weekdayType = if (weekdayFilterType.isCheckedAllWeekday()) "전체"
                    else weekdayFilterType.getNameList().joinToString(","),
                )
            }
            item {
                LiftLabelFilterSmallButton(
                    modifier = modifier.noRippleClickable {
                        routineListScreenState.updateLabelFilterTypeBottomSheetView(true)
                    },
                    labelType = labelFilterType.getIdList().toSet(),
                    selectedAll = labelFilterType.getIdList().toSet().size == Label.entries.size
                )
            }
        }
    }
}