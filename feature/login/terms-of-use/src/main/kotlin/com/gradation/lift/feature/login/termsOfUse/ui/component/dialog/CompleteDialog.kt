package com.gradation.lift.feature.login.termsOfUse.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftImage
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun CompleteDialog(
    modifier: Modifier = Modifier,
    onComplete: () -> Unit,
) {
    LiftDialog(onDismissRequest = onComplete) {
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
            Image(
                modifier = modifier
                    .size(LiftTheme.space.space108),
                painter = painterResource(id = LiftImage.Congrats),
                contentDescription = "Congrats",
            )
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LiftText(
                    modifier = modifier,
                    text = "회원가입을 완료하셨어요.",
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No2,
                    color = LiftTheme.colorScheme.no3,
                )

                LiftText(
                    modifier = modifier,
                    text = "이어서 상세정보를 등록합니다.",
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No4,
                    color = LiftTheme.colorScheme.no9,
                )

                LiftSolidButton(
                    modifier = modifier.fillMaxWidth(),
                    text = "상세정보 등록하기",
                    onClick = onComplete,
                )
            }
        }
    }
}