package com.gradation.lift.feature.routine_detail.routine_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListScreenState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.SearchView
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet.LabelFilterBottomSheet
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet.SortBottomSheet
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet.WeekdayFilterBottomSheet


@Composable
internal fun RoutineListScreen(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    routineListInfoState: RoutineListInfoState,
    routineSetRoutineList: RoutineDetailRoutineListUiState,
    sortFilterState: SortFilterState,
    labelFilterType: LabelFilterType,
    weekdayFilterType: WeekdayFilterType,
    searchFilterText: String,
    sortType: SortType
) {
    if (routineListScreenState.sortTypeBottomSheetView) {
        SortBottomSheet(modifier, routineListScreenState, sortFilterState, sortType)
    }
    if (routineListScreenState.weekdayFilterTypeBottomSheetView) {
        WeekdayFilterBottomSheet(
            modifier,
            routineListScreenState,
            sortFilterState,
            weekdayFilterType
        )
    }
    if (routineListScreenState.labelFilterTypeBottomSheetView) {
        LabelFilterBottomSheet(
            modifier,
            routineListScreenState,
            sortFilterState,
            labelFilterType
        )
    }
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내 루틴",
                onBackClickTopBar = {},
            )
        }
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no17,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (routineSetRoutineList) {
                RoutineDetailRoutineListUiState.Empty -> {

                }

                is RoutineDetailRoutineListUiState.Fail -> {

                }

                RoutineDetailRoutineListUiState.Loading -> {

                }

                is RoutineDetailRoutineListUiState.Success -> {
                    Column {
                        Column(
                            modifier = modifier
                                .background(LiftTheme.colorScheme.no5)
                                .padding(
                                    horizontal = LiftTheme.space.horizontalPaddingSpace,
                                    vertical = LiftTheme.space.verticalPaddingSpace
                                ),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                        ) {
                            SearchView(modifier, sortFilterState, searchFilterText)
                            Text(
                                text = buildAnnotatedString {
                                    append("총 ")
                                    withStyle(
                                        style = SpanStyle(fontWeight = FontWeight.Bold),
                                    ) {
                                        append("${routineSetRoutineList.routineSetRoutineList.size}개")
                                    }
                                    append("의 루틴")
                                },
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                                LiftOutlineButton(
                                    modifier = modifier.height(28.dp),
                                    contentPadding = PaddingValues(
                                        start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
                                    ),
                                    shape = RoundedCornerShape(6.dp),
                                    onClick = {
                                        routineListScreenState.updateSortTypeBottomSheetView(
                                            true
                                        )
                                    }) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = "정렬", color = LiftTheme.colorScheme.no14,
                                            style = LiftTheme.typography.no7
                                        )
                                        VerticalDivider(
                                            modifier = modifier
                                                .height(14.dp)
                                                .width(1.dp),
                                            color = LiftTheme.colorScheme.no4

                                        )
                                        Text(
                                            text = sortType.getName(),
                                            style = LiftTheme.typography.no8
                                        )
                                        Icon(
                                            painter = painterResource(id = LiftIcon.DropDown),
                                            contentDescription = "sortIcon"
                                        )
                                    }
                                }
                                LiftOutlineButton(
                                    modifier = modifier.height(28.dp),
                                    contentPadding = PaddingValues(
                                        start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
                                    ),
                                    shape = RoundedCornerShape(6.dp),
                                    onClick = {
                                        routineListScreenState.updateWeekdayFilterTypeBottomSheetView(
                                            true
                                        )
                                    }) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = "요일", color = LiftTheme.colorScheme.no14,
                                            style = LiftTheme.typography.no7
                                        )
                                        VerticalDivider(
                                            modifier = modifier
                                                .height(14.dp)
                                                .width(1.dp),
                                            color = LiftTheme.colorScheme.no4

                                        )
                                        Text(
                                            text = if (weekdayFilterType.isCheckedAllWeekday()) "전체"
                                            else weekdayFilterType.getNameList().joinToString(","),
                                            style = LiftTheme.typography.no8
                                        )
                                        Icon(
                                            painter = painterResource(id = LiftIcon.DropDown),
                                            contentDescription = "sortIcon"
                                        )
                                    }
                                }

                                LiftOutlineButton(
                                    modifier = modifier.height(28.dp),
                                    contentPadding = PaddingValues(
                                        start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp
                                    ),
                                    shape = RoundedCornerShape(6.dp),
                                    onClick = {
                                        routineListScreenState.updateLabelFilterTypeBottomSheetView(
                                            true
                                        )
                                    }) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = "라벨", color = LiftTheme.colorScheme.no14,
                                            style = LiftTheme.typography.no8
                                        )
                                        VerticalDivider(
                                            modifier = modifier
                                                .height(14.dp)
                                                .width(1.dp),
                                            color = LiftTheme.colorScheme.no4

                                        )
                                        if (labelFilterType.isCheckedAllLabel()) {
                                            Text(
                                                text = "전체",
                                                style = LiftTheme.typography.no8
                                            )
                                        } else {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    LiftTheme.space.space2
                                                )
                                            ) {
                                                labelFilterType.getIdList().forEach {
                                                    RoutineLabel(
                                                        modifier = modifier.size(LiftTheme.space.space12),
                                                        id = it
                                                    )
                                                }
                                            }
                                        }

                                        Icon(
                                            painter = painterResource(id = LiftIcon.DropDown),
                                            contentDescription = "sortIcon"
                                        )
                                    }
                                }

                            }
                        }
                        Column(modifier = modifier.padding(horizontal = LiftTheme.space.horizontalPaddingSpace)) {
                        }
                    }
                }
            }

        }
    }
}




