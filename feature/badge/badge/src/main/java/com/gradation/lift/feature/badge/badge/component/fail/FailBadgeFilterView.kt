package com.gradation.lift.feature.badge.badge.component.fail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun FailBadgeFilterView(
    modifier: Modifier = Modifier,
) {
    Surface(
        color = LiftTheme.colorScheme.no17,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Spacer(modifier = modifier.height(18.dp))
        }
    }
}



