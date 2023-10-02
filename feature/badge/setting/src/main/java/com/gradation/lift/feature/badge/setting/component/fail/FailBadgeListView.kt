package com.gradation.lift.feature.badge.setting.component.fail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun FailBadgeListView(modifier:Modifier=Modifier){
    Column(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "획득한 뱃지",
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no3
        )
    }
}