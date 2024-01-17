package com.gradation.lift.feature.myInfo.updateName.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.updateName.data.state.UpdateNameScreenState
import com.gradation.lift.feature.myInfo.updateName.ui.component.NameTextFieldView


@Composable
internal fun UpdateNameScreen(
    modifier: Modifier = Modifier,
    nameText: String,
    nameValidator: Validator,
    updateNameText: (String) -> Unit,
    clearNameText: () -> Unit,
    updateName: () -> Unit,
    navigateUpdateNameToProfileInMyInfoGraph: () -> Unit,
    updateNameScreenState: UpdateNameScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "닉네임 변경",
                onClick = navigateUpdateNameToProfileInMyInfoGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = updateNameScreenState.snackbarHostState
            )
        }
    ) { it ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
            ) {
                NameTextFieldView(
                    modifier,
                    nameText,
                    nameValidator,
                    updateNameText,
                    clearNameText,
                    updateNameScreenState
                )
                LiftSolidButton(
                    text = "저장하기",
                    enabled = nameText.isNotEmpty() && nameValidator.status,
                    onClick = updateName
                )
            }
        }
    }
}

