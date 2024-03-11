package com.gradation.lift.feature.createRotuine.updateWorkSet.ui.component.exerciseGuide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun ExerciseGuideEffectView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {

        LiftText(
            textStyle = LiftTextStyle.No2,
            text = "운동효과",
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Start
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            workCategory.effect.forEach { effect ->
                LiftSecondaryContainer(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalPadding = LiftTheme.space.space8,
                    horizontalPadding = LiftTheme.space.space8,
                    shape = RoundedCornerShape(LiftTheme.space.space6)
                ) {
                    Column(
                        modifier = modifier.padding(start = LiftTheme.space.space4),
                        verticalArrangement = Arrangement.spacedBy(
                            LiftTheme.space.space4
                        )
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = effect.title,
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                        LiftText(
                            textStyle = LiftTextStyle.No6,
                            text = effect.content,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}