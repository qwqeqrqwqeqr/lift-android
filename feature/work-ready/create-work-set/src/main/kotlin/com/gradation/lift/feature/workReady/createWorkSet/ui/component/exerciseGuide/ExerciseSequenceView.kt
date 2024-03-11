package com.gradation.lift.feature.workReady.createWorkSet.ui.component.exerciseGuide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.progress.LiftExerciseSequenceCircleLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun ExerciseSequenceView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftText(
            textStyle = LiftTextStyle.No2,
            text = "운동 방법",
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Start
        )
        Column(modifier = modifier) {
            workCategory.sequence.forEach { sequence ->
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = LiftTheme.space.space8),
                    horizontalArrangement = Arrangement.spacedBy(
                        LiftTheme.space.space16
                    )
                ) {
                    Column(
                        modifier = modifier
                            .width(LiftTheme.space.space32)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        LiftExerciseSequenceCircleLabel(
                            modifier = modifier,
                            number = sequence.sequence
                        )
                        Spacer(
                            modifier = modifier
                                .height(LiftTheme.space.space16)
                                .width(LiftTheme.space.space2)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            LiftTheme.colorScheme.no17,
                                            Color.Transparent
                                        )
                                    )
                                ),
                        )

                    }
                    LiftSecondaryContainer(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = LiftTheme.space.space8),
                        verticalPadding = LiftTheme.space.space8,
                        horizontalPadding = LiftTheme.space.space8
                    ) {
                        Column(
                            modifier = modifier.padding(start = LiftTheme.space.space4),
                            verticalArrangement = Arrangement.spacedBy(
                                LiftTheme.space.space4
                            )
                        ) {
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = sequence.title,
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                            LiftText(
                                textStyle = LiftTextStyle.No6,
                                text = sequence.content,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }
    }
}