package com.gradation.lift.createRoutine.profilePicture.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LoadingRoutineProfilePictureList(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(LiftTheme.space.paddingSpace)
            .background(LiftTheme.colorScheme.no5)
    ) {
        Box(
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(size = 12.dp))
                .background(SkeletonBrush())
                .size(96.dp),
        )
        repeat(5) {
            Spacer(modifier = modifier.padding(16.dp))
            Box(
                modifier = modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(size = 12.dp))
                    .background(SkeletonBrush())
                    .height(96.dp)
                    .fillMaxWidth(),
            )
        }
    }
}

