package com.gradation.lift.feature.register_detail.name.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.canvas.NumberCircle

@Composable
fun ProgressNumberView(
    modifier: Modifier = Modifier,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
){
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)) {
        repeat(totalRegisterProgressNumber) {
            NumberCircle(number = it + 1, checked = it + 1 == currentRegisterProgressNumber)
        }
    }
}