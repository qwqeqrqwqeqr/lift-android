package com.gradation.lift.feature.myInfo.cancelMembership.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.component.textField.LiftDropDownTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun CancelMembershipScreen(
    modifier: Modifier = Modifier,
    selectedCancelReasonValue: String,
    etcText: String,
    selectedFlag: Boolean,
    etcFlag: Boolean,
    updateEtcText: (String) -> Unit,
    updateBottomSheetView: (Boolean) -> Unit,
    navigateCancelMembershipToProfileInMyInfoGraph: () -> Unit,
    navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current


    Scaffold(
        topBar = {
            LiftTopBar(
                title = "탈퇴하기",
                onClick = navigateCancelMembershipToProfileInMyInfoGraph
            )
        },
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(
                    top = LiftTheme.space.space36,
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
        ) {
            LiftMultiStyleText(
                modifier,
                LiftTheme.colorScheme.no11,
                LiftTextStyle.No1,
                listOf(
                    TextWithStyle(text = "리프트를 "),
                    TextWithStyle(
                        text = "탈퇴하려는 이유",
                        color = LiftTheme.colorScheme.no4,
                    ),
                    TextWithStyle(text = "가 궁금해요")
                ),
                TextAlign.Center
            )
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                LiftDropDownTextField(
                    modifier = modifier.noRippleClickable { updateBottomSheetView(true) },
                    isSelected = selectedFlag,
                    emptyText = "이유를 선택해주세요",
                    selectedText = selectedCancelReasonValue
                )
                AnimatedVisibility(visible = etcFlag) {
                    LiftDefaultInputTextField(
                        value = etcText,
                        onValueChange = updateEtcText,
                        placeHolderValue = "계정을 삭제하려는 이유를 자세히 알려주세요.",
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                    )
                }
            }
            AnimatedVisibility(visible = selectedFlag) {
                LiftSolidButton(
                    text = "제출",
                    enabled = !(etcFlag && etcText.isEmpty())
                ) {
                    navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph(
                        if (etcFlag) etcText else selectedCancelReasonValue
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CancelMembershipScreenPreview() {
    LiftMaterialTheme {
        CancelMembershipScreen(
            selectedCancelReasonValue = "기타",
            etcText = "",
            selectedFlag = true,
            etcFlag = true,
            updateEtcText = {},
            updateBottomSheetView = {},
            navigateCancelMembershipToProfileInMyInfoGraph = { },
            navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph = { }
        )
    }
}
