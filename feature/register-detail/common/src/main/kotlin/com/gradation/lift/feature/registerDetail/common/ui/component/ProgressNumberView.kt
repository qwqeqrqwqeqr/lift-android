package com.gradation.lift.feature.registerDetail.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.progress.LiftProgressCircleLabel
import com.gradation.lift.designsystem.component.progress.ProgressCircleState
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun ProgressNumberView(
    modifier: Modifier = Modifier,
    current: Int,
    total: Int,
    navigateToNameInRegisterDetailGraph: () -> Unit={},
    navigateToGenderInRegisterDetailGraph: () -> Unit={},
    navigateToHeightWeightInRegisterDetailGraph: () -> Unit={},
    navigateToProfilePictureInRegisterDetailGraph: () -> Unit={},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space12,
            Alignment.CenterHorizontally
        )
    ) {
        repeat(total) {
            LiftProgressCircleLabel(
                modifier = modifier.noRippleClickable {
                    if (it + 1 < current) {
                        when (it + 1) {
                            1 -> navigateToNameInRegisterDetailGraph()
                            2 -> navigateToGenderInRegisterDetailGraph()
                            3 -> navigateToHeightWeightInRegisterDetailGraph()
                            else -> navigateToProfilePictureInRegisterDetailGraph()
                        }
                    }
                },
                number = it + 1, state =
                if (it + 1 == current) ProgressCircleState.Current
                else if (it + 1 > current) ProgressCircleState.None
                else ProgressCircleState.Done
            )
        }
    }
}

@Composable
@Preview
fun ProgressNumberViewPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            ProgressNumberView(
                modifier = modifier,
                current = 2,
                total = 4,
                {}, {}, {}, {}
            )
        }
    }
}