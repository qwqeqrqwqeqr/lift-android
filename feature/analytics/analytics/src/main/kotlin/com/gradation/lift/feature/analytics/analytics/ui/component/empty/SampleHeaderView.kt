package com.gradation.lift.feature.analytics.analytics.ui.component.empty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun SampleHeaderView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = LiftTheme.space.space20,
            end = LiftTheme.space.space20,
            bottom = LiftTheme.space.space16
        )
    ) {
        LiftMultiStyleText(
            modifier,
            LiftTheme.colorScheme.no9,
            LiftTextStyle.No1,
            listOf(
                TextWithStyle(
                    text = "운동을 진행하고 ",
                    color = LiftTheme.colorScheme.no4,
                ),
                TextWithStyle(
                    text = "분석결과를 확인해봐요.",
                ),
            ),
            TextAlign.Start
        )
    }
}