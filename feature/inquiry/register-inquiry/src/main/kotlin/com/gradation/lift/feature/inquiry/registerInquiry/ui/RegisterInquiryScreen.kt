package com.gradation.lift.feature.inquiry.registerInquiry.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.inquiry.registerInquiry.data.RegisterInquiryScreenState
import com.gradation.lift.feature.inquiry.registerInquiry.data.rememberRegisterInquiryScreenState

@Composable
fun RegisterInquiryScreen(
    modifier: Modifier = Modifier,
    content: String,
    updateContent: (String) -> Unit,
    navigatePreGraph: () -> Unit,
    createInquiry: () -> Unit,
    registerInquiryScreenState: RegisterInquiryScreenState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "건의하기",
                onClick = navigatePreGraph
            )
        },
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(
                    top = LiftTheme.space.space32,
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20
                ),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32),
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
            ) {
                LiftMultiStyleText(
                    modifier,
                    LiftTheme.colorScheme.no9,
                    LiftTextStyle.No1,
                    listOf(
                        TextWithStyle(
                            text = "소중한 의견을 ",
                            color = LiftTheme.colorScheme.no4,
                        ),
                        TextWithStyle(
                            text = "전달해주세요.",
                        ),
                    ),
                    TextAlign.Start
                )
                LiftText(
                    textStyle = LiftTextStyle.No6,
                    text = "의견은 검토 후 빠른 시일 내에 반영하도록 하겠습니다.",
                    color = LiftTheme.colorScheme.no13,
                    textAlign = TextAlign.Start
                )
            }

            LiftDefaultInputTextField(
                modifier = modifier.height(LiftTheme.space.space92),
                value = content,
                singleLine = false,
                onValueChange = updateContent,
                placeHolderValue = "소중한 의견을 전달해주세요.",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = { registerInquiryScreenState.focusManager.clearFocus() }
                ),
            )

            LiftSolidButton(
                text = "의견 전달하기",
                enabled = content.trim().isNotEmpty(),
                onClick = {
                    registerInquiryScreenState.focusManager.clearFocus()
                    createInquiry()
                }
            )
        }
    }
}


@Composable
@Preview
fun RegisterInquiryScreenPreView(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterInquiryScreen(
            content = "",
            updateContent = {},
            navigatePreGraph = {},
            createInquiry = {
            },
            registerInquiryScreenState = rememberRegisterInquiryScreenState()
        )
    }
}