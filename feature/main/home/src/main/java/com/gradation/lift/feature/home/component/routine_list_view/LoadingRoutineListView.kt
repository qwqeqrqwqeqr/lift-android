package com.gradation.lift.feature.home.component.routine_list_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush

@Composable
fun LoadingRoutineListView(modifier:Modifier=Modifier){
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