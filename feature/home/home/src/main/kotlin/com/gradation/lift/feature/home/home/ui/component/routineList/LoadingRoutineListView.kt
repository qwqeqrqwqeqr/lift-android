package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LoadingRoutineListView(
    modifier: Modifier = Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
    ) {
        RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph)
        Row(
            modifier = modifier.padding(horizontal = LiftTheme.space.space20),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            repeat(3) {
                Spacer(
                    modifier = modifier
                        .height(LiftTheme.space.space96)
                        .width(LiftTheme.space.space140)
                        .background(
                            SkeletonBrush(),
                            RoundedCornerShape(LiftTheme.space.space12)
                        )
                )
            }
        }
        Column(
            modifier = modifier.padding(
                bottom = LiftTheme.space.space16,
                start = LiftTheme.space.space20,
                end = LiftTheme.space.space20
            )
        ) {
            LiftSolidButton(
                modifier = modifier

                    .fillMaxWidth(),
                text = "루틴 추가하기",
                onClick = navigateMainGraphToCreateRoutineGraph,
            )
        }
    }

}
