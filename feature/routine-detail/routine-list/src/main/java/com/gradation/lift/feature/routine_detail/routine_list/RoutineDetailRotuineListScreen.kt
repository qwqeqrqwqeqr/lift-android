package com.gradation.lift.feature.routine_detail.routine_list

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine_detail.routine_list.data.RoutineDetailRoutineListViewModel
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.ui.SearchView


@Composable
fun RoutineDetailRoutineListRoute(
    modifier: Modifier = Modifier,
    viewModel: RoutineDetailRoutineListViewModel = hiltViewModel(),
) {

    val sortFilterState: SortFilterState = viewModel.sortFilterState
    val routineListInfoState: RoutineListInfoState = viewModel.routineListInfoState
    val routineSetRoutineList: RoutineDetailRoutineListUiState by viewModel.routineSetRoutineList.collectAsStateWithLifecycle()


    val labelFilterType: LabelFilterType by sortFilterState.labelFilterType.collectAsStateWithLifecycle()
    val weekdayFilterType: WeekdayFilterType by sortFilterState.weekdayFilterType.collectAsStateWithLifecycle()
    val searchFilterText: String by sortFilterState.searchFilterText.collectAsStateWithLifecycle()
    val sortType: SortType by sortFilterState.sortType.collectAsStateWithLifecycle()






    RoutineDetailRoutineListScreen(
        modifier,
        routineListInfoState,
        routineSetRoutineList,
        sortFilterState,
        labelFilterType,
        weekdayFilterType,
        searchFilterText,
        sortType,
    )

}


@Composable
fun RoutineDetailRoutineListScreen(
    modifier: Modifier = Modifier,
    routineListInfoState: RoutineListInfoState,
    routineSetRoutineList: RoutineDetailRoutineListUiState,
    sortFilterState: SortFilterState,
    labelFilterType: LabelFilterType,
    weekdayFilterType: WeekdayFilterType,
    searchFilterText: String,
    sortType: SortType
) {
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
                                    onClick = { }) {
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
                                            text = sortFilterState.getSortTypeName,
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
                                    onClick = { }) {
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
                                            text = if (sortFilterState.getWeekdayFilterTypeNameList.isEmpty()) "전체"
                                            else sortFilterState.getWeekdayFilterTypeNameList.joinToString { " " },
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
                                    onClick = { }) {
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
                                        if (sortFilterState.getWeekdayFilterTypeNameList.isEmpty()) {
                                            Text(
                                                text = "전체",
                                                style = LiftTheme.typography.no7
                                            )
                                        } else {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    LiftTheme.space.space2
                                                )
                                            ) {
                                                sortFilterState.getLabelFilterTypeIdList.forEach {
                                                    RoutineLabel(id = it)
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




