package com.gradation.lift.feature.work.routine_selection.component.routine_list_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LoadingRoutineSetRoutineListView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier=modifier.fillMaxSize().background(LiftTheme.colorScheme.no5),
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