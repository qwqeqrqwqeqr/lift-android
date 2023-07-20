package com.gradation.lift.feature.ready_work.change_order.component.routine_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.ready_work.change_order.ReadyWorkChangeOrderScreen
import com.gradation.lift.feature.ready_work.change_order.data.RoutineSetRoutineUiState

@Composable
fun LoadingRoutineListView(
    modifier: Modifier = Modifier,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        repeat(5) {
            Box(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(96.dp),
            )
        }
    }
}
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LoadingRoutineListViewPreview() {
    LiftMaterialTheme {
        ReadyWorkChangeOrderScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            onClickStartWork = {},
            onDeleteRoutineSetRoutineList = {},
            routineSetRoutine =

            RoutineSetRoutineUiState.Loading
        )
    }
}