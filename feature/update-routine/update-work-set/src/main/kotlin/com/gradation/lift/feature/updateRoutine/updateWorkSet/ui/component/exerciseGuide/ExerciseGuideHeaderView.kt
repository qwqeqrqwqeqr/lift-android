package com.gradation.lift.feature.updateRoutine.updateWorkSet.ui.component.exerciseGuide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.label.WorkPartLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun ExerciseGuideHeaderView(
    modifier: Modifier,
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
            text = workCategory.introduce,
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Start
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSecondaryContainer(
                modifier = modifier.fillMaxWidth(),
                verticalPadding = LiftTheme.space.space12,
                horizontalPadding = LiftTheme.space.space12
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No6,
                    text = workCategory.description,
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space6
                )
            ) {
                workCategory.workPart.forEach { workPart ->
                    WorkPartLabel(modifier, workPart)
                }
            }
        }
    }
}