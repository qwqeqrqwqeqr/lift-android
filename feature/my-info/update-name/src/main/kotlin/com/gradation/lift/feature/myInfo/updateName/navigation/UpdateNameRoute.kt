package com.gradation.lift.feature.myInfo.updateName.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.myInfo.updateName.data.UpdateNameViewModel
import com.gradation.lift.feature.myInfo.updateName.data.state.UpdateNameScreenState
import com.gradation.lift.feature.myInfo.updateName.data.state.UpdateNameState
import com.gradation.lift.feature.myInfo.updateName.data.state.rememberUpdateNameScreenState
import com.gradation.lift.feature.myInfo.updateName.ui.UpdateNameScreen

@Composable
fun UpdateNameRoute(
    modifier: Modifier = Modifier,
    navigateUpdateNameToProfileInMyInfoGraph: () -> Unit,
    viewModel: UpdateNameViewModel = hiltViewModel(),
    updateNameScreenState: UpdateNameScreenState = rememberUpdateNameScreenState(),
) {

    val nameText: String by viewModel.nameText.collectAsStateWithLifecycle()
    val nameValidator: Validator by viewModel.nameValidator.collectAsStateWithLifecycle()
    val updateNameState: UpdateNameState by viewModel.updateNameState.collectAsStateWithLifecycle()

    val updateNameText: (String) -> Unit = viewModel.updateNameText
    val clearNameText: () -> Unit = viewModel.clearNameText

    val updateUpdateNameState: (UpdateNameState) -> Unit = viewModel.updateUpdateNameState
    val updateName: () -> Unit = viewModel.updateName

    BackHandler(onBack = navigateUpdateNameToProfileInMyInfoGraph)

    when (val state: UpdateNameState = updateNameState) {
        is UpdateNameState.Fail -> {
            LaunchedEffect(true) {
                updateNameScreenState.snackbarHostState.showSnackbar(
                    message = state.message,
                    duration = SnackbarDuration.Short
                )
                updateUpdateNameState(UpdateNameState.None)
            }
        }
        UpdateNameState.None -> {}
        UpdateNameState.Success -> {
            LaunchedEffect(true) {
                navigateUpdateNameToProfileInMyInfoGraph()
            }
        }
    }


    UpdateNameScreen(
        modifier,
        nameText,
        nameValidator,
        updateNameText,
        clearNameText,
        updateName,
        navigateUpdateNameToProfileInMyInfoGraph,
        updateNameScreenState
    )
}

