package com.gradation.lift.feature.updateRoutine.updateWorkSet.ui.component.exerciseGuide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.work.WorkCategory

@Composable
fun ExerciseGuideView(
    modifier: Modifier = Modifier,
    workCategory: WorkCategory,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no5),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        item { ExerciseGuideHeaderView(modifier, workCategory) }
        item { ExerciseSequenceView(modifier, workCategory) }
        item { ExerciseGuideEffectView(modifier, workCategory) }
        item { ExerciseCautionView(modifier, workCategory) }
        item { Spacer(modifier = modifier) }
    }
}