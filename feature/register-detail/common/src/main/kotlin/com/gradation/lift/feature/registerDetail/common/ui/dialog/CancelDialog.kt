package com.gradation.lift.feature.registerDetail.common.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun CancelDialog(
    modifier: Modifier = Modifier,
    onClickDialogCancelButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    LiftDialog(onDismissRequest = onClickDialogDismissButton) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = LiftTheme.space.space24)
                )
                .padding(LiftTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Icon(
                painterResource(id = LiftIcon.Warn), contentDescription = "",
                modifier = modifier.size(LiftTheme.space.space52),
                tint = LiftTheme.colorScheme.no12
            )
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LiftText(
                    modifier = modifier,
                    text = "추가정보 등록을 중단하시겠습니까?",
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No2,
                    color = LiftTheme.colorScheme.no3,
                )

                LiftText(
                    modifier = modifier,
                    text = "추가정보 등록을 중단하실 경우\n지금까지 작성한 정보들이 삭제됩니다.",
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No4,
                    color = LiftTheme.colorScheme.no9,
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
                        text="취소",
                        onClick = onClickDialogDismissButton,
                    )
                    LiftErrorButton(
                        modifier = modifier.weight(1f),
                        text="중단",
                        onClick = onClickDialogCancelButton,
                    )
                }

            }
        }
    }
}