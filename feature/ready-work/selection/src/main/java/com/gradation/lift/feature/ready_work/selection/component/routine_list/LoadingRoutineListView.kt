package com.gradation.lift.feature.ready_work.selection.component.routine_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush

@Composable
fun LoadingRoutineListView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.CenterVertically
        ),
    ) {
        repeat(3) {
            Card(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(96.dp),
            ) {}
            Card(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(32.dp),
            ) {}
            Card(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(16.dp),
            ) {}
            Card(
                modifier
                    .background(
                        SkeletonBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(16.dp),
            ) {}


        }
    }
}