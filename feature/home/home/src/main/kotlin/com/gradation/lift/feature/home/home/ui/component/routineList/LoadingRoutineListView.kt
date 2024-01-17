package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.ui.component.RoutineListHeaderView


fun LazyListScope.loadingRoutineListView(
    modifier: Modifier = Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
) {
    item {
        RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph)

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
        ) {
            repeat(3) {
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(LiftTheme.space.space96)
                        .background(
                            SkeletonBrush(),
                            RoundedCornerShape(LiftTheme.space.space12)
                        )
                )
            }
        }
    }
}
