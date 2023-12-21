package com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routineDetail.routineList.data.model.SortType
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListScreenState
import com.gradation.lift.feature.routineDetail.routineList.data.state.SortFilterState

/**
 * 정렬 바텀 시트
 * @since 2023-12-03 22:47:17
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SortBottomSheet(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    sortFilterState: SortFilterState,
    sortType: SortType
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { routineListScreenState.updateSortTypeBottomSheetView(false) },
        dragHandle = null,
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Image(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.search_3d),
                        contentDescription = "filterSearch"
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("어떻게 ")
                            withStyle(
                                style = SpanStyle(color = LiftTheme.colorScheme.no4),
                            ) {
                                append("정렬")
                            }
                            append("할까요?")
                        },
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9
                    )
                }
                IconButton(
                    modifier = modifier.size(16.dp),
                    onClick = { routineListScreenState.updateSortTypeBottomSheetView(false) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "",
                        tint = LiftTheme.colorScheme.no9,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        sortFilterState.updateSortType(SortType.Name)
                        routineListScreenState.updateSortTypeBottomSheetView(false)
                    },
                    contentColor = if (sortType is SortType.Name) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                    containerColor = if (sortType is SortType.Name) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1
                ) {
                    Text(
                        text = SortType.Name.getName(),
                        style = if (sortType is SortType.Name) LiftTheme.typography.no3 else LiftTheme.typography.no4,

                        )
                }
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        sortFilterState.updateSortType(SortType.Count)
                        routineListScreenState.updateSortTypeBottomSheetView(false)
                    },
                    contentColor = if (sortType is SortType.Count) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                    containerColor = if (sortType is SortType.Count) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no1
                ) {
                    Text(
                        text = SortType.Count.getName(),
                        style = if (sortType is SortType.Count) LiftTheme.typography.no3 else LiftTheme.typography.no4,

                        )
                }


            }
        }
    }
}

