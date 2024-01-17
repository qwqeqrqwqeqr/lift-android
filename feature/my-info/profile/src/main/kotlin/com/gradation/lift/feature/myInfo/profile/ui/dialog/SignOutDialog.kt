package com.gradation.lift.feature.myInfo.profile.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
internal fun SignOutDialog(
    modifier: Modifier = Modifier,
    onClickDialogSignOutButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    LiftDialog(onDismissRequest = onClickDialogDismissButton) {
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
                text = "로그아웃 하시겠어요?",
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
                    onClick = onClickDialogDismissButton,
                    text = "취소"
                )
                LiftSolidButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogSignOutButton,
                    text = "로그아웃"
                )
            }

        }

    }
}

