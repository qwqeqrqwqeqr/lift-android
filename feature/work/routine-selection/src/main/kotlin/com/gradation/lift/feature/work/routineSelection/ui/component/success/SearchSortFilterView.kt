package com.gradation.lift.feature.work.routineSelection.ui.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.LiftSearchTextField
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.work.routineSelection.data.model.SortType
import com.gradation.lift.feature.work.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.work.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.work.routineSelection.data.state.SortFilterState
import com.gradation.lift.model.model.routine.RoutineSetRoutine

/**
 * 검색 및 정렬 화면 화면
 * @since 2023-12-03 22:48:19
 */
@Composable
internal fun SearchSortFilterView(
    modifier: Modifier = Modifier,
    sortFilterState: SortFilterState,
    searchFilterText: String,
    routineSetRoutineList: List<RoutineSetRoutine>,
    weekdayFilterType: WeekdayFilterType,
    labelFilterType: LabelFilterType,
    sortType: SortType,
    routineListScreenState: RoutineListScreenState

){
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
) {
    Column {
        LiftSearchTextField(
            modifier = modifier.fillMaxWidth(),
            value = searchFilterText,
            onValueChange = sortFilterState.updateSearchFilterText,
            placeholder = {
                Text(
                    text = "찾으시는 운동을 검색해주세요",
                    color = LiftTheme.colorScheme.no2,
                    style = LiftTheme.typography.no6,
                )
            }
        )
    }
}


@Composable
internal fun SortFilterView(
    modifier: Modifier = Modifier,
    routineSetRoutineList: List<RoutineSetRoutine>,
    weekdayFilterType: WeekdayFilterType,
    labelFilterType: LabelFilterType,
    sortType: SortType,
    routineListScreenState: RoutineListScreenState

) {
    Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {

        Text(
            text = buildAnnotatedString {
                append("총 ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold),
                ) {
                    append("${routineSetRoutineList.size}개")
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
}