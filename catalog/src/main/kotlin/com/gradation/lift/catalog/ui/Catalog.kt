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
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.button.LiftGoogleLoginButton
import com.gradation.lift.designsystem.component.button.LiftKakaoLoginButton
import com.gradation.lift.designsystem.component.button.LiftNaverLoginButton
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSmallButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun Catalog(
    modifier: Modifier = Modifier
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
            LiftGoogleLoginButton(modifier ) {}
            LiftKakaoLoginButton(modifier) {}
        }

        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSmallButton(modifier,"버튼") {}
        }
    }
}