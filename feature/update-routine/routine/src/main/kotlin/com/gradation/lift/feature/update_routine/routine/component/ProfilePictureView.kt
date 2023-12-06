package com.gradation.lift.feature.update_routine.routine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ProfilePictureView(
    modifier: Modifier=Modifier
){
    Column(
        modifier= modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .align(alignment = Alignment.CenterHorizontally)
                .size(128.dp)
        )
    }
}