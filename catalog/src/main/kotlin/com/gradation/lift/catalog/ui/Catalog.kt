package com.gradation.lift.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.button.LiftGoogleLoginButton
import com.gradation.lift.designsystem.component.button.LiftKakaoLoginButton
import com.gradation.lift.designsystem.component.button.LiftNaverLoginButton
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSmallButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.progress.LiftProgressCircleLabel
import com.gradation.lift.designsystem.component.progress.ProgressCircleState
import com.gradation.lift.designsystem.component.selctor.LiftPrimarySelector
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun Catalog(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(LiftTheme.colorScheme.no5)
            .fillMaxSize()
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftText(
                textStyle = LiftTextStyle.No1,
                text = "No1",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No2,
                text = "No2",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "No3",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No4,
                text = "No4",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = "No5",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "No6",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No7,
                text = "No7",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No8,
                text = "No8",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftMultiStyleText(
                modifier,
                LiftTheme.colorScheme.no9,
                LiftTextStyle.No3,
                listOf(
                    TextWithStyle(text = "텍스트"),
                    TextWithStyle(
                        text = "스타일",
                        color = LiftTheme.colorScheme.no4,
                        style = LiftTextStyle.No2
                    )
                ),
                TextAlign.Start
            )
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSolidButton(modifier, "버튼", true) {}
            LiftSolidButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftSolidButton(modifier.weight(1f), "버튼", true) {}
                LiftSolidButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftDefaultButton(modifier, "버튼", true) {}
            LiftDefaultButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftDefaultButton(modifier.weight(1f), "버튼", true) {}
                LiftDefaultButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftErrorButton(modifier, "버튼", true) {}
            LiftErrorButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftErrorButton(modifier.weight(1f), "버튼", true) {}
                LiftErrorButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftPrimaryButton(modifier, "버튼", true) {}
            LiftPrimaryButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftPrimaryButton(modifier.weight(1f), "버튼", true) {}
                LiftPrimaryButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftNaverLoginButton(modifier) {}
            LiftGoogleLoginButton(modifier) {}
            LiftKakaoLoginButton(modifier) {}
        }

        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSmallButton(modifier, "버튼") {}
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftPrimarySelector(modifier.weight(1f), "선택", true) {}
                LiftPrimarySelector(modifier.weight(1f), "비선택", false) {}
            }
        }

        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                LiftProgressCircleLabel(modifier, ProgressCircleState.Done, 1)
                LiftProgressCircleLabel(modifier, ProgressCircleState.Done, 2)
                LiftProgressCircleLabel(modifier, ProgressCircleState.Current, 3)
                LiftProgressCircleLabel(modifier, ProgressCircleState.None, 4)
                LiftProgressCircleLabel(modifier, ProgressCircleState.None, 5)
            }
        }
    }
}