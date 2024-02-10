package com.gradation.lift.feature.badge.setting.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.temp.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun ApplyView(
    modifier:Modifier=Modifier,
    buttonCondition: Boolean,
    updateUserBadgeMainFlag: () -> Unit,
){
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .padding(16.dp)
    ) {
        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = updateUserBadgeMainFlag,
            enabled = buttonCondition
        ) {
            Text(
                text = "적용하기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
    }
}