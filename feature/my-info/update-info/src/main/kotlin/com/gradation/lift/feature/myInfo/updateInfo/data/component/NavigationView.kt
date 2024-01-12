package com.gradation.lift.feature.my_info.update.data.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun UpdateView(
    modifier: Modifier = Modifier,
    updateCondition: Boolean,
    updateUserDetail: () -> Unit,
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = updateUserDetail,
        enabled = updateCondition
    ) {
        Text(
            text = "저장하기",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}