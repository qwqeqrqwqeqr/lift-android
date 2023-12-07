package com.gradation.lift.feature.updateRoutine.routineSet.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.gradation.lift.designsystem.component.LiftCancelButton
import com.gradation.lift.designsystem.component.LiftDialog
import com.gradation.lift.designsystem.component.LiftErrorButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun DeleteDialog(
    modifier: Modifier = Modifier,
    onClickDialogDeleteButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    LiftDialog(onDismissRequest = onClickDialogDismissButton) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Icon(
                painterResource(id = LiftIcon.Warn), contentDescription = "",
                modifier = modifier.size(52.dp),
                tint = LiftTheme.colorScheme.no12
            )
            Spacer(
                modifier = modifier.padding(10.dp)
            )
            Text(
                text = buildAnnotatedString {
                    append("제작한 루틴리스트가")
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no12),
                    ) {
                        append(" 삭제")
                    }
                    append("돼요")
                },
                textAlign = TextAlign.Center,
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no3
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
                    onClick = onClickDialogDeleteButton,
                ) {
                    Text(
                        text = "삭제",
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
        DeleteDialog(
            onClickDialogDeleteButton = {},
            onClickDialogDismissButton = {}
        )
    }
}