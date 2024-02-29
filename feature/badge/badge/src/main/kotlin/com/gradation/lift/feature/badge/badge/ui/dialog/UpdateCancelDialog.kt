package com.gradation.lift.feature.badge.badge.ui.dialog

import androidx.compose.foundation.background
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
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState

@Composable
internal fun UpdateCancelDialog(
    modifier: Modifier = Modifier,
    navigateBadgeGraphToHomeGraph: () -> Unit,
    badgeScreenState: BadgeScreenState,

    ) {
    LiftDialog(onDismissRequest = {
        badgeScreenState.updateUpdateCancelDialog(false)
    }) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = LiftTheme.space.space20)
                )
                .padding(LiftTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LiftText(
                textStyle = LiftTextStyle.No2,
                text = "뱃지 케이스 변경 여부를 취소할까요?",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space8,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                LiftDefaultButton(
                    modifier = modifier.weight(1f),
                    onClick = {
                        badgeScreenState.updateUpdateCancelDialog(false)
                    },
                    text = "아니요"
                )
                LiftSolidButton(
                    modifier = modifier.weight(1f),
                    onClick = {
                        navigateBadgeGraphToHomeGraph()
                    },
                    text = "네"
                )
            }

        }

    }
}

