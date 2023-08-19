package com.gradation.lift.feature.home.component.weekdate_routine_view.routine_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush

@Composable
internal fun LoadingRoutineListView(modifier:Modifier=Modifier){
    Column {
        Spacer(modifier = modifier.padding(8.dp))
        Box(
            modifier
                .background(
                    SkeletonBrush(),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(96.dp),
        )
        Spacer(modifier = modifier.padding(8.dp))

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
