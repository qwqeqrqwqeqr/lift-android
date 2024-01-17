package com.gradation.lift.feature.updateRoutine.routineSet.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
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
                    shape = RoundedCornerShape(size = LiftTheme.space.space20)
                )
                .padding(LiftTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painterResource(id = LiftIcon.Warn), contentDescription = "Warn",
                modifier = modifier.size(LiftTheme.space.space52),
                tint = LiftTheme.colorScheme.no12
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LiftMultiStyleText(
                    defaultColor = LiftTheme.colorScheme.no3,
                    defaultTextStyle = LiftTextStyle.No2,
                    textAlign = TextAlign.Center,
                    textWithStyleList = listOf(
                        TextWithStyle(text = "제작한 루틴리스트가"),
                        TextWithStyle(text = " 삭제", color = LiftTheme.colorScheme.no12),
                        TextWithStyle(text = "돼요"),
                    )
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
                        text = "취소",
                        onClick = onClickDialogDismissButton
                    )

                    LiftErrorButton(
                        modifier = modifier.weight(1f),
                        text = "삭제",
                        onClick = onClickDialogDeleteButton
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