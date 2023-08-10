package com.gradation.lift.feature.work.routine_selection.component.routine_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.work.routine_selection.WorkRoutineSelectionScreen
import com.gradation.lift.feature.work.routine_selection.data.RoutineSetRoutineSelectionUiState
import com.gradation.lift.feature.work.routine_selection.data.WeekdayCard
import com.gradation.lift.model.model.common.Weekday

@Composable
fun LoadingRoutineSetRoutineListView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.CenterVertically
        ),
    ) {
        repeat(3) {
            Box(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(96.dp),
            )
            Box(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(32.dp),
            )
            Box(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(16.dp),
            )
            Box(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(16.dp),
            )


        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun LoadingRoutineListViewPreview() {
    LiftMaterialTheme {
        WorkRoutineSelectionScreen(
            modifier = Modifier,
            weekday = listOf(
                WeekdayCard(weekday = Weekday.Monday()),
                WeekdayCard(weekday = Weekday.Tuesday()),
                WeekdayCard(weekday = Weekday.Wednesday()),
                WeekdayCard(weekday = Weekday.Thursday()),
                WeekdayCard(weekday = Weekday.Friday()),
                WeekdayCard(weekday = Weekday.Saturday()),
                WeekdayCard(weekday = Weekday.Sunday(), selected = true)
            ),
            routineSetRoutineSelection = RoutineSetRoutineSelectionUiState.Loading,
            onBackClickTopBar = {},
            onClickWeekDayCard = {},
            onClickStartWork = {},
            onUpdateRoutineSetRoutineList = { _, _ -> },
            onUpdateRoutineList = { _, _ -> },
            selectedRoutineCount = 2
        )

    }
}