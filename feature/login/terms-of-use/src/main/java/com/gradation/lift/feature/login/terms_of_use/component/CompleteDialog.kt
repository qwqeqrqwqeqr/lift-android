package com.gradation.lift.feature.login.sign_up.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftCancelButton
import com.gradation.lift.designsystem.component.LiftDialog
import com.gradation.lift.designsystem.component.LiftErrorButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun CancelDialog(
    modifier: Modifier = Modifier,
    onClickDialogSuspendButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    LiftDialog(onDismissRequest = {}) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 10.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = buildAnnotatedString {
                    append("회원가입을 ")
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no12),
                    ) {
                        append("중단")
                    }
                    append("하실건가요?")
                },
                textAlign = TextAlign.Center,
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no3
            )
            Spacer(
                modifier = modifier.padding(10.dp)
            )
            Text(
                "회원가입을 중단할 경우\n" +
                        "현재 작성 중인 정보가 삭제됩니다",
                textAlign = TextAlign.Center,
                style = LiftTheme.typography.no4,
                color = LiftTheme.colorScheme.no9
            )
            Spacer(
                modifier = modifier.padding(10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                LiftCancelButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogDismissButton,
                ) {
                    Text(
                        text = "취소",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }

                LiftErrorButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogSuspendButton,
                ) {
                    Text(
                        text = "중단",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun CompleteDialogPreview() {
    LiftMaterialTheme {
        CancelDialog(
            onClickDialogSuspendButton = {},
            onClickDialogDismissButton = {}
        )
    }
}