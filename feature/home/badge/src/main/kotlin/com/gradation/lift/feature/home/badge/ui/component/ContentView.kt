package com.gradation.lift.feature.home.badge.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.badge.Badge

@Composable
fun ContentView(
    modifier: Modifier = Modifier,
    badge: Badge,
    createUserBadge: (Int) -> Unit,
) {
    Column(
        modifier.padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LiftText(
            textStyle = LiftTextStyle.No2,
            text = badge.name,
            color = Color(android.graphics.Color.parseColor(badge.color)),
            textAlign = TextAlign.Center
        )
        Text(
            badge.description,
            textAlign = TextAlign.Center,
            style = LiftTheme.typography.no4,
            color = LiftTheme.colorScheme.no9
        )
        LiftSolidButton(
            modifier = modifier.fillMaxWidth(),
            text = "확인",
            onClick = { createUserBadge(badge.id) }
        )
    }
}