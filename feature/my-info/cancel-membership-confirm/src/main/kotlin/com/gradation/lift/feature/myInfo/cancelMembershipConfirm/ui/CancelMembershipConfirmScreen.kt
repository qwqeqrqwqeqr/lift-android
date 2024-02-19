package com.gradation.lift.feature.myInfo.cancelMembershipConfirm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun CancelMembershipConfirmScreen(
    modifier: Modifier = Modifier,
    name: String,
    deleteUser: () -> Unit,
    snackbarHostState: SnackbarHostState,
    navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "탈퇴하기",
                onClick = navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
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
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
            ) {
                LiftMultiStyleText(
                    modifier,
                    LiftTheme.colorScheme.no11,
                    LiftTextStyle.No1,
                    listOf(
                        TextWithStyle(text = "정말로 리프트를 "),
                        TextWithStyle(
                            text = "탈퇴",
                            color = LiftTheme.colorScheme.no12,
                        ),
                        TextWithStyle(text = "하실건가요?")
                    ),
                    TextAlign.Center
                )
                LiftMultiStyleText(
                    modifier,
                    LiftTheme.colorScheme.no3,
                    LiftTextStyle.No6,
                    listOf(
                        TextWithStyle(text = "${name}님, 계정을 삭제하면 "),
                        TextWithStyle(
                            text = "모든 활동 정보가 삭제되며 삭제 후 7일간 다시 가입할 수 없어요.",
                            color = LiftTheme.colorScheme.no12,
                        ),
                    ),
                    TextAlign.Start
                )
            }
            LiftErrorButton(text = "계정 삭제", onClick = deleteUser)
        }
    }
}


