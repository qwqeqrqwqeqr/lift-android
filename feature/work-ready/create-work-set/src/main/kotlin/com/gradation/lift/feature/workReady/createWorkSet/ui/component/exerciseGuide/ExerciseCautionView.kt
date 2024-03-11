package com.gradation.lift.feature.workReady.createWorkSet.ui.component.exerciseGuide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun ExerciseCautionView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftIconBox(
                icon = LiftIcon.Warn,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size20
            )
            LiftText(
                textStyle = LiftTextStyle.No2,
                text = "주의사항",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Start
            )
        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            workCategory.caution.forEach { caution ->
                LiftSecondaryContainer(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalPadding = LiftTheme.space.space8,
                    horizontalPadding = LiftTheme.space.space8,
                    shape = RoundedCornerShape(LiftTheme.space.space6)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No6,
                        text = caution,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}