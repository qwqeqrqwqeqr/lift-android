package com.gradation.lift.feature.register_detail.name

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTitleTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.name.component.HeaderView
import com.gradation.lift.feature.register_detail.name.component.NameTextFieldView
import com.gradation.lift.feature.register_detail.name.component.NavigationView
import com.gradation.lift.feature.register_detail.name.component.ProgressNumberView
import com.gradation.lift.feature.register_detail.name.data.RegisterDetailNameViewModel
import com.gradation.lift.feature.register_detail.name.data.RegisterDetailSharedViewModel
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.DevicePreview
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun RegisterDetailNameRoute(
    navController: NavController,
    navigateNameToGender: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailNameViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.REGISTER_DETAIL_GRAPH_NAME) }
    val sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val nameText: String by viewModel.nameText.collectAsStateWithLifecycle()
    val nameValidator: Validator by viewModel.nameValidator.collectAsStateWithLifecycle()
    val navigateCondition: Boolean by viewModel.navigateCondition.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()

    val updateNameText: (String) -> Unit = viewModel.updateNameText()
    val updateCreateUserDetailName: (String) -> Unit = sharedViewModel.updateCreateUserDetailName()
    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()

    val focusManager = LocalFocusManager.current


    RegisterDetailNameScreen(
        modifier,
        nameText,
        nameValidator,
        navigateCondition,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateNameText,
        updateCreateUserDetailName,
        updateCurrentRegisterProgressNumber,
        navigateNameToGender,
        focusManager
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterDetailNameScreen(
    modifier: Modifier = Modifier,
    nameText: String,
    nameValidator: Validator,
    navigateCondition: Boolean,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateNameText: (String) -> Unit,
    updateCreateUserDetailName: (String) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateNameToGender: () -> Unit,
    focusManager: FocusManager,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTitleTopBar(title = "추가정보 입력")
        },
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no5,
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                ProgressNumberView(
                    modifier,
                    currentRegisterProgressNumber,
                    totalRegisterProgressNumber
                )
                Spacer(modifier = modifier.padding(14.dp))

                HeaderView(modifier)
                Spacer(modifier = modifier.padding(15.dp))

                NameTextFieldView(
                    modifier,
                    nameText,
                    nameValidator,
                    updateNameText,
                    focusManager
                )
                Spacer(modifier = modifier.padding(28.dp))
                NavigationView(
                    modifier,
                    navigateCondition,
                    nameText,
                    currentRegisterProgressNumber,
                    updateCreateUserDetailName,
                    updateCurrentRegisterProgressNumber,
                    navigateNameToGender,
                    focusManager
                )
            }
        }
    }
}


@DevicePreview
@Composable
internal fun RegisterDetailNameScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailNameScreen(
            modifier = modifier,
            nameText = "",
            nameValidator = Validator(false, ""),
            navigateCondition = true,
            currentRegisterProgressNumber = 1,
            totalRegisterProgressNumber = 4,
            updateNameText = {},
            updateCreateUserDetailName = {},
            updateCurrentRegisterProgressNumber = {},
            navigateNameToGender = {},
            focusManager = LocalFocusManager.current
        )
    }
}